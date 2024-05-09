package com.dodeveloper.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/adminView")
public class AdminViewDetaileController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminViewDetaileController.class);
	
	@RequestMapping(value = "/viewDetails", method = RequestMethod.GET)
	public void viewDetails() {
		logger.info("상세페이지 호출");
	}
	
	@RequestMapping(value = "/lectureDetails", method = RequestMethod.GET)
	public void lectureDetails() {
		logger.info("강의추천 상세페이지 호출");
	}
	
	@RequestMapping(value = "/argDetails", method = RequestMethod.GET)
	public void argDetails() {
		logger.info("알고리즘 상세페이지 호출");
	}
}
