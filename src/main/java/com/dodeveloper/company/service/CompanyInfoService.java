package com.dodeveloper.company.service;

import java.util.List;

import com.dodeveloper.company.vodto.CompanyInfoVO;
import com.dodeveloper.company.vodto.RevCompanyBoardVO;
import com.dodeveloper.company.vodto.WrittenCompanyBoardDTO;

public interface CompanyInfoService {
	// 기업 정보 전체 조회하는 메서드
	List<CompanyInfoVO> getEntireCompanyInfo() throws Exception;
	
	// 클릭한 기업 리뷰 조회하는 메서드
	List<RevCompanyBoardVO> getCompanyInfoRev(int companyInfoNo) throws Exception;
	
	// 기업 리뷰 게시글을 저장하는 메서드
	int writeCompanyBoardService(WrittenCompanyBoardDTO newWrittenCompanyBoard) throws Exception;
	
	// 기업 리뷰 게시글을 삭제하는 메서드
	int deleteWrittenBoard(int revNo) throws Exception;
	
	// 기업 리뷰 글을 얻어오는 메서드
	RevCompanyBoardVO editWrittenBoard(int revNo) throws Exception;
	
	// 기업 리뷰 글을 수정하는 메서드
	int RevEditWrittenBoard(RevCompanyBoardVO newEditWrittenBoard) throws Exception;
	
}
