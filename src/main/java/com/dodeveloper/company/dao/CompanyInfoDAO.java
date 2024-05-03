package com.dodeveloper.company.dao;

import java.util.List;

import com.dodeveloper.company.vodto.CompanyInfoVO;

public interface CompanyInfoDAO {
	// 기업 정보 전체 조회하는 메서드
	List<CompanyInfoVO> selectEntireCompanyInfo() throws Exception;
}
