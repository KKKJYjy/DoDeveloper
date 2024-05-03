package com.dodeveloper.algorithm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.algorithm.vodto.AlgBoardDTO;
import com.dodeveloper.member.dto.LoginDTO;

@Repository
public class AlgDAOImpl implements AlgDAO {
	
	@Autowired
	private SqlSession ses;
	
	private static String ns = "com.dodeveloper.mappers.algorithmMapper";

	@Override
	public List<AlgBoardDTO> selectAlgBoard() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("!!!!DAO!!!!");
//		return ses.selectList(ns+".selectAlgBoard");
		return ses.selectList(ns+".selectAlgBoard");
	//	return null;
	}

}
