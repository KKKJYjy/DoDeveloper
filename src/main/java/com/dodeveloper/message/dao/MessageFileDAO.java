package com.dodeveloper.message.dao;

import java.util.List;

import com.dodeveloper.message.vodto.MessageFileDTO;

public interface MessageFileDAO {
    public int insertIntoMessageFile(MessageFileDTO uploadfile) throws Exception;

    public List<MessageFileDTO> selectMessageFileByMessageNo(int messageNo) throws Exception;
}
