package com.dodeveloper.admin.dao;

import java.util.List;


import com.dodeveloper.admin.vo.ReportVO;
import com.dodeveloper.member.vo.MemberVO;

public interface AdminDAO {
	// 전체 회원 조회하는 메서드
	List<MemberVO> selectMember() throws Exception;
	
	// 신고 게시판 조회하는 메서드
	List<ReportVO> selectReport() throws Exception;
	
}
