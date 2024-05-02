package com.dodeveloper.message.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.message.vodto.MessageBoxVO;

@Repository
public class MessageBoxDAOImpl implements MessageBoxDAO{

	private String namespace = "com.dodeveloper.mappers.messageBoxMapper";
	
	@Autowired
	private SqlSessionTemplate template;
	
	@Override
	public List<MessageBoxVO> selectByReceiver(String receiver, int startPoint, int amountToShow) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("receiver", receiver);
		map.put("startPoint", Integer.valueOf(startPoint));
		map.put("amountToShow", Integer.valueOf(amountToShow));
		
		return template.selectList(namespace + ".selectMessageBoxByReceiver", map);
	}

}
