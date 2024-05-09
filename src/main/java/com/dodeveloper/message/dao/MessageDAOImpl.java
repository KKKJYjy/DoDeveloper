package com.dodeveloper.message.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.message.vodto.MessageDTO;
import com.dodeveloper.message.vodto.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO{

	private SqlSessionTemplate template;
	private String namespace = "com.dodeveloper.mappers.messageMapper";
	
	@Autowired
	public MessageDAOImpl(SqlSessionTemplate template) {
		this.template = template;
	}
	
	
	@Override
	public MessageVO selectMessageByNo(int messageNo) throws Exception {
		return template.selectOne(namespace + ".selectMessageByNo", messageNo);
	}

	@Override
	public List<MessageVO> selectMessagesByUserId(String userId, int startPoint, int amountToShow) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("startPoint", startPoint);
		map.put("amountToShow", amountToShow);
		
		return template.selectList(namespace + ".selectMessagesByUserId", map);
	}

	@Override
	public int insertIntoMessage(MessageDTO messageDTO) throws Exception {
		template.insert(namespace + ".insertIntoMessage", messageDTO);
		return messageDTO.getMessageNo();
	}

}
