package com.dodeveloper.member.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dodeveloper.member.dao.MemberDAO;
import com.dodeveloper.member.dto.DropMemberDTO;
import com.dodeveloper.member.dto.LoginDTO;
import com.dodeveloper.member.dto.RegisterDTO;
import com.dodeveloper.member.dto.SessionDTO;
import com.dodeveloper.member.vo.MemberVO;
import com.dodeveloper.mypage.dto.ChangeEmailDTO;
import com.dodeveloper.mypage.dto.ChangeProfileDTO;
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
		System.out.println("keepLogin sessionDTO : " + sessionDTO.toString());
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

	@Override
	public int changeProfile(ChangeProfileDTO changeProfileDTO) throws Exception {
		return mDao.changeProfile(changeProfileDTO);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public boolean dropMember(DropMemberDTO dropMemberDTO) throws Exception {
		boolean result = false;
		if (mDao.dropMember(dropMemberDTO) > 0) {
			if (mDao.changeDropStatus(dropMemberDTO) > 0) {
				result = true;
			}
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public int deleteAllDroppedMember() throws Exception {
		return mDao.deleteAllDroppedMembers();
	}

	@Override
	public List<MemberVO> getMemberByEmail(String email) throws Exception {
		return mDao.getMemberByEmail(email);
	}

	@Override
	public boolean changeEmail(ChangeEmailDTO changeEmailDTO) throws Exception {
		return (mDao.changeEmail(changeEmailDTO) == 1);
	}
}
