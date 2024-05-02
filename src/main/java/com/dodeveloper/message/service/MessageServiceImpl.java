package com.dodeveloper.message.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.message.dao.MessageBoxDAO;
import com.dodeveloper.message.dao.MessageDAO;
import com.dodeveloper.message.vodto.MessageBoxVO;
import com.dodeveloper.message.vodto.MessageVO;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageDAO messageDAO;

	@Autowired
	private MessageBoxDAO messageBoxDAO;
	
	@Override
	public MessageVO getMessageByNo(int messageNo) throws Exception {
		return messageDAO.selectMessageByNo(messageNo);
	}

	@Override
	public List<MessageVO> getReceivedMessages(String receiver, int startPoint, int amountToShow) throws Exception {
		List<MessageVO> receivedMessages = new LinkedList<MessageVO>();
		List<MessageBoxVO> receivedMessageInfo = messageBoxDAO.selectByReceiver(receiver, startPoint, amountToShow);
		
		for(MessageBoxVO messageInfo : receivedMessageInfo) {
			receivedMessages.add(messageDAO.selectMessageByNo(messageInfo.getMessageNo()));
		}
		
		return receivedMessages;
	}

}
