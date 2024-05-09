package com.dodeveloper.admin.controller;

import java.util.List;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.dodeveloper.admin.service.AdminService;
import com.dodeveloper.admin.vo.AdminVO;
import com.dodeveloper.admin.vo.BadMemberBoardVO;
import com.dodeveloper.member.vo.MemberVO;


@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService aService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String index() {
		logger.info("dashboard 페이지 호출");

		return "/admin/dashboard";

	}

	@RequestMapping(value = "/inquiry", method = RequestMethod.GET)
	public void inquiry(Model model) throws Exception {
		logger.info("불량 회원 게시글 페이지 호출");
		
		List<BadMemberBoardVO> bedMemberBoardList = aService.getListBadMemberBoard();
		
		model.addAttribute("bedMemberBoardList", bedMemberBoardList);

	}

	@RequestMapping(value = "/blank", method = RequestMethod.GET)
	public void blank() {
		logger.info("blank 페이지 호출");

	}

	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public void table(Model model)  {
		logger.info("table 호출");

		
	}
	
	@RequestMapping(value = "/selectBoard", method = RequestMethod.GET)
	public void selectBoard(Model model) throws Exception {
		logger.info("게시판 조회 페이지");
		
		List<AdminVO> stuBoardList = aService.getlistStudyBoard();

		model.addAttribute("stuBoardList", stuBoardList);
	}

   @RequestMapping(value="/userList")
   public void userList(Model model) throws Exception {
	   logger.info("userList 페이지 호출");
	   
	   List<MemberVO> returnMap = null;
	   
	   returnMap = aService.getAllUser();
	   
	   
	   
	   
   }
   
   

}
