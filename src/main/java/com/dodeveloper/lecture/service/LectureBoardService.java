package com.dodeveloper.lecture.service;

import java.util.List;
import java.util.Map;

import com.dodeveloper.lecture.vodto.LectureBoardDTO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.lecture.vodto.LectureSearchDTO;

public interface LectureBoardService {

	// 게시판의 lecNo번 페이지의 글을 조회하는 메서드
	Map<String, Object> getListAllBoard(int pageNo, LectureSearchDTO lsDTO) throws Exception;
	
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
	
	// 게시글에 좋아요(눌려있는지/안눌려있는지) 체크하는 메서드
	boolean checkLikeStatus(int lecNo, String user) throws Exception;
	
	// 게시글에 좋아요 버튼 눌렀을 경우 - 좋아요 갯수 1개 update (전체 게시글에 보여주기)
	boolean likeUpBoard(int lecNo, String user) throws Exception;
	
	// 게시글에 좋아요 버튼 한번 더 눌렀을 경우 - 좋아요 갯수 1개(down) update (전체 게시글에 보여주기)
	boolean likeDownBoard(int lecNo, String user) throws Exception;

	// 최신 5개 강의 게시글을 얻어오는 메서드
	List<LectureBoardVO> getLectureTo5() throws Exception;
	
}
