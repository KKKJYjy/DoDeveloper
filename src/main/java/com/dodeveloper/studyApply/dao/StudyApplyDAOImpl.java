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

    @Override
    public int insertApply(StudyApplyVO newApply) throws Exception {
	return ses.insert(ns + ".insertApply", newApply);
    }

}
