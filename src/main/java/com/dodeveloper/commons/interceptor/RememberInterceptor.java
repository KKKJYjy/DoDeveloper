package com.dodeveloper.commons.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.dodeveloper.member.service.MemberService;
import com.dodeveloper.member.vo.MemberVO;

public class RememberInterceptor extends HandlerInterceptorAdapter implements SessionNames {
    private static final Logger logger = LoggerFactory.getLogger(RememberInterceptor.class);

    @Autowired
    private MemberService mService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {

	logger.info("RememberInterceptor.pre>> 모든 주소");

	HttpSession session = request.getSession();

	Cookie loginCookie = WebUtils.getCookie(request, LOGIN_COOKIE);
	logger.info("loginCookie : " + loginCookie);
	if (loginCookie != null) {
	    logger.info("loginCookie.getValue() : " + loginCookie.getValue());
	    MemberVO loginedUser = mService.checkLoginBefore(loginCookie.getValue());
	    logger.info("loginedUser : " + loginedUser);
	    if (loginedUser != null)
		session.setAttribute(LOGIN_MEMBER, loginedUser);
	}
	return true;
    }
}
