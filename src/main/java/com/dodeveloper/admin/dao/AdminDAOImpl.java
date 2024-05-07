package com.dodeveloper.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.admin.vo.BadMemberBoardVO;
import com.dodeveloper.study.vodto.StudyBoardVO;



@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSession ses;
	
	private static String ns = "com.dodeveloper.mappers.adminMapper";
	
	@Override
	public List<StudyBoardVO> selectlistStudyBoard() throws Exception {
		
	
		return ses.selectList(ns + ".getBoard");
	}

	@Override
	public List<BadMemberBoardVO> selectListBadMemberBoard() throws Exception {
		
		return ses.selectList(ns + ".getBadMemberBoard");
	}

}
