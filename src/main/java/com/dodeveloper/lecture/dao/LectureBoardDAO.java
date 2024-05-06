package com.dodeveloper.lecture.dao;

import java.util.List;


import com.dodeveloper.lecture.vodto.LectureBoardDTO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.lecture.vodto.LectureSearchDTO;

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
	
	// 게시판에 새로운 글을 insert하는 메서드
	int insertNewLectureBoard(LectureBoardDTO newLecBoard) throws Exception;
	
	// 실제 게시글을 수정하는 메서드
	int updateLectureBoard(LectureBoardDTO modifyBoard) throws Exception;
	
	// lecNo번 게시글 삭제 처리하는 메서드
	int deleteLectureBoard(int lecNo) throws Exception;
	
	// 검색어가 있을 경우 검색된 글의 갯수를 가져오는 메서드 - 검색조건
	int lectureBoardCntWithSc(LectureSearchDTO lsDTO) throws Exception;
	
	// 검색어가 있을 경우 검색된 글을 가져오는 메서드 - 검색조건
	List<LectureBoardVO> lectureBoardListWithSc(LectureSearchDTO lsDTO) throws Exception;
	
	// 검색 필터(최신순 / 인기순 / 조회순)을 선택했을 때 글을 가져오는 메서드 - 검색 필터
	List<LectureBoardVO> listAllBoardByFilter(List<LectureBoardVO> lectureBoardList, String filterType) throws Exception;
	
	
}
