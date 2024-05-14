package com.dodeveloper.admin.service;

import java.util.List;
import java.util.Map;

import com.dodeveloper.admin.dto.SearchCriteriaDTO;
import com.dodeveloper.admin.vo.AdminArgBoardVO;
import com.dodeveloper.admin.vo.AdminLectureVO;
import com.dodeveloper.admin.vo.AdminReviewBoardVO;


public interface AdminBoardService {
	
	// study게시판 조회하는 메서드
		Map<String, Object> getlistStudyBoard(int pageNo, SearchCriteriaDTO sc) throws Exception;
	
	// 강의추천게시판 조회하는 메서드
		Map<String, Object> getlistLectureBoard(int pageNo) throws Exception;
		
	// 알고리즘게시판 조회하는 메서드
		List<AdminArgBoardVO> getlistArgBoard() throws Exception;
		
	// 제직자리뷰게시판 조회하는 메서드
		Map<String, Object> getlistRevBoard(int pageNo) throws Exception;
}
