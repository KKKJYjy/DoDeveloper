package com.dodeveloper.admin.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dodeveloper.admin.dao.AdminDAO;

import com.dodeveloper.admin.dto.ConnectLogDTO;
import com.dodeveloper.admin.dto.UserDTO;
import com.dodeveloper.admin.dto.UserStatusDTO;
import com.dodeveloper.admin.vo.AdminVO;
import com.dodeveloper.admin.vo.BadMemberBoardVO;
import com.dodeveloper.admin.vo.ConnectLogVO;
import com.dodeveloper.admin.vo.CountUriVO;
import com.dodeveloper.study.vodto.StudyBoardVO;


import com.dodeveloper.admin.vo.BadMemberBoardVO;

import com.dodeveloper.member.vo.MemberVO;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO aDao;
	
	


  
	
	public List<UserDTO> getAllUser() throws Exception {
		System.out.println("서비스단 유저 조회");
		
		List<UserDTO> userList = aDao.selectAllUser();
		
		return userList;

	}

	@Override
	public boolean modifyUserStatus(String newStatus,String userId) throws Exception {
		boolean result = true;
		
		
		
		int userStatus = aDao.modifyUserStatus(newStatus, userId);
		
		return result;
	}

	@Override
	public int getConnectLog(ConnectLogDTO connectLog) throws Exception {
		System.out.println("접속기록 서비스단 호출" + connectLog);
		
		int result = aDao.insertConnectLog(connectLog);
		
		return result;
	}

	@Override
	public List<CountUriVO> getPageLogCount() throws Exception {
		
		List<CountUriVO> result = aDao.selectPageLog();
		
		
		return result;
	}

	@Override
	public List<ConnectLogVO> getDateLog(int month) throws Exception {
		
		List<ConnectLogVO> result = aDao.connectDateLog(month);
		
		for(ConnectLogVO c : result) {
			System.out.println(c.toString());
		}
		
		return result;
	}

	

}

