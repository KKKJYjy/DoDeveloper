package com.dodeveloper.message.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dodeveloper.message.dao.MessageBoxDAO;
import com.dodeveloper.message.dao.MessageDAO;
import com.dodeveloper.message.dao.MessageFileDAO;
import com.dodeveloper.message.vodto.MessageBoxDTO;
import com.dodeveloper.message.vodto.MessageBoxVO;
import com.dodeveloper.message.vodto.MessageDTO;
import com.dodeveloper.message.vodto.MessageVO;
import com.dodeveloper.message.vodto.SendMessageDTO;
import com.dodeveloper.message.vodto.MessageFileDTO;

@Service
public class MessageServiceImpl implements MessageService{

	private MessageDAO messageDAO;
	private MessageBoxDAO messageBoxDAO;
	private MessageFileDAO messageFileDAO;
	
	@Autowired
	public MessageServiceImpl(MessageDAO messageDAO, MessageBoxDAO messageBoxDAO, MessageFileDAO messageFileDAO) {
		this.messageDAO = messageDAO;
		this.messageBoxDAO = messageBoxDAO;
		this.messageFileDAO = messageFileDAO;
	}
	
	@Override
	public MessageVO getMessageByNo(int messageNo) throws Exception {
		return messageDAO.selectMessageByNo(messageNo);
	}

	@Override
	public List<MessageVO> getReceivedMessages(String receiver, int startPoint, int amountToShow) throws Exception {
		List<MessageVO> receivedMessages = new LinkedList<MessageVO>();
		
		for(MessageBoxVO messageInfo : messageBoxDAO.selectByReceiver(receiver, startPoint, amountToShow)) {
			receivedMessages.add(messageDAO.selectMessageByNo(messageInfo.getMessageNo()));
		}
		
		return receivedMessages;
	}

	@Override
	public List<MessageVO> getSentMessages(String writer, int startPoint, int amountToShow) throws Exception {
		return messageDAO.selectMessagesByUserId(writer, startPoint, amountToShow);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void sendMessage(SendMessageDTO sendMessageDTO) throws Exception {
		
		MessageDTO messageToSend = sendMessageDTO.getMessage();
		List<String> receiverIdList = sendMessageDTO.getReceiverIdList();
		List<MessageFileDTO> uploadedFileList = sendMessageDTO.getFileList();
		
		int pk = messageDAO.insertIntoMessage(messageToSend);
		for(String receiver : receiverIdList) {
			if(!messageBoxDAO.insert(new MessageBoxDTO(pk, receiver))) {
				throw new Exception("insert into messageBox 에서 오류가 발생함");
			}
		}
		
		for(MessageFileDTO file : uploadedFileList) {
			file.setMessageNo(pk);
			if(messageFileDAO.insertIntoMessageFile(file) != 1) {
				throw new Exception("insert into messageFile 에서 오류가 발생함");
			}
		}
		
	}

	@Override
	public int getReceivedMessageCnt(String receiver) throws Exception {
		return messageBoxDAO.selectMessageBoxCntByReceiverId(receiver);
	}

	@Override
	public int getSentMessageCnt(String sender) throws Exception {
		return messageDAO.selectMessageCntByUserID(sender);
	}

}
