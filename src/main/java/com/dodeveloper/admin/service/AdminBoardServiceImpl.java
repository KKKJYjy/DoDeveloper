package com.dodeveloper.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.admin.dao.AdminBoardDAO;
import com.dodeveloper.admin.dto.SearchCriteriaDTO;

import com.dodeveloper.admin.vo.AdminArgBoardVO;
import com.dodeveloper.admin.vo.AdminLectureVO;
import com.dodeveloper.admin.vo.AdminReviewBoardVO;
import com.dodeveloper.admin.vo.AdminVO;
import com.dodeveloper.etc.PagingInfo;

@Service
public class AdminBoardServiceImpl implements AdminBoardService {
	
	
	@Autowired
	private AdminBoardDAO bDao;
	
	@Autowired
	private PagingInfo pi;

	
	@Override
	public Map<String, Object> getlistStudyBoard(int pageNo, SearchCriteriaDTO sc) throws Exception {
		
		System.out.println("서비스단 : study게시물 조회");
		
		List<AdminVO> stuBoardList = null;
		
		if (sc.getSearchType() != null && sc.getSearchValue() != null) {
			makePagingInfo(pageNo, sc);
			stuBoardList = bDao.selectBoardListSC(sc, pi);
		} else {
			makePagingInfo(pageNo);
			stuBoardList = bDao.selectlistStuBoard(pi);
		}
		
		
		
		// DAO 단 호출
		// List<AdminVO> stuBoardList = bDao.selectlistStuBoard(pi);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("stuBoardList", stuBoardList);
		returnMap.put("pagingInfo", this.pi);
		
		return returnMap;
	}
	
	private void makePagingInfo(int pageNo, SearchCriteriaDTO sc) throws Exception {
		this.pi.setPageNo(pageNo);
		
		this.pi.setTotalPostCnt(bDao.selectTotalBoardCnt());
		
		// 총 페이지 수
		this.pi.setTotalPageCnt();
		
		// 보여주기 시작할 글의 번호
		this.pi.setStartRowIndex();
		
		
		// 전체 페이지 블럭 갯수
		this.pi.setTotalPageBlockCnt();
		
		// 현재 페이지가 속한 페이징 블럭 번호
		this.pi.setPageBlockOfCurrentPage();
		
		// 현재 페이징 블럭 시작 페이지 번호
		this.pi.setStartNumOfCurrentPagingBlock();
		
		// 현재 페이징 블럭 끝 페이지 번호
		this.pi.setEndNumOfCurrentPagingBlock();
	}
	
	private void makePagingInfo(int pageNo) throws Exception {
		this.pi.setPageNo(pageNo);
		
		// 게시물 데이터 갯수
		this.pi.setTotalPostCnt(bDao.selectTotalBoardCnt());
		
		// 총 페이지 수 
		this.pi.setTotalPageCnt();
		
		// 보여주기 시작할 글 번호
		this.pi.setStartRowIndex();
		
		
		
		// 전체 페이지 블럭 갯수
		this.pi.setTotalPageBlockCnt();
		
		// 현재 페이지가 속한 페이징 블럭 번호
		this.pi.setPageBlockOfCurrentPage();
		
		// 현재 페이징 블럭 시작 페이지 번호
		this.pi.setStartNumOfCurrentPagingBlock();
		
		// 현재 페이징 블럭 끝 페이지 번호
		this.pi.setEndNumOfCurrentPagingBlock();
	}

	@Override
	public Map<String, Object> getlistLectureBoard(int pageNo) throws Exception {
		
		System.out.println("서비스단 : lecture게시물 조회");
		
		makePagingInfo(pageNo);
		
		List<AdminLectureVO> lecBoardList = bDao.selectListLecBoard(pi);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("lecBoardList", lecBoardList);
		returnMap.put("pagingInfo", this.pi);
		
		return returnMap;
	}

	@Override
	public Map<String, Object> getlistArgBoard(int pageNo) throws Exception {
		
		System.out.println("서비스단 : 알고리즘 게시물 조회");
		
		makePagingInfo(pageNo);
		
		List<AdminArgBoardVO> argBoardList = bDao.selectListArgBoard(pi);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("argBoardList", argBoardList);
		returnMap.put("pagingInfo", this.pi);
		
		return returnMap;
	}

	@Override
	public Map<String, Object> getlistRevBoard(int pageNo) throws Exception {
		
		System.out.println("서비스단 : review게시물 조회");
		
		makePagingInfo(pageNo);
		
		List<AdminReviewBoardVO> revBoardList = bDao.selectListRevBoard(pi);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("revBoardList", revBoardList);
		returnMap.put("pagingInfo", this.pi);
		
		
		
		return returnMap;
	}

//	@Override
//	public boolean studeleteBoard(int stuNo) throws Exception {
//		boolean result = false;
//		
//		if(bDao.stuBoardDelete(stuNo) == 1) {
//			result = true;
//		}
//		
//		return result;
//	}

	@Override
	public void studeleteBoard(String stuNo) throws Exception {
		bDao.deleteStu(stuNo);
		
	}
	
	
}
