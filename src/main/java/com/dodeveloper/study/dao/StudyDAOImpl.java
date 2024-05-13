package com.dodeveloper.study.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.study.vodto.StuStackVO;
import com.dodeveloper.study.vodto.StudyBoardDTO;
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.study.vodto.PagingInfo;
import com.dodeveloper.study.vodto.SearchStudyDTO;
import com.dodeveloper.study.vodto.StackVO;
import com.dodeveloper.study.vodto.StuStackDTO;

@Repository
public class StudyDAOImpl implements StudyDAO {

	@Autowired
	private SqlSession ses;
	
	private static String ns = "com.dodeveloper.mappers.studyMapper";
	
	@Override
	public List<StudyBoardVO> selectAllList(PagingInfo pi) throws Exception {
		return ses.selectList(ns + ".selectAllList", pi);
	}

	@Override
	public List<StuStackDTO> selectAllStudyStack(int stuBoardNo) {
		return ses.selectList(ns + ".selectAllstudyStack", stuBoardNo);
	}

	@Override
	public int selectNextStuNo() throws Exception {
		return ses.selectOne(ns + ".selectNextStuNo");
	}

	@Override
	public int insertNewStack(int stuBoardNo, int chooseStack) throws Exception {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("stuBoardNo", stuBoardNo);
		param.put("chooseStack", chooseStack);
		
		return ses.insert(ns + ".insertNewStack", param);
	}

	@Override
	public int insertNewStudy(StudyBoardDTO newStudyDTO) throws Exception {
		return ses.insert(ns + ".insertNewStudy", newStudyDTO);
	}

	@Override
	public StudyBoardVO selectStudyByStuNo(int stuNo) throws Exception {
		return ses.selectOne(ns + ".selectStudyByStuNo", stuNo);
	}

	@Override
	public List<StudyBoardVO> selectAllListWithsDTO(SearchStudyDTO sDTO, PagingInfo pi) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("searchType", sDTO.getSearchType());
		param.put("searchValue", "%" +sDTO.getSearchValue() + "%");
		param.put("startRowIndex", pi.getStartRowIndex());
		param.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".selectAllListWithsDTO", param);
	}

	@Override
	public List<StackVO> selectAllStack() throws Exception {
		return ses.selectList(ns + ".selectAllStack");
	}

	@Override
	public int deleteStudyBoard(int stuNo) throws Exception {
		return ses.delete(ns + ".deleteStudyBoard", stuNo);
	}

	@Override
	public int modifyStudy(StudyBoardDTO modifyStudy) throws Exception {
		return ses.update(ns + ".modifyStudy", modifyStudy);
	}

	@Override
	public int modifyStack(int stuStackNo, int chooseStack) throws Exception {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("stuStackNo", stuStackNo);
		param.put("chooseStack", chooseStack);
			
		return ses.update(ns + ".modifyStack", param);
	}

	@Override
	public int deleteStudyStack(int stuStackNo) throws Exception {
		
		return ses.delete(ns + ".deleteStudyStack", stuStackNo);
	}

	@Override
	public int selectTotalBoardCnt() throws Exception {
		return ses.selectOne(ns + ".selectTotalBoardCnt");
	}

	@Override
	public int selectTotalBoardCntWithSdto(SearchStudyDTO sDTO) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("searchType", sDTO.getSearchType());
		param.put("searchValue", "%" +sDTO.getSearchValue() + "%");
		return ses.selectOne(ns + ".selectTotalBoardCntWithSdto", param);
	}

}
