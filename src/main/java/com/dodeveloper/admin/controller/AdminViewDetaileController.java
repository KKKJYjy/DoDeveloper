package com.dodeveloper.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodeveloper.admin.dto.NoticeDTO;
import com.dodeveloper.admin.service.AdminBoardService;

@Controller
@RequestMapping("/adminView")
public class AdminViewDetaileController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminViewDetaileController.class);
	
	@Autowired
	private AdminBoardService bService;
	
	@RequestMapping(value = "/noticViewDetail", method = RequestMethod.GET)
	public void noticeDetail(Model model, @RequestParam("boardNo") int boardNo) throws Exception {
		
		logger.info(boardNo + "번글 조회");
		
		NoticeDTO notice = bService.getNotcBoardNo(boardNo);
		
		model.addAttribute("notice", notice);
	}
	
//	@RequestMapping(value = "/viewBoard", method = RequestMethod.GET)
//	public void viewBoard() {
//		logger.info("강의추천 상세페이지 호출");
//	}	
//	
//	@RequestMapping(value = "/algDetail", method = RequestMethod.GET)
//	public void algDetail() {
//		logger.info("알고리즘 상세페이지 호출");
//	}
//	
//	@RequestMapping(value = "/reviewDetails", method = RequestMethod.GET)
//	public void reviewDetails() {
//		logger.info("리뷰게시판 상세페이지 호출");
//	}
}
