package com.dodeveloper.admin.dao;



import java.util.List;
import java.util.Map;

import com.dodeveloper.admin.dto.ConnectLogDTO;
import com.dodeveloper.admin.dto.UserDTO;
import com.dodeveloper.admin.vo.AdminVO;
import com.dodeveloper.admin.vo.BadMemberBoardVO;
import com.dodeveloper.admin.vo.ConnectLogVO;
import com.dodeveloper.admin.vo.CountUriVO;
import com.dodeveloper.member.vo.MemberVO;

public interface AdminDAO {

	
	
	List<BadMemberBoardVO> selectListBadMemberBoard() throws Exception;
  
	// 유저 전체조회하는 메서드
	List<UserDTO> selectAllUser() throws Exception;

	// 유저 상태 변경하는 메서드
	int updateUserStatus(Map<String, Object> userParams) throws Exception;
	
	// 접속기록을 기록하는 메서드
	int insertConnectLog(ConnectLogDTO connectLog) throws Exception;

	// 페이지별 접속횟수를 조회하는 메서드
	List<CountUriVO> selectPageLog() throws Exception;
	
	// 날짜별 접속자 수 조회하는 메서드
	List<ConnectLogVO> connectDateLog(int month) throws Exception;

	// 상태 변경된 유저 정지일과 해제일 입력하는 메서드
	int updatePenaltyRecord(Map<String, Object> penaltyParams) throws Exception;
	
	// 탈퇴회원 처리
	int banUser(String userId) throws Exception;


	
	
  

}

