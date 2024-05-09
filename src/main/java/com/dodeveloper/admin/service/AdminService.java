package com.dodeveloper.admin.service;

import java.util.List;


import com.dodeveloper.admin.vo.AdminVO;
import com.dodeveloper.admin.vo.BadMemberBoardVO;
import com.dodeveloper.member.vo.MemberVO;

public interface AdminService {
	
	List<AdminVO> getlistStudyBoard() throws Exception;
	
	List<BadMemberBoardVO> getListBadMemberBoard() throws Exception;

public interface AdminService {
	// 전체 유저를 조회하는 메서드
	List<MemberVO> getAllUser() throws Exception;

  }
}