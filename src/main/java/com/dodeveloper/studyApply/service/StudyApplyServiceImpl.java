package com.dodeveloper.studyApply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.studyApply.dao.StudyApplyDAO;
import com.dodeveloper.studyApply.vodto.StudyApplyVO;

@Service
public class StudyApplyServiceImpl implements StudyApplyService {

    @Autowired
    StudyApplyDAO saDao;

    @Override
    public int insertApply(StudyApplyVO newApply) throws Exception {
	return saDao.insertApply(newApply);
    }

}
