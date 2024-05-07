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
	
}
