package com.dodeveloper.mypage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.mypage.dao.MyPageDAO;
import com.dodeveloper.mypage.dto.ChangePwdDTO;
import com.dodeveloper.mypage.dto.ProfileDTO;
import com.dodeveloper.mypage.vo.ProfileVO;

@Service
public class MyPageServiceImpl implements MyPageService {

    private static final Logger logger = LoggerFactory.getLogger(MyPageServiceImpl.class);

    @Autowired
    private MyPageDAO myPageDao;

    public int setProfileImage(ProfileDTO profileDTO) throws Exception {
	return myPageDao.setProfileImage(profileDTO);
    }

    public ProfileVO getProfileImage(String userId) throws Exception {
	return myPageDao.getProfileImage(userId);
    }

    @Override
    public int removeProfileImage(String userId) throws Exception {
	return myPageDao.removeProfileImage(userId);
    }
}
