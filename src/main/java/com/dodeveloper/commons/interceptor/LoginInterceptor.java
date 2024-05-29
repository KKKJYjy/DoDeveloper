package com.dodeveloper.commons.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dodeveloper.member.vo.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter implements SessionNames {

    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {

	logger.info("LoginInterceptor.pre>>");

	HttpSession session = request.getSession();

	if (session.getAttribute(LOGIN_MEMBER) != null) {
	    session.removeAttribute(LOGIN_MEMBER);
	}
	return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	    ModelAndView modelAndView) throws Exception {

	HttpSession session = request.getSession();

	MemberVO loginMember = (MemberVO) modelAndView.getModelMap().get(LOGIN_MEMBER);
	logger.info("LoginInterceptor.post>>" + loginMember);

	if (loginMember != null) {
	    session.setAttribute(LOGIN_MEMBER, loginMember);

	    if (StringUtils.hasText(request.getParameter("remember"))) {
		Cookie loginCookie = new Cookie(LOGIN_COOKIE, session.getId());
		loginCookie.setPath("/");
		loginCookie.setMaxAge(EXPIRE);

		response.addCookie(loginCookie);
	    }

	    String attempted = (String) session.getAttribute(ATTEMPTED);
	    System.out.println("attempted : " + attempted);
	    if (StringUtils.hasText(attempted)) {
		response.sendRedirect(attempted);
		session.removeAttribute(ATTEMPTED);

	    } else {
		response.sendRedirect("/");
	    }
	}
    }

}
