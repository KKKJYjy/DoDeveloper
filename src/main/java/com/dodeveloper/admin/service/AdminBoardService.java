package com.dodeveloper.admin.service;

import java.util.List;

import com.dodeveloper.admin.vo.AdminArgBoardVO;
import com.dodeveloper.admin.vo.AdminLectureVO;
import com.dodeveloper.admin.vo.AdminReviewBoardVO;
import com.dodeveloper.admin.vo.AdminVO;


public interface AdminBoardService {
	
	// study게시판 조회하는 메서드
		List<AdminVO> getlistStudyBoard() throws Exception;
	
	// 강의추천게시판 조회하는 메서드
		List<AdminLectureVO> getlistLectureBoard() throws Exception;
		
	// 알고리즘게시판 조회하는 메서드
		List<AdminArgBoardVO> getlistArgBoard() throws Exception;
		
	// 제직자리뷰게시판 조회하는 메서드
		List<AdminReviewBoardVO> getlistRevBoard() throws Exception;
}
