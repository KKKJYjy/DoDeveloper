
package com.dodeveloper.lecture.controller;

import java.util.List;

import java.util.Map;

import javax.servlet.http.Cookie;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dodeveloper.company.vodto.ScrapVO;
import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.lecture.service.LectureBoardService;
import com.dodeveloper.lecture.vodto.LectureBoardDTO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.lecture.vodto.LectureSearchDTO;
import com.dodeveloper.member.vo.MemberVO;

/**
 * @PackageName : com.dodeveloper.lecture.controller
 * @fileName : LectureBoardController.java
 * @author :
 * @date : 2024.05.13
 * @description :
 */
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
	 * int 매개변수 'lecNo'가 존재하지만 기본 유형으로 선언되기 때문에 null값으로 변환할 수 없어서 객체 래퍼로 선언한 것
	 * @param : LectureSearchDTO lsDTO - 검색어 Type와 Value를 가져옴
	 * @return : void
	 * @throws Exception
	 * @description : 1) 강의 추천 게시판 전체 글 조회를 담당하는 controller 메서드
	 * 2) 검색 조건 : 검색을 했을 경우 - 제목 / 작성자 / 내용
	 */
	@RequestMapping(value = "/listAll")
	public void listBoardGet(Model model, LectureSearchDTO lsDTO,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) throws Exception {
		logger.info(pageNo + "게시글 전체 글을 조회하자!");
		logger.info("검색어 : " + lsDTO.toString());

		Map<String, Object> resultMap = null;

		String resultPage = null;

		// 페이지 번호가 1이상이 되도록 설정
		if (pageNo <= 0) {
			pageNo = 1;
		}

		// 서비스단 호출(getListAllBoard() 메서드 호출)
		resultMap = lService.getListAllBoard(pageNo, lsDTO);

		// 게시글 목록 가져오기
		List<LectureBoardVO> lectureBoardList = (List<LectureBoardVO>) resultMap.get("lectureBoardList");

		// for 문을 사용하여 lectureBoardList를 순회
//	    for (LectureBoardVO lecture : lectureBoardList) {
//	        logger.info("제목: " + lecture.getLecTitle() + "조회수 : " + lecture.getLecReadCount());
//	    }

		// 바인딩
		// 게시글 자체를 바인딩
		model.addAttribute("lectureBoardList", (List<LectureBoardVO>) resultMap.get("lectureBoardList"));
		// 페이징 정보를 바인딩
		model.addAttribute("pagingInfo", (PagingInfo) resultMap.get("pagingInfo"));

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

		String user = null;

		if (ses.getAttribute("loginMember") != null) {
			// 로그인 한 유저의 경우
			user = ((MemberVO) ses.getAttribute("loginMember")).getUserId();
		} else if (cookieExist(req, "rses") == null) {
			// 로그인 안한 유저의 경우 쿠키가 없으면
			// sessionId를 불러온다.
			String sesId = req.getSession().getId();
			user = sesId;
			saveCookie(resp, sesId);

			logger.info("로그인 안한 유저의 쿠키값" + user);
		} else {
			// 로그인을 하지않았는데 쿠키가 있다면
			// 쿠키를 찾아서 조회수를 안올리도록
			user = cookieExist(req, "rses");
		}

		logger.info("현재 상태의 유저 : " + user + "가 " + lecNo + "번 글을 조회한다!");

		Map<String, Object> result = lService.getBoardByBoardNo(lecNo, user);

		mav.addObject("lecBoard", (LectureBoardVO) result.get("lecBoard"));
		mav.setViewName("/lecture/viewBoard");

		return mav;
	}

	/**
	 * @methodName : saveCookie
	 * @author :
	 * @date : 2024.05.14
	 * @param : HttpServletResponse resp
	 * @param : String sesId - sessionId 값
	 * @return : void
	 * @description : 세션 Id값을 쿠키에 '1일' 저장 조회수를 올리기 위해 user가 로그인을 하지않아도 sessionId값을
	 *              이용해 기록이 남도록
	 */
	private void saveCookie(HttpServletResponse resp, String sesId) {
		Cookie sessionCookie = new Cookie("rses", sesId);
		sessionCookie.setMaxAge(60 * 60 * 24);
		resp.addCookie(sessionCookie);
	}

	/**
	 * @methodName : cookieExist
	 * @author :
	 * @date : 2024.05.13
	 * @param : HttpServletRequest req
	 * @param : String cookieName - 찾을 쿠키 이름
	 * @return : String
	 * @description : 쿠키 이름을 가지고, 그 이름의 쿠키를 찾는 메서드 조회수를 올리기 위해 user가 로그인을 하지않아도
	 *              sessionId값을 이용해 기록이 남도록해서 user 한명 당 게시글 한번에 조회수 한번만 올라갈 수 있도록 하기
	 */
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
	 * @return : String
	 * @throws Exception
	 * @description : 유저가 작성한 글을 실제 DB(lectureBoard)에 insert 시키기 위한 controller 메서드
	 *              유저가 작성한 글을 DB에 보내야하니까 POST 방식을 사용
	 */
	@RequestMapping(value = "/writePOST", method = RequestMethod.POST)
	public String writeBoard(LectureBoardDTO newLecBoard) throws Exception {
	        logger.info("controller: " + newLecBoard.toString() + " 글을 저장하러 갈게요!");
	        
	        String returnPage = "/lecture/listAll";
	        
	        if (lService.writeBoardService(newLecBoard)) {
	            logger.info("controller: 글 작성 성공");
	            returnPage = "redirect:" + returnPage;
	        } else {
	            logger.info("controller: 글 작성 실패");
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

	/**
	 * @methodName : cancelModifyBoard
	 * @author :
	 * @date : 2024.05.12
	 * @return : String
	 * @description : 게시글 수정하려다가 취소 버튼을 누른 경우 작동되는 메서드
	 */
	@RequestMapping(value = "/cancelModify", method = RequestMethod.POST)
	public @ResponseBody String cancelModifyBoard() {
		System.out.println("게시글 수정 취소 요청");

		return "success";
	}
	
	/**
	 * @methodName : getLikeStatus
	 * @author : 
	 * @date : 2024.05.23
	 * @param : int lecNo - 좋아요 눌렸는지 확인할 게시글 번호
	 * @param : String user - 좋아요를 누가 누른건지 확인할 유저
	 * @return : @ResponseBody String - 게시글 번호, 유저 정보를 받아서 문자열(success/fail)로 지정해서 반환
	 * @description : 게시글에 좋아요(눌려있는지/안눌려있는지) 체크하는 메서드
	 */
	@GetMapping("/likeStatus")
	public @ResponseBody String getLikeStatus(int lecNo, String user, String lecLikeTitle) {
		
		try {
			if (lService.checkLikeStatus(lecNo, user)) {
				// 유저가 좋아요를 누른 게시글일 경우 "success" 반환
				System.out.println(user + "가 " + lecNo + "번 게시글에 좋아요를 눌렀었다.");
			    return "success";
			} else {
				// 유저가 좋아요를 누르지 않은 게시글일 경우 "fail" 반환
				System.out.println(user + "가 " + lecNo + "번 게시글에 좋아요를 취소&안눌렀다.");
			    return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * @methodName : likeBoard
	 * @author :
	 * @date : 2024.05.18
	 * @param : @RequestBody Map<String, String> likeRequest
	 * json 데이터를 자바의 Map 형태로 매핑(lecNo가 int형이라 넘길수가 없기에 Map 형태로 String으로 만들어서 넘겼다.)
	 * @return : ResponseEntity<String> - 문자열을 응답 본문으로 가지는 객체 / 그 중 Stirng 타입(문자열)으로 응답
	 * @description : 로그인 한 유저인 경우만 좋아요를 누를 수 있다.
	 * 유저가 하트를 눌렀을 때 좋아요 수가 1증가 -> ♥
	 */
	@PostMapping("/like")
	public ResponseEntity<String> likePost(@RequestBody Map<String, String> likeRequest, String lecLikeTitle) {
	    
	    int lecNo = Integer.parseInt(likeRequest.get("lecNo"));
	    String user = likeRequest.get("user");
	    
	    logger.info(lecNo + "번 글에 " + user + "가 좋아요를 눌렀습니다! 제목: " + lecLikeTitle);
	    
	    ResponseEntity<String> result = null; // 초기값 설정

	    try {
	        // 좋아요를 안눌렀었는지 확인 후 누르기
	        lService.likeUpBoard(lecNo, user, lecLikeTitle);
	        result = new ResponseEntity<String>("success", HttpStatus.OK);
	    } catch (Exception e) {
	        // 예외 발생 시 예외 처리
	        e.printStackTrace();
	        result = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	    }

	    return result;
	}

	/**
	 * @methodName : likeBoard
	 * @author :
	 * @date : 2024.05.22
	 * @param : @RequestBody Map<String, String> likeRequest
	 * json 데이터를 자바의 Map 형태로 매핑(lecNo가 int형이라 넘길수가 없기에 Map 형태로 String으로 만들어서 넘겼다.)
	 * @return : ResponseEntity<String> - 문자열을 응답 본문으로 가지는 객체 / 그 중 Stirng 타입(문자열)으로 응답
	 * @description : 로그인 한 유저인 경우만 좋아요를 누를 수 있다.
	 * 유저가 하트를 한번 더 눌렀을 경우 1감소 -> ♡
	 */
	@PostMapping("/unLike")
	public ResponseEntity<String> unLikePost(@RequestBody Map<String, String> unlikeRequest, String lecLikeTitle) {
	    
	    int lecNo = Integer.parseInt(unlikeRequest.get("lecNo"));
	    String user = unlikeRequest.get("user");
	    
	    logger.info(lecNo + "번 글에 " + user + "가 좋아요를 취소했습니다! 제목: " + lecLikeTitle);

	    ResponseEntity<String> result = null; // 초기값 설정

	    try {
	        // 좋아요를 눌렀었는지 확인 후 취소하기
	        lService.likeDownBoard(lecNo, user, lecLikeTitle);
	        result = new ResponseEntity<String>("success", HttpStatus.OK);
	    } catch (Exception e) {
	        // 예외 발생 시 예외 처리
	        e.printStackTrace();
	        result = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	    }

	    return result;
	}
	
	
	/**
	 * @methodName : getLikeStatus
	 * @author : 
	 * @date : 2024.05.23
	 * @param : int lecNo - 스크랩을 눌렸는지 확인할 게시글 번호
	 * @param : String user - 스크랩을 누가 누른건지 확인할 유저
	 * @return : @ResponseBody String - 스크랩 번호, 유저 정보를 받아서 문자열(success/fail)로 지정해서 반환
	 * @description : 게시글에 스크랩(눌려있는지/안눌려있는지) 체크하는 메서드
	 */
	@GetMapping("/scrapStatus")
	public @ResponseBody String lectureScrap(int lecNo, String user) {
		try {
			if (lService.selectAllLectureScrap(lecNo, user)) {
				// 유저가 스크랩을 누른 게시글일 경우 "success" 반환
				System.out.println(user + "가 " + lecNo + "번 게시글에 스크랩을 눌렀었다.");
			    return "success";
			} else {
				// 유저가 스크랩을 누르지 않은 게시글일 경우 "fail" 반환
				System.out.println(user + "가 " + lecNo + "번 게시글에 스크랩을 취소&안눌렀다.");
			    return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * @methodName : likeBoard
	 * @author :
	 * @date : 2024.05.18
	 * @param : @RequestBody Map<String, String> likeRequest
	 * json 데이터를 자바의 Map 형태로 매핑(lecNo가 int형이라 넘길수가 없기에 Map 형태로 String으로 만들어서 넘겼다.)
	 * @return : ResponseEntity<String> - 문자열을 응답 본문으로 가지는 객체 / 그 중 Stirng 타입(문자열)으로 응답
	 * @description : 로그인 한 유저인 경우만 스크랩을 누를 수 있다.
	 * 유저가 스크랩을 눌렀을 경우 스크랩 수가 1증가
	 */
    @PostMapping("/scrap")
    public ResponseEntity<String> scrapPost(@RequestBody Map<String, String> scrapRequest) {
    	
        int lecNo = Integer.parseInt(scrapRequest.get("lecNo"));
        String user = scrapRequest.get("user");
        
        logger.info(lecNo + "번 글에 " + user + "가 스크랩을 눌렀습니다!");
        
        ResponseEntity<String> result = null; // 초기값 설정

        try {
            // 좋아요를 안눌렀었는지 확인 후 누르기
            lService.scrapUpBoard(lecNo, user);
            result = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            // 예외 발생 시 예외 처리
            e.printStackTrace();
            result = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
        }

        return result;
    }

	/**
	 * @methodName : likeBoard
	 * @author :
	 * @date : 2024.05.22
	 * @param : @RequestBody Map<String, String> likeRequest
	 * json 데이터를 자바의 Map 형태로 매핑(lecNo가 int형이라 넘길수가 없기에 Map 형태로 String으로 만들어서 넘겼다.)
	 * @return : ResponseEntity<String> - 문자열을 응답 본문으로 가지는 객체 / 그 중 Stirng 타입(문자열)으로 응답
	 * @description : 로그인 한 유저인 경우만 스크랩을 누를 수 있다.
	 * 유저가 스크랩을 눌렀던 경우 한번 더 눌렀을 때 1감소
	 */
	@PostMapping("/unScrap")
	public ResponseEntity<String> unScrapPost(@RequestBody Map<String, String> unScrapRequest) {
		
	    int lecNo = Integer.parseInt(unScrapRequest.get("lecNo"));
	    String user = unScrapRequest.get("user");
	    
	    logger.info(lecNo + "번 글에 " + user + "가 좋아요를 취소했습니다!");

	    ResponseEntity<String> result = null; // 초기값 설정

        try {
        	// 좋아요를 눌렀었는지 확인 후 취소하기
            lService.scrapDownBoard(lecNo, user);
            result = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            // 예외 발생 시 예외 처리
            e.printStackTrace();
            result = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
        }

        return result;
	}

}
