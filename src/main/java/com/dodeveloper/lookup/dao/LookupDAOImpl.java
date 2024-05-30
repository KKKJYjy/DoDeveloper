package com.dodeveloper.lookup.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LookupDAOImpl implements LookupDAO {

	@Autowired
	private SqlSession ses;
	
	private static String ns = "com.dodeveloper.mappers.lookupMapper";
	
	/**
		* @author : yeonju
		* @date : 2024. 5. 14.
		* @param : String readWho - 조회한 사람
		* @param : int boardNo - 게시글 번호
		* @param : int bType - 게시판 번호
		* @return : int - 오늘 조회했으면 0, 오늘 조회 안했으면 -1 반환
		* @description : 해당 유저가 ?게시판 ?번글을 언제 읽었는지 확인하는 메서드
	 */
	@Override
	public int selectDiff(String readWho, int boardNo, int bType) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("readWho", readWho);
		param.put("boardNo", boardNo);
		param.put("bType", bType);
		return ses.selectOne(ns + ".selectDiff", param);
	}

	/**
		* @author : yeonju
		* @date : 2024. 5. 14.
		* @param : String readWho - 조회한 사람
		* @param : int boardNo - 게시글 번호
		* @param : int bType - 게시판 번호 
		* @return : int - 성공하면 1 반환
		* @description : 해당 유저가 ?게시판 ?번 게시글을 조회했다는 이력을 DB에 insert 하는 메서드
	 */
	@Override
	public int insertLookup(String readWho, int boardNo, int bType) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("readWho", readWho);
		param.put("boardNo", boardNo);
		param.put("bType", bType);
		return ses.insert(ns + ".insertLookup", param);
	}
	
	/**
		* @author : yeonju
		* @date : 2024. 5. 14.
		* @param : int stuNo
		* @return : int - 성공하면 1 반환
		* @description : 스터디 게시판의 stuNo번글의 조회수를 올리는 메서드
	 */
	@Override
	public int updateLookupStudyBoard(int stuNo) throws Exception {
		return ses.update(ns + ".updateLookupStudyBoard", stuNo);
	}


}
