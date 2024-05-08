package com.dodeveloper.admin.dao;


import java.util.List;

import com.dodeveloper.admin.vo.AdminVO;
import com.dodeveloper.admin.vo.BadMemberBoardVO;

public interface AdminDAO {

	List<AdminVO> selectlistStuBoard() throws Exception;
	
	List<BadMemberBoardVO> selectListBadMemberBoard() throws Exception;
	
}

