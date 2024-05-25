package com.dodeveloper.study.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dodeveloper.reply.service.ReplyService;
import com.dodeveloper.reply.vodto.ReplyVO;

@RestController
@RequestMapping("/studyReply")
public class StudyReplyController {

	private static final Logger logger = LoggerFactory.getLogger(StudyReplyController.class);
	
	@Autowired
	private ReplyService rs;
	
	/**
		* @author : yeonju
		* @date : 2024. 5. 25.
		* @param : int stuNo
		* @return : ResponseEntity<List<ReplyVO>>
		* @description : stuNo번째 게시글의 모든 댓글 리스트 반환
	 */
	@GetMapping("/replyAll/{stuNo}")
	public ResponseEntity<List<ReplyVO>> replyAll(@PathVariable("stuNo") int stuNo) {
		// ResponseEntity: <List<ReplyVO>를 json으로 만들어서 반환할 수 있는 애
		// @PathVariable: 메서드 매개변수가 URI 템플릿 변수에 바인딩되어야 함을 나타내는 어노테이션
		logger.info(stuNo + "번 게시글의 댓글 가져오기");

		ResponseEntity<List<ReplyVO>> result = null;
		List<ReplyVO> replyList = null;
		try {
			replyList = rs.selectAllReply(stuNo, 2);
			result = new ResponseEntity<List<ReplyVO>>(replyList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<List<ReplyVO>>(HttpStatus.CONFLICT);
		}
		
		return result;
	}
}
