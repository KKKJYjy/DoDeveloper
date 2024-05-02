package com.dodeveloper.study.service;

import java.util.List;

import com.dodeveloper.study.vodto.StuStackVO;
import com.dodeveloper.study.vodto.StudyBoardVO;

public interface StudyService {
	//전체 게시글을 조회하는 메서드
	List<StudyBoardVO> selectAllList() throws Exception;

	//stuNo번째 스터디 언어를 가져오는 메서드
	List<StuStackVO> selectAllStudyStack(int stuNo) throws Exception;
}
