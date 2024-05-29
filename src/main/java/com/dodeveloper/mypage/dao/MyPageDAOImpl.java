package com.dodeveloper.mypage.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.mypage.dto.ChangePwdDTO;
import com.dodeveloper.mypage.dto.ProfileDTO;
import com.dodeveloper.mypage.vo.ProfileVO;

@Repository
public class MyPageDAOImpl implements MyPageDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NS = "com.dodeveloper.mappers.mypageMapper";

    private static final String SET_PROFILE_IMAGE = NS + ".setProfileImage";
    private static final String GET_PROFILE_IMAGE = NS + ".getProfileImage";
    private static final String REMOVE_PROFILE_IMAGE = NS + ".removeProfileImage";

    @Override
    public int setProfileImage(ProfileDTO profileDTO) throws Exception {
	return sqlSession.update(SET_PROFILE_IMAGE, profileDTO);
    }

    @Override
    public ProfileVO getProfileImage(String userId) throws Exception {
	return sqlSession.selectOne(GET_PROFILE_IMAGE, userId);
    }

    @Override
    public int removeProfileImage(String userId) throws Exception {
	return sqlSession.update(REMOVE_PROFILE_IMAGE, userId);
    }
}
