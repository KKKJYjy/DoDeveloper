package com.dodeveloper.reply.service;

import java.util.List;
import com.dodeveloper.reply.vodto.ReplyDTO;
import com.dodeveloper.reply.vodto.ReplyVO;

public interface ReplyService {

	// (Read) 부모글이 ?번 글에 대한 모든 댓글을 가져오는 메서드
	List<ReplyVO> selectAllReply(int bNo, int bType) throws Exception;
	
	// ?번 글에 대한 댓글을 작성하는 메서드
	int insertReply(ReplyDTO rDTO) throws Exception;
	
	// ?번 글에 대한 댓글을 수정하는 메서드
	int updateReply(ReplyDTO rDTO) throws Exception;
	
	// ?번 댓글을 삭제하는 메서드
	int deleteReply(int replyNo) throws Exception;

	// 스터디 게시판에 댓글을 작성하는 메서드 (댓글 작성후 댓글수 업데이트)
	int insertReplyStudy(ReplyDTO newReply) throws Exception;

	// 스터디 게시판에 댓글을 삭제하는 메서드 (댓글 삭제후 댓글수 업데이트)
	int deleteReplyStudy(int replyNo, int stuNo) throws Exception;
	
}