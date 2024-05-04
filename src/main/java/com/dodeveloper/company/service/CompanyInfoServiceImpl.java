package com.dodeveloper.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.company.dao.CompanyInfoDAO;
import com.dodeveloper.company.vodto.CompanyInfoVO;

/**
 * @packageName : com.dodeveloper.company.service
 * @fileName : CompanyInfoServiceImpl.java 	
 * @author kimso05
 * @date : 2024.05.03
 * @description : 
 * 게시판 서비스단(비즈니스 로직에 의해 트랜잭션 처리를 하며, DAO단을 호출하고, DAO에서 넘겨받는 데이터를 Controller단으로 반환)
 * 서비스단에서 해야 할 일
 * 1) 기업 정보 전체 보기 -> 해당 기업 클릭하면 리뷰 볼 수 있도록 한다 -> 리뷰 작성 
 * 2) 비즈니스 로직 처리 하려면 -> 트랜잭션 관리
 * 3) dao단 호출 
 * 4) dao단에서 넘겨받은 결과를 다시 Controller단으로 반환
 */
@Service // 아래의 클래스가 서비스 객체임을 명시해주는 어노테이션
public class CompanyInfoServiceImpl implements CompanyInfoService {
	
	@Autowired // root-context.xml 객체의 '타입'에 해당하는 bean을 찾아 의존 주입(CompanyInfoDAO)하는 어노테이션 
	private CompanyInfoDAO ciDao;  

	
	/**
	 * @methodName : getEntireCompanyInfo
	 * @author : kimso05
	 * @date : 2024.05.03
	 * @return : List<CompanyInfoVO> : 기업 정보 전체 게시글 조회
	 * @throws Exception 
	 * @description : 기업 정보 전체 조회에 대한 서비스 메서드
	 */
	@Override
	public List<CompanyInfoVO> getEntireCompanyInfo() throws Exception {
		System.out.println("서비스단 : 기업정보 전체 조회!");
		
		// DAO단 호출 (selectEntireCompanyInfo()) 
		// static 하지 않으니까 객체명.메서드 로 호출해야함 
		// 스프링에서의 서비스단에는 request, response객체가 없기 때문에 예외처리 해줘야함
		List<CompanyInfoVO> ciList = ciDao.selectEntireCompanyInfo(); 
		
		return ciList;
	}

}