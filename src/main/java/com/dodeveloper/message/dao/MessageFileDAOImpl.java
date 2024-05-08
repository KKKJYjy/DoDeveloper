package com.dodeveloper.message.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.message.vodto.MessageFileDTO;

@Repository
public class MessageFileDAOImpl implements MessageFileDAO {

	private SqlSessionTemplate template;
	private String namespace = "com.dodeveloper.mappers.messageFileMapper";
	
	@Autowired
	public MessageFileDAOImpl(SqlSessionTemplate template) {
		this.template = template;
	}
	
	
	@Override
	public int insertIntoMessageFile(MessageFileDTO uploadfile) throws Exception {
		return template.insert(namespace + ".insertIntoMessageFile", uploadfile);
	}

}
