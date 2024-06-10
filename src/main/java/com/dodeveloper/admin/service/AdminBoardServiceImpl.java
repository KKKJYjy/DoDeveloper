package com.dodeveloper.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.admin.dao.AdminBoardDAO;
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

@Service
public class AdminBoardServiceImpl implements AdminBoardService {

	@Autowired
	private AdminBoardDAO bDao;

	@Autowired
	private PagingInfo pi;

	@Override
	public Map<String, Object> getlistStudyBoard(int pageNo, SearchCriteriaDTO sc) throws Exception {

		System.out.println("서비스단 : study게시물 조회");

		List<AdminVO> stuBoardList = null;

		if (sc.getSearchType() != null && sc.getSearchValue() != null) {
			makePagingInfo(pageNo, sc);
			stuBoardList = bDao.selectBoardListSC(sc, pi);
		} else {
			makePagingInfo(pageNo);
			stuBoardList = bDao.selectlistStuBoard(pi);
		}

		// DAO 단 호출
		// List<AdminVO> stuBoardList = bDao.selectlistStuBoard(pi);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("stuBoardList", stuBoardList);
		returnMap.put("pagingInfo", this.pi);

		return returnMap;
	}

	private void makePagingInfo(int pageNo, SearchCriteriaDTO sc) throws Exception {
		this.pi.setPageNo(pageNo);

		this.pi.setViewPostCntPerPage(10);
		this.pi.setPageCntPerBlock(3);

		this.pi.setTotalPostCnt(bDao.selectBoardSearchCritera(sc));

		// 총 페이지 수
		this.pi.setTotalPageCnt();

		// 보여주기 시작할 글의 번호
		this.pi.setStartRowIndex();

		// 전체 페이지 블럭 갯수
		this.pi.setTotalPageBlockCnt();

		// 현재 페이지가 속한 페이징 블럭 번호
		this.pi.setPageBlockOfCurrentPage();

		// 현재 페이징 블럭 시작 페이지 번호
		this.pi.setStartNumOfCurrentPagingBlock();

		// 현재 페이징 블럭 끝 페이지 번호
		this.pi.setEndNumOfCurrentPagingBlock();
	}

	private void makePagingInfo(int pageNo) throws Exception {
		this.pi.setPageNo(pageNo);

		this.pi.setViewPostCntPerPage(10);
		this.pi.setPageCntPerBlock(3);

		// 게시물 데이터 갯수
		this.pi.setTotalPostCnt(bDao.selectTotalBoardCnt());

		// 총 페이지 수
		this.pi.setTotalPageCnt();

		// 보여주기 시작할 글 번호
		this.pi.setStartRowIndex();

		// 전체 페이지 블럭 갯수
		this.pi.setTotalPageBlockCnt();

		// 현재 페이지가 속한 페이징 블럭 번호
		this.pi.setPageBlockOfCurrentPage();

		// 현재 페이징 블럭 시작 페이지 번호
		this.pi.setStartNumOfCurrentPagingBlock();

		// 현재 페이징 블럭 끝 페이지 번호
		this.pi.setEndNumOfCurrentPagingBlock();
	}

	private void makeLecPagingInfo(int pageNo) throws Exception {
		this.pi.setPageNo(pageNo);

		this.pi.setViewPostCntPerPage(10);
		this.pi.setPageCntPerBlock(3);

		// 게시물 데이터 갯수
		this.pi.setTotalPostCnt(bDao.selectLecTotalBoardCnt());

		// 총 페이지 수
		this.pi.setTotalPageCnt();

		// 보여주기 시작할 글 번호
		this.pi.setStartRowIndex();

		// 전체 페이지 블럭 갯수
		this.pi.setTotalPageBlockCnt();

		// 현재 페이지가 속한 페이징 블럭 번호
		this.pi.setPageBlockOfCurrentPage();

		// 현재 페이징 블럭 시작 페이지 번호
		this.pi.setStartNumOfCurrentPagingBlock();

		// 현재 페이징 블럭 끝 페이지 번호
		this.pi.setEndNumOfCurrentPagingBlock();
	}

	private void makeAlgPagingInfo(int pageNo) throws Exception {
		this.pi.setPageNo(pageNo);

		this.pi.setViewPostCntPerPage(10);
		this.pi.setPageCntPerBlock(3);

		// 게시물 데이터 갯수
		this.pi.setTotalPostCnt(bDao.selectAlgTotalBoardCnt());

		// 총 페이지 수
		this.pi.setTotalPageCnt();

		// 보여주기 시작할 글 번호
		this.pi.setStartRowIndex();

		// 전체 페이지 블럭 갯수
		this.pi.setTotalPageBlockCnt();

		// 현재 페이지가 속한 페이징 블럭 번호
		this.pi.setPageBlockOfCurrentPage();

		// 현재 페이징 블럭 시작 페이지 번호
		this.pi.setStartNumOfCurrentPagingBlock();

		// 현재 페이징 블럭 끝 페이지 번호
		this.pi.setEndNumOfCurrentPagingBlock();
	}

	private void makeRevPagingInfo(int pageNo) throws Exception {
		this.pi.setPageNo(pageNo);

		this.pi.setViewPostCntPerPage(10);
		this.pi.setPageCntPerBlock(3);

		// 게시물 데이터 갯수
		this.pi.setTotalPostCnt(bDao.selectRevTotalBoardCnt());

		// 총 페이지 수
		this.pi.setTotalPageCnt();

		// 보여주기 시작할 글 번호
		this.pi.setStartRowIndex();

		// 전체 페이지 블럭 갯수
		this.pi.setTotalPageBlockCnt();

		// 현재 페이지가 속한 페이징 블럭 번호
		this.pi.setPageBlockOfCurrentPage();

		// 현재 페이징 블럭 시작 페이지 번호
		this.pi.setStartNumOfCurrentPagingBlock();

		// 현재 페이징 블럭 끝 페이지 번호
		this.pi.setEndNumOfCurrentPagingBlock();
	}

	private void makeNotcPagingInfo(int pageNo) throws Exception {
		this.pi.setPageNo(pageNo);

		this.pi.setViewPostCntPerPage(10);
		this.pi.setPageCntPerBlock(3);

		// 게시물 데이터 갯수
		this.pi.setTotalPostCnt(bDao.selectNotcTotalBoardCnt());

		// 총 페이지 수
		this.pi.setTotalPageCnt();

		// 보여주기 시작할 글 번호
		this.pi.setStartRowIndex();

		// 전체 페이지 블럭 갯수
		this.pi.setTotalPageBlockCnt();

		// 현재 페이지가 속한 페이징 블럭 번호
		this.pi.setPageBlockOfCurrentPage();

		// 현재 페이징 블럭 시작 페이지 번호
		this.pi.setStartNumOfCurrentPagingBlock();

		// 현재 페이징 블럭 끝 페이지 번호
		this.pi.setEndNumOfCurrentPagingBlock();
	}
	
	
	private void makeQnaPagingInfo(int pageNo) throws Exception {
		this.pi.setPageNo(pageNo);

		this.pi.setViewPostCntPerPage(10);
		this.pi.setPageCntPerBlock(3);

		// 게시물 데이터 갯수
		this.pi.setTotalPostCnt(bDao.selectQnaTotalBoardCnt());

		// 총 페이지 수
		this.pi.setTotalPageCnt();

		// 보여주기 시작할 글 번호
		this.pi.setStartRowIndex();

		// 전체 페이지 블럭 갯수
		this.pi.setTotalPageBlockCnt();

		// 현재 페이지가 속한 페이징 블럭 번호
		this.pi.setPageBlockOfCurrentPage();

		// 현재 페이징 블럭 시작 페이지 번호
		this.pi.setStartNumOfCurrentPagingBlock();

		// 현재 페이징 블럭 끝 페이지 번호
		this.pi.setEndNumOfCurrentPagingBlock();
	}

	
	

	@Override
	public Map<String, Object> getlistLectureBoard(int pageNo, SearchCriteriaDTO sc) throws Exception {

		System.out.println("서비스단 : lecture게시물 조회");

		List<AdminLectureVO> lecBoardList = null;

		if (sc.getSearchType() != null && sc.getSearchValue() != null) {
			makePagingInfo(pageNo, sc);
			lecBoardList = bDao.selectLecBoardListSc(sc, pi);
		} else {
			makeLecPagingInfo(pageNo);
			lecBoardList = bDao.selectListLecBoard(pi);
		}

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("lecBoardList", lecBoardList);
		returnMap.put("pagingInfo", this.pi);

		return returnMap;
	}

	@Override
	public Map<String, Object> getlistArgBoard(int pageNo, SearchCriteriaDTO sc) throws Exception {

		System.out.println("서비스단 : 알고리즘 게시물 조회");

		List<AdminArgBoardVO> argBoardList = null;

		if (sc.getSearchType() != null && sc.getSearchValue() != null) {
			makePagingInfo(pageNo, sc);
			argBoardList = bDao.selectAlgBoardListSc(sc, pi);
		} else {
			makeAlgPagingInfo(pageNo);
			argBoardList = bDao.selectListArgBoard(pi);
		}

		// argBoardList = bDao.selectListArgBoard(pi);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("argBoardList", argBoardList);
		returnMap.put("pagingInfo", this.pi);

		return returnMap;
	}

	@Override
	public Map<String, Object> getlistRevBoard(int pageNo, SearchCriteriaDTO sc) throws Exception {

		System.out.println("서비스단 : review게시물 조회");

		List<AdminReviewBoardVO> revBoardList = null;

		if (sc.getSearchType() != null && sc.getSearchValue() != null) {
			makePagingInfo(pageNo, sc);
			revBoardList = bDao.selectRevBoardListSc(sc, pi);
		} else {
			makeRevPagingInfo(pageNo);
			revBoardList = bDao.selectListRevBoard(pi);
		}

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("revBoardList", revBoardList);
		returnMap.put("pagingInfo", this.pi);

		return returnMap;
	}

	@Override
	public Map<String, Object> getlistNotcBoard(int pageNo, SearchCriteriaDTO sc) throws Exception {

		System.out.println("서비스단 : 공지사항 조회");

		List<NoticeDTO> notcBoardList = null;

		if (sc.getSearchType() != null && sc.getSearchValue() != null) {
			makePagingInfo(pageNo, sc);
			notcBoardList = bDao.selectNotcBoardListSc(sc, pi);
		} else {
			makeNotcPagingInfo(pageNo);
			notcBoardList = bDao.selectListNotcBoard(pi);
		}

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("notcBoardList", notcBoardList);
		returnMap.put("pagingInfo", this.pi);

		return returnMap;
	}

//	@Override
//	public boolean studeleteBoard(int stuNo) throws Exception {
//		boolean result = false;
//		
//		if(bDao.stuBoardDelete(stuNo) == 1) {
//			result = true;
//		}
//		
//		return result;
//	}

	@Override
	public void studeleteBoard(String stuNo) throws Exception {

		bDao.deleteStu(stuNo);

	}

	@Override
	public void algdeleteBoard(String boardNo) throws Exception {

		bDao.deleteAlg(boardNo);

	}

	@Override
	public void lecdeleteBoard(String lecNo) throws Exception {

		bDao.deleteLec(lecNo);

	}

	@Override
	public void revdeleteBoard(String revNo) throws Exception {

		bDao.deleteRev(revNo);

	}

	@Override
	public void notcdeleteBoard(String boardNo) throws Exception {

		bDao.deleteNotc(boardNo);
	}

	@Override
	public void reportDelete(String reportNo) throws Exception {

		bDao.deleteReport(reportNo);
	}

	@Override
	public void qnaDelete(String no) throws Exception {

		bDao.deleteQna(no);
	}
	
	
	@Override
	public void qnaDeleteBoard(int no) throws Exception {
		
		bDao.deleteQnaBoard(no);
	}
	

	@Override
	public boolean writeNoticeBoard(NoticeDTO newBoard) throws Exception {

		boolean result = false;

		bDao.insertNoticeBoard(newBoard);

		return result;
	}

	@Override
	public List<ReportVO> getReport() throws Exception {

		System.out.println("서비스단 : 신고내역 조회");

		List<ReportVO> reportList = bDao.selectReport();

		return reportList;
	}

	@Override
	public ReportVO getReportNO(int btypeNo) throws Exception {

		System.out.println("서비스단 : 신고게시글 상세조회");

		ReportVO report = bDao.selectReportBoardNo(btypeNo);

		return report;
	}

	@Override
	public NoticeDTO getNotcBoardNo(int boardNo) throws Exception {

		System.out.println("서비스단 : 공지사항 상세페이지");

		NoticeDTO notice = bDao.selectNoticeBoardNo(boardNo);

		return notice;
	}

	@Override
	public boolean modifyNotcBoard(NoticeDTO mdBoard) throws Exception {

		System.out.println("서비스단 : 공지사항 수정");

		boolean result = false;

		if (bDao.updateNoticeBoard(mdBoard) == 1) {
			result = true;
		}

		return result;
	}

	@Override
	public Map<String, Object> getNotcByBoardNo(int boardNo) throws Exception {

		System.out.println("수정할 글번호");

		NoticeDTO notcBoard = bDao.selectNoticeBoardNo(boardNo);

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("notcBoard", notcBoard);

		return result;
	}

	@Override
	public Map<String, Object> getQnaBoard(int pageNo) throws Exception {

		System.out.println("서비스단 : 문의게시글 조회");
		
		List<QnaBoardVO> qnaList = null;
		
		makeQnaPagingInfo(pageNo);
		qnaList = bDao.selectQnaBoard(pi);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("qnaList", qnaList);
		returnMap.put("pagingInfo", this.pi);
		
		return returnMap;
	}

	@Override
	public QnaBoardVO getQnaBoardNo(int no) throws Exception {

		System.out.println("서비스단 : 문의 상세페이지");

		QnaBoardVO qnaViewBoard = bDao.selectQnaBoardNo(no);

		return qnaViewBoard;
	}

	@Override
	public boolean writeQndBoard(QnaBoardVO newBoard) throws Exception {

		boolean result = false;
		
		bDao.insertQnaBoard(newBoard);

		return result;

	}

	@Override
	public List<NoticeDTO> diffNotice() throws Exception {
		
		List<NoticeDTO> diffNotc = bDao.selectDiffNotice();
		
		return diffNotc;
	}

	@Override
	public List<QnaBoardVO> diffQna() throws Exception {
		
		List<QnaBoardVO> diffQna = bDao.selectDiffQna();
		
		return diffQna;
	}

	@Override
	public List<AdminVO> diffStu() throws Exception {
		
		List<AdminVO> diffStu = bDao.selectDiffStu();
		
		return diffStu;
	}

	@Override
	public List<AdminLectureVO> diffLec() throws Exception {
		
		List<AdminLectureVO> diffLec = bDao.selectDiffLec();
		
		return diffLec;
	}

	@Override
	public List<AdminArgBoardVO> diffAlg() throws Exception {
		
		List<AdminArgBoardVO> diffAlg = bDao.selectDiffAlg();
		
		return diffAlg;
	}

	@Override
	public List<AdminReviewBoardVO> diffRev() throws Exception {
		
		List<AdminReviewBoardVO> diffRev = bDao.selectDiffRev();
		
		return diffRev;
	}

	/**
		* @author : yeonju
		* @date : 2024. 6. 10.
		* @return : List<NoticeDTO>
		* @description : 최신 공지글 5개를 가져오는 메서드 (메인홈에 출력할 용도) 
	 */
	@Override
	public List<NoticeDTO> getNoticeTop5() throws Exception {
		return bDao.getNoticeTop5();
	}




}
