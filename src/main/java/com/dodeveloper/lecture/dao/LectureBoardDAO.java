package com.dodeveloper.lecture.dao;

import java.util.List;

import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.lecture.vodto.LectureBoardDTO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.lecture.vodto.LectureSearchDTO;

public interface LectureBoardDAO {
	
	// 게시판의 글을 조회하는 메서드
	List<LectureBoardVO> selectListAllLecBoard(PagingInfo pi) throws Exception;

	// ?번 글을 가져오는 메서드
	LectureBoardVO selectBoardLecNo(int lecNo) throws Exception;
	
	// 유저가 ?번 글을 언제 읽었는지 select하는 메서드
	int selectDiff(String user, int lecNo) throws Exception;
	
	// ?번 글의 조회수를 증가하는 메서드
	int updateReadCount(int lecNo) throws Exception;
	
	// ?번 글을 ?유저가 조회했다는 이력을 기록하는 메서드
	int insertReadCountProcess(String user, int lecNo) throws Exception;
	
	// 게시판에 새로운 글을 insert하는 메서드
	int insertNewLectureBoard(LectureBoardDTO newLecBoard) throws Exception;
	
	// 실제 게시글을 수정하는 메서드
	int updateLectureBoard(LectureBoardDTO modifyBoard) throws Exception;
	
	// lecNo번 게시글 삭제 처리하는 메서드
	int deleteLectureBoard(int lecNo) throws Exception;
	
	// 검색어가 없을 경우 게시글 전체 글 갯수를 얻어오는 메서드
	int selectTotalLectureBoardCnt() throws Exception;
	
	// 검색어가 있을 경우 검색된 글의 갯수를 가져오는 메서드 - 검색조건
	int lectureBoardCntWithSc(LectureSearchDTO lsDTO) throws Exception;
	
	// 검색어가 있을 경우 검색된 글을 가져오는 메서드 - 검색조건
	List<LectureBoardVO> lectureBoardListWithSc(LectureSearchDTO lsDTO, PagingInfo pi) throws Exception;
	
	// 검색 필터(최신순 / 인기순 / 조회순)을 선택했을 때 글의 갯수를 가져오는 메서드 - 검색 필터
	int lectureBoardCntFilter(LectureSearchDTO lsDTO) throws Exception;
	
	// 검색 필터(최신순 / 인기순 / 조회순)을 선택했을 때 글을 가져오는 메서드 - 검색 필터
	List<LectureBoardVO> listAllBoardByFilter(LectureSearchDTO lsDTO, PagingInfo pi) throws Exception;
	
	// 게시글에 유저가 좋아요를 눌렀는지 안눌렀는지 확인하는 메서드
	int selectLikeBoard(int lecNo, String user) throws Exception;
	
	// 게시글에 좋아요 처리하는 메서드
	int insertLikeBoard(int lecNo, String user) throws Exception;
	
	// 게시글에 좋아요 버튼 눌렀을 경우 갯수 1개 update (전체 게시글에 보여주기)
	int updateLikeCount(int lecNo) throws Exception;
	
	// 게시글에 좋아요 한 번 더 눌렀을 경우 취소처리하는 메서드
	int deleteLikeBoard(int lecNo, String user) throws Exception;
	
	// 유저가 게시글 하트를 한번 더 눌렀을 경우 좋아요 횟수 down하는 update (전체 게시글에 보여주기)
	int updateLikeDownCount(int lecNo) throws Exception;
	
}
