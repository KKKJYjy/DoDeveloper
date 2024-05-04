package com.dodeveloper.study.dao;

import java.util.List;

import com.dodeveloper.study.vodto.StuStackVO;
import com.dodeveloper.study.vodto.StudyBoardDTO;
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.study.vodto.StuStackDTO;

public interface StudyDAO {
	
	List<StudyBoardVO> selectAllList() throws Exception;

	//stuNo번째 스터디 언어를 가져오는 메서드
	List<StuStackDTO> selectAllStudyStack(int stuNo);

	//다음 stuNo를 가져오는 메서드
	int selectNextStuNo() throws Exception;

	
	int insertNewStack(int stuBoardNo, int chooseStack) throws Exception;

	int insertNewStudy(StudyBoardDTO newStudyDTO)throws Exception;

}
