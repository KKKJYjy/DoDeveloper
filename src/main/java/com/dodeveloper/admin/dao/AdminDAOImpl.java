package com.dodeveloper.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.dodeveloper.admin.vo.ReportVO;
import com.dodeveloper.member.vo.MemberVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSession ses;
	
	private static final String NS = "com.dodeveloper.mappers.adminMapper";
	
	@Override
	public List<MemberVO> selectMember() throws Exception {
		
		
		return ses.selectList(NS + ".selectUser");
	}

	@Override
	public List<ReportVO> selectReport() throws Exception {
		
		return ses.selectList(NS + ".selectReport");
	}
	
}
