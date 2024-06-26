package com.dodeveloper.company.dao;

import java.util.List;

import com.dodeveloper.company.vodto.CompanyInfoVO;
import com.dodeveloper.company.vodto.CompanyInfoWithRevVO;
import com.dodeveloper.company.vodto.RevCompanyBoardVO;
import com.dodeveloper.company.vodto.ScrapVO;
import com.dodeveloper.company.vodto.WrittenCompanyBoardDTO;

public interface CompanyInfoDAO {
	
	// 기업 정보 전체 조회하는 메서드
	List<CompanyInfoVO> selectEntireCompanyInfo() throws Exception;

	// 클릭한 기업 정보 조회하는 메서드
	List<RevCompanyBoardVO> selectCompanyInfoRev(int companyInfoNo) throws Exception;

	// 기업 리뷰 글을 저장하는 메서드 (insert)
	int insertRevWrittenBoard(WrittenCompanyBoardDTO newWrittenCompanyBoard) throws Exception;

	// 기업 리뷰 글을 삭제하는 메서드 (delete)
	int deleteWrittenBoard(int revNo) throws Exception;

	// 기업 리뷰 글을 얻어오는 메서드 (select) :
	// 기업리뷰게시글번호(revNo)의 리뷰글(RevCompanyBoardVO) 객체 하나 반환
	RevCompanyBoardVO selectEditWrittenBoard(int revNo) throws Exception;

	// 기업 리뷰 글을 수정하는 메서드
	int updateEditWrittenBoard(RevCompanyBoardVO newEditWrittenBoard) throws Exception;

	// 유저가 어떤 게시판의 ?번 글을 스크랩 하는 메서드 (insert)
	int insertScrap(int scrapBoard, String scrapId, int bType) throws Exception;

	// 최근 5개의 기업 정보 리스트를 불러오는 메서드
	List<CompanyInfoWithRevVO> getCompanyTop5() throws Exception;

}
