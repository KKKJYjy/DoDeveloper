package com.dodeveloper.study.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodeveloper.study.service.StudyService;
import com.dodeveloper.study.vodto.StudyBoardVO;

@Controller
@RequestMapping("/study")
public class StudyContoller {
	
	private static final Logger logger = LoggerFactory.getLogger(StudyContoller.class);
	
	@Autowired
	StudyService stuService;
	
	@GetMapping(value = "/listAll")
	public void listAllGet(Model model) throws Exception {
		logger.info("listAll View.");
		
		List<StudyBoardVO> studyList = stuService.selectAllList();
		
		model.addAttribute("studyList", studyList);
	}
}
