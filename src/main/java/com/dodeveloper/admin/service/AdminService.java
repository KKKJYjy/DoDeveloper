package com.dodeveloper.admin.service;

import java.util.List;

import com.dodeveloper.admin.dto.ConnectLogDTO;
import com.dodeveloper.admin.vo.AdminVO;

import com.dodeveloper.admin.vo.BadMemberBoardVO;
import com.dodeveloper.admin.vo.ConnectLogVO;
import com.dodeveloper.admin.vo.CountUriVO;
import com.dodeveloper.member.vo.MemberVO;

public interface AdminService {
	
	
	
	// 불량회원 조회 메서드
	List<BadMemberBoardVO> getListBadMemberBoard() throws Exception;

	// 전체 유저를 조회하는 메서드
	List<MemberVO> getAllUser() throws Exception;
	
	// 유저의 상태를 변경하는 메서드
	boolean modifyUserStatus(String newStatus,String userId) throws Exception;
	
	// 접속자의 세션아이디, 접속uri, 접속시간을 가져오는 메서드
	int getConnectLog(ConnectLogDTO connectLog) throws Exception;
	
	// 페이지별 접속자 수 조회하는 메서드
	List<CountUriVO> getPageLogCount() throws Exception;
	
	// 날짜별 접속자 수 조회하는 메서드
	List<ConnectLogVO> getDateLog(int month) throws Exception;

 }
