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

import com.dodeveloper.admin.dto.NoticeDTO;
import com.dodeveloper.admin.dto.SearchCriteriaDTO;
import com.dodeveloper.admin.service.AdminBoardService;
import com.dodeveloper.admin.vo.QnaBoardVO;
import com.dodeveloper.etc.PagingInfo;

@Controller
@RequestMapping("/notice")
public class noticeBoardController {

	private static final Logger logger = LoggerFactory.getLogger(noticeBoardController.class);

	@Autowired
	private AdminBoardService bService;

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void noticeBoard(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			SearchCriteriaDTO sc) throws Exception {
		logger.info("list 페이지 호출");

		Map<String, Object> returnMap = null;

		String resultPage = null;

		if (pageNo <= 0) {
			pageNo = 1;
		}

		returnMap = bService.getlistNotcBoard(pageNo, sc);
		model.addAttribute("notcBoardList", (List<NoticeDTO>) returnMap.get("notcBoardList"));
		model.addAttribute("pagingInfo", (PagingInfo) returnMap.get("pagingInfo"));

		resultPage = "/notice/listAll";

	}

	@RequestMapping(value = "/viewBoard", method = RequestMethod.GET)
	public void noticeDetail(Model model, @RequestParam("boardNo") int boardNo) throws Exception {

		logger.info(boardNo + "번글 조회");

		NoticeDTO notice = bService.getNotcBoardNo(boardNo);
		

		model.addAttribute("notice", notice);
		
	}

}
