package com.dodeveloper.lecture.service;

import java.util.List;
import java.util.Map;

import com.dodeveloper.lecture.vodto.LectureBoardVO;

public interface LectureBoardService {
	
	// 게시판의 lecNo번 페이지의 글을 조회하는 메서드
	List<LectureBoardVO> getListAllBoard() throws Exception;

	// 게시글을 상세 조회하는 메서드(조회수를 올려야할지 말아야할지 검사하여 조회수 업 하는 기능까지)
	Map<String, Object> getBoardByBoardNo(int lecNo, String user) throws Exception;
	
}