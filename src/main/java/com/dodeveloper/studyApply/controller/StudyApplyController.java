package com.dodeveloper.studyApply.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dodeveloper.studyApply.service.StudyApplyService;
import com.dodeveloper.studyApply.vodto.StudyApplyVO;

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
	public String insertApply(StudyApplyVO newApply) {
		String result = "";
		
		logger.info(newApply.toString() + "을 신청하자");
		try {
			
			if(saService.insertApply(newApply) == 1) {
				result = "redirect:/study/viewStudyBoard?stuNo=" + newApply.getStuNo() + "&status=success";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
