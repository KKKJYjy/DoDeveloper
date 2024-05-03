package com.dodeveloper.lecture.dao;

import java.util.List;

import com.dodeveloper.lecture.vodto.LectureBoardVO;

public interface LectureBoardDAO {
	
	// 게시판의 글을 조회하는 메서드
	List<LectureBoardVO> selectListAllLecBoard() throws Exception;
}
