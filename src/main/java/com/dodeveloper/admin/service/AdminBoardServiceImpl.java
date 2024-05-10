package com.dodeveloper.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.admin.dao.AdminBoardDAO;
import com.dodeveloper.admin.vo.AdminArgBoardVO;
import com.dodeveloper.admin.vo.AdminLectureVO;
import com.dodeveloper.admin.vo.AdminReviewBoardVO;
import com.dodeveloper.admin.vo.AdminVO;

@Service
public class AdminBoardServiceImpl implements AdminBoardService {
	
	
	@Autowired
	private AdminBoardDAO bDao;
	

	
	@Override
	public List<AdminVO> getlistStudyBoard() throws Exception {
		
		System.out.println("서비스단 : study게시물 조회");
		
		List<AdminVO> stuBoardList = bDao.selectlistStuBoard();
		
		return stuBoardList;
	}

	@Override
	public List<AdminLectureVO> getlistLectureBoard() throws Exception {
		
		System.out.println("서비스단 : lecture게시물 조회");
		
		List<AdminLectureVO> lecBoardList = bDao.selectListLecBoard();
		
		return lecBoardList;
	}

	@Override
	public List<AdminArgBoardVO> getlistArgBoard() throws Exception {
		
		System.out.println("서비스단 : 알고리즘 게시물 조회");
		
		List<AdminArgBoardVO> argBoardList = bDao.selectListArgBoard();
		
		return argBoardList;
	}

	@Override
	public List<AdminReviewBoardVO> getlistRevBoard() throws Exception {
		
		System.out.println("서비스단 : review게시물 조회");
		
		List<AdminReviewBoardVO> revBoardList = bDao.selectListRevBoard();
		
		return revBoardList;
	}
	
	
}
