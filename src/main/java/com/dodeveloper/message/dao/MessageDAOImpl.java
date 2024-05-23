package com.dodeveloper.message.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.message.vodto.MessageDTO;
import com.dodeveloper.message.vodto.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO {

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
	public int insertIntoMessage(MessageDTO messageDTO) throws Exception {
		template.insert(namespace + ".insertIntoMessage", messageDTO);
		return messageDTO.getMessageNo();
	}
	
	@Override
	public int selectSentMessagesCnt(String writer, String title, String content) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("writer", writer);
		map.put("title", title);
		map.put("content", content);

		return template.selectOne(namespace + ".selectMessageCntByWriter", map);
	}

	@Override
	public int selectMessageCntByWriter(String writer) throws Exception {
		return selectSentMessagesCnt(writer, null, null);
	}

	@Override
	public int selectMessageCntByWriterAndTitle(String writer, String title) throws Exception {
		return selectSentMessagesCnt(writer, "%" + title + "%", null);
	}

	@Override
	public int selectMessageCntByWriterAndContent(String writer, String content) throws Exception {
		return selectSentMessagesCnt(writer, null, "%" + content + "%");
	}
	
	@Override
	public int selectReceivedMessagesCnt(String receiver, String writer, String title, String content)
			throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("receiver", receiver);
		map.put("writer", writer);
		map.put("title", title);
		map.put("content", content);
		
		return template.selectOne(namespace + ".selectMessageCntByReceiverId", map);
	}
	
	@Override
	public int selectMessageCntByReceiverAndWriter(String receiver, String writer) throws Exception {
		return selectReceivedMessagesCnt(receiver, writer, null, null);
	}
	
	@Override
	public int selectMessageCntByReceiverAndTitle(String receiver, String title) throws Exception {
		return selectReceivedMessagesCnt(receiver, null, "%" + title + "%" , null);
	}

	@Override
	public int selectMessageCntByReceiverAndContent(String receiver, String content) throws Exception {
		return selectReceivedMessagesCnt(receiver, null, null, "%" + content + "%");
	}
	
	@Override
	public List<MessageVO> selectReceivedMessages(String receiver, String writer, String title, String content,
			int startPoint, int amountToShow) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("receiver", receiver);
		map.put("writer", writer);
		map.put("title", title);
		map.put("content", content);
		map.put("startPoint", startPoint);
		map.put("amountToShow", amountToShow);

		return template.selectList(namespace + ".selectMessageByReceiverId", map);
	}

	@Override
	public List<MessageVO> selectMessagesByReceiverAndWriter(String receiver, String writer, int startPoint,
			int amountToShow) throws Exception {
		return selectReceivedMessages(receiver, "%" + writer + "%", null, null, startPoint, amountToShow);
	}

	@Override
	public List<MessageVO> selectMessagesByReceiverAndTitle(String receiver, String title, int startPoint,
			int amountToShow) throws Exception {
		return selectReceivedMessages(receiver, null, "%" + title + "%", null, startPoint, amountToShow);
	}

	@Override
	public List<MessageVO> selectMessagesByReceiverAndContent(String receiver, String content, int startPoint,
			int amountToShow) throws Exception {
		return selectReceivedMessages(receiver, null, null, "%" + content + "%", startPoint, amountToShow);
	}

	@Override
	public List<MessageVO> selectMessagesByReceiver(String receiver, int startPoint, int amountToShow)
			throws Exception {
		return selectReceivedMessages(receiver, null, null, null, startPoint, amountToShow);
	}

	@Override
	public List<MessageVO> selectSentMessages(String writer, String title, String content, int startPoint,
			int amountToShow) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("writer", writer);
		map.put("title", title);
		map.put("content", content);
		map.put("startPoint", startPoint);
		map.put("amountToShow", amountToShow);

		return template.selectList(namespace + ".selectMessagesByWriter", map);
	}

	@Override
	public List<MessageVO> selectMessagesByWriter(String writer, int startPoint, int amountToShow) throws Exception {
		return selectSentMessages(writer, null, null, startPoint, amountToShow);
	}

	@Override
	public List<MessageVO> selectMessagesByWriterAndTitle(String writer, String title, int startPoint, int amountToShow)
			throws Exception {
		return selectSentMessages(writer, "%" + title + "%", null, startPoint, amountToShow);
	}

	@Override
	public List<MessageVO> selectMessagesByWriterAndContent(String writer, String content, int startPoint,
			int amountToShow) throws Exception {
		return selectSentMessages(writer, null, "%" + content + "%", startPoint, amountToShow);
	}

}
