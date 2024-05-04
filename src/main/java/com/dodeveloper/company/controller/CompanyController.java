package com.dodeveloper.company.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodeveloper.company.service.CompanyInfoService;
import com.dodeveloper.company.vodto.CompanyInfoVO;


/**
 * @packageName : com.dodeveloper.company.controller
 * @fileName : CompanyController.java 	
 * @author kimso05
 * @date : 2024.05.02
 * @description : 기업 정보 전체 조회 기능과 관련된 Controller단의 객체
 * Controller단에서 해야 할 일 :
 * 1) View에서 보내준 파라메터(유효성 검사 할것이 있다면) 수집 -> 처리 -> (파일->파일 처리)
 * 2) 서비스단 호출 (서비스 => DAO => DB => 서비스 => 컨트롤러)
 * 3) 서비스에서 넘겨받은 데이터가 있다면 바인딩 (바인딩은 Model객체가 필요함)
 * 4) view페이지로 포워딩, 리다이렉트 해야함
 * 
 */
@Controller // CompanyController: 컨트롤러 객체임을 명시
@RequestMapping("/companyInfo") // "/companyInfo" GET방식으로 요청 
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	private CompanyInfoService ciService; // CompanyInfoService객체를 찾아 주입

	/**
	 * @methodName : companyListAll
	 * @author : kimso05
	 * @date : 2024.05.02
	 * @param : Model : view단으로 옮겨주는 객체
	 * @param : 
	 * @param : 
	 * @return : String
	 * @throws Exception 
	 * @description : 기업 정보 전체 조회를 담당하는 컨트롤러 메서드
	 */
	@GetMapping(value = "/entire") 
	public String companyListAll(Model model) throws Exception {
		logger.info("기업리스트를 모두 보여주자");
		
		// 서비스단 호출(getEntireCompanyInfo()) : 서비스단에서 예외처리 날 수 있으니까 컨트롤러단에도 예외터지는건 당연함
		List<CompanyInfoVO> ciList = ciService.getEntireCompanyInfo();
		
		model.addAttribute("ciList", ciList); // 바인딩시킴  
		// ciList : listAll.jsp로 간다~
		
		return "companyInfo/listAll"; //컨트롤러의 메서드 반환값이 void일때는 매핑된 uri이름과 같은 이름의 jsp로 포워딩 된다! 
	}

}