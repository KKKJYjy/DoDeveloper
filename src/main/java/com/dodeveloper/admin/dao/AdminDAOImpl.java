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

	// 유저 상태 변경
	@Override
	public int updateUserStatus(Map<String, Object> params) throws Exception {
		// System.out.println(params + "상태 변경(DAO)");
			
		return ses.update(ns + ".updateUserStatus", params);
	}
	
	// 패널티 테이블에서 정지일자와 해제일 업데이트
	@Override
	public int updatePenaltyRecord(Map<String, Object> penaltyParams) throws Exception {
		// System.out.println("DAO단 정지일과 해제일 실행 : " + penaltyParams);
		
		return ses.update(ns + ".updatePenaltyRecord", penaltyParams);
	}
	
	// 탈퇴회원 처리
	@Override
	public int banUser(String userId) throws Exception {
		// System.out.println(userId + "탈퇴처리(DAO)");
		
		return ses.update(ns + ".banUser", userId);
	}


	
	@Override
	public int insertConnectLog(ConnectLogDTO cl) throws Exception {
		// System.out.println("접속기록 저장 : " + cl.toString());
		
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
