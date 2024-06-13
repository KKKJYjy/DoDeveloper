package com.dodeveloper.admin.service;

import java.util.List;
import java.util.Map;

import com.dodeveloper.admin.dto.SearchCriteriaDTO;
import com.dodeveloper.admin.dto.NoticeDTO;
import com.dodeveloper.admin.vo.AdminArgBoardVO;
import com.dodeveloper.admin.vo.AdminLectureVO;
import com.dodeveloper.admin.vo.AdminReviewBoardVO;
import com.dodeveloper.admin.vo.AdminVO;
import com.dodeveloper.admin.vo.QnaBoardVO;
import com.dodeveloper.admin.vo.QnaReplyVO;
import com.dodeveloper.admin.vo.ReportVO;
import com.dodeveloper.reply.vodto.ReplyVO;


public interface AdminBoardService {
	
	// study게시판 조회하는 메서드
		Map<String, Object> getlistStudyBoard(int pageNo, SearchCriteriaDTO sc) throws Exception;
	
	// 강의추천게시판 조회하는 메서드
		Map<String, Object> getlistLectureBoard(int pageNo, SearchCriteriaDTO sc) throws Exception;
		
	// 알고리즘게시판 조회하는 메서드
		Map<String, Object> getlistArgBoard(int pageNo, SearchCriteriaDTO sc) throws Exception;
		
	//  기업리뷰게시판 조회하는 메서드
		Map<String, Object> getlistRevBoard(int pageNo, SearchCriteriaDTO sc) throws Exception;
		
	// 공지사항 조회하는 메서드
		Map<String, Object> getlistNotcBoard(int pageNo, SearchCriteriaDTO sc) throws Exception;
		
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
		
		// 공지사항 게시물 삭제 처리하는 메더스
		void notcdeleteBoard(String boardNo) throws Exception;
		
		
		// 관리자 페이지에서 문의게시물 삭제 처리한는 메서드
		void qnaDelete(String no) throws Exception;
		
		// 문의게시물 삭제 처리한는 메서드
		void qnaDeleteBoard(int no) throws Exception;
		
		// 공지사항 작성
		boolean writeNoticeBoard(NoticeDTO newBoard) throws Exception;
		
	// 신고내역 조회하는 메서드
		List<ReportVO> getReport() throws Exception;


	// 신고내역 상세조회
		List<ReportVO> getReportNO(int btypeNo, int boardNo) throws Exception;

		
		// 공지사항 상세페이지
	    NoticeDTO getNotcBoardNo(int boardNo) throws Exception;
	    
	    // 공지사항 게시물 얻어오는 메서드
	    Map<String, Object> getNotcByBoardNo (int boardNo) throws Exception;
	    

		// 공지사항 게시글 수정
	    boolean modifyNotcBoard(NoticeDTO mdBoard) throws Exception;
	    
	    // 문의 게시판 조회
	    Map<String, Object> getQnaBoard(int pageNo) throws Exception;
	    
	    // 문의 게시판 상세페이지
	    QnaBoardVO getQnaBoardNo(int no) throws Exception;
	    
	    // 문의사항 작성
	    boolean writeQndBoard(QnaBoardVO newBoard) throws Exception;
	    

	    // 신고내역 삭제
		boolean deleteBoard(int btypeNo, int boardNo, String deleteReason) throws Exception;
	    
	    // dashboard에 공지사항 5개만 출력
	    List<NoticeDTO> diffNotice() throws Exception;
	    
	    // dashboard에 문의사항 5개 출럭
	    List<QnaBoardVO> diffQna() throws Exception;
	    
	    // 홈에 스터디모임 최신글 5개 출력
	    List<AdminVO> diffStu() throws Exception;
	    
	    // 홈에 강의추천 최신글 5개 출력
	    List<AdminLectureVO> diffLec() throws Exception;
	    
	    // 홈에 알고리즘 최신글 5개 출력
	    List<AdminArgBoardVO> diffAlg() throws Exception;
	    
	    // 홈에 기업리뷰 최신글 5개 출력
	    List<AdminReviewBoardVO> diffRev() throws Exception;

	    // 공지사항 최신글 5개를 가져오는 메서드
		List<NoticeDTO> getNoticeTop5() throws Exception;
	    
	
}
