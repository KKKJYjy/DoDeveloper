package com.dodeveloper.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dodeveloper.admin.service.AdminBoardService;
import com.dodeveloper.admin.vo.QnaReplyVO;
import com.dodeveloper.reply.service.ReplyService;
import com.dodeveloper.reply.vodto.ReplyDTO;
import com.dodeveloper.reply.vodto.ReplyVO;


@RestController 
@RequestMapping("/qnaReply")
public class QnaReplyController {
	
	private static final Logger logger = LoggerFactory.getLogger(QnaReplyController.class);

	@Autowired
	private ReplyService rService;
	
	@RequestMapping(value="/all/{boardNo}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> getAllReplies(@PathVariable("bNo") int bNo, @PathVariable("bType") int bType) {

		System.out.println(bNo + "번 글의 댓글을 가져오자");

		ResponseEntity<List<ReplyVO>> result = null;

		try {
			List<ReplyVO> lst = rService.selectAllReply(bNo, bType);

			result = new ResponseEntity<List<ReplyVO>>(lst, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();

			result = new ResponseEntity<List<ReplyVO>>(HttpStatus.CONFLICT);
		}

		return result;


	}
	
	@RequestMapping(value="/{bNo}", method = RequestMethod.POST)
	public ResponseEntity<String> saveReply(@PathVariable("bNo") int bNo, @PathVariable("bType") int bType, @RequestBody ReplyDTO replydto) {
		System.out.println(replydto.toString() + "댓글을 저장하자" + ", " + bNo);
		
		ResponseEntity<String> result = null;
		
		replydto.setBNo(bNo);
		
		try {
			if(rService.insertReply(replydto) == 1) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			result = new ResponseEntity<String>("fail", HttpStatus.CONFLICT);
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/{replyNo}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateReply(@PathVariable("replyNo") int replyNo, @RequestBody ReplyDTO newReply) {
		System.out.println(newReply.toString() + "로 댓글을 수정하자, " + replyNo);
		
		ResponseEntity<String> result = null;
		
		try {
			if (rService.updateReply(newReply) == 1) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			result = new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
		}
		
		return result;
	}
	

	@RequestMapping(value = "/{replyNo}")
	public ResponseEntity<String> deleteReply(@PathVariable("replyNo") int replyNo) {
		System.out.println(replyNo + "번 댓글을 삭제하자");
		
        ResponseEntity<String> result = null;
		
		try {
			if (rService.deleteReply(replyNo) == 1) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			result = new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
		}
		
		return result;
		
		
	}
}
