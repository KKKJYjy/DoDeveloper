package com.dodeveloper.message.dao;

import java.util.List;

import com.dodeveloper.message.vodto.MessageBoxDTO;
import com.dodeveloper.message.vodto.MessageBoxVO;

public interface MessageBoxDAO {
	public List<MessageBoxVO> selectByReceiver(String receiver, int startPoint, int amountToShow) throws Exception;
	public int selectReceivedMessageCnt(String receiver) throws Exception;
	public boolean insert(MessageBoxDTO messageBoxDTO) throws Exception;
	public List<MessageBoxVO> selectMessageBoxByMessageNo(int messageNo) throws Exception;
	
	public int countUnreadMessages(String receiver) throws Exception;
	public int updateIsRead(String receiver) throws Exception;
}
