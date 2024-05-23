package com.dodeveloper.commons.interceptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dodeveloper.admin.dto.ConnectLogDTO;
import com.dodeveloper.admin.service.AdminService;

public class ConnectUserInterceptor extends HandlerInterceptorAdapter {
	
	
	@Autowired
	private AdminService aService;
	
	
	private ConnectLogDTO connectLog = new ConnectLogDTO(null, null, null);

	// 각 페이지접속 세션아이디와 url, 접속 시간 받아오는 interceptor
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		

		System.out.println("prehandle이 호출됨!!!!!!!!");

		HttpSession session = request.getSession();

		// 세션아이디 가져옴
		String sessionId = session.getId();

		// 접속한 uri를 가져옴
		String uri = request.getRequestURI().toString();

		// 접속한 시간을 가져옴
		LocalDateTime accessTime = LocalDateTime.now();
		
		
		// 날짜를 보기 편하게 변환
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		String accessDate = accessTime.format(formatter);

		System.out.println("Session ID: " + sessionId + "접속중인 uri : " + uri + "접속시간 : " + accessDate);
		
		connectLog.setSessionId(sessionId);
		connectLog.setUri(uri);
		connectLog.setAccessDate(accessDate);
		
		aService.getConnectLog(connectLog);

		// 반환값 타입이 true이면 원래의 컨트롤러 단으로 제어를 돌려줌
		// 반환값 타입이 false이면 원래의 컨트롤러 단으로 제어를 안돌려줌
		return true;
	}
}
