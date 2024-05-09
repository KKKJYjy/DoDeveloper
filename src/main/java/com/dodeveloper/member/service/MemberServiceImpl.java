package com.dodeveloper.member.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.member.dao.MemberDAO;
import com.dodeveloper.member.dto.LoginDTO;
import com.dodeveloper.member.dto.RegisterDTO;
import com.dodeveloper.member.dto.SessionDTO;
import com.dodeveloper.member.vo.MemberVO;
import com.dodeveloper.mypage.dto.ChangePwdDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
	private MemberDAO mDao;
	
	@Override
	public MemberVO login(LoginDTO loginDTO) throws Exception {
		return mDao.loginMember(loginDTO);
	}

	@Override
	public void keepLogin(SessionDTO sessionDTO) throws Exception {
		System.out.println("sessionDTO : " + sessionDTO.toString());
		mDao.keepLogin(sessionDTO);
	}

	@Override
	public MemberVO checkLoginBefore(String loginCookie) throws Exception {
		return mDao.checkLoginBefore(loginCookie);
	}

	@Override
	public int duplicateUserId(String userId) throws Exception {
		return mDao.duplicateUserId(userId);
	}

	@Override
	public int registerMember(RegisterDTO registerDTO) throws Exception {
		return mDao.registerMember(registerDTO);
	}

	@Override
	public MemberVO getMemberInfo(String userId) throws Exception {
		return mDao.getMemberInfo(userId);
	}

	@Override
	public int checkUserPwd(ChangePwdDTO changePwdDTO) throws Exception {
		return mDao.checkUserPwd(changePwdDTO);
	}

	@Override
	public int changeUserPwd(ChangePwdDTO changePwdDTO) throws Exception {
		return mDao.changeUserPwd(changePwdDTO);
	}
}
