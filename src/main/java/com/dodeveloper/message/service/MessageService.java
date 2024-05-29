package com.dodeveloper.message.service;

import java.util.List;

import com.dodeveloper.message.vodto.MessageBoxDTO;
import com.dodeveloper.message.vodto.MessageBoxVO;
import com.dodeveloper.message.vodto.MessageDTO;
import com.dodeveloper.message.vodto.MessageFileDTO;
import com.dodeveloper.message.vodto.MessageVO;
import com.dodeveloper.message.vodto.SendMessageDTO;

public interface MessageService {
    MessageVO getMessageByNo(int messageNo) throws Exception;

    List<MessageVO> getReceivedMessages(String receiver, int startPoint, int amountToShow) throws Exception;

    List<MessageVO> getSentMessages(String writer, int startPoint, int amountToShow) throws Exception;

    List<MessageVO> searchReceivedMessagesByWriter(String receiver, String writer, int startPoint, int amountToShow)
	    throws Exception;

    List<MessageVO> searchReceivedMessagesByContent(String receiver, String content, int startPoint, int amountToShow)
	    throws Exception;

    List<MessageVO> searchReceivedMessagesByTitle(String receiver, String title, int startPoint, int amountToShow)
	    throws Exception;

    List<MessageVO> searchWrittenMessagesByContent(String writer, String content, int startPoint, int amountToShow)
	    throws Exception;

    List<MessageVO> searchWrittenMessagesByTitle(String writer, String title, int startPoint, int amountToShow)
	    throws Exception;

    int getReceivedMessagesCntByWriter(String reciever, String writer) throws Exception;

    int getReceivedMessagesCntByContent(String receiver, String content) throws Exception;

    int getReceivedMessagesCntByTitle(String receiver, String title) throws Exception;

    int getSentMessagesCntByContent(String writer, String content) throws Exception;

    int getSentMessagesCntByTitle(String writer, String title) throws Exception;

    int countUnreadReceivedMessages(String receiver) throws Exception;

    int getReceivedMessageCnt(String receiver) throws Exception;

    int getSentMessageCnt(String sender) throws Exception;

    void sendMessage(SendMessageDTO sendMessageDTO) throws Exception;

    List<MessageFileDTO> getMessageFilesByMessageNo(int messageNo) throws Exception;

    List<MessageBoxVO> getReceivedLogsOfMessage(int messageNo) throws Exception;
}
