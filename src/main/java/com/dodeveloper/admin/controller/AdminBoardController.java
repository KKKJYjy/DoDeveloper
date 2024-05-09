package com.dodeveloper.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dodeveloper.admin.service.AdminBoardService;
import com.dodeveloper.admin.vo.AdminArgBoardVO;
import com.dodeveloper.admin.vo.AdminLectureVO;
import com.dodeveloper.admin.vo.AdminVO;

@Controller
@RequestMapping("/admin")
public class AdminBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminBoardController.class);

	@Autowired
	private AdminBoardService bService;
	
	
	
	
	@RequestMapping(value = "/selectBoard", method = RequestMethod.GET)
	public void selectBoard(Model model) throws Exception {
		logger.info("study게시판 조회 페이지");
		
		List<AdminVO> stuBoardList = bService.getlistStudyBoard();

		model.addAttribute("stuBoardList", stuBoardList);
	}
	
	@RequestMapping(value = "/lectureBoard", method = RequestMethod.GET)
	public void lectureBoard(Model model) throws Exception {
		logger.info("lectureBoard 조회");
		
		List<AdminLectureVO> lecBoardList = bService.getlistLectureBoard();
		
		model.addAttribute("lecBoardList", lecBoardList);
	}
	
	@RequestMapping(value = "/algorithmBoard", method = RequestMethod.GET)
	public void algorithmBoard(Model model) throws Exception {
		logger.info("algorithmBoard 조회");
		
		List<AdminArgBoardVO> argBoardList = bService.getlistArgBoard();
		
		model.addAttribute("argBoardList", argBoardList);
	}
	
	@RequestMapping(value = "/reviewBoard", method = RequestMethod.GET)
	public void reviewBoard() {
		logger.info("reviewBoard 조회");
	}
	

}
