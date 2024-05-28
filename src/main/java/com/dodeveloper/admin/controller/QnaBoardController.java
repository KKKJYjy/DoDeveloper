package com.dodeveloper.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodeveloper.admin.service.AdminBoardService;
import com.dodeveloper.admin.vo.QnaBoardVO;

@Controller
@RequestMapping("/qna")
public class QnaBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminBoardController.class);

	@Autowired
	private AdminBoardService bService;
	
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void qnaBoard(Model model) throws Exception {
		logger.info("list 페이지 호출");

		List<QnaBoardVO> qnaBoardList = bService.getQnaBoard();
		
		model.addAttribute("qnaBoardList", qnaBoardList);

	}
	
	
	@RequestMapping(value = "/viewBoard", method = RequestMethod.GET)
	public void qnaViewBoard(Model model, @RequestParam("no") int no) throws Exception {
		
		logger.info("문의 상세페이지");
		
		QnaBoardVO qnaView = bService.getQnaBoardNo(no);
		
		model.addAttribute("qnaView", qnaView);
		
	}

}
