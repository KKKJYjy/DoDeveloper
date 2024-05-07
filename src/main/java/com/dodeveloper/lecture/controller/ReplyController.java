package com.dodeveloper.lecture.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodeveloper.reply.service.ReplyService;
import com.dodeveloper.reply.vodto.ReplyVO;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	private static final Logger logger = LoggerFactory.getLogger(LectureBoardController.class);
	
	@Autowired
	private ReplyService rService;
	
	/**
	 * @methodName : selectAllReply
	 * @author :
	 * @date : 2024.05.07
	 * @param : @PathVariable("bNo") int bNo - 게시글 번호를 메서드의 매개변수로 전달하여 사용
	 * @return : ResponseEntity<List<ReplyVO>>
	 * @description : (Read) 부모글이 ?번 글에 대한 모든 댓글을 가져오는 메서드
	 */
	@GetMapping("/list/{bNo}")
	public ResponseEntity<List<ReplyVO>> selectAllReply(@PathVariable("bNo") int bNo) {
		try {
			// Service단에서 게시글 번호에 대한 모든 댓글을 가져와서 replyList 담고,
			List<ReplyVO> replyList = rService.selectAllReply(bNo);
			// ResponseEntity에 담아서 반환하는데 HTTP상태 코드는 200(OK)이다.
			return new ResponseEntity<List<ReplyVO>>(replyList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// Service단에서 게시글 번호에 대한 모든 댓글을 못가져왔을 경우 빈 목록을 반환
			return new ResponseEntity<List<ReplyVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
