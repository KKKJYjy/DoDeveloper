package com.dodeveloper.admin.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.admin.dto.ConnectLogDTO;
import com.dodeveloper.admin.dto.UserDTO;
import com.dodeveloper.admin.dto.UserStatusDTO;
import com.dodeveloper.admin.vo.AdminVO;
import com.dodeveloper.admin.vo.BadMemberBoardVO;
import com.dodeveloper.admin.vo.ConnectLogVO;
import com.dodeveloper.admin.vo.CountUriVO;
import com.dodeveloper.member.vo.MemberVO;


@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSession ses;
	

	private static String ns = "com.dodeveloper.mappers.adminMapper";
	
	

	@Override
	public List<BadMemberBoardVO> selectListBadMemberBoard() throws Exception {
		
		return ses.selectList(ns + ".getBadMemberBoard");
    
  }
	
	@Override
	public List<UserDTO> selectAllUser() throws Exception {
		
		return ses.selectList(ns + ".selectAllUser");

	}

	@Override
	public int modifyUserStatus(String newStatus, String userId) throws Exception {
		System.out.println(userId + "유저상태를" + newStatus + "로 변경");
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("userId", userId);
		result.put("newStatus", newStatus);
		
		return ses.update(ns + ".updateStatus", result);
	}

	
	@Override
	public int insertConnectLog(ConnectLogDTO cl) throws Exception {
		System.out.println("접속기록 저장 : " + cl.toString());
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sessionId", cl.getSessionId());
		result.put("uri", cl.getUri());
		result.put("accessDate", cl.getAccessDate());
		
		
		return ses.insert(ns + ".insertLog", result);
	}

	@Override
	public List<CountUriVO> selectPageLog() throws Exception {
		
		
		return ses.selectList(ns + ".selectLog");
		
	}

	@Override
	public List<ConnectLogVO> connectDateLog(int month) throws Exception {
		
		return ses.selectList(ns + ".selectDatelog", month);
	}

}
