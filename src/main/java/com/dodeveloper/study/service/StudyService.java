package com.dodeveloper.study.service;

import java.util.List;

import com.dodeveloper.study.vodto.StudyBoardVO;

public interface StudyService {
	//전체 게시글을 조회하는 메서드
	List<StudyBoardVO> selectAllList() throws Exception;
}
