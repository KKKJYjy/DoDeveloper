package com.dodeveloper.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/adminView")
public class AdminViewDetaileController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminViewDetaileController.class);
	
	@RequestMapping(value = "/viewStudyBoard", method = RequestMethod.GET)
	public void viewStudyBoard() {
		logger.info("상세페이지 호출");
	}
	
	@RequestMapping(value = "/viewBoard", method = RequestMethod.GET)
	public void viewBoard() {
		logger.info("강의추천 상세페이지 호출");
	}
	
	@RequestMapping(value = "/algDetail", method = RequestMethod.GET)
	public void algDetail() {
		logger.info("알고리즘 상세페이지 호출");
	}
	
	@RequestMapping(value = "/reviewDetails", method = RequestMethod.GET)
	public void reviewDetails() {
		logger.info("리뷰게시판 상세페이지 호출");
	}
}
