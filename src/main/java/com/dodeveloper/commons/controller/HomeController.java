package com.dodeveloper.commons.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dodeveloper.study.service.StudyService;
import com.dodeveloper.study.vodto.StudyBoardVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private StudyService ss;

	/**
		* @author : yeonju
		* @date : 2024. 6. 9.
		* @return : String
		* @description : 스터디, 강의추천, 기업리뷰, 알고리즘 상위 5개글을 index 페이지에 출력
	 */
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("dodeveloper.com의 index가 호출.", locale);
		
		//스터디 모임글 상위 5개글 가져오기
		List<StudyBoardVO> studyList = null;
		
		try {
			studyList = ss.getStudyTop5();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		model.addAttribute("studyList", studyList);

		return "index"; // index.jsp 호출
	}

}
