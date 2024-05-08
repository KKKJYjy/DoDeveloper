package com.dodeveloper.admin.service;

import java.util.List;

import com.dodeveloper.admin.vo.AdminVO;
import com.dodeveloper.admin.vo.BadMemberBoardVO;


public interface AdminService {
	
	List<AdminVO> getlistStudyBoard() throws Exception;
	
	List<BadMemberBoardVO> getListBadMemberBoard() throws Exception;
}
