package com.dodeveloper.admin.service;

import java.util.List;
import java.util.Map;

import com.dodeveloper.admin.dto.SearchCriteriaDTO;
import com.dodeveloper.admin.dto.NoticeDTO;
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
		
	//  기업리뷰게시판 조회하는 메서드
		Map<String, Object> getlistRevBoard(int pageNo) throws Exception;
		
	// 공지사항 조회하는 메서드
		List<NoticeDTO> getlistNotcBoard() throws Exception;
		
	// 스터디 게시물 삭체 처리하는 메서드
	//	boolean studeleteBoard(int stuNo) throws Exception;
		
		// 스터디 게시물 삭제 처리하는 메서드
		void studeleteBoard(String stuNo) throws Exception;
		
		// 강의 추천 게시판 삭제 처리하는 메서드
		void lecdeleteBoard(String lecNo) throws Exception;
		
		// 알고리즘 게시물 삭제 처리하는 메서드
		void algdeleteBoard(String boardNo) throws Exception;
		
		// review게시물 삭제 처리하는 메서드
		void revdeleteBoard(String revNo) throws Exception;
		
		// 공지사항 작성
		boolean writeNoticeBoard(NoticeDTO newBoard) throws Exception;
		
}
