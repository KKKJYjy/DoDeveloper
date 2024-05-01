package com.dodeveloper.study.dao;

import java.util.List;

import com.dodeveloper.study.vodto.StudyBoardVO;

public interface StudyDAO {

	List<StudyBoardVO> selectAllList() throws Exception;

}
