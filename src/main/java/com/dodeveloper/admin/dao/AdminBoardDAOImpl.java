package com.dodeveloper.admin.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


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
	public List<AdminVO> selectlistStuBoard() throws Exception {
		
		return ses.selectList(ns + ".getBoard");
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
	public List<AdminReviewBoardVO> selectListRevBoard() throws Exception {
		
		return ses.selectList(ns + ".getRevBoard");
	}

	
}
