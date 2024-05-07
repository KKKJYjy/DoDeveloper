package com.dodeveloper.reply.dao;

import java.util.List;

import com.dodeveloper.reply.vodto.ReplyVO;

public interface ReplyDAO {
	
	// (Read) 부모글이 ?번 글에 대한 모든 댓글을 가져오는 메서드
	List<ReplyVO> selectAllReply(int bNo) throws Exception;
	
}
