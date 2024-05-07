package com.dodeveloper.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.admin.dao.AdminDAO;
import com.dodeveloper.admin.vo.BadMemberBoardVO;
import com.dodeveloper.study.vodto.StudyBoardVO;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO aDao;
	
	@Override
	public List<StudyBoardVO> getlistStudyBoard() throws Exception {
		
		System.out.println("서비스단 : study게시물 조회");
		
		List<StudyBoardVO> studyBoardList = aDao.selectlistStudyBoard();
		
		return studyBoardList;
	}

	@Override
	public List<BadMemberBoardVO> getListBadMemberBoard() throws Exception {
		
		System.out.println("서비스단 : 불량 회원 게시물 조회");
		
		List<BadMemberBoardVO> badMemberList = aDao.selectListBadMemberBoard();
	
		return badMemberList;
	}

}

