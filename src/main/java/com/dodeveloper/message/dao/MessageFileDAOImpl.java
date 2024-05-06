package com.dodeveloper.message.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.message.vodto.MessageFileDTO;

@Repository
public class MessageFileDAOImpl implements MessageFileDAO {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	private String namespace = "com.dodeveloper.mappers.messageFileMapper";
	
	@Override
	public int insertIntoMessageFile(MessageFileDTO uploadfile) throws Exception {
		return sessionTemplate.insert(namespace + ".insertIntoMessageFile", uploadfile);
	}

}
