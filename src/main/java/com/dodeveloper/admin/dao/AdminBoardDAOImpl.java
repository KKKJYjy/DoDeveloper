package com.dodeveloper.admin.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.admin.dto.SearchCriteriaDTO;
import com.dodeveloper.admin.etc.PagingInfo;
import com.dodeveloper.admin.vo.AdminArgBoardVO;
import com.dodeveloper.admin.vo.AdminLectureVO;
import com.dodeveloper.admin.vo.AdminReviewBoardVO;
import com.dodeveloper.admin.vo.AdminVO;

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
	public List<AdminLectureVO> selectListLecBoard() throws Exception {
		
		return ses.selectList(ns + ".getLecBoard");
	}

	@Override
	public List<AdminArgBoardVO> selectListArgBoard() throws Exception {
		
		return ses.selectList(ns + ".getArgBoard");
	}

	@Override
	public List<AdminReviewBoardVO> selectListRevBoard(PagingInfo pi) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".getRevBoard", params);
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

	
}
