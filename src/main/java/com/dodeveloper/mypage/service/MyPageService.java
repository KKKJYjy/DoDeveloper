package com.dodeveloper.mypage.service;

import java.util.Map;

import com.dodeveloper.mypage.dto.ChangePwdDTO;
import com.dodeveloper.mypage.dto.ProfileDTO;
import com.dodeveloper.mypage.vo.ProfileVO;

public interface MyPageService {
	// 프로필 사진 등록
	int setProfileImage(ProfileDTO profileDTO) throws Exception;
	
	// 프로필 사진 가져오기
    ProfileVO getProfileImage(String userId) throws Exception;
    
    // 프로필 사진 삭제하기
	int removeProfileImage(String userId) throws Exception;

	//userId가 쓴 스터디 모임글 & 스터디 언어 & 참여 신청 리스트 불러오기
	Map<String, Object> getMyStudyList(String userId) throws Exception;

	//userId가 참여 신청한 스터디 모임글 & 스터디 언어 & 참여 신청 리스트 불러오기
	Map<String, Object> getMyApplyList(String userId) throws Exception;
}
