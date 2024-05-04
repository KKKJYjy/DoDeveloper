package com.dodeveloper.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.study.dao.StudyDAO;
import com.dodeveloper.study.vodto.StuStackVO;
import com.dodeveloper.study.vodto.StudyBoardDTO;
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.study.vodto.StuStackDTO;

@Service
public class StudyServiceImpl implements StudyService {
	
	@Autowired
	StudyDAO sDao;
	
	@Override
	public List<StudyBoardVO> selectAllList() throws Exception {
		return sDao.selectAllList();
	}

	@Override
	public List<StuStackDTO> selectAllStudyStack(int stuNo) throws Exception {
		//System.out.println("서비스단" + sDao.selectAllStudyStack(stuNo).toString());
		return sDao.selectAllStudyStack(stuNo);
	}

	@Override
	public int selectNextStuNo() throws Exception {
		return sDao.selectNextStuNo();
	}

	@Override
	public int insertNewStack(int stuBoardNo, int chooseStack) throws Exception {
		return sDao.insertNewStack(stuBoardNo, chooseStack);
	}

	@Override
	public int insertNewStudy(StudyBoardDTO newStudyDTO) throws Exception {
		return sDao.insertNewStudy(newStudyDTO);
	}

}
