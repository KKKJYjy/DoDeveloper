package com.dodeveloper.admin.dao;

import java.util.List;

import com.dodeveloper.admin.dto.SearchCriteriaDTO;
import com.dodeveloper.admin.dto.NoticeDTO;
import com.dodeveloper.admin.vo.AdminArgBoardVO;
import com.dodeveloper.admin.vo.AdminLectureVO;
import com.dodeveloper.admin.vo.AdminReviewBoardVO;
import com.dodeveloper.admin.vo.AdminVO;
import com.dodeveloper.admin.vo.ReportVO;
import com.dodeveloper.etc.PagingInfo;

public interface AdminBoardDAO {
	// 스터디게시판 조회 메서드
	List<AdminVO> selectlistStuBoard(PagingInfo pi) throws Exception;

	// 강의게시판 조회 메서드
	List<AdminLectureVO> selectListLecBoard(PagingInfo pi) throws Exception;

	// 알고리즘게시판 조회 메서드
	List<AdminArgBoardVO> selectListArgBoard(PagingInfo pi) throws Exception;

	// review게시판 조회 메서드
	List<AdminReviewBoardVO> selectListRevBoard(PagingInfo pi) throws Exception;
	
	// 공지사항 조회 메서드
	List<NoticeDTO> selectListNotcBoard(PagingInfo pi) throws Exception;

	// 게시글 전체 갯수 구할 수 있는 메서드
	int selectTotalBoardCnt() throws Exception;
	
	// 검색된 글의 갯수를 가져오는 메서드
	int selectBoardSearchCritera(SearchCriteriaDTO sc) throws Exception;
	
	// 검색어가 있을 경우 검색된 글을 가져오는 메서드
	List<AdminVO> selectBoardListSC(SearchCriteriaDTO sc, PagingInfo pi) throws Exception;
	
	// stuNo번 글 삭제
	// int stuBoardDelete(int stuNo) throws Exception;
	
	// stuNo번 게시글 삭제
    void deleteStu(String stuNo) throws Exception;
    
    // lecNo번 게시글 삭제
    void deleteLec(String lecNo) throws Exception;
     
    // boardNo번 게시글 삭제
    void deleteAlg(String boardNo) throws Exception;
    
    // revNo번 게시글 삭제
    void deleteRev(String revNo) throws Exception;
    
    // 공지사항 게시글 삭제
    void deleteNotc(String boardNo) throws Exception;
    
    // 신고내역 삭제 
    void deleteReport(String reportNo) throws Exception;
    
    // 공지사항 테이블에 insert하는 메서드
    int insertNoticeBoard(NoticeDTO newBoard) throws Exception;
    
    // 신고내역 조회 메서드
    List<ReportVO> selectReport() throws Exception;



    // ?번 글을 가져오는 메서드
   ReportVO selectReportBoardNo(int btypeNo) throws Exception;
    
    
}
