package com.dodeveloper.admin.service;

import java.util.List;

import com.dodeveloper.admin.vo.BadMemberBoardVO;
import com.dodeveloper.member.vo.MemberVO;

public interface AdminService {
	
	
	
	// 불량회원 조회 메서드
	List<BadMemberBoardVO> getListBadMemberBoard() throws Exception;

	// 전체 유저를 조회하는 메서드
	List<MemberVO> getAllUser() throws Exception;

 }
