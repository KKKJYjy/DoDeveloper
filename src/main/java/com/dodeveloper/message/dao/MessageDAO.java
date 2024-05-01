package com.dodeveloper.message.dao;

import org.springframework.stereotype.Repository;

import com.dodeveloper.message.vodto.MessageVO;

public interface MessageDAO {
	MessageVO getMessageByNo(int messageNo) throws Exception;
}
