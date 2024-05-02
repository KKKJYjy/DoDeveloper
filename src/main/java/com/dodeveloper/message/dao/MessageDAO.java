package com.dodeveloper.message.dao;

import org.springframework.stereotype.Repository;

import com.dodeveloper.message.vodto.MessageVO;

public interface MessageDAO {
	MessageVO selectMessageByNo(int messageNo) throws Exception;
}
