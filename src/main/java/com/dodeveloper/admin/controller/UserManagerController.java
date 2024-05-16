package com.dodeveloper.admin.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodeveloper.admin.service.AdminService;
import com.dodeveloper.member.vo.MemberVO;

@Controller
@RequestMapping("/admin")
public class UserManagerController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService aService;
	
		// 회원 전체 조회하는 메서드
	   @RequestMapping("/userList")
	   public void userList(Model model) throws Exception {
		   logger.info("userList 페이지 호출");
		   
		   List<MemberVO> returnMap = null;
		   
		   String resultPage = null;
		   
		   returnMap = aService.getAllUser();
		   model.addAttribute("userList", returnMap);
		   
		   resultPage = "redirect:/adminUserlist/userList";
		   
		   
	   }
	   
	   @PostMapping("/status")
	   public void userStatus(@RequestParam("newStatus") String newStatus) {
		   logger.info("status 변경");
	   }
	   
}
