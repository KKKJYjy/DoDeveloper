package com.dodeveloper.mypage.dao;

import com.dodeveloper.mypage.dto.ChangePwdDTO;
import com.dodeveloper.mypage.dto.ProfileDTO;
import com.dodeveloper.mypage.vo.ProfileVO;

public interface MyPageDAO {
    // 프로필 사진 등록
    int setProfileImage(ProfileDTO profileDTO) throws Exception;

    // 프로필 사진 가져오기
    ProfileVO getProfileImage(String userId) throws Exception;

    // 프로필 사진 삭제하기
    int removeProfileImage(String userId) throws Exception;
}
