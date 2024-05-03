package com.dodeveloper.admin.service;

import java.util.List;

import com.dodeveloper.member.vo.MemberVO;

public interface AdminService {
	// 전체 유저를 조회하는 메서드
	List<MemberVO> getAllUser() throws Exception;
}
