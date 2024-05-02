package com.dodeveloper.message.dao;

import java.util.List;

import com.dodeveloper.message.vodto.MessageBoxVO;

public interface MessageBoxDAO {
	public List<MessageBoxVO> selectByReceiver(String receiver, int startPoint, int amountToShow) throws Exception;
}
