package com.dodeveloper.lecture.service;

import java.util.List;

import java.util.Map;

import com.dodeveloper.lecture.vodto.LectureBoardDTO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.lecture.vodto.LectureSearchDTO;

public interface LectureBoardService {

	// 게시판의 lecNo번 페이지의 글을 조회하는 메서드
	List<LectureBoardVO> getListAllBoard(int lecNo) throws Exception;
	
	// 게시글을 상세 조회하는 메서드(조회수를 올려야할지 말아야할지 검사하여 조회수 업 하는 기능까지)
	Map<String, Object> getBoardByBoardNo(int lecNo, String user) throws Exception;

	// 게시물을 얻어오는 메서드(글 수정시 글을 가져온다.)
	Map<String, Object> getBoardByBoardNo(int lecNo) throws Exception;

	// 게시글을 저장하는 메서드
	boolean writeBoardService(LectureBoardDTO newLecBoard) throws Exception;

	// 게시글을 수정하는 메서드
	boolean modifyBoard(LectureBoardDTO modifyBoard) throws Exception;

	// 게시글을 삭제 처리하는 메서드
	boolean deleteLectureBoard(int lecNo) throws Exception;
	
	// 검색 조건을 선택하고 검색어를 입력했을 때 글을 가져오는 메서드 - 검색 조건
	List<LectureBoardVO> listAllBoardBySearch(int lecNo, LectureSearchDTO lsDTO) throws Exception;

	// 검색 필터(최신순 / 인기순 / 조회순)을 선택했을 때 글을 가져오는 메서드 - 검색 필터
	List<LectureBoardVO> listAllBoardByFilter(List<LectureBoardVO> lectureBoardList, String filterType) throws Exception;
	
}
