package com.dodeveloper.message.service;

import java.util.List;

import com.dodeveloper.message.vodto.MessageBoxDTO;
import com.dodeveloper.message.vodto.MessageBoxVO;
import com.dodeveloper.message.vodto.MessageDTO;
import com.dodeveloper.message.vodto.MessageFileDTO;
import com.dodeveloper.message.vodto.MessageVO;
import com.dodeveloper.message.vodto.SendMessageDTO;

public interface MessageService {
	// PK(메세지 번호)로 메세지에 대한 총체적인 정보들을 가져오기
	MessageVO getMessageByNo(int messageNo) throws Exception;

	// 어떤 유저가 받은 메세지 일부를 가져오기
	List<MessageVO> getReceivedMessages(String receiver, int startPoint, int amountToShow) throws Exception;

	// 어떤 유저가 보낸 메세지 내역 일부를 가져오기
	List<MessageVO> getSentMessages(String writer, int startPoint, int amountToShow) throws Exception;
	
	// ---------------------------- 수령된 메세지에서 검색해서 가져오기
	// 수령된 메세지 중에서 특정인이 보낸 메세지들을 검색해서 가져온다
	List<MessageVO> searchReceivedMessagesByWriter(String receiver, String writer, int startPoint, int amountToShow) throws Exception;
	// 수령된 메세지 중에서 내용에 특정 키워드가 포함되어 있는 메세지들을 검색해서 가져온다.
	List<MessageVO> searchReceivedMessagesByContent(String receiver, String content, int startPoint, int amountToShow) throws Exception;
	// 수령된 메세지 중에서 제목에 특정 키워드가 포함되어 있는 메세지들을 검색해서 가져온다.
	List<MessageVO> searchReceivedMessagesByTitle(String receiver, String title, int startPoint, int amountToShow) throws Exception;

	// ------------------------------------ 보낸 메세지 내역에서 검색해서 가져오기
	// 보낸 메세지 중에서 내용에 특정 키워드가 포함되어있는 메세지를 가져온다.
	List<MessageVO> searchWrittenMessagesByContent(String writer, String content, int startPoint, int amountToShow) throws Exception;
	// 보낸 메세지 중에서 제목에 특정 키워드가 포함되어있는 메세지를 가져온다.
	List<MessageVO> searchWrittenMessagesByTitle(String writer, String title, int startPoint, int amountToShow) throws Exception;


	// ----------------------------- 수령된 메세지 중에서 특정 조건을 만족하는 메세지의 개수를 가져온다.
	// 수령된 메세지 중에서 특정인이 작성한 메세지의 개수를 가져온다.
	int getReceivedMessagesCntByWriter(String reciever, String writer) throws Exception;
	// 수령된 메세지 중에서 내용에 특정 키워드가 포함되어 있는 메세지를 가져온다.
	int getReceivedMessagesCntByContent(String receiver, String content) throws Exception;
	// 수령된 메세지 중에서 제목에 특정 키워드가 포함되어 있는 메세지를 가져온다.
	int getReceivedMessagesCntByTitle(String receiver, String title) throws Exception;
	
	// ----------------------------- 보낸 메세지 중에서 특정 조건을 만족하는 메세지의 개수를 가져온다.
	// 보낸 메세지 중에서, 내용에 content 라는 문자열을 포함하고 있는 메세지의 개수를 가져온다.
	int getSentMessagesCntByContent(String writer, String content) throws Exception;
	// 보낸 메세지 중에서, 제목에 title 라는 문자열을 포함하고 있는 메세지의 개수를 가져온다.
	int getSentMessagesCntByTitle(String writer, String title) throws Exception;

	// 아직 읽지 않은 메세지의 개수를 가져온다.
	int countUnreadReceivedMessages(String receiver) throws Exception;
	// 수령된 메세지가 얼마나 있는지를 가져온다.
	int getReceivedMessageCnt(String receiver) throws Exception;
	// 보낸 메세지가 얼마나 있는지를 가져온다.
	int getSentMessageCnt(String sender) throws Exception;
	
	// 메세지를 보낸다.
	void sendMessage(SendMessageDTO sendMessageDTO) throws Exception;
	
	// 메세지에 첨부되어있는 파일들을 가져온다.
	List<MessageFileDTO> getMessageFilesByMessageNo(int messageNo) throws Exception;
	// 메세지 수령 로그들을 가져온다.
	List<MessageBoxVO> getReceivedLogsOfMessage(int messageNo) throws Exception;
}
