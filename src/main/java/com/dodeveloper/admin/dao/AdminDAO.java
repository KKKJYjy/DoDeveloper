package com.dodeveloper.admin.dao;

import java.util.List;


import com.dodeveloper.study.vodto.StudyBoardVO;

public interface AdminDAO {
	List<StudyBoardVO> selectlistStudyBoard() throws Exception;
}

