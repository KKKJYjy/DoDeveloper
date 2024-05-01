package com.dodeveloper.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.study.dao.StudyDAO;
import com.dodeveloper.study.vodto.StudyBoardVO;

@Service
public class StudyServiceImpl implements StudyService {
	
	@Autowired
	StudyDAO sDao;
	
	@Override
	public List<StudyBoardVO> selectAllList() throws Exception {
		return sDao.selectAllList();
	}

}
