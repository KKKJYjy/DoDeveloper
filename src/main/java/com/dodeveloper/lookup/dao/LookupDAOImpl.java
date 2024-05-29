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

    @Override
    public int selectDiff(String readWho, int boardNo, int bType) throws Exception {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("readWho", readWho);
	param.put("boardNo", boardNo);
	param.put("bType", bType);
	return ses.selectOne(ns + ".selectDiff", param);
    }

    @Override
    public int insertLookup(String readWho, int boardNo, int bType) throws Exception {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("readWho", readWho);
	param.put("boardNo", boardNo);
	param.put("bType", bType);
	return ses.insert(ns + ".insertLookup", param);
    }

    @Override
    public int updateLookupStudyBoard(int stuNo) throws Exception {
	return ses.update(ns + ".updateLookupStudyBoard", stuNo);
    }

}
