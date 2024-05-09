package com.dodeveloper.admin.controller;



import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dodeveloper.admin.service.AdminService;
import com.dodeveloper.member.vo.MemberVO;


@Controller
@RequestMapping("/admin")
public class AdminController {
private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
   
	@Autowired
	private AdminService aService; // adminService 객체 주입

   /**
    * Simply selects the home view to render by returning its name.
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
   public String index() {
      logger.info("dashboard 페이지 호출");
      
      
      return "/admin/dashboard";
      
   }
   
   @RequestMapping(value = "/inquiry", method = RequestMethod.GET)
   public String inquiry() {
      logger.info("inquiry 페이지 호출");
   
      return "/admin/inquiry";
   }

   @RequestMapping(value = "/blank", method = RequestMethod.GET)
   public String blank() {
      logger.info("blank 페이지 호출");
      
      return "/admin/blank";
   }
   
   @RequestMapping(value="/userList")
   public void userList(Model model) throws Exception {
	   logger.info("userList 페이지 호출");
	   
	   List<MemberVO> returnMap = null;
	   
	   returnMap = aService.getAllUser();
	   
	   
	   
	   
   }
   
   
}