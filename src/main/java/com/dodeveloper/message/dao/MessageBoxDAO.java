package com.dodeveloper.message.dao;

import java.util.List;

import com.dodeveloper.message.vodto.MessageBoxDTO;
import com.dodeveloper.message.vodto.MessageBoxVO;

public interface MessageBoxDAO {
	public List<MessageBoxVO> selectByReceiver(String receiver, int startPoint, int amountToShow) throws Exception;
	public int selectMessageBoxCntByReceiverId(String receiver) throws Exception;
	public boolean insert(MessageBoxDTO messageBoxDTO) throws Exception;
	
}
