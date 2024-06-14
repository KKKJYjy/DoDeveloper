package com.dodeveloper.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.member.dto.DropMemberDTO;
import com.dodeveloper.member.dto.LoginDTO;
import com.dodeveloper.member.dto.RegisterDTO;
import com.dodeveloper.member.dto.SessionDTO;
import com.dodeveloper.member.vo.MemberVO;
import com.dodeveloper.mypage.dto.ChangeEmailDTO;
import com.dodeveloper.mypage.dto.ChangeProfileDTO;
import com.dodeveloper.mypage.dto.ChangePwdDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String NS = "com.dodeveloper.mappers.memberMapper";
	private static final String LOGIN_MEMBER = NS + ".loginMember";
	private static final String KEEP_LOGIN = NS + ".keepLogin";
	private static final String CHECK_LOGIN_BEFORE = NS + ".checkLoginBefore";
	
	private static final String DUPLICATE_USER_ID = NS + ".duplicateUserId";
	private static final String REGISTER_MEMBER = NS + ".registerMember";
	private static final String GET_MEMBER_INFO = NS + ".getMemberInfo";
	private static final String GET_MEMBER_BY_EMAIL = NS + ".getMemberInfoByEmail";
	private static final String CHECK_USER_PWD = NS + ".checkUserPwd";
	private static final String CHANGE_PWD = NS + ".changePwd";
	private static final String CHANGE_PROFILE = NS + ".changeProfile";
	private static final String DROP_MEMBER = NS + ".dropMember";
	private static final String CHANGE_DROP_STATUS = NS + ".changeDropStatus";
	private static final String DELETE_DROPPED_MEMBER = NS + ".deleteAllDroppedMembers";
	private static final String CHANGE_EMAIL = NS + ".changeEmail";
	
	@Override
	public MemberVO loginMember(LoginDTO dto) throws Exception {
		return sqlSession.selectOne(LOGIN_MEMBER, dto);
	}
	
	@Override
	public void keepLogin(SessionDTO sessionDTO) throws Exception {
		sqlSession.update(KEEP_LOGIN, sessionDTO);
	}
	
	@Override
	public MemberVO checkLoginBefore(String loginCookie) {
		return sqlSession.selectOne(CHECK_LOGIN_BEFORE, loginCookie);
	}
	
	@Override
	public int duplicateUserId(String userId) throws Exception {
		return sqlSession.selectOne(DUPLICATE_USER_ID, userId);
	}

	@Override
	public int registerMember(RegisterDTO registerDTO) throws Exception {
		return sqlSession.update(REGISTER_MEMBER, registerDTO);
	}

	@Override
	public MemberVO getMemberInfo(String userId) throws Exception {
		return sqlSession.selectOne(GET_MEMBER_INFO, userId);
	}

	@Override
	public int checkUserPwd(ChangePwdDTO changePwdDTO) throws Exception {
		return sqlSession.selectOne(CHECK_USER_PWD, changePwdDTO);
	}

	@Override
	public int changeUserPwd(ChangePwdDTO changePwdDTO) throws Exception {
		return sqlSession.update(CHANGE_PWD, changePwdDTO);
	}

	@Override
	public int changeProfile(ChangeProfileDTO changeProfileDTO) throws Exception {
		return sqlSession.update(CHANGE_PROFILE, changeProfileDTO);
	}

	@Override
	public int dropMember(DropMemberDTO dropMemberDTO) throws Exception {
		return sqlSession.insert(DROP_MEMBER, dropMemberDTO);
	}
	
	@Override
	public int changeDropStatus(DropMemberDTO dropMemberDTO) throws Exception {
		return sqlSession.update(CHANGE_DROP_STATUS, dropMemberDTO);
	}

	@Override
	public int deleteAllDroppedMembers() throws Exception {
		return sqlSession.delete(DELETE_DROPPED_MEMBER);
	}

	@Override
	public List<MemberVO> getMemberByEmail(String email) throws Exception {
		return sqlSession.selectList(GET_MEMBER_BY_EMAIL, email);
	}
	
	@Override
	public int changeEmail(ChangeEmailDTO changeEmailDTO) throws Exception {
		return sqlSession.update(CHANGE_EMAIL, changeEmailDTO);
	}
}
