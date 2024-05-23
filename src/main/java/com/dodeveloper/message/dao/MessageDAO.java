package com.dodeveloper.message.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dodeveloper.message.vodto.MessageDTO;
import com.dodeveloper.message.vodto.MessageVO;

public interface MessageDAO {
	MessageVO selectMessageByNo(int messageNo) throws Exception;
	int insertIntoMessage(MessageDTO messageDTO) throws Exception;

	int selectSentMessagesCnt(String writer, String title, String content) throws Exception;
	int selectMessageCntByWriter(String writer) throws Exception;
	int selectMessageCntByWriterAndTitle(String writer, String title) throws Exception;
	int selectMessageCntByWriterAndContent(String writer, String content) throws Exception;
	
	int selectReceivedMessagesCnt(String receiver, String writer, String title, String content) throws Exception;
	int selectMessageCntByReceiverAndWriter(String receiver, String writer) throws Exception;
	int selectMessageCntByReceiverAndTitle(String receiver, String title) throws Exception;
	int selectMessageCntByReceiverAndContent(String receiver, String content) throws Exception;
	
	List<MessageVO> selectReceivedMessages(String receiver, String writer, String title, String content, int startPoint, int amountToShow) throws Exception;
	List<MessageVO> selectMessagesByReceiver(String receiver, int startPoint, int amountToShow) throws Exception;
	List<MessageVO> selectMessagesByReceiverAndWriter(String receiver, String writer, int startPoint, int amountToShow) throws Exception;
	List<MessageVO> selectMessagesByReceiverAndTitle(String receiver, String title, int startPoint, int amountToShow) throws Exception;
	List<MessageVO> selectMessagesByReceiverAndContent(String receiver, String content, int startPoint, int amountToShow) throws Exception;

	List<MessageVO> selectSentMessages(String writer, String title, String content, int startPoint, int amountToShow) throws Exception;
	List<MessageVO> selectMessagesByWriter(String writer, int startPoint, int amountToShow) throws Exception;
	List<MessageVO> selectMessagesByWriterAndTitle(String writer, String title, int startPoint, int amountToShow) throws Exception;
	List<MessageVO> selectMessagesByWriterAndContent(String writer, String content, int startPoint, int amountToShow) throws Exception;
	
}
