package com.dodeveloper.lecture.dao;

import java.util.List;

import com.dodeveloper.lecture.vodto.LectureBoardVO;

public interface LectureBoardDAO {
	
	// 게시판의 글을 조회하는 메서드
	List<LectureBoardVO> selectListAllLecBoard() throws Exception;

	// ?번 글을 가져오는 메서드
	LectureBoardVO selectBoardLecNo(int lecNo) throws Exception;
	
	// ?번 글을 ?유저가 조회했다는 이력을 기록하는 메서드
	int insertReadCountProcess(String user, int lecNo) throws Exception;
	
	// 유저가 ?번 글을 언제 읽었는지 select하는 메서드
	int selectDiff(String user, int lecNo) throws Exception;
	
	// ?번 글의 조회수를 증가하는 메서드
	int updateReadCount(int lecNo) throws Exception;
	
}
