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
