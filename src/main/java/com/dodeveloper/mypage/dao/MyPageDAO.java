package com.dodeveloper.mypage.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.dodeveloper.admin.vo.QnaBoardVO;
import com.dodeveloper.admin.vo.ReportVO;
import com.dodeveloper.company.vodto.ScrapVO;
import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.lecture.vodto.LectureLikeVO;
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
	
	// 유저가 강의 추천 게시판에 작성한 게시글의 글의 갯수 가져오기
    int getMyPageLecBoardListCnt(String userId) throws Exception;
	
	// 유저가 강의 추천 게시판에 작성한 게시글 가져오기
    List<LectureBoardVO> getMyPageLecBoardList(String userId, PagingInfo pi) throws Exception;
    
    // 유저가 강의 추천 게시판의 게시글에 작성한 댓글의 갯수 가져오기
    int getMyPageLecBoardReplyListCnt(String userId) throws Exception;
    
    // 유저가 강의 추천 게시판의 게시글에 작성한 댓글 가져오기
    List<ReplyVO> getMyPageLecBoardReplyList(String userId, PagingInfo pi) throws Exception;
    
    // 유저가 강의 추천 게시판의 게시글 스크랩한 게시글의 갯수 가져오기
    int getMyPageLecBoardScrapListCnt(String userId) throws Exception;
    
    // 유저가 강의 추천 게시판의 게시글 스크랩한 게시글 가져오기
    List<ScrapVO> getMyPageLecBoardScrapList(String userId, PagingInfo pi) throws Exception;
    
    // 유저가 강의 추천 게시판의 게시글에 좋아요 누른 게시글의 갯수 가져오기
    int getMyPageLecBoardLikeListCnt(String userId) throws Exception;
    
    // 유저가 강의 추천 게시판의 게시글에 좋아요 누른 게시글 가져오기
    List<LectureLikeVO> getMyPageLecBoardLikeList(String userId, PagingInfo pi) throws Exception;
	
    // 유저가 게시판마다 신고한 게시글 갯수 가져오기
    int getMyPageReportCnt(String userId) throws Exception;
    
    // 유저가 게시판마다 신고한 게시글 가져오기
    List<ReportVO> getMyPageReport(String userId, PagingInfo pi) throws Exception;
    
    // 유저가 문의 남긴 게시글 갯수 가져오기
    int getMyPageQnACnt(String userId) throws Exception;
    
    // 유저가 문의 남긴 게시글 가져오기
    List<QnaBoardVO> getMyPageQnA(String userId, PagingInfo pi) throws Exception;
    
    // 마이페이지에서 유저가 강의 추천 게시글에 좋아요 남긴 게시글로 이동
    List<LectureLikeVO> getMyLikeListGo(int lecNo, int lecLikeNo) throws Exception;
    
    // 마이페이지의 신고 게시글 -> 게시판마다의 유저가 신고한 게시글로 이동
    List<ReportVO> getMyReportListGo(int btypeNo, int reportNo) throws Exception;
    
}
