package com.dodeveloper.study.dao;

import java.util.List;

import com.dodeveloper.study.vodto.StuStackVO;
import com.dodeveloper.study.vodto.StudyBoardDTO;
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.study.vodto.StuStackDTO;

public interface StudyDAO {
	
	//모든 스터디 리스트를 가져오는 메서드
	List<StudyBoardVO> selectAllList() throws Exception;

	//stuNo번째 스터디 언어를 가져오는 메서드
	List<StuStackDTO> selectAllStudyStack(int stuNo);

	//다음 stuNo를 가져오는 메서드
	int selectNextStuNo() throws Exception;

	//stuBoardNo번째 스터디 언어를 insert
	int insertNewStack(int stuBoardNo, int chooseStack) throws Exception;

	//새로운 스터디글을 insert
	int insertNewStudy(StudyBoardDTO newStudyDTO)throws Exception;

	//stuNo번째 스터디 글을 가져오는 메서드
	StudyBoardVO selectStudyByStuNo(int stuNo)throws Exception;

}
