package com.dodeveloper.admin.controller;

import java.util.List;

import java.util.List;
import java.util.Map;

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
import com.dodeveloper.admin.dto.SearchCriteriaDTO;
import com.dodeveloper.admin.service.AdminBoardService;
import com.dodeveloper.admin.service.AdminService;
import com.dodeveloper.admin.vo.AdminArgBoardVO;
import com.dodeveloper.admin.vo.AdminLectureVO;
import com.dodeveloper.admin.vo.AdminReviewBoardVO;
import com.dodeveloper.admin.vo.AdminVO;
import com.dodeveloper.admin.vo.BadMemberBoardVO;
import com.dodeveloper.admin.vo.QnaBoardVO;
import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.member.vo.MemberVO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService aService;
	
	@Autowired
	private AdminBoardService bService;

	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public void index(Model model) throws Exception {
		logger.info("dashboard 페이지 호출");
		

		List<NoticeDTO> diffNotc = bService.diffNotice();
		List<QnaBoardVO> diffQna = bService.diffQna();
		List<AdminVO> diffStu = bService.diffStu();
		List<AdminLectureVO> diffLec = bService.diffLec();
		List<AdminArgBoardVO> diffAlg = bService.diffAlg();
		List<AdminReviewBoardVO> diffRev = bService.diffRev();
		
		model.addAttribute("diffNotc", diffNotc);
		model.addAttribute("diffQna", diffQna);
		model.addAttribute("diffStu", diffStu);
		model.addAttribute("diffLec", diffLec);
		model.addAttribute("diffAlg", diffAlg);
		model.addAttribute("diffRev", diffRev);
	

	}

//	@RequestMapping(value = "/inquiry", method = RequestMethod.GET)
//	public void inquiry(Model model) throws Exception {
//		logger.info("불량 회원 게시글 페이지 호출");
//		
//		List<BadMemberBoardVO> bedMemberBoardList = aService.getListBadMemberBoard();
//		
//		model.addAttribute("bedMemberBoardList", bedMemberBoardList);
//
//	}

	@RequestMapping(value = "/blank", method = RequestMethod.GET)
	public void blank() {
		logger.info("blank 페이지 호출");

	}

	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public void notice() {
		logger.info("notice 호출");
	}

//	@RequestMapping(value = "/selectBoard", method = RequestMethod.GET)
//	public void selectBoard(Model model) throws Exception {
//		logger.info("게시판 조회 페이지");
//		
//		List<AdminVO> stuBoardList = aService.getlistStudyBoard();
//
//		model.addAttribute("stuBoardList", stuBoardList);
//	}

}
