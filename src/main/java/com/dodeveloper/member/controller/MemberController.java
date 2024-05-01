package com.dodeveloper.member.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.dodeveloper.commons.interceptor.SessionNames;
import com.dodeveloper.member.dto.LoginDTO;
import com.dodeveloper.member.dto.RegisterDTO;
import com.dodeveloper.member.dto.SessionDTO;
import com.dodeveloper.member.service.MemberService;
import com.dodeveloper.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService mService;
	
	@GetMapping("/login")
	public void loginGet() {
		logger.info("Login View.");
	}
	
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPost(LoginDTO loginDTO, Model model, HttpSession session) throws Exception {
		logger.info("login...LoginDTO={}", loginDTO);

		MemberVO loginMember = mService.login(loginDTO);
		if (loginMember != null) {

			String sessionId = session.getId();
			Timestamp sessionLimit = new Timestamp(System.currentTimeMillis() + (1000 * SessionNames.EXPIRE));

			mService.keepLogin(new SessionDTO(sessionId, sessionLimit, loginMember.getUserId()));
			model.addAttribute(SessionNames.LOGIN_MEMBER, loginMember);
		} else {
			model.addAttribute("loginResult", "Login Fail!!");
		}
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
		return "redirect:/"; // index로 이동
	}

	@GetMapping("/register")
	public void registerGet() {
		logger.info("register View.");
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
	
	
	
}
