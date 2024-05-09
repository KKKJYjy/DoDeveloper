package com.dodeveloper.company.service;

import java.util.List;

import com.dodeveloper.company.vodto.CompanyInfoVO;
import com.dodeveloper.company.vodto.RevCompanyBoardVO;

public interface CompanyInfoService {
	// 기업 정보 전체 조회하는 메서드
	List<CompanyInfoVO> getEntireCompanyInfo() throws Exception;
	
	// 클릭한 기업 리뷰 조회하는 메서드
	List<RevCompanyBoardVO> getCompanyInfoRev(int companyInfoNo) throws Exception;
}
