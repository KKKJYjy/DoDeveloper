
package com.dodeveloper.lecture.controller;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodeveloper.lecture.service.LectureBoardService;
import com.dodeveloper.lecture.vodto.LectureBoardDTO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.lecture.vodto.LectureSearchDTO;

@Controller // 아래의 클래스가 컨트롤러 객체임을 명시
@RequestMapping("/lecture") // "/lecture"가 GET방식으로 요청될 때 아래의 클래스가 동작되도록 설정
public class LectureBoardController {

	private static final Logger logger = LoggerFactory.getLogger(LectureBoardController.class);

	@Autowired
	private LectureBoardService lService; // 스프링 컨테이너에서 LectureService 객체를 찾아 주입
  
	// 생성자를 이용하여 객체 주입
	public LectureBoardController(LectureBoardService service) {
		this.lService = service;
	}
	
	/**
	 * @methodName : listAllGet
	 * @author : kde
	 * @date : 2024.05.02
	 * @param : Model model : View단(listAll)으로 바인딩하는 전용 객체
	 * @param : @RequestParam(value = "lecNo", defaultValue = "1") int lecNo
	 * 			int 매개변수 'lecNo'가 존재하지만 기본 유형으로 선언되기 때문에 null값으로 변환할 수 없어서 객체 래퍼로 선언한 것
	 * @param : LectureSearchDTO lsDTO - 검색어 Type와 Value를 가져옴
	 * @return : void
	 * @throws Exception
	 * @description : 1) 강의 추천 게시판 전체 글 조회를 담당하는 controller 메서드
	 * 2) 검색 조건 : 검색을 했을 경우 - 제목 / 작성자 / 내용
	 */
	@GetMapping(value = "/listAll")
	public void listBoardGet(Model model,
	        @RequestParam(value = "searchType", required = false) String searchType,
	        @RequestParam(value = "searchValue", required = false) String searchValue,
	        @RequestParam(value = "filterType", required = false) String filterType,
	        LectureSearchDTO lsDTO) throws Exception {
	    
	    List<LectureBoardVO> lectureBoardList = null;
	    
	    // 검색 필터와 검색 조건을 동시에 처리
	    if (searchType != null && searchValue != null) {
	    	logger.info("검색 조건을 선택하고 검색어를 입력했어요!");
	        // 검색 조건이 있는 경우
	        lsDTO.setSearchType(searchType);
	        lsDTO.setSearchValue(searchValue);
	        lectureBoardList = lService.listAllBoardBySearch(1, lsDTO);
	    } else {
	        // 검색 조건이 없는 경우
	        lectureBoardList = lService.getListAllBoard(1);
	    }
	    
	    // 검색 필터 적용
	    if (filterType != null && !filterType.isEmpty()) {
	    	logger.info("검색 필터를 선택했어요!");
	        // 필터가 존재하면 해당 필터에 따라 게시글을 가져옴
	        lectureBoardList = lService.listAllBoardByFilter(lectureBoardList, filterType);
	    }
	    
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
	 * @description : 유저가 lecNo번 글을 눌렀을 때 상세 페이지로 가는 메서드
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
	public String writeBoard() {
		logger.info("controller : 글을 작성하러 갈게요!");

		return "/lecture/writeBoard";
	}

	/**
	 * @methodName : writeBoard
	 * @author :
	 * @date : 2024.05.04
	 * @param : LectureBoardDTO newLecBoard - 유저가 작성한 글 객체
	 * @param :
	 * @param :
	 * @return : String
	 * @throws Exception
	 * @description : 유저가 작성한 글을 실제 DB(lectureBoard)에 insert 시키기 위한 controller 메서드
	 *              유저가 작성한 글을 DB에 보내야하니까 POST 방식을 사용
	 */
	@RequestMapping(value = "/writePOST", method = RequestMethod.POST)
	public String writeBoard(LectureBoardDTO newLecBoard) throws Exception {
		logger.info("controller : " + newLecBoard.toString() + "글을 저장하러 갈게요!");

		String returnPage = "/lecture/listAll"; // 게시판 전체 조회 페이지로

		// 서비스단 호출
		if (lService.writeBoardService(newLecBoard)) {
			// 유저가 작성한 게시글 저장이 성공했을 경우
			returnPage = "redirect:" + returnPage + "?status=writeSuccess";
		} else {
			// 유저가 작성한 게시글 저장이 실패한 경우
			returnPage = "redirect:" + returnPage + "?status=writeFail";
		}

		return returnPage;
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
	@GetMapping("/modifyLectureBoard")
	public String modifyBoard(@RequestParam("lecNo") int lecNo, Model model) throws Exception {
		logger.info("controller : 게시글을 수정할게요!");

		Map<String, Object> map = lService.getBoardByBoardNo(lecNo);

		model.addAttribute("result", map);

		return "/lecture/modifyBoard";
	}

	/**
	 * @methodName : modifyBoard
	 * @author :
	 * @date : 2024.05.04
	 * @param : LectureBoardDTO modifyBoard - 새롭게 수정되어야 할 게시글
	 * @param : HttpServletRequest req
	 * @return : String
	 * @throws Exception
	 * @description : 게시글 수정 시 update 처리
	 */
	@PostMapping("/modifyPost")
	public String modifyBoard(LectureBoardDTO modifyBoard, HttpServletRequest req) throws Exception {
		logger.info("controller : 게시글 수정 update");

		System.out.println(modifyBoard.toString() + "을 수정!");

		// 서비스단 호출
		lService.modifyBoard(modifyBoard);

		// 수정 후 viewBoard의 lecNo로 간다.
		return "redirect:/lecture/viewBoard?lecNo=" + modifyBoard.getLecNo();
	}

	/**
	 * @methodName : removeLectureBoard
	 * @author :
	 * @date : 2024.05.05
	 * @param : @RequestParam("lecNo") int lecNo - 게시글 삭제할 번호
	 * @return : String
	 * @throws Exception 
	 * @description : 게시글 삭제 시 delete 처리
	 */
	@GetMapping("/removeLectureBoard")
	public String removeLectureBoard(@RequestParam("lecNo") int lecNo) throws Exception {
	    logger.info("controller :" + lecNo + "번 게시글 삭제 delete");
	    
	    lService.deleteLectureBoard(lecNo);
	    
	    return "forward:/lecture/listAll"; // forward를 사용하여 다시 조회 페이지로 이동
	}

	/**
	 * @methodName : cancelBoard
	 * @author :
	 * @date : 2024.05.05
	 * @return : String
	 * @description : 유저가 게시글을 작성하려다 취소버튼을 누른 경우에 작동되는 메서드
	 */
	@RequestMapping(value = "/cancel", method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
	public @ResponseBody String cancelBoard() {
		System.out.println("게시글 작성 안할래요!");

		return "success";
	}

}

