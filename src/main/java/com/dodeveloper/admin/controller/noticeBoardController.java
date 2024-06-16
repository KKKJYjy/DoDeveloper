package com.dodeveloper.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dodeveloper.admin.dto.NoticeDTO;
import com.dodeveloper.admin.dto.SearchCriteriaDTO;
import com.dodeveloper.admin.service.AdminBoardService;
import com.dodeveloper.admin.vo.QnaBoardVO;
import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.member.vo.MemberVO;

@Controller
@RequestMapping("/notice")
public class noticeBoardController {

	private static final Logger logger = LoggerFactory.getLogger(noticeBoardController.class);

	@Autowired
	private AdminBoardService bService;

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void noticeBoard(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value="notc", defaultValue = "date") String notc, SearchCriteriaDTO sc) throws Exception {
		logger.info("list 페이지 호출");

		Map<String, Object> returnMap = null;

		String resultPage = null;

		if (pageNo <= 0) {
			pageNo = 1;
		}

		if (notc.equals("date")) {
			returnMap = bService.getlistNotcBoard(pageNo, sc);
		} else {
			returnMap = bService.getlistViewNotcBoard(pageNo, sc);
		}

		// returnMap = bService.getlistNotcBoard(pageNo, sc);
		model.addAttribute("notcBoardList", (List<NoticeDTO>) returnMap.get("notcBoardList"));
		model.addAttribute("pagingInfo", (PagingInfo) returnMap.get("pagingInfo"));
		model.addAttribute("notc", notc);
		
		resultPage = "/notice/listAll";

	}

	@RequestMapping(value = "/viewBoard", method = RequestMethod.GET)
	public ModelAndView noticeDetail(Model model, @RequestParam("boardNo") int boardNo, HttpServletRequest req,
			HttpServletResponse resp, ModelAndView mav, HttpSession ses) throws Exception {

		logger.info(boardNo + "번글 조회");

		String user = null;

		if (ses.getAttribute("loginMember") != null) {
			user = ((MemberVO) ses.getAttribute("loginMember")).getUserId();
		} else if (cookieExist(req, "rses") == null) {
			String sesId = req.getSession().getId();
			user = sesId;
			saveCookie(resp, sesId);
		} else {
			user = cookieExist(req, "rses");
		}

		Map<String, Object> result = bService.getNotcBoardNo(boardNo, user);

		mav.addObject("notice", (NoticeDTO) result.get("notice"));
		mav.setViewName("/notice/viewBoard");

		// model.addAttribute("notice", notice);
		return mav;
	}

	private void saveCookie(HttpServletResponse resp, String sesId) {
		Cookie sessionCookie = new Cookie("rses", sesId);
		sessionCookie.setMaxAge(60 * 60 * 24);
		resp.addCookie(sessionCookie);
	}

	private String cookieExist(HttpServletRequest req, String cookieName) {
		String result = null;

		for (Cookie c : req.getCookies()) {
			if (c.getName().equals(cookieName)) {
				// 로그인을 하지 않았는데 쿠키가 있는 유저
				result = c.getValue();
			}
		}
		return result;
	}

}
