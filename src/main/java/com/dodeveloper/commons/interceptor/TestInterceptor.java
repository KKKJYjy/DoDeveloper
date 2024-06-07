package com.dodeveloper.commons.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dodeveloper.algorithm.dao.AlgDAO;
import com.dodeveloper.algorithm.service.AlgService;
import com.dodeveloper.algorithm.vodto.AlgDetailDTO;
import com.dodeveloper.member.service.MemberService;
import com.dodeveloper.member.vo.MemberVO;

public class TestInterceptor extends HandlerInterceptorAdapter implements SessionNames {
    private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	@Autowired
	private MemberService mService;
	
	@Autowired
	AlgDAO aDao;
	
	@Autowired
	AlgService aService;
	
	
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
		// 컨트롤러단 getAlgDetail 에서 세션으로 보낸 boardNo
		int boardNo = (int)session.getAttribute("boardNum");
		
		
		if(uri.contains("write") || uri.contains("modify")) {
		    
		    List<AlgDetailDTO> list = aService.getListDetail(boardNo);
		    System.out.println(boardNo);
		    System.out.println(list.toString());
		//    if(mem.getUserId().equals(  aDao.selectAlgDetail(boardNo) )) { }
		    return true;
		    
		}
	    }
	    
		    return false;
	    
	}
}
