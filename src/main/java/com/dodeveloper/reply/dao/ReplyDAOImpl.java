package com.dodeveloper.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.reply.vodto.ReplyDTO;
import com.dodeveloper.reply.vodto.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Autowired
	private SqlSession ses;

	private static String ns = "com.dodeveloper.mappers.replyMapper";

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

		return ses.selectList(ns + ".selectAllReply", bNo);
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

		return ses.insert(ns + ".insertReply", rDTO);
	}

	/**
	 * @methodName : updateReply
	 * @author : kde
	 * @date : 2024.05.08
	 * @param : ReplyDTO updateDTO - 댓글 수정할때 필요한 변수들
	 * @return : int
	 * @description : ?번 글에 대한 댓글을 수정하는 메서드
	 */
	@Override
	public int updateReply(ReplyDTO rDTO) throws Exception {
		
		return ses.update(ns + ".updateReply", rDTO);
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
		
		return ses.delete(ns + ".deleteReply", replyNo);
	}

}
