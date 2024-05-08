package com.dodeveloper.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.reply.dao.ReplyDAO;
import com.dodeveloper.reply.vodto.ReplyDTO;
import com.dodeveloper.reply.vodto.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO rDao;

	/**
	 * @methodName : selectAllReply
	 * @author : kde
	 * @date : 2024.05.07
	 * @param : int bNo - 게시글 번호
	 * @return : List<ReplyVO>
	 * @description : (Read) 부모글이 ?번 글에 대한 모든 댓글을 가져오는 메서드
	 */
	@Override
	public List<ReplyVO> selectAllReply(int bNo) throws Exception {

		return rDao.selectAllReply(bNo);
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

		return rDao.insertReply(rDTO);
	}

}
