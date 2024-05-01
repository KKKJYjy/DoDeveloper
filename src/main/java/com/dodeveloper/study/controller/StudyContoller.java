package com.dodeveloper.study.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study")
public class StudyContoller {
	
	private static final Logger logger = LoggerFactory.getLogger(StudyContoller.class);
	
	@GetMapping(value = "/listAll")
	public void listAllGet() {
		logger.info("listAll View.");
	}
}
