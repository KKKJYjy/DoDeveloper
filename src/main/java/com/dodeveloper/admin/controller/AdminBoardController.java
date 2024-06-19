package com.dodeveloper.admin.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodeveloper.admin.dto.SearchCriteriaDTO;
import com.dodeveloper.admin.dto.NoticeDTO;
import com.dodeveloper.admin.service.AdminBoardService;
import com.dodeveloper.admin.vo.AdminArgBoardVO;
import com.dodeveloper.admin.vo.AdminLectureVO;
import com.dodeveloper.admin.vo.AdminReviewBoardVO;
import com.dodeveloper.admin.vo.AdminVO;
import com.dodeveloper.admin.vo.QnaBoardVO;
import com.dodeveloper.admin.vo.ReportVO;
import com.dodeveloper.etc.PagingInfo;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

import lombok.val;

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
		model.addAttribute("stuBoardList", (List<AdminVO>) returnMap.get("stuBoardList"));
		model.addAttribute("pagingInfo", (PagingInfo) returnMap.get("pagingInfo"));

		resultPage = "/admin/selectBoard";
	}

	@RequestMapping(value = "/lectureBoard", method = RequestMethod.GET)
	public void lectureBoard(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			SearchCriteriaDTO sc) throws Exception {
		logger.info("lectureBoard 조회");

		Map<String, Object> returnMap = null;

		String resultPage = null;

		// List<AdminLectureVO> lecBoardList = bService.getlistLectureBoard();

		if (pageNo <= 0) {
			pageNo = 1;
		}

		returnMap = bService.getlistLectureBoard(pageNo, sc);
		model.addAttribute("lecBoardList", (List<AdminLectureVO>) returnMap.get("lecBoardList"));
		model.addAttribute("pagingInfo", (PagingInfo) returnMap.get("pagingInfo"));

		resultPage = "/admin/lectureBoard";

	}

	@RequestMapping(value = "/algorithmBoard", method = RequestMethod.GET)
	public void algorithmBoard(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			SearchCriteriaDTO sc) throws Exception {
		logger.info("algorithmBoard 조회");

		Map<String, Object> returnMap = null;

		String resultPage = null;

		if (pageNo <= 0) {
			pageNo = 1;
		}

		returnMap = bService.getlistArgBoard(pageNo, sc);
		model.addAttribute("argBoardList", (List<AdminArgBoardVO>) returnMap.get("argBoardList"));
		model.addAttribute("pagingInfo", (PagingInfo) returnMap.get("pagingInfo"));

		resultPage = "/admin/algorithmBoard";

	}

	@RequestMapping(value = "/reviewBoard", method = RequestMethod.GET)
	public void reviewBoard(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			SearchCriteriaDTO sc) throws Exception {
		logger.info("reviewBoard 조회");

		Map<String, Object> returnMap = null;

		String resultPage = null;

		if (pageNo <= 0) {
			pageNo = 1;
		}

		// Map<String, Object> returnMap = bService.getlistRevBoard(pageNo);
		returnMap = bService.getlistRevBoard(pageNo, sc);
		model.addAttribute("revBoardList", (List<AdminReviewBoardVO>) returnMap.get("revBoardList"));
		model.addAttribute("pagingInfo", (PagingInfo) returnMap.get("pagingInfo"));

		resultPage = "/admin/reviewBoard";

	}

	@RequestMapping(value = "/noticeBoard", method = RequestMethod.GET)
	public void noticeBoard(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			SearchCriteriaDTO sc) throws Exception {
		logger.info("공지사항 조회");

		Map<String, Object> returnMap = null;

		String resultPage = null;

		if (pageNo <= 0) {
			pageNo = 1;
		}
		

		returnMap = bService.getlistNotcBoard(pageNo, sc);
		model.addAttribute("notcBoardList", (List<NoticeDTO>) returnMap.get("notcBoardList"));
		model.addAttribute("pagingInfo", (PagingInfo) returnMap.get("pagingInfo"));

		resultPage = "/admin/noticeBoard";

	}
	
	

	@RequestMapping(value = "/delete")
	public String removeStuBoard(HttpServletRequest request) throws Exception {

		String[] remStuBoard = request.getParameterValues("valueArr");
		int size = remStuBoard.length;
		for (int i = 0; i < size; i++) {
			bService.studeleteBoard(remStuBoard[i]);
		}

		return "redirect:selectBoard";
	}

	@RequestMapping(value = "/algDelete")
	public String removealgBoard(HttpServletRequest request) throws Exception {

		String[] remalgBoard = request.getParameterValues("valueArr");
		int size = remalgBoard.length;
		for (int i = 0; i < size; i++) {
			bService.algdeleteBoard(remalgBoard[i]);
		}

		return "redirect:algorithmBoard";
	}

	@RequestMapping(value = "/lecDelete")
	public String removeLecBoard(HttpServletRequest request) throws Exception {

		String[] remLecBoard = request.getParameterValues("valueArr");
		int size = remLecBoard.length;
		for (int i = 0; i < size; i++) {
			bService.lecdeleteBoard(remLecBoard[i]);
		}

		return "redirect:lectureBoard";
	}

	@RequestMapping(value = "/revDelete")
	public String removeRevBoard(HttpServletRequest request) throws Exception {

		String[] remRevBoard = request.getParameterValues("valueArr");
		int size = remRevBoard.length;
		for (int i = 0; i < size; i++) {
			bService.revdeleteBoard(remRevBoard[i]);
		}

		return "redirect:reviewBoard";
	}

	@RequestMapping(value = "/notcDelete")
	public String removeNotcBoard(HttpServletRequest request) throws Exception {

		String[] remNotcBoard = request.getParameterValues("valueArr");
		int size = remNotcBoard.length;
		for (int i = 0; i < size; i++) {
			bService.notcdeleteBoard(remNotcBoard[i]);
		}

		return "redirect:noticeBoard";
	}


	@RequestMapping(value = "/qnaDelete")
	public String removeQna(HttpServletRequest request) throws Exception {

		String[] remQna = request.getParameterValues("valueArr");
		int size = remQna.length;
		for (int i = 0; i < size; i++) {
			bService.qnaDelete(remQna[i]);
		}

		return "redirect:inquiry";

	}

	@RequestMapping(value = "/noticePOST", method = RequestMethod.POST)
	public String noticeBoard(NoticeDTO newBoard) throws Exception {
		logger.info("controller : " + newBoard.toString() + "글 저장");

		String returnPage = "/admin/noticeBoard";

		if (bService.writeNoticeBoard(newBoard)) {
			returnPage = "redirect:" + returnPage + "?status=writeSuccess";
		} else {
			returnPage = "redirect:" + returnPage + "?status=writeFail";
		}

		return returnPage;

	}

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public void report(Model model) throws Exception {
		logger.info("신고내역 조회");

		List<ReportVO> reportList = bService.getReport();

		model.addAttribute("reportList", reportList);

	}

	@RequestMapping(value = "/updateNotice", method = RequestMethod.GET)
	public String modifyNotcBoard(Model model, @RequestParam("boardNo") int boardNo) throws Exception {

		logger.info("공지사항 수정페이지 호출");

		Map<String, Object> map = bService.getNotcByBoardNo(boardNo);

		model.addAttribute("result", map);

//		if (bService.modifyNotcBoard(mdBoard)) {
//			returnPath = "redirect:/adminView/noticeViewDetail?boardNo=" + mdBoard.getBoardNo();
//		}

		return "/admin/updateNotice";

	}

	@RequestMapping(value = "/updatePost", method = RequestMethod.POST)
	public String mdBoardPOST(NoticeDTO mdBoard) throws Exception {
		System.out.println(mdBoard.toString() + "을 수정");
		bService.modifyNotcBoard(mdBoard);

		return "redirect:/admin/noticeBoard";
	}

	@RequestMapping(value = "/inquiry", method = RequestMethod.GET)
	public void qnaBoard(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo) throws Exception {
		
		Map<String, Object> returnMap = null;

		String resultPage = null;

		if (pageNo <= 0) {
			pageNo = 1;
		}
		
		returnMap = bService.getQnaBoard(pageNo);
		
		model.addAttribute("qnaList", (List<QnaBoardVO>) returnMap.get("qnaList"));
		model.addAttribute("pagingInfo", (PagingInfo) returnMap.get("pagingInfo"));

		resultPage = "/admin/inquiry";
		
	}

	@PostMapping("/insertPenalty")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteBoard(@RequestParam("btypeNo") int btypeNo,
	        @RequestParam("boardNo") int boardNo, @RequestParam("deleteReason") String deleteReason, @RequestParam("userId") String userId) throws Exception {

	    System.out.println("btypeNo: " + btypeNo);
	    System.out.println("boardNo: " + boardNo);
	    System.out.println("userId: " + userId);
	    System.out.println("deleteReason: " + deleteReason);
	    
	    boolean success = bService.insertOrUpdatePenaltyRecord(deleteReason, userId, btypeNo, boardNo);
	    Map<String, Object> response = new HashMap<>();
	    response.put("success", success);

	    if (success) {
	        return ResponseEntity.ok(response); 
	    } else {
	        return ResponseEntity.badRequest().body(response); 
	    }
	}
	
	@PostMapping("/deleteReport")
	public ResponseEntity<String> deleteReport(@RequestBody List<Long> selectedIds) throws Exception {
	    // 서비스 계층에 선택된 데이터 ID들을 전달하여 삭제 처리
	    boolean success = bService.deleteSelectedData(selectedIds);

	    if (success) {
	        return ResponseEntity.ok("삭제 성공");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
	    }
	}	
	
}
