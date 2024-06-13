package com.dodeveloper.company.service;

import java.util.List;

import com.dodeveloper.company.vodto.CompanyInfoVO;
import com.dodeveloper.company.vodto.CompanyInfoWithRevVO;
import com.dodeveloper.company.vodto.RevCompanyBoardVO;
import com.dodeveloper.company.vodto.ScrapRevJoinVO;
import com.dodeveloper.company.vodto.ScrapVO;
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
	int revEditWrittenBoard(RevCompanyBoardVO newEditWrittenBoard) throws Exception;

	
	// 유저가 어떤 게시판의 ?번 글을 스크랩 하는 메서드 (insert)
	int insertScrap(int scrapBoard, String scrapId, int bType) throws Exception;

	
	// 기업 리뷰 글 관련 스크랩 
	List<ScrapRevJoinVO> selectAllScrap(String scrapId) throws Exception;

	
	// 어떤 유저가 해당 스크랩한 게시글들만 전체 볼 수 있는 메서드 (select)
	List<ScrapVO> selectScrap(String scrapId, int bType) throws Exception;

	
	// 유저가 ?번 스크랩 글을 취소하는 메서드 (delete)
	int deleteScrap(int scrapNo) throws Exception;


	// 최근 5개의 기업 정보 리스트를 불러오는 메서드
	List<CompanyInfoWithRevVO> getCompanyTop5() throws Exception;

}
