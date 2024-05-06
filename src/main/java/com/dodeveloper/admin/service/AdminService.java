package com.dodeveloper.admin.service;

import java.util.List;


import com.dodeveloper.study.vodto.StudyBoardVO;

public interface AdminService {
	
	List<StudyBoardVO> getlistStudyBoard() throws Exception;
}
