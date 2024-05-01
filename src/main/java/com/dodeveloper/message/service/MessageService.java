package com.dodeveloper.message.service;

import com.dodeveloper.message.vodto.MessageVO;

public interface MessageService {
	MessageVO getMessageByNo(int messageNo) throws Exception;
}
