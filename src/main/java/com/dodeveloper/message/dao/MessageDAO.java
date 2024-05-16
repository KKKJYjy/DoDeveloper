package com.dodeveloper.message.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dodeveloper.message.vodto.MessageDTO;
import com.dodeveloper.message.vodto.MessageVO;

public interface MessageDAO {
	MessageVO selectMessageByNo(int messageNo) throws Exception;
	List<MessageVO> selectMessagesByUserId(String userId, int startPoint, int amountToShow) throws Exception;
	int insertIntoMessage(MessageDTO messageDTO) throws Exception;
	int selectMessageCntByUserID(String userId) throws Exception;
}
