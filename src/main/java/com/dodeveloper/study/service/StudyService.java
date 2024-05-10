package com.dodeveloper.study.service;

import java.util.Collection;
import java.util.List;

import com.dodeveloper.study.vodto.StuStackVO;
import com.dodeveloper.study.vodto.StudyBoardDTO;
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.study.vodto.SearchStudyDTO;
import com.dodeveloper.study.vodto.StackVO;
import com.dodeveloper.study.vodto.StuStackDTO;

public interface StudyService {
	//전체 게시글을 조회하는 메서드 + 검색 기능 추가
	List<StudyBoardVO> selectAllList(SearchStudyDTO sDTO) throws Exception;

	//stuNo번째 스터디 언어를 가져오는 메서드
	List<StuStackDTO> selectAllStudyStack(int stuNo) throws Exception;

	//다음 게시글 번호를 얻어오는 메서드
	int selectNextStuNo() throws Exception;

	//새로운 스터디글 + 스터디언어를 insert하는 메서드
	int insertStudyWithStack(StudyBoardDTO newStudy, StuStackVO newStack) throws Exception;

	//stuNo번째 스터디 글을 조회하는 메서드
	StudyBoardVO selectStudyByStuNo(int stuNo) throws Exception;

	//stack테이블의 전체 데이터를 조회하는 메서드
	List<StackVO> selectAllStack() throws Exception;
	
	//stuNo번째 글을 삭제하는 메서드
	int deleteStudyBoard(int stuNo) throws Exception;

	//stuNo번째 글과 스택 테이블을 수정하는 메서드
	int modifyStudy(StudyBoardDTO newStudy, StuStackVO modifyStackVO) throws Exception;

	
}
