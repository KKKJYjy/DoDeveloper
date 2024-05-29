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


@RestController 
@RequestMapping("/qnaReply")
public class QnaReplyController {
	
	private static final Logger logger = LoggerFactory.getLogger(QnaReplyController.class);

	@Autowired
	private AdminBoardService bService;
	
	@RequestMapping(value="/qnaAll", method = RequestMethod.GET)
	public ResponseEntity<List<QnaReplyVO>> getAllReplies(@PathVariable("no") int no) {

		System.out.println(no + "번 글의 댓글을 가져오자");

		ResponseEntity<List<QnaReplyVO>> result = null;

		try {
			List<QnaReplyVO> lst = bService.selectReply(no);

			result = new ResponseEntity<List<QnaReplyVO>>(lst, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();

			result = new ResponseEntity<List<QnaReplyVO>>(HttpStatus.CONFLICT);
		}

		return result;


	}
	
	@RequestMapping(value="/replyPost", method = RequestMethod.POST)
	public ResponseEntity<String> saveReply(@PathVariable("bNo") int bNo, @RequestBody QnaReplyVO replyVo) {
		System.out.println(replyVo.toString() + "댓글을 저장하자" + ", " + bNo);
		
		ResponseEntity<String> result = null;
		
		replyVo.setBNo(bNo);
		
		try {
			if(bService.insertReply(replyVo) == 1) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			result = new ResponseEntity<String>("fail", HttpStatus.CONFLICT);
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/qnaModifyReply", method = RequestMethod.PUT)
	public ResponseEntity<String> updateReply(@PathVariable("replyNo") int replyNo, @RequestBody QnaReplyVO newReply) {
		System.out.println(newReply.toString() + "로 댓글을 수정하자, " + replyNo);
		
		ResponseEntity<String> result = null;
		
		try {
			if (bService.updateReply(newReply) == 1) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			result = new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
		}
		
		return result;
	}
	

	@RequestMapping(value = "/qnaDelete")
	public ResponseEntity<String> deleteReply(@PathVariable("replyNo") int replyNo) {
		System.out.println(replyNo + "번 댓글을 삭제하자");
		
        ResponseEntity<String> result = null;
		
		try {
			if (bService.deleteReply(replyNo) == 1) {
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
