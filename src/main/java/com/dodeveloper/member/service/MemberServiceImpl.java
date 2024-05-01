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

@Service
public class MemberServiceImpl implements MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
	private MemberDAO mDao;
	
	@Override
	public MemberVO login(LoginDTO loginDTO) throws Exception {
		MemberVO loginMember = null;
		// 로그인 시도
		loginMember = mDao.loginMember(loginDTO); // select

		if (loginMember != null) {
			System.out.println("로그인 성공");
		} else {
			System.out.println("로그인 실패");
		}
		return loginMember;
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
}
