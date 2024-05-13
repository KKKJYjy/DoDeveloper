package com.dodeveloper.admin.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodeveloper.admin.dto.SearchCriteriaDTO;
import com.dodeveloper.admin.etc.PagingInfo;
import com.dodeveloper.admin.service.AdminBoardService;
import com.dodeveloper.admin.vo.AdminArgBoardVO;
import com.dodeveloper.admin.vo.AdminLectureVO;
import com.dodeveloper.admin.vo.AdminReviewBoardVO;
import com.dodeveloper.admin.vo.AdminVO;

@Controller
@RequestMapping("/admin")
public class AdminBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminBoardController.class);

	@Autowired
	private AdminBoardService bService;
	
	
	
	
	@RequestMapping(value = "/selectBoard", method = RequestMethod.GET)
	public void selectBoard(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			SearchCriteriaDTO sc) throws Exception {
		logger.info(pageNo + "번째 study게시판 페이지 조회");
		
		Map<String, Object> returnMap = null;
		
		String resultPage = null;
		
		// Map<String, Object> returnMap = bService.getlistStudyBoard(pageNo);

		if (pageNo <= 0) {
			pageNo = 1;
		}
		
		// model.addAttribute("stuBoardList", stuBoardList);
		returnMap = bService.getlistStudyBoard(pageNo, sc);
		model.addAttribute("stuBoardList", (List<AdminVO>)returnMap.get("stuBoardList"));
		model.addAttribute("pagingInfo", (PagingInfo)returnMap.get("pagingInfo"));
		
		resultPage = "/admin/selectBoard";
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
	public void reviewBoard(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo) throws Exception {
		logger.info("reviewBoard 조회");
		
		
		
		// Map<String, Object> returnMap = bService.getlistRevBoard(pageNo);
		Map<String, Object> returnMap = bService.getlistRevBoard(pageNo);
		model.addAttribute("revBoardList", (List<AdminReviewBoardVO>)returnMap.get("revBoardList"));
		model.addAttribute("pagingInfo", (PagingInfo)returnMap.get("pagingInfo"));
		
		
	}
	

}
