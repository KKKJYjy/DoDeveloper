package com.dodeveloper.admin.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AdminController {
private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */


	@RequestMapping(value = "/dashBoard", method = RequestMethod.GET)
	public void index() {
		logger.info("index페이지 호출");
		
		
		
		
	}

	
	
}
