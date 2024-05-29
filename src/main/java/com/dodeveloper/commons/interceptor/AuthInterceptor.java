package com.dodeveloper.commons.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.dodeveloper.member.service.MemberService;
import com.dodeveloper.member.vo.MemberVO;

/**
 * @packageName : com.dodeveloper.commons.interceptor
 * @fileName : AuthInterceptor.java
 * @author : kjshsy0226
 * @date : 2024.05.14
 * @description : 특정 경로(게시글 작성, 게시글 수정, 게시글 삭제, 댓글 작성, 수정, 삭제)에 접근하는 경우에 로그인한
 *              유저인지 아닌지를 체크하는 기능을 담당하는 인터셉터
 */
public class AuthInterceptor extends HandlerInterceptorAdapter implements SessionNames {

    private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private MemberService mService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {

	logger.info("AuthInterceptor.pre>>");

	HttpSession session = request.getSession();

	if (session.getAttribute(LOGIN_MEMBER) == null) {
	    Cookie loginCookie = WebUtils.getCookie(request, LOGIN_COOKIE);
	    if (loginCookie != null) {
		MemberVO loginedUser = mService.checkLoginBefore(loginCookie.getValue());
		if (loginedUser != null) {
		    session.setAttribute(LOGIN_MEMBER, loginedUser);
		    return true;
		}
	    }
	    logger.info("AuthInterceptor.pre>> no loginCookie");

	    saveAttemptedLocation(request, session);

	    response.sendRedirect("/member/login");
	}
	return true;
    }

    private void saveAttemptedLocation(HttpServletRequest request, HttpSession session) {
	String uri = request.getRequestURI();
	String query = request.getQueryString();
	if (StringUtils.hasText(query)) {
	    uri += "?" + query;
	}
	logger.info("saveAttemptedLocation uri : " + uri);
	session.setAttribute(ATTEMPTED, uri);
    }

}
