package com.dodeveloper.admin.dao;

import java.util.List;

import com.dodeveloper.member.vo.MemberVO;

public interface AdminDAO {
	// 유저 전체조회하는 메서드
	List<MemberVO> selectAllUser() throws Exception;
}
