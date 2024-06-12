package com.dodeveloper.company.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dodeveloper.company.service.CompanyInfoService;
import com.dodeveloper.company.vodto.ScrapRevJoinVO;
import com.dodeveloper.company.vodto.ScrapVO;


@RestController
@RequestMapping("/scrap")
public class ScrapController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScrapController.class);
	
	@Autowired
	private CompanyInfoService ciService; 
	
	
	/**
	 * @methodName : selectAllScrap
	 * @author : kimso05
	 * @date : 2024.06.03
	 * @param : String scrapId : 스크랩한 사람 
	 * @param : ResponseEntity<List<ScrapVO>> : 강의,스터디,기업리뷰,알고리즘 등 스크랩한 목록들을 볼 수 있다.
	 * @return : ResponseEntity<String>
	 * @throws Exception 
	 * @description :  
	 */
	@RequestMapping(value="/all/{scrapId}", method = RequestMethod.GET)
	public ResponseEntity<List<ScrapRevJoinVO>> selectAllScrap(@PathVariable("scrapId") String scrapId) {
		
		System.out.println(scrapId + "스크랩 확인!!!");
		
		ResponseEntity<List<ScrapRevJoinVO>> result = null;
		
		// scrapId를 서비스단으로 보내고 DAO단으로 보내서
		// 해당 유저의 스크랩 목록을 가져와 json으로 반환해준다.
		try {
			List<ScrapRevJoinVO> lst = ciService.selectAllScrap(scrapId);
			
			result = new ResponseEntity<List<ScrapRevJoinVO>>(lst, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			
			result = new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		return result;
	}
	
}

