package com.dodeveloper.study.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dodeveloper.reply.service.ReplyService;
import com.dodeveloper.reply.vodto.ReplyDTO;
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
//		logger.info(stuNo + "번 게시글의 댓글 가져오기");

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
	
	/**
		* @author : yeonju
		* @date : 2024. 5. 25.
		* @param : int bNo - 스터디 게시판 번호
		* @param : ReplyDTO newReply - 새로 추가될 댓글 정보를 담고있는 객체
		* @return : ResponseEntity<String> - 성공시 HttpStatus.OK, 실패시 HttpStatus.CONFLICT 반환
		* @description : 스터디 게시판의 bNo번째 게시물에 새로운 댓글을 작성할 때 호출되는 메서드
	 */
	@PostMapping("/saveReply/{bNo}")
	public ResponseEntity<String> saveReply(@PathVariable("bNo") int bNo, @RequestBody ReplyDTO newReply){
		// @RequestBody : 알아서 ReplyDTO 의 매개변수 수집
		ResponseEntity<String> result = null;
		
		//몇번 게시글에 댓글을 작성할건지 세팅
		newReply.setBNo(bNo);
		//몇번 게시판에 댓글을 작성할건지 세팅
		newReply.setBType(2);
//		logger.info(bNo + "번 게시글에" + newReply.toString() + "댓글을 추가하자");
		
		try {
			if(rs.insertReplyStudy(newReply) ==1) {
				result = new ResponseEntity<String>("insertSuccess", HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<String>(HttpStatus.CONFLICT);
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
		* @author : yeonju
		* @date : 2024. 5. 25.
		* @param : int replyNo - 삭제할 댓글 번호 pk값
		* @return : ResponseEntity<String> - 성공시 HttpStatus.OK, 실패시 HttpStatus.CONFLICT 반환
		* @description : repyNo번째 댓글을 삭제 처리한다.
	 */
	@DeleteMapping("/deleteReply/{replyNo}/{stuNo}")
	public ResponseEntity<String> deleteReply(@PathVariable("replyNo") int replyNo,
			@PathVariable("stuNo") int stuNo){
		ResponseEntity<String> result = null;
//		logger.info(stuNo + "번째 게시글의 " + replyNo + "번 댓글을 삭제하자");
		
		try {
			if(rs.deleteReplyStudy(replyNo, stuNo) ==1) {
				result = new ResponseEntity<String>("deleteSuccess", HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<String>(HttpStatus.CONFLICT);
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
		* @author : yeonju
		* @date : 2024. 5. 27.
		* @param : int replyNo - 수정할 댓글 번호 pk값
		* @param : ReplyDTO modifyReply - 수정할 댓글의 내용을 담은 객체
		* @return : ResponseEntity<String> - 성공시 HttpStatus.OK, 실패시 HttpStatus.CONFLICT 반환
		* @description : repyNo번째 댓글을 수정한다.
	 */
	@RequestMapping(value = "/modifyReply/{replyNo}/{bNo}", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyReply(@PathVariable("replyNo") int replyNo, @PathVariable("bNo") int bNo,
			@RequestBody ReplyDTO modifyReply){
		
		ResponseEntity<String> result = null;
		
		modifyReply.setBNo(bNo);
		modifyReply.setBType(2);
//		logger.info(modifyReply.toString() + "댓글을 수정하자");
		
		try {
			if(rs.updateReply(modifyReply) ==1) {
				result = new ResponseEntity<String>("updateSuccess", HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<String>(HttpStatus.CONFLICT);
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
