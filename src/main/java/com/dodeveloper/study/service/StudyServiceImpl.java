package com.dodeveloper.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.study.dao.StudyDAO;
import com.dodeveloper.study.vodto.StuStackVO;
import com.dodeveloper.study.vodto.StudyBoardDTO;
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.study.vodto.SearchStudyDTO;
import com.dodeveloper.study.vodto.StackVO;
import com.dodeveloper.study.vodto.StuStackDTO;

@Service
public class StudyServiceImpl implements StudyService {
	
	@Autowired
	StudyDAO sDao;
	
	@Override
	public List<StudyBoardVO> selectAllList(SearchStudyDTO sDTO) throws Exception {
		
		//검색어가 있을 경우의 게시글 목록과 없는 경우의 게시글 목록이 다르다.
		List<StudyBoardVO> lst = null;
		
		if(sDTO.getSearchType() != null && sDTO.getSearchContent() != null) {
			//검색어가 있는 경우
			lst = sDao.selectAllListWithsDTO(sDTO);
		}else {
			//검색어가 없는 경우
			lst = sDao.selectAllList();
		}
		
		return lst;
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

	@Override
	public StudyBoardVO selectStudyByStuNo(int stuNo) throws Exception {
		return sDao.selectStudyByStuNo(stuNo);
	}

	@Override
	public List<StackVO> selectAllStack() throws Exception {
		return sDao.selectAllStack();
	}

	@Override
	public int deleteStudyBoard(int stuNo) throws Exception {
		return sDao.deleteStudyBoard(stuNo);
	}

}
