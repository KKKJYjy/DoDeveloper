package com.dodeveloper.study.service;

import java.util.List;

import com.dodeveloper.study.vodto.StuStackVO;
import com.dodeveloper.study.vodto.StudyBoardDTO;
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.study.vodto.StuStackDTO;

public interface StudyService {
	//전체 게시글을 조회하는 메서드
	List<StudyBoardVO> selectAllList() throws Exception;

	//stuNo번째 스터디 언어를 가져오는 메서드
	List<StuStackDTO> selectAllStudyStack(int stuNo) throws Exception;

	//다음 게시글 번호를 얻어오는 메서드
	int selectNextStuNo() throws Exception;

	//stuBoardNo번째 글의 스터디할 언어 인서트
	int insertNewStack(int stuBoardNo, int chooseStack) throws Exception;

	//새로운 스터디글 인서트
	int insertNewStudy(StudyBoardDTO newStudyDTO) throws Exception;

	//stuNo번째 스터디 글을 조회하는 메서드
	StudyBoardVO selectStudyByStuNo(int stuNo) throws Exception;
	
}
