package com.dodeveloper.lecture.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dodeveloper.lecture.service.LectureBoardService;
import com.dodeveloper.lecture.vodto.LectureBoardDTO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;

@Controller // 아래의 클래스가 컨트롤러 객체임을 명시
@RequestMapping("/lecture") // "/lecture"가 GET방식으로 요청될 때 아래의 클래스가 동작되도록 설정
public class LectureBoardController {

	private static final Logger logger = LoggerFactory.getLogger(LectureBoardController.class);

	@Autowired
	private LectureBoardService lService; // 스프링 컨테이너에서 LectureService 객체를 찾아 주입

	/**
	 * @methodName : listAllGet
	 * @author : kde
	 * @date : 2024.05.02
	 * @param : Model model : View단(listAll)으로 바인딩하는 전용 객체
	 * @return : void
	 * @throws Exception
	 * @description : 강의 추천 게시판 전체 글 조회를 담당하는 controller 메서드
	 */
	@GetMapping(value = "/listAll")
	public void listAllBoardGet(Model model) throws Exception {
		logger.info("강의 추천 게시판 전체 게시글 조회 : listAll View");

		// 서비스단 호출 (getListAllBoard() 메서드 호출)
		List<LectureBoardVO> lectureBoardList = lService.getListAllBoard();

		// 바인딩
		model.addAttribute("lectureBoardList", lectureBoardList);
	}

	/**
	 * @methodName : viewBoard
	 * @author :
	 * @date : 2024.05.03
	 * @param : @RequestParam("lecNo") int lecNo : 조회하려는 글 번호
	 * @return : ModelAndView : 조회하는 글을 바인딩 한 후 게시판 상세 페이지로 이동시키는 객체
	 * @throws Exception
	 * @description :
	 */
	@GetMapping("/viewBoard") // 상세 게시글로 가도록 Mapping함
	public ModelAndView viewBoard(@RequestParam("lecNo") int lecNo, HttpServletRequest req, HttpServletResponse resp,
			ModelAndView mav, HttpSession ses) throws Exception {

		String user = (String) ses.getAttribute("user");

		System.out.println("현재 상태의 유저 : " + user + "가" + lecNo + "번 글을 조회한다!");

		Map<String, Object> result = lService.getBoardByBoardNo(lecNo, user);

		mav.addObject("lecBoard", (LectureBoardVO) result.get("lecBoard"));
		mav.setViewName("/lecture/viewBoard");

		return mav;
	}
	
	/**
	 * @methodName : writeBoard
	 * @author : 
	 * @date : 2024.05.04
	 * @return : void
	 * @description : 유저가 게시글 작성 버튼을 눌렀을 때 게시글 작성 페이지로 가는 메서드
	 */
	@RequestMapping("/writeBoard")
	public void writeBoard() {
		logger.info("controller : 글을 작성하러 갈게요!");
	}
	
	/**
	 * @methodName : modifyBoard
	 * @author : 
	 * @date : 2024.05.04
	 * @param : @RequestParam("lecNo") int lecNo - 수정될 게시글 번호
	 * @param : Model model - View단(modifyBoard)으로 바인딩하는 전용 객체
	 * @return : void
	 * @throws Exception 
	 * @description : 유저가 작성한 게시글을 수정하는 메서드
	 */
	@GetMapping("/modifyBoard")
	public String modifyBoard(@RequestParam("lecNo") int lecNo, Model model) throws Exception {
		logger.info("controller : 게시글을 수정할게요!");
		
		Map<String, Object> map = lService.getBoardByBoardNo(lecNo);
		
		model.addAttribute("result", map);
		
		return "/lecture/modifyBoard";
	}

}
