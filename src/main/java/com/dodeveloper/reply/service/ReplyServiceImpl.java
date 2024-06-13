package com.dodeveloper.reply.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.reply.dao.ReplyDAO;
import com.dodeveloper.reply.vodto.ReplyDTO;
import com.dodeveloper.reply.vodto.ReplyVO;
import com.dodeveloper.study.dao.StudyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO rDao;
	
	@Autowired
	private StudyDAO sDao;

	/**
	 * @methodName : selectAllReply
	 * @author : kde
	 * @date : 2024.05.07
	 * @param : int bNo - 게시글 번호
	 * @param : int bType - 게시판 구분
	 * @return : List<ReplyVO>
	 * @description : (Read) 부모글이 ?번 글에 대한 모든 댓글을 가져오는 메서드
	 */
	@Override
	public List<ReplyVO> selectAllReply(int bNo, int bType) throws Exception {
		
		return rDao.selectAllReply(bNo, bType);
	}

	/**
	 * @methodName : insertReply
	 * @author : kde
	 * @date : 2024.05.08
	 * @param : ReplyDTO rDTO - ReplyDTO(댓글 작성시 반환할 변수들)
	 * @return : int
	 * @description : ?번 글에 대한 댓글을 작성하는 메서드
	 */
	@Override
	public int insertReply(ReplyDTO rDTO) throws Exception {

		System.out.println("서비스단 댓글 작성 시 " +  rDTO.toString());
		
		return rDao.insertReply(rDTO);
	}

	/**
	 * @methodName : updateReply
	 * @author : kde
	 * @date : 2024.05.08
	 * @param : ReplyDTO rDTO - 댓글 수정 시 필요한 변수들
	 * @return : int
	 * @description : ?번 글에 대한 댓글을 수정하는 메서드
	 */
	@Override
	public int updateReply(ReplyDTO rDTO) throws Exception {
		
		return rDao.updateReply(rDTO);
	}

	/**
	 * @methodName : deleteReply
	 * @author : kde
	 * @date : 2024.05.11
	 * @param : int replyNo - 댓글 번호
	 * @return : int
	 * @description : ?번 댓글을 삭제하는 메서드
	 */
	@Override
	public int deleteReply(int replyNo) throws Exception {
		
		return rDao.deleteReply(replyNo);
	}

	/**
		* @author : yeonju
		* @date : 2024. 6. 10.
		* @param : ReplyDTO newReply
		* @return : int
		* @description :댓글 작성에 성공하면 스터디 테이블의 댓글수가 +1 update
	 */
	@Override
	public int insertReplyStudy(ReplyDTO newReply) throws Exception {
		int result = 0;
		
		if(rDao.insertReply(newReply) == 1) {
			System.out.println(newReply.getBNo() + "번째 스터디 모임글 댓글 인서트 성공");
			if(sDao.ReplyCntUp(newReply.getBNo()) ==1) {
				System.out.println(newReply.getBNo() + "번째 스터디 모임글 댓글수 +1 성공");
				result = 1;
			}
		}
		
		return result;
	}

	/**
	* @author : yeonju
	* @date : 2024. 6. 10.
	* @param : ReplyDTO newReply
	* @return : int
	* @description :댓글 작성에 성공하면 스터디 테이블의 댓글수가 -1 update
 */
	@Override
	public int deleteReplyStudy(int replyNo, int stuNo) throws Exception {
		int result = 0;
		
		if(rDao.deleteReply(replyNo) == 1) {
			System.out.println(replyNo + "번째 스터디 모임글 댓글 삭제 성공");
			if(sDao.ReplyCntDown(stuNo) == 1) {
				System.out.println(stuNo + "번째 스터디 모임글 댓글수 -1 성공");
				result= 1;
			}
		}
		
		return result;
	}

}
