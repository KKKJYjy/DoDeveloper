package com.dodeveloper.studyApply.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.studyApply.vodto.StudyApplyVO;

@Repository
public class StudyApplyDAOImpl implements StudyApplyDAO {

	@Autowired
	private SqlSession ses;
	
	private static String ns = "com.dodeveloper.mappers.studyApplyMapper";
	
	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : StudyApplyVO newApply
		* @return : int - 성공하면 1 반환
		* @description : 해당 스터디 모임글에 신청 내용을 insert 한다.
	 */
	@Override
	public int insertApply(StudyApplyVO newApply) throws Exception {
		return ses.insert(ns + ".insertApply", newApply);
	}

}
