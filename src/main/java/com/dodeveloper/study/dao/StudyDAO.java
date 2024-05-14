package com.dodeveloper.study.dao;

import java.util.List;

import com.dodeveloper.study.vodto.StuStackVO;
import com.dodeveloper.study.vodto.StudyBoardDTO;
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.study.etc.PagingInfo;
import com.dodeveloper.study.vodto.SearchStudyDTO;
import com.dodeveloper.study.vodto.StackVO;
import com.dodeveloper.study.vodto.StuStackDTO;
import com.dodeveloper.study.vodto.StuStackModifyDTO;

public interface StudyDAO {
	
	//모든 스터디 리스트를 가져오는 메서드 + 페이징 객체
	List<StudyBoardVO> selectAllList(PagingInfo pi) throws Exception;

	//stuNo번째 스터디 언어를 가져오는 메서드
	List<StuStackDTO> selectAllStudyStack(int stuNo);

	//다음 stuNo를 가져오는 메서드
	int selectNextStuNo() throws Exception;

	//stuBoardNo번째 스터디 언어를 insert
	int insertNewStack(int stuBoardNo, int chooseStack) throws Exception;

	//새로운 스터디글을 insert
	int insertNewStudy(StudyBoardDTO newStudyDTO)throws Exception;

	//stuNo번째 스터디 글을 가져오는 메서드
	StudyBoardVO selectStudyByStuNo(int stuNo)throws Exception;

	//검색어가 있을 경우 해당되는 스터디 리스트를 가져오는 메서드 + 페이징 객체
	List<StudyBoardVO> selectAllListWithsDTO(SearchStudyDTO sDTO, PagingInfo pi) throws Exception;

	//stack테이블의 모든 리스트를 가져오는 메서드
	List<StackVO> selectAllStack() throws Exception;

	//stuNo번째 글을 삭제하는 메서드
	int deleteStudyBoard(int stuNo) throws Exception;

	//stuNo번째 글을 수정하는 메서드
	int modifyStudy(StudyBoardDTO modifyStudy) throws Exception;

	//stuNo번째 스터디 언어를 수정하는 메서드
	int modifyStack(int stuStackNo, int chooseStack) throws Exception;

	//stuNo번째 스터디 언어를 삭제하는 메서드
	int deleteStudyStack(int stuStackNo) throws Exception;

	//전체 게시글 갯수롤 가져오는 메서드
	int selectTotalBoardCnt() throws Exception;

	//전체 게시글 갯수롤 가져오는 메서드 + 검색어가 있을때	
	int selectTotalBoardCntWithSdto(SearchStudyDTO sDTO) throws Exception;




}
