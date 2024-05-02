package com.dodeveloper.algorithm.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodeveloper.algorithm.service.AlgService;
import com.dodeveloper.algorithm.vodto.AlgBoardDTO;

@Controller
@RequestMapping("/algorithm")
public class AlgorithmController {
	
	@Autowired
	AlgService aService;
	
	private static final Logger logger = LoggerFactory.getLogger(AlgorithmController.class);
	
	@GetMapping(value = "/listAll")
	public void listAllGet(Model model) throws Exception { 
		logger.info("listAll View.");
		System.out.println("!!!컨트롤러!!!");
		
		List<AlgBoardDTO> returnMap = null;
		
		// 멤버테이블 출력함
		returnMap = aService.getListAllBoard();
		
		
		model.addAttribute("algBoardList",returnMap);
	}

}
