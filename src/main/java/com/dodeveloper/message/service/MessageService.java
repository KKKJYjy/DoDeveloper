package com.dodeveloper.message.service;

import java.util.List;

import com.dodeveloper.message.vodto.MessageBoxVO;
import com.dodeveloper.message.vodto.MessageVO;

public interface MessageService {
	MessageVO getMessageByNo(int messageNo) throws Exception;
	List<MessageVO> getReceivedMessages(String receiver, int startPoint, int amountToShow) throws Exception;
}
