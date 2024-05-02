package com.dodeveloper.message.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.message.vodto.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO{

	@Autowired
	private SqlSessionTemplate template;
	
	private String namespace = "com.dodeveloper.mappers.messageMapper";
	
	@Override
	public MessageVO selectMessageByNo(int messageNo) throws Exception {
		return template.selectOne(namespace + ".selectMessageByNo", messageNo);
	}

}
