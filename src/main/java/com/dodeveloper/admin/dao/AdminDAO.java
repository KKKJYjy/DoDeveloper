package com.dodeveloper.admin.dao;


import java.util.List;

import com.dodeveloper.admin.vo.BadMemberBoardVO;
import com.dodeveloper.study.vodto.StudyBoardVO;

public interface AdminDAO {

	List<StudyBoardVO> selectlistStudyBoard() throws Exception;
	
	List<BadMemberBoardVO> selectListBadMemberBoard() throws Exception;
	
}
