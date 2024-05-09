package com.dodeveloper.message.dao;

import com.dodeveloper.message.vodto.MessageFileDTO;

public interface MessageFileDAO {
	public int insertIntoMessageFile(MessageFileDTO uploadfile) throws Exception;
}
