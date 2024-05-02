package com.dodeveloper.study.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodeveloper.study.service.StudyService;
import com.dodeveloper.study.vodto.StuStackVO;
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
		
		//스터디 목록
		List<StudyBoardVO> studyList = stuService.selectAllList();
		
		//스터디 No번째글 스터디 언어 목록
		List<StuStackVO> stuStackList = new ArrayList<StuStackVO>();
		
		for(StudyBoardVO s : studyList) {
			//stuNo를 넘겨주어 공부할 언어 정보를 가져오자
			
			stuStackList.addAll(stuService.selectAllStudyStack(s.getStuNo())); 				
			
			System.out.println(s.getStuNo());
		}
		
		System.out.println(stuStackList.toString());
		
		model.addAttribute("studyList", studyList);
		model.addAttribute("stuStackList", stuStackList);
	}
	
	@GetMapping(value = "/writeStudyBoard")
	public void writeBoard() {
		logger.info("writeStudyBoard View.");
		
	}
}
