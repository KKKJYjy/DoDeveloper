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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodeveloper.algorithm.service.AlgService;
import com.dodeveloper.algorithm.vodto.AlgBoardDTO;
import com.dodeveloper.algorithm.vodto.AlgDetailDTO;

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
	
	
	@GetMapping("/algDetail")
	public void getAlgDetail(@RequestParam("boardNo") int boardNo,Model model) throws Exception {
		System.out.println("알고리즘상세 : "+boardNo+" 번 알고리즘");
		
		List<AlgDetailDTO> returnMap = null;
		
		
		returnMap = aService.getListDetail(boardNo);
		
		
		model.addAttribute("algDetailList", returnMap);
	}
	
	@RequestMapping(value="/writePOST",method = RequestMethod.POST)
	public String writeAlg(AlgBoardDTO algBoardDTO) throws Exception {
		System.out.println("글 작성()");
		System.out.println(algBoardDTO);
		aService.writeAlgBoard(algBoardDTO);
		
		return "redirect:listAll";
	}
	
	@RequestMapping("/writePOST") // "/algorithm/write"가 get 방식으로 요청될 때... 호출
	public String writeBoard() {
		// //algorithm/writeBoard.jsp로 포워딩
		System.out.println("글작성");
		return "/algorithm/writeBoard";
	}


}
