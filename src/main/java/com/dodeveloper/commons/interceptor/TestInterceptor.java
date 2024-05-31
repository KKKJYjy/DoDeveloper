package com.dodeveloper.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dodeveloper.member.service.MemberService;
import com.dodeveloper.member.vo.MemberVO;

public class TestInterceptor extends HandlerInterceptorAdapter implements SessionNames {
    private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	@Autowired
	private MemberService mService;
	
	
	@Autowired
	HttpSession ses;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception{
	    
	   
	    HttpSession session = request.getSession();
	    		
	    
	    if (session.getAttribute(LOGIN_MEMBER) != null) {
		MemberVO mem = (MemberVO)session.getAttribute(LOGIN_MEMBER);
		System.out.println("아이디 : "+mem.getUserId());
		String uri = request.getRequestURI();
		System.out.println(uri);
		System.out.println(session.getAttribute("boardNum"));
		
		if(uri.contains("write") || uri.contains("modify")) {
//		    int boardNo = Integer.parseInt( request.getParameter("boardNo"));
//		    ses.getAttribute("boardNo");
		    
		}
	    }
	    
		    return false;
	    
	}
}
