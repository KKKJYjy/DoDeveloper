package com.dodeveloper.study.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;

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

import com.dodeveloper.study.service.StudyService;
import com.dodeveloper.study.vodto.StuStackVO;
import com.dodeveloper.study.vodto.StudyBoardDTO;
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.study.vodto.SearchStudyDTO;
import com.dodeveloper.study.vodto.StackVO;
import com.dodeveloper.study.vodto.StuStackDTO;

@Controller
@RequestMapping("/study")
public class StudyContoller {

	private static final Logger logger = LoggerFactory.getLogger(StudyContoller.class);

	private StudyBoardDTO newStudy;

	@Autowired
	StudyService stuService;

	// 스터디 모든 목록을 불러오는 메서드 + 검색 기능 추가
	@GetMapping(value = "/listAll")
	public void listAllGet(Model model, SearchStudyDTO sDTO) throws Exception {
		logger.info("listAll View.");

		// 스터디 목록
		List<StudyBoardVO> studyList = stuService.selectAllList(sDTO);

		// 스터디 No번째글 스터디 언어 목록
		List<StuStackDTO> stuStackList = new ArrayList<StuStackDTO>();

		for (StudyBoardVO s : studyList) {
			// stuNo를 넘겨주어 공부할 언어 정보를 가져오자

			stuStackList.addAll(stuService.selectAllStudyStack(s.getStuNo()));

			// System.out.println(s.getStuNo());
		}

		//System.out.println(stuStackList.toString());
		model.addAttribute("studyList", studyList);
		model.addAttribute("stuStackList", stuStackList);
	}

	// 스터디 작성 페이지로 이동하는 메서드
	@GetMapping(value = "/writeStudyBoard")
	public void writeBoard() {
		logger.info("writeStudyBoard View.");

	}

	// 스터디 작성 버튼을 누르면 study 테이블에 인서트
	@RequestMapping(value = "/insertStudy", method = RequestMethod.POST)
	public ResponseEntity<String> insertStudy(@RequestBody StudyBoardDTO newStudyDTO) {

		ResponseEntity<String> result = null;
		
		logger.info("insertStudy: 새로 추가할 스터디 모집글" + newStudyDTO.toString());

		// 새로추가할 newStudyDTO를 멤버변수 newStudy에 저장
		newStudy = newStudyDTO;
		logger.info("insertStudy: 새로 추가할 스터디 모집글" + newStudy.toString());

		if(newStudy != null) {
			result = new ResponseEntity<String>("success", HttpStatus.OK);
		}else {
			result = new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		
		return result;

	}

	// 스터디 작성 버튼을 누르면 stuStack 테이블에 인서트
	@PostMapping(value = "/insertStack")
	public String insertStack(StuStackVO newStack) throws Exception {
		String result = null;
		
		// StuStackVO의 stuBoardNo값 세팅
		newStack.setStuBoardNo(stuService.selectNextStuNo()+1);
		System.out.println("insertStack: 추가할 스터디 스택 게시글 번호" + newStack.getStuBoardNo());
		int[] chooseStacks = newStack.getChooseStack();
		
		logger.info("insertStack: 새로 추가할 스터디 스택가져오자" + newStack.toString());
		logger.info("insertStack: 새로 추가할 스터디 스터디 모집글" + newStudy.toString());
		
//		if (chooseStacks != null) {
		if (stuService.insertNewStudy(newStudy) == 1) {
			System.out.println("스터디글추가성공");
			
			
			for (int chooseStack : chooseStacks) {
				if (stuService.insertNewStack(newStack.getStuBoardNo(), chooseStack) == 1) {
					System.out.println("스택추가성공");
					result = "redirect:/study/listAll";
					newStudy = null;
				}
			}
		}
//		}

		return result;
	}

	// stuNo번째 스터디 글을 불러오는 메서드
	@GetMapping("/viewStudyBoard")
	public void viewStudyBoard(@RequestParam("stuNo") int stuNo, Model model) throws Exception {

		logger.info(stuNo + "번 글을 조회하자");

		// 스터디 목록
		StudyBoardVO studyList = stuService.selectStudyByStuNo(stuNo);

		// 스터디 No번째글 스터디 언어 목록
		List<StuStackDTO> stuStackList = new ArrayList<StuStackDTO>();

		// stuNo를 넘겨주어 공부할 언어 정보를 가져오자

		stuStackList.addAll(stuService.selectAllStudyStack(studyList.getStuNo()));

		System.out.println(stuStackList.toString());
		model.addAttribute("studyList", studyList);
		model.addAttribute("stuStackList", stuStackList);

	}

	// stuNo번째 글을 수정하는 페이지로 이동하는 메서드
	@GetMapping("/modifyStudyBoard")
	public void modifyStudyBoard(@RequestParam("stuNo") int stuNo, Model model) throws Exception {
		logger.info(stuNo + "번 글을 수정하는 페이지로 이동");

		// 스터디 목록
		StudyBoardVO studyList = stuService.selectStudyByStuNo(stuNo);

		// 스터디 No번째글 스터디 언어 목록
		List<StuStackDTO> stuStackListByNo = new ArrayList<StuStackDTO>();

		// stuNo를 넘겨주어 공부할 언어 정보를 가져오자
		stuStackListByNo.addAll(stuService.selectAllStudyStack(studyList.getStuNo()));

		// stuNo번째 공부할 언어중 chooseStack만 담는 배열
		List<Integer> chooseStack = new ArrayList<Integer>();

		for (StuStackDTO stuStack : stuStackListByNo) {
			chooseStack.add(stuStack.getChooseStack());
		}
		// stack테이블의 모든 값들을 가져오자
		List<StackVO> stackList = stuService.selectAllStack();
		System.out.println(stackList.toString());

		System.out.println(stuStackListByNo.toString());
		model.addAttribute("studyList", studyList);
		model.addAttribute("stackList", stackList);
		model.addAttribute("chooseStack", chooseStack);
	}

	// stuNo번째 글을 삭제하는 메서드
	@GetMapping("/deleteStudy")
	public String deleteStudyBoard(@RequestParam("stuNo") int stuNo) throws Exception {
		
		String result = null;
		
		logger.info(stuNo + "번 글을 삭제하자");
		if(stuService.deleteStudyBoard(stuNo) == 1) {
			result = "/study/listAll";
			logger.info(stuNo + "번 글을 삭제 성공!");
		}
		
		return "redirect:" + result;
	}

}
