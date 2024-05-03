package com.dodeveloper.company.service;

import java.util.List;

import com.dodeveloper.company.vodto.CompanyInfoVO;

public interface CompanyInfoService {
	// 기업 정보 전체 조회하는 메서드
	List<CompanyInfoVO> getEntireCompanyInfo() throws Exception;
}
