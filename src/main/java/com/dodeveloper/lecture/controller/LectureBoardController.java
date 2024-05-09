package com.dodeveloper.lecture.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodeveloper.lecture.service.LectureBoardService;
import com.dodeveloper.lecture.vodto.LectureBoardVO;

@Controller // 아래의 클래스가 컨트롤러 객체임을 명시
@RequestMapping("/lecture") // "/lecture"가 GET방식으로 요청될 때 아래의 클래스가 동작되도록 설정함
public class LectureBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(LectureBoardController.class);
	
	@Autowired
	private LectureBoardService lService; // 스프링 컨테이너에서 LectureService 객체를 찾아 주입
	
	/**
	 * @methodName : listAllGet
	 * @author : kde
	 * @date : 2024.05.02
	 * @param : Model model : 전체 게시글을 바인딩 한 후 게시판 전체 페이지로 이동시키는 객체
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
}