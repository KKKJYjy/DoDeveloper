package com.dodeveloper.studyApply.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dodeveloper.studyApply.service.StudyApplyService;
import com.dodeveloper.studyApply.vodto.StudyApplyDTO;

@Controller
@RequestMapping("/studyApply")
public class StudyApplyController {

	private static final Logger logger = LoggerFactory.getLogger(StudyApplyController.class);

	@Autowired
	StudyApplyService saService;

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : StudyApplyVO newApply - 신청 내용을 담을 객체
	 * @return : String - 신청 성공하면 해당 모임글 상세페이지로 success 파라메터 달고 이동
	 * @description : 해당 스터디 모임글에 신청 내용을 insert 한다.
	 */
	@RequestMapping(value = "/insertApply", method = RequestMethod.POST)
	public String insertApply(StudyApplyDTO newApply) {
		String result = "";

		logger.info(newApply.toString() + "을 신청하자");
		try {

			if (saService.insertApply(newApply) == 1) {
				result = "redirect:/study/viewStudyBoard?stuNo=" + newApply.getStuNo() + "&status=success";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 6. 3.
	 * @param : int applyNo
	 * @return : String
	 * @description : applyNo번째 스터디 신청을 수락한다 수락 성공시 파라메터 applyAccept=success를 달고
	 *              myStudyList 페이지로 이동
	 */
	@GetMapping("/acceptApply/{applyNo}")
	public String acceptApply(@PathVariable("applyNo") int applyNo) {
		String result = "";
		logger.info(applyNo + "번째 스터디 신청을 수락하자");

		try {
			if (saService.acceptApply(applyNo) == 1) {
				System.out.println(applyNo + "번째 스터디 신청 수락 성공");
				result = "redirect:/mypage/myStudyList?applyAccept=success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 6. 3.
	 * @param : int applyNo
	 * @return : String
	 * @description : applyNo번째 스터디 신청을 거절한다  
	 * 성공시 파라메터 applyRefuse=success를 달고 myStudyList 페이지로 이동
	 */
	@GetMapping("/refuseApply/{applyNo}")
	public String refuseApply(@PathVariable("applyNo") int applyNo) {
		String result = "";
		logger.info(applyNo + "번째 스터디 신청을 거절하자");

		try {
			if (saService.refuseApply(applyNo) == 1) {
				System.out.println(applyNo + "번째 스터디 신청 거절 성공");
				result = "redirect:/mypage/myStudyList?applyRefuse=success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
