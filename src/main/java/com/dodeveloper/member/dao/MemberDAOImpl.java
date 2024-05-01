package com.dodeveloper.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.member.dto.LoginDTO;
import com.dodeveloper.member.dto.RegisterDTO;
import com.dodeveloper.member.dto.SessionDTO;
import com.dodeveloper.member.vo.MemberVO;

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
}
