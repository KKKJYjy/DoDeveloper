package com.dodeveloper.admin.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

@Repository
public class AdminBoardDAOImpl implements AdminBoardDAO {
	
	@Autowired
	private SqlSession ses;
	

	private static String ns = "com.dodeveloper.mappers.adminMapper";
	
	@Override
	public List<AdminVO> selectlistStuBoard(PagingInfo pi) throws Exception {
		
		// return ses.selectList(ns + ".getBoard");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".getBoard", params);
	}

	@Override
	public List<AdminLectureVO> selectListLecBoard(PagingInfo pi) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".getLecBoard", params);
	}

	@Override
	public List<AdminArgBoardVO> selectListArgBoard(PagingInfo pi) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".getArgBoard", params);
	}

	@Override
	public List<AdminReviewBoardVO> selectListRevBoard(PagingInfo pi) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".getRevBoard", params);
	}
	
	
	@Override
	public List<NoticeDTO> selectListNotcBoard(PagingInfo pi) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".getNotcBoard", params);
	}
	
	

	@Override
	public int selectTotalBoardCnt() throws Exception {
		
		return ses.selectOne(ns + ".getTotalBoardCnt");
	}

	@Override
	public int selectBoardSearchCritera(SearchCriteriaDTO sc) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", sc.getSearchType());
		params.put("searchValue", "%" + sc.getSearchValue() + "%");
		
		return ses.selectOne(ns + ".getStuBoardCntWithSC", params);
	}
	
	

	@Override
	public List<AdminVO> selectBoardListSC(SearchCriteriaDTO sc, PagingInfo pi) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", sc.getSearchType());
		params.put("searchValue", "%" + sc.getSearchValue() + "%");
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".getStuBoardListWithSC", params);
	}
	
	
	
	@Override
	public int selectLecTotalBoardCnt() throws Exception {
		
		return ses.selectOne(ns + ".getLecTotalBoardCnt");
	}
	
	@Override
	public int selectLecBoardSearchCritera(SearchCriteriaDTO sc) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", sc.getSearchType());
		params.put("searchValue", "%" + sc.getSearchValue() + "%");
		
		return ses.selectOne(ns + ".getLecBoardCntWithSC", params);
	}
	
	
	@Override
	public List<AdminLectureVO> selectLecBoardListSc(SearchCriteriaDTO sc, PagingInfo pi) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", sc.getSearchType());
		params.put("searchValue", "%" + sc.getSearchValue() + "%");
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".getLecBoardListWithSC", params);
	}
	
	
	@Override
	public int selectAlgTotalBoardCnt() throws Exception {
		
		return ses.selectOne(ns + ".getAlgTotalBoardCnt");
	}

	@Override
	public int selectAlgBoardSearchCritera(SearchCriteriaDTO sc) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", sc.getSearchType());
		params.put("searchValue", "%" + sc.getSearchValue() + "%");
		
		return ses.selectOne(ns + ".getAlgBoardCntWithSC", params);
	}

	@Override
	public List<AdminArgBoardVO> selectAlgBoardListSc(SearchCriteriaDTO sc, PagingInfo pi) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", sc.getSearchType());
		params.put("searchValue", "%" + sc.getSearchValue() + "%");
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".getAlgBoardListWithSC", params);
	}
	
	
	
	@Override
	public int selectRevTotalBoardCnt() throws Exception {
		
		return ses.selectOne(ns + ".getRevTotalBoardCnt");
	}

	@Override
	public int selectRevBoardSearchCritera(SearchCriteriaDTO sc) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", sc.getSearchType());
		params.put("searchValue", "%" + sc.getSearchValue() + "%");
		
		return ses.selectOne(ns + ".getRevBoardCntWithSC", params);
	}

	@Override
	public List<AdminReviewBoardVO> selectRevBoardListSc(SearchCriteriaDTO sc, PagingInfo pi) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", sc.getSearchType());
		params.put("searchValue", "%" + sc.getSearchValue() + "%");
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".getRevBoardListWithSC", params);
	}
	
	
	
	@Override
	public int selectNotcTotalBoardCnt() throws Exception {
		
		return ses.selectOne(ns + ".getNotcTotalBoardCnt");
	}

	@Override
	public int selectNotcBoardSearchCritera(SearchCriteriaDTO sc) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", sc.getSearchType());
		params.put("searchValue", "%" + sc.getSearchValue() + "%");
		
		return ses.selectOne(ns + ".getNotcBoardCntWithSC", params);
	}

	@Override
	public List<NoticeDTO> selectNotcBoardListSc(SearchCriteriaDTO sc, PagingInfo pi) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", sc.getSearchType());
		params.put("searchValue", "%" + sc.getSearchValue() + "%");
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".getNotcBoardListWithSC", params);
	}

	
	
	
	
	

	@Override
	public void deleteStu(String stuNo) throws Exception {
		
		ses.delete(ns + ".deleteStuBoard", stuNo);
	}

	@Override
	public void deleteAlg(String boardNo) throws Exception {
		
		ses.delete(ns + ".deleteAlgBoard", boardNo);
	}

	@Override
	public void deleteLec(String lecNo) throws Exception {
		
		ses.delete(ns + ".deleteLecBoard", lecNo);
	}

	@Override
	public void deleteRev(String revNo) throws Exception {
		
		ses.delete(ns + ".deleteRevBoard", revNo);
	}
	
	
	@Override
	public void deleteNotc(String boardNo) throws Exception {
		
		ses.delete(ns + ".deleteNotcBoard", boardNo);
	}
	
	
	@Override
	public void deleteReport(String reportNo) throws Exception {
		
		ses.delete(ns + ".deleteReportBoard", reportNo);
	}
	
	
	@Override
	public void deleteQna(String no) throws Exception {
		
		ses.delete(ns + ".deleteQnaBoard", no);
	}
	

	@Override
	public int insertNoticeBoard(NoticeDTO newBoard) throws Exception {
		
		return ses.insert(ns + ".insertNoticeBoard", newBoard);
	}

	@Override
	public List<ReportVO> selectReport() throws Exception {
		
		return ses.selectList(ns + ".getReport");
	}

	@Override
	public ReportVO selectReportBoardNo(int btypeNo) throws Exception {
		
		return ses.selectOne(ns + ".selectReportNo", btypeNo);
	}

	@Override
	public NoticeDTO selectNoticeBoardNo(int boardNo) throws Exception {
		
		return ses.selectOne(ns + ".selectNoticeBoardNo", boardNo);
	}
	

	@Override
	public int updateNoticeBoard(NoticeDTO mdBoard) throws Exception {
		System.out.println("dao단");
		return ses.update(ns + ".updateNotcBoard", mdBoard);
	}

	@Override
	public List<QnaBoardVO> selectQnaBoard() throws Exception {
		
		return ses.selectList(ns + ".selectQna");
	}

	@Override
	public QnaBoardVO selectQnaBoardNo(int no) throws Exception {
		
		return ses.selectOne(ns + ".selectQnaBoardNo", no);
	}

	@Override
	public int insertQnaBoard(QnaBoardVO newBoard) throws Exception {
		
		return ses.insert(ns + ".insertQna", newBoard);
	}

	@Override
	public List<QnaReplyVO> selectReply(int bNo) throws Exception {
		
		return ses.selectList(ns + ".getReply", ns);
	}

	@Override
	public int insertReply(QnaReplyVO reply) throws Exception {
		
		return ses.insert(ns + ".insertReply", reply);
	}

	@Override
	public int updateReply(QnaReplyVO updateReply) throws Exception {
		
		return ses.update(ns + ".updateReply", updateReply);
	}

	@Override
	public int deleteReply(int replyNo) throws Exception {
		
		return ses.delete(ns + ".deleteReply", replyNo);
	}

	

	
	//@Override
//	public int stuBoardDelete(int stuNo) throws Exception {
//		
//		return ses.delete(ns + ".deleteStuBoard", stuNo);
//	}

	
}
