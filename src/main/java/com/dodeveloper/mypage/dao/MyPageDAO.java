package com.dodeveloper.mypage.dao;

import java.util.Collection;
import java.util.List;

import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.mypage.dto.ChangePwdDTO;
import com.dodeveloper.mypage.dto.ProfileDTO;
import com.dodeveloper.mypage.vo.ProfileVO;
import com.dodeveloper.reply.vodto.ReplyVO;
import com.dodeveloper.study.vodto.StuStackDTO;
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.studyApply.vodto.StudyApplyVO;

public interface MyPageDAO {
	// 프로필 사진 등록	
	int setProfileImage(ProfileDTO profileDTO) throws Exception;
	
	// 프로필 사진 가져오기
	ProfileVO getProfileImage(String userId) throws Exception;
	
	// 프로필 사진 삭제하기
	int removeProfileImage(String userId) throws Exception;

	//userId가 쓴 스터디 모임글 리스트 가져오기
	List<StudyBoardVO> getMyStudyList(String userId) throws Exception;

	//userId가 쓴 스터디 모임글의 스터디 참여 신청 리스트 가져오기
	List<StudyApplyVO> getMyStudyApplyList(String userId) throws Exception;

	//userId가 참여 신청한 스터디 모임글 리스트 가져오기
	List<StudyBoardVO> getMyAppliedStudyList(String userId) throws Exception;

	//userId가 참여 신청한 스터디 모임글의 참여 신청 리스트 가져오기
	List<StudyApplyVO> getMyAppliedStudyApplyList(String userId) throws Exception;

	//userId가 참여중인 스터디 모임글 리스트 가져오기
	List<StudyBoardVO> getMyjoinedStudyList(String userId) throws Exception;

	//userId가 참여중인 스터디 모임글의 참여 신청 리스트 가져오기
	List<StudyApplyVO> getMyjoinedStudyApplyList(String userId) throws Exception;
	
	// userId가 강의 추천 게시판에 작성한 게시글로 이동
    List<LectureBoardVO> getMyPageLecBoardList(String userId);
	
}
