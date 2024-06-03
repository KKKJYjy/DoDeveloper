package com.dodeveloper.member.controller;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

@Controller
@RequestMapping("/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private final String PASSWORD_RESET_URL = "password-reset";
	private Map<String, MemberVO> pwdResetUserHolder = new HashMap<String, MemberVO>(100);
	
	@Autowired
	private MailManager mailManager;

	@Autowired
	private MemberService mService;

	@Autowired
	private MessageService messageService;

	@GetMapping("/login")
	public void loginGet(HttpServletRequest request) {
		logger.info("Login View.");
		
		String fullUrl = request.getRequestURL().toString();
		System.out.println(fullUrl);
		
		String url = fullUrl.substring(0, fullUrl.indexOf(request.getRequestURI()));
		System.out.println(url);
		
		String sp = request.getServletPath();
		System.out.println(sp.split("/")[1]);
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
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(false);
		}

	}

	@RequestMapping(value = "/emailCode", method = RequestMethod.POST)
	public ResponseEntity<Boolean> checkEMailCode(@RequestParam("code") String code, HttpSession session)
			throws Exception {
		if (code.equals(session.getAttribute(SessionNames.EMAIL_VALIDATION_CODE))) {
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.ok(false);
		}

	}

	@RequestMapping(value = "/registerPost", method = RequestMethod.POST)
	public String registerPost(RegisterDTO registerDTO) throws Exception {
		logger.info(registerDTO.toString() + "회원가입");

		if (mService.registerMember(registerDTO) == 1) {
			return "redirect:/member/login?status=registerSuccess";
		}
		return "redirect:/member/register?status=registerFail";
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
		System.out.println(email);
		MemberVO member = mService.getMemberByEmail(email);
		if (member == null) {
			System.out.println("해당 이메일로 가입된 회원이 없습니다.");
			return ResponseEntity.ok("해당 이메일로 가입된 회원이 없습니다.");
		}

		mailManager.sendUserId(email, member.getUserId());
		System.out.println("전송완료");
		return ResponseEntity.ok("해당 이메일로 유저 아이디를 전송했습니다.");
	}

	@ResponseBody
	@RequestMapping(value = "/pwdResetLink", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> pwdResetLink(String userId, String email, HttpServletRequest request, HttpSession session) throws Exception {
		System.out.println("uid: " + userId + "// email : " + email);
		MemberVO member = mService.getMemberByEmail(email);

		if (!member.getUserId().equals(userId)) {
			System.out.println("회원 아이디와 이메일이 매치되지 않습니다.");
			return ResponseEntity.ok("회원 아이디와 이메일이 매치되지 않습니다.");
		}

		String uuid = UUID.randomUUID().toString().replace("-", "") + UUID.randomUUID().toString().replace("-", "");
		
		
		String fullUrl = request.getRequestURL().toString();
		String url = fullUrl.substring(0, fullUrl.indexOf(request.getRequestURI()));
		String projectPath = request.getServletPath();
		
		url += "/" + projectPath.split("/")[1] + "/" + PASSWORD_RESET_URL + "/" + uuid;
		
		pwdResetUserHolder.put(uuid, member);
		
		mailManager.sendPwdResetLink(email, url);
		System.out.println("전송완료");
		return ResponseEntity.ok("해당 이메일로 비밀번호 재설정 링크를 전송했습니다.");
	}

	
	@RequestMapping(value = "/" + PASSWORD_RESET_URL + "/{UUID}", method = RequestMethod.GET)
	public String pwdResetPage(@PathVariable("UUID") String uuid) throws Exception {
		System.out.println("HI MAN");
		if(!pwdResetUserHolder.containsKey(uuid)) {
			return "home";
		}

		return "member/pwdReset";
	}

	@ResponseBody
	@RequestMapping(value = "/" + PASSWORD_RESET_URL + "/{UUID}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public ResponseEntity<Map<String, String>> pwdReset(String pwd, @PathVariable("UUID") String uuid) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		
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
