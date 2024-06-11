package com.dodeveloper.mypage.service;

import java.util.List;
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

	//userId가 참여중인 스터디 모임글 & 스터디 언어 & 참여 신청 리스트 불러오기
	Map<String, Object> getMyJoinedStudyList(String userId) throws Exception;
	
	// 유저가 강의 추천 게시판에 작성한 게시글 불러오기
	Map<String, Object> getMyLectureList(String userId) throws Exception;
	
	// 유저가 강의 추천 게시판의 게시글에 작성한 댓글 불러오기
	Map<String, Object> getMyReplyLectureList(String userId) throws Exception;

	// 유저가 강의 추천 게시판의 게시글 스크랩한 게시글 불러오기
	Map<String, Object> getMyScrapLectureList(String userId) throws Exception;

	// 유저가 강의 추천 게시판의 게시글에 좋아요 누른 게시글 불러오기
	Map<String, Object> getMyLikedLectureList(String userId) throws Exception;
}
