package com.dodeveloper.lecture.service;

import java.util.List;

import com.dodeveloper.lecture.vodto.LectureBoardVO;

public interface LectureBoardService {
	
	// 게시판의 lecNo번 페이지의 글을 조회하는 메서드
	List<LectureBoardVO> getListAllBoard() throws Exception;
}
