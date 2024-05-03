package com.dodeveloper.admin.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/admin")
public class AdminController {
private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
 	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String index() {
		logger.info("dashboard 페이지 호출");
		
		
		return "/admin/dashboard";
		
	}
	
	@RequestMapping(value = "/inquiry", method = RequestMethod.GET)
	public void inquiry() {
		logger.info("inquiry 페이지 호출");
	
	
	}

	@RequestMapping(value = "/blank", method = RequestMethod.GET)
	public void blank() {
		logger.info("blank 페이지 호출");
		
	}
	
	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public void table() {
		logger.info("table 페이지 호출");
	}
	
	
	
}
