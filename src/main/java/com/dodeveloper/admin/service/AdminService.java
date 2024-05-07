package com.dodeveloper.admin.service;

import java.util.List;

import com.dodeveloper.admin.vo.BadMemberBoardVO;
import com.dodeveloper.study.vodto.StudyBoardVO;

public interface AdminService {
	
	List<StudyBoardVO> getlistStudyBoard() throws Exception;
	
	List<BadMemberBoardVO> getListBadMemberBoard() throws Exception;
}
