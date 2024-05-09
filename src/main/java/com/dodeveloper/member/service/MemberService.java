package com.dodeveloper.member.service;

import com.dodeveloper.member.dto.LoginDTO;
import com.dodeveloper.member.dto.RegisterDTO;
import com.dodeveloper.member.dto.SessionDTO;
import com.dodeveloper.member.vo.MemberVO;
import com.dodeveloper.mypage.dto.ChangePwdDTO;

public interface MemberService {
	// 로그인 하는 메서드
	MemberVO login(LoginDTO loginDTO) throws Exception;

	// 자동 로그인 세션 정보 DB 저장
	void keepLogin(SessionDTO sessionDTO) throws Exception;

	// 자동 로그인 쿠키 유효성 검사
	MemberVO checkLoginBefore(String value) throws Exception;

	// 아이디 중복 체크
	int duplicateUserId(String userId) throws Exception;

	// 회원가입
	int registerMember(RegisterDTO registerDTO) throws Exception;

	// 회원 정보 가져오는 메서드
	MemberVO getMemberInfo(String userId) throws Exception;
	
	// 비밀번호 확인 메서드
	int checkUserPwd(ChangePwdDTO changePwdDTO) throws Exception;
	
	// 비밀번호 변경
	int changeUserPwd(ChangePwdDTO changePwdDTO) throws Exception;
}
