package com.dodeveloper.member.controller;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.util.WebUtils;

import com.dodeveloper.commons.interceptor.SessionNames;
import com.dodeveloper.etc.MailManager;
import com.dodeveloper.member.dto.DropMemberDTO;
import com.dodeveloper.member.dto.LoginDTO;
import com.dodeveloper.member.dto.RegisterDTO;
import com.dodeveloper.member.dto.SessionDTO;
import com.dodeveloper.member.service.MemberService;
import com.dodeveloper.member.vo.MemberVO;
import com.dodeveloper.message.service.MessageService;
import com.dodeveloper.mypage.dto.ChangePwdDTO;

@Controller
@RequestMapping("/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private final String PASSWORD_RESET_URL = "password-reset";
	private Map<String, PwdResetUser> pwdResetUserHolder = new ConcurrentHashMap<String, PwdResetUser>(100);
	private Map<String, EmailToValidate> emailsToValidate = new ConcurrentHashMap<String, EmailToValidate>(100);
	
	@Autowired
	private MailManager mailManager;
	
	@Autowired
	private MemberService mService;

	@Autowired
	private MessageService messageService;

	
	private class PwdResetUser {
		private final LocalDateTime requestTime;
		private final MemberVO member;
		
		public PwdResetUser(MemberVO member){
			this.requestTime = LocalDateTime.now();
			this.member = member;
		}
		
		public MemberVO getMember() {
			return member;
		}
		public LocalDateTime getRequestTime() {
			return requestTime;
		}
	}
	
	private class EmailToValidate {
		private final LocalDateTime requestTime;
		private final String email;
		
		public EmailToValidate(String email){
			this.requestTime = LocalDateTime.now();
			this.email = email;
		}
		
		public String getEmail() {
			return email;
		}
		public LocalDateTime getRequestTime() {
			return requestTime;
		}
	}
	
	private static final long PWD_RESET_LINK_EXPIRE_MINUTE = 30;
	private static final long EMAIL_WAIT_TO_VALIDATION_MINUTE = 5;
	
	public MemberController() {
		timer.schedule(deleteOldRequest, 1000, 1000 * 60);
	}
	
	private Timer timer = new Timer(true);
	
	TimerTask deleteOldRequest = new TimerTask() {
		
		@Override
		public void run() {
			//logger.info("timer 가 호출되었다.");
			List<Map.Entry<String, PwdResetUser>> pwdResetHolderEntryList = new ArrayList<>(pwdResetUserHolder.entrySet());
			for(Map.Entry<String, PwdResetUser> entry : pwdResetHolderEntryList) {
				logger.info(entry.getKey() + "를 검사한다.");
				
				long passedMinuteAfterRequest = ChronoUnit.MINUTES.between(entry.getValue().getRequestTime(), LocalDateTime.now());
				
				if(passedMinuteAfterRequest > PWD_RESET_LINK_EXPIRE_MINUTE){
					logger.info(entry.getKey() + "를 제거한다.");
					pwdResetUserHolder.remove(entry.getKey());
				}
			}
			
			List<Map.Entry<String, EmailToValidate>> emailsToValidateEntryList = new ArrayList<>(emailsToValidate.entrySet());
			for(Map.Entry<String, EmailToValidate> entry : emailsToValidateEntryList) {
				logger.info(entry.getKey() + "를 검사한다");
				
				long passedMinuteAfterRequest = ChronoUnit.MINUTES.between(entry.getValue().getRequestTime(), LocalDateTime.now());
				
				if(passedMinuteAfterRequest > EMAIL_WAIT_TO_VALIDATION_MINUTE){
					logger.info(entry.getKey() + "를 제거한다.");
					emailsToValidate.remove(entry.getKey());
				}
			}
		}
	};
	
	
	@GetMapping("/login")
	public void loginGet(HttpServletRequest request, HttpSession session) {
		logger.info("Login View.");

		if(request.getParameter("redirectUrl") == null) {
			return;
		}
		
		String urlToVisitAfterLogin = "";
		
		if(request.getParameter("redirectUrl").equals("viewBoard") && request.getParameter("lecNo") != null) {
			urlToVisitAfterLogin = "/lecture/viewBoard?lecNo=" + request.getParameter("lecNo");
			session.setAttribute(SessionNames.ATTEMPTED, urlToVisitAfterLogin);
		}else if(request.getParameter("redirectUrl").equals("viewStudyBoard") && request.getParameter("stuNo") != null) {
			urlToVisitAfterLogin = "/study/viewStudyBoard?stuNo=" + request.getParameter("stuNo");
			session.setAttribute(SessionNames.ATTEMPTED, urlToVisitAfterLogin);
		}
		
	}

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public String loginPost(LoginDTO loginDTO, Model model, HttpSession session) throws Exception {
		logger.info("login...LoginDTO={}", loginDTO);

		MemberVO loginMember = mService.login(loginDTO);
		System.out.println("loginMember : " + loginMember);

		if (loginMember == null) {

			model.addAttribute("loginResult", "fail");

			System.out.println("로그인 실패");

			return "redirect:/member/login";

		}

		if (loginDTO.isRemember()) {
			String sessionId = session.getId();
			System.out.println("sessionId : " + sessionId);
			Timestamp sessionLimit = new Timestamp(System.currentTimeMillis() + (1000 * SessionNames.EXPIRE));

			mService.keepLogin(new SessionDTO(sessionId, sessionLimit, loginMember.getUserId()));
		}

		model.addAttribute(SessionNames.LOGIN_MEMBER, loginMember);
		session.setAttribute(SessionNames.UNREAD_MESSAGE_CNT,
				messageService.countUnreadReceivedMessages(loginDTO.getUserId()));
		System.out.println("로그인 성공2");

		return "/member/loginPost";

	}

	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Cookie loginCookie = WebUtils.getCookie(request, SessionNames.LOGIN_COOKIE);
		if (loginCookie != null) {
			loginCookie.setPath("/");
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);

			String sessionId = session.getId();
			Timestamp sessionLimit = new Timestamp(System.currentTimeMillis());
			MemberVO loginMember = (MemberVO) session.getAttribute(SessionNames.LOGIN_MEMBER);
			logger.info(loginMember.toString() + "가 로그아웃 합니다.");

			SessionDTO sessionDTO = new SessionDTO(sessionId, sessionLimit, loginMember.getUserId());
			mService.keepLogin(sessionDTO);
		}

		if (session.getAttribute(SessionNames.LOGIN_MEMBER) != null) {
			session.removeAttribute(SessionNames.LOGIN_MEMBER);
			session.invalidate();
		}
		return "redirect:/member/login"; // index로 이동
	}

	@GetMapping("/register")
	public void registerGet() {
		logger.info("register View.");
	}

	@RequestMapping(value = "/emailConfirmRequest", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public ResponseEntity<Boolean> sendComfirmMail(@RequestParam("emailAddress") String emailAddress,
			HttpSession session) throws Exception {
		
		String code = UUID.randomUUID().toString().replace("-", "");

		try {
			mailManager.sendValidationCode(emailAddress, code);
			session.setAttribute(SessionNames.EMAIL_VALIDATION_CODE, code);
			emailsToValidate.put(code, new EmailToValidate(emailAddress));

			return ResponseEntity.ok(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return ResponseEntity.ok(false);
		}

	}

	@RequestMapping(value = "/emailCode", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> checkEMailCode(@RequestParam("code") String code, HttpSession session)
			throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		
		if(code == null) {
			result.put("isSuccess", "0");
			return ResponseEntity.ok(result);
		}
		
		if (code.equals(session.getAttribute(SessionNames.EMAIL_VALIDATION_CODE)) == false) {
			result.put("isSuccess", "0");
			return ResponseEntity.ok(result);
		}

		if (emailsToValidate.containsKey(code) == false) {
			result.put("isSuccess", "0");
			return ResponseEntity.ok(result);
		}


		session.setAttribute(SessionNames.VERIFIED_EMAIL, emailsToValidate.get(code).getEmail());
		emailsToValidate.remove(code);

		result.put("isSuccess", "1");
		return ResponseEntity.ok(result);	
		

	}

	@RequestMapping(value = "/registerPost", method = RequestMethod.POST)
	public String registerPost(RegisterDTO registerDTO, HttpSession session) throws Exception {
		logger.info(registerDTO.toString() + "회원가입");
		
		if(registerDTO.getEmail() == null) {
			registerDTO.setEmail("");
		}else if(registerDTO.getEmail().equals(session.getAttribute(SessionNames.VERIFIED_EMAIL)) == false) {
			return "redirect:/member/register?status=registerFail";
		}
		
		if (mService.registerMember(registerDTO) != 1) {
			return "redirect:/member/register?status=registerFail";
		}

		return "redirect:/member/login?status=registerSuccess";
	}

	@ResponseBody
	@RequestMapping(value = "/duplicateUserId", method = RequestMethod.POST)
	public Map<String, Boolean> duplicateUserId(String userId) throws Exception {
		logger.info("아이디 중복체크 : " + userId);

		boolean result = false;

		if (mService.duplicateUserId(userId) == 1) {
			result = true;
		}
		logger.info("아이디 중복체크 결과 : " + result);

		Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
		resultMap.put("isDuplicate", result);

		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "/forgottenUserId", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> sendUserId(String email) throws Exception {
		logger.info(email + "이 아이디를 요청함");
		
		if(email.equals("")) {
			return ResponseEntity.ok("이메일을 입력해주십시오.");
		}
		
		List<MemberVO> members = mService.getMemberByEmail(email);
		
		if (members == null || members.isEmpty()) {
			System.out.println("해당 이메일로 가입된 회원이 없습니다.");
			return ResponseEntity.ok("해당 이메일로 가입된 회원이 없습니다.");
		}

		List<String> userIds = new LinkedList<String>();
		
		for(MemberVO member : members) {
			userIds.add(member.getUserId());
		}
		
		mailManager.sendUserId(email, userIds);
		logger.info(email + " 에게 전송완료");
		return ResponseEntity.ok("해당 이메일로 유저 아이디를 전송했습니다.");
	}

	@ResponseBody
	@RequestMapping(value = "/pwdResetLink", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> pwdResetLink(String userId, String email, HttpServletRequest request, HttpSession session) throws Exception {
		logger.info("uid: " + userId + "// email : " + email + " 에서 비밀번호 재생성을 요청함");
		
		if(email.equals("")) {
			return ResponseEntity.ok("이메일을 입력해주십시오.");
		}
		
		List<MemberVO> members = mService.getMemberByEmail(email);
		
		if(members == null || members.isEmpty()) {
			return ResponseEntity.ok("회원 아이디와 이메일이 매치되지 않습니다.");
		}
		
		MemberVO requestingMember = null;
		for(MemberVO member : members) {
			if(member.getUserId().equals(userId)) {
				requestingMember = member;
				break;
			}
		}
		
		if (requestingMember == null) {
			logger.info(email + "이 보낸 요청에서 회원 아이디와 이메일이 매치되지 않습니다.");
			return ResponseEntity.ok("회원 아이디와 이메일이 매치되지 않습니다.");
		}

		String uuid = UUID.randomUUID().toString().replace("-", "") + UUID.randomUUID().toString().replace("-", "");
		
		String fullUrl = request.getRequestURL().toString();
		String urlWithoutParam = fullUrl.substring(0, fullUrl.indexOf(request.getRequestURI()));
		String projectPath = request.getServletPath();
		String classMappingUrl = projectPath.split("/")[1];
		
		String urlResult = urlWithoutParam + "/" + classMappingUrl + "/" + PASSWORD_RESET_URL + "/" + uuid;
		
		pwdResetUserHolder.put(uuid, new PwdResetUser(requestingMember));
		
		mailManager.sendPwdResetLink(email, urlResult);
		
		logger.info(email + " 에게 전송완료");
		return ResponseEntity.ok("해당 이메일로 비밀번호 재설정 링크를 전송했습니다.");
	}

	
	@RequestMapping(value = "/" + PASSWORD_RESET_URL + "/{UUID}", method = RequestMethod.GET)
	public String pwdResetPage(@PathVariable("UUID") String uuid, Model model) throws Exception {
		logger.info("패스워드 리셋 링크: " + uuid + " 접속 요청됨");
		
		if(!pwdResetUserHolder.containsKey(uuid)) {
			logger.info("패스워드 리셋 링크: " + uuid + " 접속 거절 -> 유효하지 않은 uuid");
			return "redirect:/";
		}
		
		long passedMinuteAfterRequest = ChronoUnit.MINUTES.between(pwdResetUserHolder.get(uuid).getRequestTime(), LocalDateTime.now());
		
		if(passedMinuteAfterRequest > PWD_RESET_LINK_EXPIRE_MINUTE) {
			logger.info("패스워드 리셋 링크: " + uuid + " 접속 거절 -> timeout");
			return "redirect:/";
		}
		
		model.addAttribute(pwdResetUserHolder.get(uuid));
		
		return "member/pwdReset";
	}


	@ResponseBody
	@RequestMapping(value = "/" + PASSWORD_RESET_URL, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public ResponseEntity<Map<String, String>> pwdReset(@RequestParam("newPwd")String newPwd, @RequestParam("UUID")String uuid) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		
		MemberVO pwdResettingMember = pwdResetUserHolder.get(uuid).getMember();
		
		logger.info("패스워드 리셋 요청 들어옴");
		logger.info("새로운 패스워드 : " + newPwd + "//" + "uuid : " + uuid);
		
		if(pwdResettingMember == null) {
			logger.info("uuid 를 통한 멤버 탐색 실패함");
			result.put("isSuccess", "0");
			result.put("reason", "timeout");
			return ResponseEntity.ok(result);
		}

		ChangePwdDTO changePwdDTO = new ChangePwdDTO(pwdResettingMember.getUserId(), null, newPwd);
		if(mService.changeUserPwd(changePwdDTO) != 1) {
			logger.info("비밀번호 변경 로직 실패함");
			result.put("isSuccess", "0");
			result.put("reason", "serverFail");
			return ResponseEntity.ok(result);
		};
		
		logger.info("성공적으로 비밀번호 수정함");
		
		result.put("isSuccess", "1");
		return ResponseEntity.ok(result);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/dropMember", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<String, Object>> dropMember(@RequestBody DropMemberDTO dropMemberDTO) throws Exception {
		System.out.println("dropMemberDTO : " + dropMemberDTO.toString());

		Map<String, Object> returnMap = new HashMap<String, Object>();

		if (mService.dropMember(dropMemberDTO)) {
			returnMap.put("state", "T");
			returnMap.put("message", "Success");
		} else {
			returnMap.put("state", "F");
			returnMap.put("message", "Fail");
		}

		HttpHeaders headers = new HttpHeaders();
		Charset utf8 = Charset.forName("utf-8");
		MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON_UTF8, utf8);
		headers.setContentType(mediaType);

		return ResponseEntity.ok().headers(headers).body(returnMap);
	}

}
