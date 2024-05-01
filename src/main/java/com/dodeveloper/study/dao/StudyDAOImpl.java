package com.dodeveloper.study.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.study.vodto.StudyBoardVO;

@Repository
public class StudyDAOImpl implements StudyDAO {

	@Autowired
	private SqlSession ses;
	
	private static String ns = "com.dodeveloper.mappers.studyMapper";
	
	@Override
	public List<StudyBoardVO> selectAllList() throws Exception {
		return ses.selectList(ns + ".selectAllList");
	}

}
