package com.dodeveloper.company.dao;

import java.util.List;

import com.dodeveloper.company.vodto.CompanyInfoVO;
import com.dodeveloper.company.vodto.RevCompanyBoardVO;
import com.dodeveloper.company.vodto.WrittenCompanyBoardDTO;

public interface CompanyInfoDAO {
	// 기업 정보 전체 조회하는 메서드
	List<CompanyInfoVO> selectEntireCompanyInfo() throws Exception;
	
	// 클릭한 기업 정보 조회하는 메서드
	List<RevCompanyBoardVO> selectCompanyInfoRev(int companyInfoNo) throws Exception;
	
	// 기업 리뷰 글을 저장하는 메서드 (insert)
	int insertRevWrittenBoard(WrittenCompanyBoardDTO newWrittenCompanyBoard) throws Exception;
}

