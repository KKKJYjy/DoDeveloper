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
import com.dodeveloper.study.vodto.SearchStudyDTO;
import com.dodeveloper.study.vodto.StackVO;
import com.dodeveloper.study.vodto.StuStackDTO;

@Repository
public class StudyDAOImpl implements StudyDAO {

	@Autowired
	private SqlSession ses;
	
	private static String ns = "com.dodeveloper.mappers.studyMapper";
	
	@Override
	public List<StudyBoardVO> selectAllList() throws Exception {
		return ses.selectList(ns + ".selectAllList");
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
	public List<StudyBoardVO> selectAllListWithsDTO(SearchStudyDTO sDTO) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("searchType", sDTO.getSearchType());
		param.put("searchContent", "%" +sDTO.getSearchContent() + "%");
		
		return ses.selectList(ns + ".selectAllListWithsDTO", param);
	}

	@Override
	public List<StackVO> selectAllStack() throws Exception {
		return ses.selectList(ns + ".selectAllStack");
	}

}
