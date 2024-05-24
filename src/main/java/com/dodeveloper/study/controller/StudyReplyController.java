package com.dodeveloper.study.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dodeveloper.reply.vodto.ReplyVO;

@RestController
@RequestMapping("/studyReply")
public class StudyReplyController {


	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : int stuNo 
		* @return : ResponseEntity<List<ReplyVO>>
		* @description : stuNo번째 게시글의 모든 댓글 리스트 반환
	 */
	@GetMapping("/replyAll")
	public ResponseEntity<List<ReplyVO>> replyAll(@PathVariable("stuNo") int stuNo) {
		// ResponseEntity: <List<ReplyVO>를 json으로 만들어서 반환할 수 있는 애
		// @PathVariable: 메서드 매개변수가 URI 템플릿 변수에 바인딩되어야 함을 나타내는 어노테이션
		
		System.out.println(stuNo + "번 게시글의 댓글 가져오기");

		ResponseEntity<List<ReplyVO>> result = null;
		
		

		return result;
	}
}
