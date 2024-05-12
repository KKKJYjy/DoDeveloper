package com.dodeveloper.admin.dao;

import java.util.List;

import com.dodeveloper.admin.etc.PagingInfo;
import com.dodeveloper.admin.vo.AdminArgBoardVO;
import com.dodeveloper.admin.vo.AdminLectureVO;
import com.dodeveloper.admin.vo.AdminReviewBoardVO;
import com.dodeveloper.admin.vo.AdminVO;

public interface AdminBoardDAO {
	// 스터디게시판 조회 메서드
	List<AdminVO> selectlistStuBoard(PagingInfo pi) throws Exception;

	// 강의게시판 조회 메서드
	List<AdminLectureVO> selectListLecBoard() throws Exception;

	// 알고리즘게시판 조회 메서드
	List<AdminArgBoardVO> selectListArgBoard() throws Exception;

	// review게시판 조회 메서드
	List<AdminReviewBoardVO> selectListRevBoard() throws Exception;

	// 게시글 전체 갯수 구할 수 있는 메서드
	int selectTotalBoardCnt() throws Exception;

}
