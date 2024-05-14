package com.dodeveloper.algorithm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.dodeveloper.algorithm.vodto.AlgClassificationDTO;
import com.dodeveloper.algorithm.vodto.AlgDetailDTO;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
@RequestMapping("/algorithm")
public class AlgorithmController {
	
	@Autowired
	AlgService aService;
	
	@Autowired
	HttpSession ses;
	
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
	public String writeBoard( Model model ) throws Exception {
		// //algorithm/writeBoard.jsp로 포워딩
		System.out.println("글작성");
		
		List<AlgClassificationDTO> returnMap = null;
		
		// 알고리즘코드번호 테이블 받아와야 함
		returnMap = aService.getAlgClassification();
		System.out.println(returnMap.toString());
		model.addAttribute("algClassification", returnMap);
		
		return "/algorithm/writeBoard";
	}
	
	@RequestMapping(value = "/newClassification", method = RequestMethod.GET)
	public String newClassification(@RequestParam("algClassification") String algClassification) {
		//System.out.println("!!!!!!!!!!!!");
		System.out.println(algClassification);
		try {
			aService.writeAlgClassification(algClassification);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:writePOST";
	}
	
	
	@RequestMapping(value = "/modifyAlg", method = RequestMethod.GET)
	public void modifyBoard(AlgBoardDTO algBoardDTO, Model model) throws Exception {
		// 알고리즘게시판 수정페이지로 이동할 때 algBoard 를 modifyAlg.jsp 로 전달
		System.out.println("!!!!!!!!!!!!");
		List<AlgBoardDTO> returnMap = null;
		
		// AlgClassification 항목을 수정하려면 ㄱList<AlgClassificationDTO>도 modifyAlg.jsp 로 전달(GET)
		List<AlgClassificationDTO> returnMap2 = null;
		
		returnMap = aService.getListAllBoard();
		returnMap2 = aService.getAlgClassification();
		
		model.addAttribute("algBoardList",returnMap);
		model.addAttribute("algClassification",returnMap2);
		
		//
		
	}
	
	
	@RequestMapping(value = "/modifyAlg", method = RequestMethod.POST)
	public String modifyAlg(AlgBoardDTO algBoardDTO) {
		System.out.println("modmod");
		System.out.println(algBoardDTO);
		
		aService.updateAlgBoard(algBoardDTO);
		
		
		return "redirect:listAll";
	}
	
	
	
	@RequestMapping("/writeDetailPOST") // "/algorithm/write"가 get 방식으로 요청될 때... 호출
	public String writeDetailBoard( Model model, HttpServletRequest req, HttpSession ses) throws Exception {
		// //algorithm/writeBoard.jsp로 포워딩
		System.out.println("상세글작성");
		
		System.out.println(ses.getAttribute("boardNo"));
		
		int boardNo = Integer.parseInt((String) ses.getAttribute("boardNo"));
		
		System.out.println(boardNo);
		
		
		
		
		
		
		
		
		return "/algorithm/writeDetail";
	}
	
	
	@RequestMapping(value = "/writeDetailPOST", method = RequestMethod.POST)
	public void writeAlgDetail() {
		
	}


}
