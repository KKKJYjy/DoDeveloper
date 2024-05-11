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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dodeveloper.reply.service.ReplyService;
import com.dodeveloper.reply.vodto.ReplyDTO;
import com.dodeveloper.reply.vodto.ReplyVO;

@RestController
@RequestMapping("/reply")
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(LectureBoardController.class);

	@Autowired
	private ReplyService rService; // 스프링 컨테이너에서 ReplyService 객체를 찾아 주입

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

	/**
	 * @methodName : insertReply
	 * @author :
	 * @date : 2024.05.08
	 * @param : @PathVariable("bNo") int bNo - 게시글 번호
	 * @param : @RequestBody ReplyDTO rDTO - ReplyDTO(댓글 작성시 반환할 변수들)
	 * @return : ResponseEntity<String>
	 * @description : ?번 글에 대한 댓글을 작성하는 메서드
	 */
	@RequestMapping(value = "/{bNo}", method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
	public ResponseEntity<String> insertReply(@PathVariable("bNo") int bNo, @RequestBody ReplyDTO rDTO) {
		System.out.println(bNo + "번 게시글에 " + rDTO.toString() + "댓글 작성 완료!");

		ResponseEntity<String> result = null;

		// rDTO 객체의 bNo 속성을 bNo 변수의 값으로 설정 후, rDTO 객체가 특정 게시글 번호를 가지도록
		rDTO.setBNo(bNo);

		try {
			if (rService.insertReply(rDTO) == 1) {
				// 댓글 작성에 성공했을 경우
				result = new ResponseEntity<String>("댓글 작성 성공", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 댓글 작성에 실패했을 경우
			result = new ResponseEntity<String>("댓글 작성 실패", HttpStatus.CONFLICT);
		}
		return result;
	}
	
	/**
	 * @methodName : updateReply
	 * @author : 
	 * @date : 2024.05.08
	 * @param : @PathVariable("replyNo") int replyNo - 수정되는 댓글 번호
	 * @param : @RequestBody ReplyDTO rDTO - 수정되는 댓글 그 자체
	 * @return : ResponseEntity<String>
	 * @description : 게시글에 유저가 작성한 댓글을 수정하는 메서드
	 */
	@RequestMapping(value = "/{replyNo}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateReply(@PathVariable("replyNo") int replyNo, @RequestBody ReplyDTO newReply) {
		System.out.println(replyNo + "번 " + newReply.toString() + "댓글 수정이 완료 되었습니다!");
		
		ResponseEntity<String> result = null;
		
		try {
			if (rService.updateReply(newReply) == 1) {
				result = new ResponseEntity<String>("댓글 수정 완료", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			result = new ResponseEntity<String>("댓글 수정 실패", HttpStatus.CONFLICT);
		} 
		
		return result;
	}
	
	/**
	 * @methodName : removeReply
	 * @author : 
	 * @date : 2024.05.11
	 * @param : 
	 * @param : 
	 * @param : 
	 * @return : ResponseEntity<String>
	 * @description : 게시글에 유저가 작성한 댓글을 삭제하는 메서드
	 */
	@RequestMapping(value = "/{replyNo}")
	public ResponseEntity<String> removeReply(@PathVariable("replyNo") int replyNo) {
		System.out.println(replyNo + "번 댓글이 삭제되었습니다!");
		
		ResponseEntity<String> result = null;
		
		try {
			if (rService.deleteReply(replyNo) == 1) {
				// 댓글 삭제
				result = new ResponseEntity<String>("댓글 삭제 완료", HttpStatus.OK);
			}
		} catch (Exception e) {
			// 댓글 삭제 실패
			e.printStackTrace();
			
			result = new ResponseEntity<String>("댓글 삭제 실패", HttpStatus.BAD_REQUEST);
		}
		
		return result;
	}

}
