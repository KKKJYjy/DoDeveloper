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
		Map<String, Object> getlistArgBoard(int pageNo) throws Exception;
		
	// 제직자리뷰게시판 조회하는 메서드
		Map<String, Object> getlistRevBoard(int pageNo) throws Exception;
		
	// 스터디 게시물 삭체 처리하는 메서드
	//	boolean studeleteBoard(int stuNo) throws Exception;
		
		// 스터디 게시물 삭제 처리하는 메서드
		void studeleteBoard(String stuNo) throws Exception;
		
		// 강의 추천 게시판 삭제 처리하는 메서드
		void lecdeleteBoard(String lecNo) throws Exception;
		
		// 알고리즘 게시물 삭제 처리하는 메서드
		void algdeleteBoard(String boardNo) throws Exception;
		
		// review게물 삭제 처리하는 메서드
		void revdeleteBoard(String revNo) throws Exception;
}
