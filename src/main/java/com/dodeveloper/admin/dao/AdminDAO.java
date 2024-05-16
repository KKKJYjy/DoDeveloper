package com.dodeveloper.admin.dao;



import java.util.List;

import com.dodeveloper.admin.vo.AdminVO;
import com.dodeveloper.admin.vo.BadMemberBoardVO;
import com.dodeveloper.member.vo.MemberVO;

public interface AdminDAO {

	
	
	List<BadMemberBoardVO> selectListBadMemberBoard() throws Exception;
  
	// 유저 전체조회하는 메서드
	List<MemberVO> selectAllUser() throws Exception;
  

}

