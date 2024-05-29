package com.dodeveloper.study.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.member.vo.MemberVO;
import com.dodeveloper.study.service.StudyService;
import com.dodeveloper.study.vodto.StuStackVO;
import com.dodeveloper.study.vodto.StudyBoardDTO;
import com.dodeveloper.study.vodto.StudyBoardVO;

import javax.servlet.http.Cookie;

import com.dodeveloper.study.vodto.SearchStudyDTO;
import com.dodeveloper.study.vodto.StackVO;
import com.dodeveloper.study.vodto.StuStackDTO;
import com.dodeveloper.study.vodto.StuStackModifyDTO;

@Controller
@RequestMapping("/study")
public class StudyContoller {

	private static final Logger logger = LoggerFactory.getLogger(StudyContoller.class);

	private StudyBoardDTO newStudy;

	// view단에서 보낸 스터디 언어들을 담고있는 ArrayList.
	// 저장, 수정, 취소 등의 버튼을 누르기 전까지 스터디 언어들을 임시로 보관하는 곳
	private List<StuStackDTO> stuStackList = new ArrayList<StuStackDTO>();

	@Autowired
	StudyService stuService;

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : int pageNo - 페이지 번호
	 * @param : String status - 스터디 모집 상태(모집중/모집마감)
	 * @param : SearchStudyDTO sDTO - 검색 내용을 담고있는 객체
	 * @return : void
	 * @description : 스터디 모든 목록을 불러오는 메서드 + 검색 기능 추가 + 모집중 필터기능 추가
	 */
	@GetMapping(value = "/listAll")
	public void listAllGet(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			SearchStudyDTO sDTO)
			throws Exception {

		logger.info("listAll View");
		logger.info("listAll : " + pageNo + "번째 글" + "statusFilter : " + "검색 내용 :" + sDTO.toString());

		Map<String, Object> result = null;

		if (pageNo <= 0) {
			pageNo = 1;
		}
		
		
		result = stuService.selectAllList(sDTO, pageNo);
		List<StudyBoardVO> studyList = (List<StudyBoardVO>) result.get("studyList");

		// 스터디 No번째글 스터디 언어 목록
		List<StuStackDTO> stuStackList = new ArrayList<StuStackDTO>();
		
		if(studyList != null) {			
			for (StudyBoardVO s : studyList) {
				// stuNo를 넘겨주어 공부할 언어 정보를 가져오자
				stuStackList.addAll(stuService.selectAllStudyStack(s.getStuNo()));
				// System.out.println(s.getStuNo());
			}
		}

		// stack테이블의 모든 값들을 가져오자
		List<StackVO> stackList = stuService.selectAllStack();

		// System.out.println(stuStackList.toString());
		model.addAttribute("studyList", studyList);
		model.addAttribute("stuStackList", stuStackList);
		model.addAttribute("stackList", stackList);
		model.addAttribute("pagingInfo", (PagingInfo) result.get("pagingInfo"));

	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : Model model
	 * @return : void
	 * @description : 스터디 작성 페이지로 이동하는 메서드
	 */
	@GetMapping(value = "/writeStudyBoard")
	public void writeBoard(Model model) throws Exception {
		logger.info("writeStudyBoard View.");

		// stack테이블의 모든 값들을 가져오자
		List<StackVO> stackList = stuService.selectAllStack();
		model.addAttribute("stackList", stackList);

	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : StudyBoardDTO newStudyDTO - 새로 저장될 스터디 모임글 정보를 담고있는 객체
	 * @return : ResponseEntity<String> - newStudy에 저장 성공하면 HttpStatus.OK, 실패하면
	 *         HttpStatus.CONFLICT
	 * @description : 스터디 작성 버튼을 누르면 이 메서드가 먼저 수행되어 newStudyDTO를 StudyController단의
	 *              멤버변수 newStudy에 저장한다.
	 */
	@RequestMapping(value = "/insertStudy", method = RequestMethod.POST)
	public ResponseEntity<String> insertStudy(@RequestBody StudyBoardDTO newStudyDTO) {

		ResponseEntity<String> result = null;

		logger.info("insertStudy: 새로 추가할 스터디 모집글" + newStudyDTO.toString());

		// 새로추가할 newStudyDTO를 멤버변수 newStudy에 저장
		newStudy = newStudyDTO;
		logger.info("insertStudy: 새로 추가할 스터디 모집글" + newStudy.toString());

		if (newStudy != null) {
			result = new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>(HttpStatus.CONFLICT);
		}

		return result;

	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : StuStackVO newStack - 새로 저장할 스터디 모임글에서 공부할 스터디 언어(스택)
	 * @return : String - 스터디 모임글+스터디 언어 인서트 성공하면 리스트 페이지로 이동
	 * @description :insertStudy 메서드가 먼저 수행된 뒤에 수행되는 메서드 1) insertStudy 메서드가 성공적으로
	 *              수행되면 newStudy에 새로 insert할 스터디 모임글 객체가 담겨져 있다. 2) 서비스단에서 스터디 모임글이
	 *              먼저 insert 된 후 스터디 언어가 insert되도록 한다.
	 */
	@PostMapping(value = "/insertStudyWithStack")
	public String insertStudyWithStack(StuStackVO newStack) throws Exception {
		String result = null;

		if (stuService.insertStudyWithStack(newStudy, newStack) == 1) {
			result = "redirect:/study/listAll";
			logger.info("스터디테이블, 스터디스택 인서트 성공");
			// newStudy = null;
		}

		return result;
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : int stuNo
	 * @param : Model model
	 * @param : HttpServletRequest req
	 * @param : HttpServletResponse resp
	 * @return : String
	 * @description : stuNo번째 스터디글의 상세페이지를 불러오는 메서드 비회원 또는 로그인한 유저가 bType 게시판의
	 *              stuNo번째 스터디글 상세페이지를 조회했을 때 오늘 조회한 기록이 있는지 체크하기 위해서 비회원의 경우엔
	 *              userId에 세션아이디를, 로그인한 유저의 경우에는 유저아이디를 넣어준다.
	 */
	@GetMapping("/viewStudyBoard")
	public String viewStudyBoard(@RequestParam("stuNo") int stuNo, Model model, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {

		// 유저 정보
		String userId = null;
		MemberVO loginMember = (MemberVO) req.getSession().getAttribute("loginMember");

		// 로그인한 유저의 경우
		if (loginMember != null) {
			userId = loginMember.getUserId();
			// System.out.println("로그인된 유저아이디:" + userId);
		} else if (haveCookie(req, "rses") == null) {
			// 비회원의 경우 쿠키가 저장되어있는지 검사
			// rses라는 이름의 쿠키가 없다면
			String sesId = req.getSession().getId(); // 세션아이디 저장
			userId = sesId; // sesId를 userId에 저장
			// sesId라는 이름의 쿠키 저장
			saveCookie(resp, sesId);
		} else {
			// rses라는 이름의 쿠키가 있다면, 있던 쿠키값 그대로 쓰기
			userId = haveCookie(req, "rses");
		}

		logger.info(userId + "가" + stuNo + "번 글을 조회한다");

		Map<String, Object> result = stuService.selectStudyByStuNo(stuNo, userId, 2);

		model.addAttribute("studyList", (StudyBoardVO) result.get("studyList"));
		model.addAttribute("stuStackList", (List<StuStackDTO>) result.get("stuStackList"));

		return "study/viewStudyBoard";

	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : int stuNo
	 * @param : Model model
	 * @return : void
	 * @description : stuNo번째 글을 수정하는 페이지로 이동하는 메서드
	 *
	 */
	@GetMapping("/modifyStudyBoard")
	public void modifyStudyBoard(@RequestParam("stuNo") int stuNo, Model model) throws Exception {
		logger.info(stuNo + "번 글을 수정하는 페이지로 이동");

		// 스터디 목록
		StudyBoardVO studyList = stuService.selectStudyByStuNo(stuNo);

		// 스터디 No번째글 스터디 언어 목록
		List<StuStackDTO> stuStackListByNo = new ArrayList<StuStackDTO>();

		this.stuStackList.clear(); // 비워주기
		this.stuStackList = stuStackListByNo; // 스터디 언어 목록 멤버변수에 넣어주기

		// stuNo를 넘겨주어 공부할 언어 정보를 가져오자
		stuStackListByNo.addAll(stuService.selectAllStudyStack(studyList.getStuNo()));

		// stuNo번째 공부할 언어중 chooseStack만 담는 배열
		List<Integer> chooseStack = new ArrayList<Integer>();

		// stuNo번째 공부할 언어중 stuStackNo만 담는 배열
		List<Integer> stuStackNo = new ArrayList<Integer>();

		for (StuStackDTO stuStack : stuStackListByNo) {
			chooseStack.add(stuStack.getChooseStack());
			stuStackNo.add(stuStack.getStuStackNo());
		}
		// stack테이블의 모든 값들을 가져오자
		List<StackVO> stackList = stuService.selectAllStack();
		System.out.println(stackList.toString());

		System.out.println(stuStackListByNo.toString());
		model.addAttribute("studyList", studyList); // 현재 스터디 모임글
		model.addAttribute("chooseStack", chooseStack); // 현재 스터디 모임글에서 선택된 스택만(스터디 언어)
		model.addAttribute("stuStackNo", stuStackNo); // 현재 스터디 모임글의 선택된 stuStackNo만(pk)
		model.addAttribute("stackList", stackList); // 전체 스택 테이블(셀렉트 박스를 세팅을 위한)

	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : StudyBoardDTO modifyStudyDTO
	 * @return : ResponseEntity<String> - 성공하면 view단에 success 반환
	 * @description : 스터디 수정 버튼을 누르면 이 메서드가 먼저 수행되어 modifyStudyDTO를 StudyController의
	 *              멤버변수 newStudy에 저장한다.
	 */
	@PostMapping("/modifyStudy")
	public ResponseEntity<String> modifyStudy(@RequestBody StudyBoardDTO modifyStudyDTO) {

		ResponseEntity<String> result = null;

		newStudy = modifyStudyDTO;

		logger.info("modifyStudy: 수정할 스터디 모집글" + newStudy.toString());

		if (newStudy != null) {
			result = new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>(HttpStatus.CONFLICT);
		}

		return result;
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @return : String - 모임글 수정 성공하면 해당 모임글의 상세페이지로 이동
	 * @description : modifyStudy 메서드가 먼저 수행된 뒤에 수행되는 메서드 1) modifyStudy 메서드가 성공적으로
	 *              수행되면 newStudy에 수정할 스터디 모임글 객체가 담겨져 있다. 2) 서비스단에서 스터디 모임글이 먼저
	 *              update된 후, 3) 스터디 언어가 delete 혹은 insert 되도록 한다.
	 */
	@PostMapping(value = "/modifyStudyWithStack")
	public String modifyStudyWithStack() throws Exception {

		String result = null;

		// 수정에 성공했다면 수정한 상세 페이지로 이동
		if (stuService.modifyStudyWithStack(newStudy, this.stuStackList) == 1) {
			result = "redirect:/study/viewStudyBoard?stuNo=" + newStudy.getStuNo();
			logger.info("스터디테이블, 스터디스택 업데이트 성공");
			// newStudy = null;
		}

		return result;
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : int newStuStack - 새로 추가 마킹을 남길 stuStack
	 * @return : ResponseEntity<String> - 성공했을 경우 newMarkSuccess, 실패했을 경우
	 *         newMarkfail 반환
	 * @description : 스터디 모임글 수정시에 스터디 언어 새로 추가했을 때 해당 스터디 언어를 인서트처리 하겠다는 마킹을 남긴다.
	 */
	@RequestMapping(value = "/modifyNewMark", method = RequestMethod.POST)
	public ResponseEntity<String> modifyNewMark(@RequestParam("newStuStack") int newStuStack) {
		ResponseEntity<String> result = null;
		System.out.println(newStuStack + "스터디 언어에 new 처리 마킹하자");

		boolean isFind = false;

		stuStackList.add(new StuStackDTO(0, stuStackList.get(0).getStuBoardNo(), newStuStack, null, false, false));

		for (StuStackDTO stack : this.stuStackList) {

			if (stack.getChooseStack() == newStuStack) {
				// stack의 언어 넘버와 view단에서 넘어온 추가할 스터디 언어 넘버가 같다면
				stack.setNew(true); // 새로 인서트하겠다는 마킹 남기기
				isFind = true;
				break;
			}
		}

		System.out.println("===================== 수정시 스터디 언어 new 마킹 =======================");
		for (StuStackDTO stack : this.stuStackList) {
			System.out.println(stack.toString());
		}
		System.out.println("====================================================================");

		if (!isFind) { // 언어를 못찾았다면
			result = new ResponseEntity<String>("newMarkfail", HttpStatus.CONFLICT);
		} else {
			result = new ResponseEntity<String>("newMarkSuccess", HttpStatus.OK);
		}

		return result;
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : int remStuStack - 삭제 처리 마킹을 남길 stuStack
	 * @return : ResponseEntity<String> - 성공했을 경우 remMarkSuccess, 실패했을 경우
	 *         remMarkFail 반환
	 * @description : 스터디 모임글 수정시 스터디 언어 삭제버튼을 눌렀을 때, 해당 스터디 언어를 삭제처리 하겠다는 마킹을 남긴다.
	 */
	@RequestMapping(value = "/modifyRemMark", method = RequestMethod.POST)
	public ResponseEntity<String> modifyRemMark(@RequestParam("remStuStack") int remStuStack) {
		ResponseEntity<String> result = null;
		System.out.println(remStuStack + "스터디 언어에 delete 처리 마킹하자");

		boolean isFind = false;

		for (StuStackDTO stack : this.stuStackList) {
			if (stack.getChooseStack() == remStuStack) {
				// stack의 언어 넘버와 view단에서 넘어온 삭제할 스터디 언어 넘버가 같다면
				stack.setDelete(true); // 삭제하겠다는 마킹 남기기
				isFind = true;
				break;
			}
		}

		System.out.println("===================== 수정시 스터디 언어 delete 마킹 =======================");
		for (StuStackDTO stack : this.stuStackList) {
			System.out.println(stack.toString());
		}
		System.out.println("====================================================================");

		if (!isFind) { // 언어를 못찾았다면
			result = new ResponseEntity<String>("remMarkFail", HttpStatus.CONFLICT);
		} else {
			result = new ResponseEntity<String>("remMarkSuccess", HttpStatus.OK);
		}

		return result;
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : int stuNo
	 * @return : String
	 * @description : stuNo번째 글을 삭제하는 메서드
	 */
	@GetMapping("/deleteStudy")
	public String deleteStudyBoard(@RequestParam("stuNo") int stuNo) throws Exception {

		String result = null;

		logger.info(stuNo + "번 글을 삭제하자");
		if (stuService.deleteStudyBoard(stuNo) == 1) {
			result = "/study/listAll";
			logger.info(stuNo + "번 글을 삭제 성공!");
		}

		return "redirect:" + result;
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : List<String> studyStackList
	 * @param : Model model
	 * @return : ResponseEntity<Map<String, Object>> - 스터디 모임글 리스트와 스터디 언어 리스트를 담고있는
	 *         Map
	 * @description : 스터디 언어로 필터링한 뒤 게시글 가져오는 메서드
	 */
	@ResponseBody // json으로 응답 해줄때 쓰는 어노테이션
	@RequestMapping(value = "/searchStudyByStack", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Map<String, Object>> searchStudyByStack(
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestBody List<String> studyStackList,
			Model model) {
		// @RequestBody는 json으로 요청 받을때 쓰는 어노테이션

		if (pageNo <= 0) {
			pageNo = 1;
		}

		logger.info(studyStackList.toString());
		ResponseEntity<Map<String, Object>> result = null;
		Map<String, Object> map = null;

		try {
			map = stuService.searchStudyByStack(studyStackList, pageNo);
			System.out.println("Controller 검색할 스터디 언어 갯수 :" + studyStackList.size());

			if (studyStackList.size() > 0) {
				result = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			} else {
				result = new ResponseEntity<Map<String, Object>>(HttpStatus.CONFLICT);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : HttpServletResponse resp
	 * @param : String sesId
	 * @return : void
	 * @description : sesId값을 저장한 쿠키 "rses"를 저장 (하루짜리)
	 */
	private void saveCookie(HttpServletResponse resp, String sesId) {
		Cookie sesCookie = new Cookie("rses", sesId); // sesId값을 rses라는 쿠키 이름으로 저장
		sesCookie.setPath("/");
		sesCookie.setMaxAge(60 * 60 * 24);
		resp.addCookie(sesCookie);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : HttpServletRequest req
	 * @param : String cookieName
	 * @return : String
	 * @description : 쿠키가 있는지 없는지 검사
	 */
	private String haveCookie(HttpServletRequest req, String cookieName) {
		String result = null;

		// 쿠키가 여러개니까 쿠키가 있는동안 반복
		for (Cookie c : req.getCookies()) {
			if (c.getName().equals(cookieName)) {
				// 쿠키의 이름이 넘겨받은 쿠키이름과 같다면 쓰던 쿠키값 반환
				result = c.getValue();
			}
		} // 다 돌리고도 없다면 쿠키가 없는 경우. null 반환

		return result;
	}

}
