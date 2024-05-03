package com.dodeveloper.lecture.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.lecture.vodto.LectureBoardVO;

@Repository // 아래의 클래스가 DAO 객체임을 명시
public class LectureBoardDAOImpl implements LectureBoardDAO {
	
	@Autowired
	private SqlSession ses; // SqlSession 객체 주입
	
	private static String ns = "com.dodeveloper.mappers.lectureBoardMapper";
	
	/**
	 * @methodName : selectListAllLecBoard
	 * @author : kde
	 * @date : 2024.05.02
	 * @param : 
	 * @return : List<LectureBoardVO>
	 * @description : DAO
	 */
	@Override
	public List<LectureBoardVO> selectListAllLecBoard() throws Exception {
		
		return ses.selectList(ns + ".getAllBoard");
	}

}
