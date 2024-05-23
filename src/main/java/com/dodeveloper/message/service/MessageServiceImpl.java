package com.dodeveloper.message.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
public class MessageServiceImpl implements MessageService {

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
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void sendMessage(SendMessageDTO sendMessageDTO) throws Exception {

		MessageDTO messageToSend = sendMessageDTO.getMessage();
		List<String> receiverIdList = sendMessageDTO.getReceiverIdList();
		List<MessageFileDTO> uploadedFileList = sendMessageDTO.getFileList();

		int pk = messageDAO.insertIntoMessage(messageToSend);
		for (String receiver : receiverIdList) {
			if (!messageBoxDAO.insert(new MessageBoxDTO(pk, receiver))) {
				throw new Exception("insert into messageBox 에서 오류가 발생함");
			}
		}

		for (MessageFileDTO file : uploadedFileList) {
			file.setMessageNo(pk);
			if (messageFileDAO.insertIntoMessageFile(file) != 1) {
				throw new Exception("insert into messageFile 에서 오류가 발생함");
			}
		}

	}
	
	
	@Override
	public int getReceivedMessageCnt(String receiver) throws Exception {
		return messageBoxDAO.selectReceivedMessageCnt(receiver);
	}

	@Override
	public int getSentMessageCnt(String sender) throws Exception {
		return messageDAO.selectMessageCntByWriter(sender);
	}

	@Override
	public List<MessageFileDTO> getMessageFilesByMessageNo(int messageNo) throws Exception {
		return messageFileDAO.selectMessageFileByMessageNo(messageNo);
	}

	@Override
	public List<MessageBoxVO> getReceivedLogsOfMessage(int messageNo) throws Exception {
		return messageBoxDAO.selectMessageBoxByMessageNo(messageNo);
	}

	
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public List<MessageVO> getReceivedMessages(String receiver, int startPoint, int amountToShow) throws Exception {
		messageBoxDAO.updateIsRead(receiver);
		return messageDAO.selectMessagesByReceiver(receiver, startPoint, amountToShow);
	}
	
	@Override
	public List<MessageVO> getSentMessages(String writer, int startPoint, int amountToShow) throws Exception {
		return messageDAO.selectMessagesByWriter(writer, startPoint, amountToShow);
	}
	
	@Override
	public List<MessageVO> searchReceivedMessagesByWriter(String receiver, String writer, int startPoint,
			int amountToShow) throws Exception {
		return messageDAO.selectMessagesByReceiverAndWriter(receiver, writer, startPoint, amountToShow);
	}

	@Override
	public List<MessageVO> searchReceivedMessagesByContent(String receiver, String content, int startPoint,
			int amountToShow) throws Exception {
		return messageDAO.selectMessagesByReceiverAndContent(receiver, content, startPoint, amountToShow);
	}

	@Override
	public List<MessageVO> searchReceivedMessagesByTitle(String receiver, String title, int startPoint,
			int amountToShow) throws Exception {
		return messageDAO.selectMessagesByReceiverAndTitle(receiver, title, startPoint, amountToShow);
	}

	@Override
	public List<MessageVO> searchWrittenMessagesByContent(String writer, String content, int startPoint,
			int amountToShow) throws Exception {
		return messageDAO.selectMessagesByWriterAndContent(writer, content, startPoint, amountToShow);
	}

	@Override
	public List<MessageVO> searchWrittenMessagesByTitle(String writer, String title, int startPoint, int amountToShow)
			throws Exception {
		return messageDAO.selectMessagesByWriterAndTitle(writer, title, startPoint, amountToShow);
	}
	
	
	@Override
	public int getReceivedMessagesCntByWriter(String reciever, String writer) throws Exception{
		return messageDAO.selectMessageCntByReceiverAndWriter(reciever, writer);
	}

	@Override
	public int getReceivedMessagesCntByContent(String receiver, String content) throws Exception {
		return messageDAO.selectMessageCntByReceiverAndContent(receiver, content);
	}

	@Override
	public int getReceivedMessagesCntByTitle(String receiver, String title) throws Exception {
		return messageDAO.selectMessageCntByReceiverAndTitle(receiver, title);
	}

	@Override
	public int getSentMessagesCntByContent(String writer, String content) throws Exception {
		return messageDAO.selectMessageCntByWriterAndContent(writer, content);
	}

	@Override
	public int getSentMessagesCntByTitle(String writer, String title) throws Exception {
		return messageDAO.selectMessageCntByWriterAndTitle(writer, title);
	}

	@Override
	public int countUnreadReceivedMessages(String receiver) throws Exception {
		System.out.println(messageBoxDAO.countUnreadMessages(receiver));
		return messageBoxDAO.countUnreadMessages(receiver);
	}
	
}
