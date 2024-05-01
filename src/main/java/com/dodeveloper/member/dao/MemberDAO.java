package com.dodeveloper.member.dao;

import com.dodeveloper.member.dto.LoginDTO;
import com.dodeveloper.member.dto.RegisterDTO;
import com.dodeveloper.member.dto.SessionDTO;
import com.dodeveloper.member.vo.MemberVO;

public interface MemberDAO {
	// 로그인 하는 메서드
		MemberVO loginMember(LoginDTO loginDTO) throws Exception;

		// 자동 로그인 세션 정보 DB 저장
		void keepLogin(SessionDTO sessionDTO) throws Exception;

		// 자동 로그인 쿠키 유효성 검사
		MemberVO checkLoginBefore(String loginCookie) throws Exception;
		
		// 아이디 중복체크
		int duplicateUserId(String userId) throws Exception;
		
		// 회원가입
		int registerMember(RegisterDTO registerDTO) throws Exception;
}
