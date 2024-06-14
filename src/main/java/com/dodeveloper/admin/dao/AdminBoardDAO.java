package com.dodeveloper.admin.dao;

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
import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.reply.vodto.ReplyVO;

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
	
	// 공지사항 조회순 메서드
	List<NoticeDTO> selectListViewNotcBoard(PagingInfo pi) throws Exception;

	// 스터디게시글 전체 갯수 구할 수 있는 메서드
	int selectTotalBoardCnt() throws Exception;

	// 강의추천게시글 전체 갯수 구할 수 있는 메서드
	int selectLecTotalBoardCnt() throws Exception;

	// 알고리즘 게시글 전체 갯수 구할 수 있는 메서드
	int selectAlgTotalBoardCnt() throws Exception;

	// 기업리뷰 게시글 전체 갯수 구할 수 있는 메서드
	int selectRevTotalBoardCnt() throws Exception;

	// 공지사항 게시글 전체 갯수 구할 수 있는 메서드
	int selectNotcTotalBoardCnt() throws Exception;

	// 문의사항 게시글 전체 갯수 구할 수 있는 메서드
	int selectQnaTotalBoardCnt() throws Exception;

	// 검색된 글의 갯수를 가져오는 메서드
	int selectBoardSearchCritera(SearchCriteriaDTO sc) throws Exception;

	// 검색어가 있을 경우 검색된 글을 가져오는 메서드
	List<AdminVO> selectBoardListSC(SearchCriteriaDTO sc, PagingInfo pi) throws Exception;

	// lec 검색된 글의 갯수를 가져오는 메서드
	int selectLecBoardSearchCritera(SearchCriteriaDTO sc) throws Exception;

	// lec 검색어가 있을 경우 검색된 글을 가져오는 메서드
	List<AdminLectureVO> selectLecBoardListSc(SearchCriteriaDTO sc, PagingInfo pi) throws Exception;

	// alg 검색된 글의 갯수를 가져오는 메서드
	int selectAlgBoardSearchCritera(SearchCriteriaDTO sc) throws Exception;

	// alg 검색어가 있을 경우 검색된 글을 가져오는 메서드
	List<AdminArgBoardVO> selectAlgBoardListSc(SearchCriteriaDTO sc, PagingInfo pi) throws Exception;

	// 기업리뷰 검색된 글의 갯수를 가져오는 메서드
	int selectRevBoardSearchCritera(SearchCriteriaDTO sc) throws Exception;

	// 기업리뷰 검색된 글의 갯수를 가져오는 메서드
	List<AdminReviewBoardVO> selectRevBoardListSc(SearchCriteriaDTO sc, PagingInfo pi) throws Exception;

	// 공지사항 검색된 글의 갯수를 가져오는 메서드
	int selectNotcBoardSearchCritera(SearchCriteriaDTO sc) throws Exception;

	// 공지사항 검색된 글의 갯수를 가져오는 메서드
	List<NoticeDTO> selectNotcBoardListSc(SearchCriteriaDTO sc, PagingInfo pi) throws Exception;

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

	// 관리자 페이지에서 문의 게시글 삭제
	void deleteQna(String no) throws Exception;

	// 문의 게시글 삭제
	void deleteQnaBoard(int no) throws Exception;

	// 공지사항 테이블에 insert하는 메서드
	int insertNoticeBoard(NoticeDTO newBoard) throws Exception;

	// 신고내역 조회 메서드
	List<ReportVO> selectReport() throws Exception;

	// ?번 글을 가져오는 메서드
	List<ReportVO> selectReportBoardNo(int btypeNo, int boardNo) throws Exception;


	// 공지사항 ?번 글 조회
	NoticeDTO selectNoticeBoardNo(int boardNo) throws Exception;

	// 공지사항 수정
	int updateNoticeBoard(NoticeDTO mdBoard) throws Exception;

	// 문의 게시판 조회 메서드
	List<QnaBoardVO> selectQnaBoard(PagingInfo pi) throws Exception;

	// 문의 ?번 글을 가져오는 메서드
	QnaBoardVO selectQnaBoardNo(int no) throws Exception;

	// 문의사항 테이블 insert
	int insertQnaBoard(QnaBoardVO newBoard) throws Exception;

	// 홈에 공지사항 5개 출력
	List<NoticeDTO> selectDiffNotice() throws Exception;

	// 홈에 문의사항 5개 출력
	List<QnaBoardVO> selectDiffQna() throws Exception;

	// 홈에 스터디모임 최신글 5개 출력
	List<AdminVO> selectDiffStu() throws Exception;

	// 홈에 강의추천 최신글 5개 출력
	List<AdminLectureVO> selectDiffLec() throws Exception;

	// 홈에 알로리즘 최신글 5개 출력
	List<AdminArgBoardVO> selectDiffAlg() throws Exception;

	// 홈에 기업리뷰 최신글 5개 출력
	List<AdminReviewBoardVO> selectDiffRev() throws Exception;
	
	// 공지사항 조회수
	int notcReadCnt(int boardNo) throws Exception;
	
	// 공지사항 글을 언제 읽었는지
	int selectDiffNotc(int boardNo, String user) throws Exception;
	
	// ?글을 유저가 조회했다는 이력 기록
	int insertReadCntProcess(int boardNo, String user) throws Exception;
	
	// 패널티를 증가 시키며, 불량회원 테이블에 insert
	int insertPenalty(String deleteReason, String userId) throws Exception;
	
	// 카테고리에 해당되는 테이블에 글 삭제
	int deleteSelectBoard(int btypeNo, int boardNo) throws Exception;
	
	// 신고내역 삭제하는 메서드
	int deleteBoard(int btypeNo, int boardNo) throws Exception;

  //최신 공지글 5개를 가져오는 메서드 (메인홈에 출력할 용도)
	List<NoticeDTO> getNoticeTop5() throws Exception;
}
