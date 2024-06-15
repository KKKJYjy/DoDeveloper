package com.dodeveloper.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.dodeveloper.admin.vo.ReportVO;
import com.dodeveloper.company.vodto.ScrapVO;
import com.dodeveloper.lecture.vodto.LectureLikeVO;
import com.dodeveloper.mypage.dto.ChangePwdDTO;
import com.dodeveloper.mypage.dto.ProfileDTO;
import com.dodeveloper.mypage.vo.ProfileVO;
import com.dodeveloper.reply.vodto.ReplyVO;

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
	
	// 유저가 강의 추천 게시판에 작성한 게시글 불러오기 + 페이징
	Map<String, Object> getMyLectureList(int pageNo, String userId) throws Exception;
	
    // 유저가 강의 추천 게시판의 게시글에 작성한 댓글 불러오기 + 페이징
    Map<String, Object> getMyReplyLectureList(int pageNo, String userId) throws Exception;

    // 유저가 강의 추천 게시판의 게시글 스크랩한 게시글 불러오기 + 페이징
    Map<String, Object> getMyScrapLectureList(int pageNo, String userId) throws Exception;

    // 유저가 강의 추천 게시판의 게시글에 좋아요 누른 게시글 불러오기 + 페이징
    Map<String, Object> getMyLikedLectureList(int pageNo, String userId) throws Exception;
    
    // 유저가 신고한 게시판의 게시글 불러오기 + 페이징
    Map<String, Object> getMyPageReportList(int pageNo, String userId) throws Exception;
    
    // 유저가 문의남긴 게시글 불러오기 + 페이징
    Map<String, Object> getMyPageQnAList(int pageNo, String userId) throws Exception;
    
    // 마이페이지에서 유저가 강의 추천 게시글에 스크랩 남긴 게시글로 이동
    List<ScrapVO> getMyScrapListGo(int bType, int scrapBoard) throws Exception;
    
    // 마이페이지에서 유저가 강의 추천 게시글에 좋아요 남긴 게시글로 이동
    List<LectureLikeVO> getMyLikeListGo(int lecNo, int lecLikeNo) throws Exception;
    
    // 마이페이지의 신고 게시글 -> 게시판마다의 유저가 신고한 게시글로 이동
    List<ReportVO> getReportNO(int btypeNo, int reportNo) throws Exception;
    
}
