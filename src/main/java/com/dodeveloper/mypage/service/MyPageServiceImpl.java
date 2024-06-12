package com.dodeveloper.mypage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.company.vodto.ScrapVO;
import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.lecture.dao.LectureBoardDAO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.lecture.vodto.LectureSearchDTO;
import com.dodeveloper.mypage.dao.MyPageDAO;
import com.dodeveloper.mypage.dto.ChangePwdDTO;
import com.dodeveloper.mypage.dto.ProfileDTO;
import com.dodeveloper.mypage.vo.ProfileVO;
import com.dodeveloper.reply.vodto.ReplyVO;
import com.dodeveloper.study.dao.StudyDAO;
import com.dodeveloper.study.vodto.StuStackDTO;
import com.dodeveloper.study.vodto.StuStackVO;
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.studyApply.vodto.StudyApplyVO;

@Service
public class MyPageServiceImpl implements MyPageService {

	private static final Logger logger = LoggerFactory.getLogger(MyPageServiceImpl.class);

	@Autowired
	private StudyDAO studyDao;
	
	@Autowired
	private LectureBoardDAO lectureDao; // 스프링 컨테이너에 있는 LectureDAO 객체를 찾아 주입
	
	@Autowired
	private PagingInfo pi; // 스프링 컨테이너에 있는 PagingInfo 객체를 찾아 주입
	
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

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 31.
	 * @param : String userId - 로그인한 유저
	 * @return : Map<String, Object>
	 * @description : userId가 쓴 스터디 모임글 & 스터디 언어 & 참여 신청 리스트를 Map에 저장
	 */
	@Override
	public Map<String, Object> getMyStudyList(String userId) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		// userId의 스터디 모임글 리스트 가져와 map에 저장
		List<StudyBoardVO> studyList = myPageDao.getMyStudyList(userId);
		result.put("studyList", studyList);

		// userId의 스터디 모임글의 스터디 언어 리스트 가져와 map에 저장
		List<StuStackDTO> stuStackList = new ArrayList<StuStackDTO>();
		if(studyList != null) {			
			for (StudyBoardVO s : studyList) {				
				stuStackList.addAll(studyDao.selectAllStudyStack(s.getStuNo()));
			}
		}
		result.put("stuStackList", stuStackList);

		// userId의 스터디 모임글의 참여 신청 리스트 가져와 map에 저장
		List<StudyApplyVO> stuApplyList = myPageDao.getMyStudyApplyList(userId);
		result.put("stuApplyList", stuApplyList);

		return result;
	}

	/**
		* @author : yeonju
		* @date : 2024. 6. 3.
		* @param : String userId - 로그인한 유저
		* @return : Map<String, Object>
		* @description : userId가 참여 신청한 스터디 모임글 & 스터디 언어 & 참여 신청 리스트를 Map에 저장
	 */
	@Override
	public Map<String, Object> getMyApplyList(String userId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		// userId가 참여신청한 스터디 모임글 리스트 가져와 map에 저장
		List<StudyBoardVO> studyList = myPageDao.getMyAppliedStudyList(userId);
		result.put("studyList", studyList);

		// userId의 스터디 모임글의 스터디 언어 리스트 가져와 map에 저장
		List<StuStackDTO> stuStackList = new ArrayList<StuStackDTO>();
		if(studyList != null) {			
			for (StudyBoardVO s : studyList) {				
				stuStackList.addAll(studyDao.selectAllStudyStack(s.getStuNo()));
			}
		}
		result.put("stuStackList", stuStackList);

		// userId의 스터디 모임글의 참여 신청 리스트 가져와 map에 저장
		List<StudyApplyVO> stuApplyList = myPageDao.getMyAppliedStudyApplyList(userId);
		result.put("stuApplyList", stuApplyList);

		return result;
	}

	/**
		* @author : yeonju
		* @date : 2024. 6. 4.
		* @param : String userId - 로그인한 유저
		* @return : Map<String, Object>
		* @description : userId가 참여중인 스터디 모임글 & 스터디 언어 & 참여 신청 리스트 불러오기
	 */
	@Override
	public Map<String, Object> getMyJoinedStudyList(String userId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		// userId가 참여신청한 스터디 모임글 리스트 가져와 map에 저장
		List<StudyBoardVO> studyList = myPageDao.getMyjoinedStudyList(userId);
		result.put("studyList", studyList);

		// userId의 스터디 모임글의 스터디 언어 리스트 가져와 map에 저장
		List<StuStackDTO> stuStackList = new ArrayList<StuStackDTO>();
		if(studyList != null) {			
			for (StudyBoardVO s : studyList) {				
				stuStackList.addAll(studyDao.selectAllStudyStack(s.getStuNo()));
			}
		}
		result.put("stuStackList", stuStackList);

		// userId의 스터디 모임글의 참여 신청 리스트 가져와 map에 저장
		List<StudyApplyVO> stuApplyList = myPageDao.getMyjoinedStudyApplyList(userId);
		result.put("stuApplyList", stuApplyList);

		return result;
	}
	
	/**
	 * @methodName : getMyLectureList
	 * @author : kde
	 * @date : 2024.06.11
	 * @param : int pageNo - 페이징 하기 위한 pageNo
	 * @param : String userId - 로그인 한 유저
	 * @return : Map<String, Object>
	 * @description : 유저가 강의 추천 게시판에 작성한 게시글로 이동 + 페이징
	 */
	@Override
	public Map<String, Object> getMyLectureList(int pageNo, String userId) throws Exception {
	    System.out.println(userId + "가 서비스단 : " + pageNo + "강의 추천 게시판에 작성한 게시글 확인!");

	    List<LectureBoardVO> lectureList = null;

	    // 검색 조건과 검색어를 입력 안하고, 검색 필터를 선택 안 한 경우
	    makePagingInfo(pageNo, userId);
	    lectureList = myPageDao.getMyPageLecBoardList(userId, pi);
	        
	    System.out.println("마이페이지에서 " + userId + "가 강의 추천 게시판에 작성한 게시글 : " + lectureList.toString() + "확인");


	    Map<String, Object> returnMap = new HashMap<String, Object>();
	    returnMap.put("lectureList", lectureList);
	    returnMap.put("pagingInfo", this.pi);

	    System.out.println(pi);

	    return returnMap;
	}
	
    /**
     * @methodName : getMyReplyLectureList
	 * @author : kde
	 * @date : 2024.06.11
	 * @param : int pageNo - 페이징 하기 위한 pageNo
     * @param : String userId - 로그인 한 유저
     * @return : Map<String, Object>
     * @description : 유저가 강의 추천 게시판의 게시글에 작성한 댓글 불러오기 + 페이징
     */
    @Override
    public Map<String, Object> getMyReplyLectureList(int pageNo, String userId) throws Exception {
	    System.out.println(userId + "가 서비스단 : " + pageNo + "강의 추천 게시판에 작성한 게시글 확인!");

	    List<LectureBoardVO> lectureReplyList = null;

	    // 검색 조건과 검색어를 입력 안하고, 검색 필터를 선택 안 한 경우
	    makePagingInfo(pageNo, userId);
	    lectureReplyList = myPageDao.getMyPageLecBoardList(userId, pi);
	        
	    System.out.println("마이페이지에서 " + userId + "가 강의 추천 게시판에 작성한 게시글 : " + lectureReplyList.toString() + "확인");


	    Map<String, Object> returnMap = new HashMap<String, Object>();
	    returnMap.put("lectureReplyList", lectureReplyList);
	    returnMap.put("pagingInfo", this.pi);

	    System.out.println(pi);

	    return returnMap;
    }

    /**
     * @methodName : getMyScrapLectureList
     * @author : kde
	 * @date : 2024.06.11
	 * @param : int pageNo - 페이징 하기 위한 pageNo
     * @param : String userId - 로그인 한 유저
     * @return : Map<String, Object>
     * @description : 유저가 강의 추천 게시판의 게시글 스크랩한 게시글 불러오기 + 페이징
     */
    @Override
    public Map<String, Object> getMyScrapLectureList(int pageNo, String userId) throws Exception {
	    System.out.println(userId + "가 서비스단 : " + pageNo + "강의 추천 게시판에 작성한 게시글 확인!");

	    List<LectureBoardVO> lectureScrapList = null;

	    // 검색 조건과 검색어를 입력 안하고, 검색 필터를 선택 안 한 경우
	    makePagingInfo(pageNo, userId);
	    lectureScrapList = myPageDao.getMyPageLecBoardList(userId, pi);
	        
	    System.out.println("마이페이지에서 " + userId + "가 강의 추천 게시판에 작성한 게시글 : " + lectureScrapList.toString() + "확인");


	    Map<String, Object> returnMap = new HashMap<String, Object>();
	    returnMap.put("lectureScrapList", lectureScrapList);
	    returnMap.put("pagingInfo", this.pi);

	    System.out.println(pi);

	    return returnMap;
    }

    /**
     * @methodName : getMyLikedLectureList
     * @author : kde
	 * @date : 2024.06.11
	 * @param : int pageNo - 페이징 하기 위한 pageNo
     * @param : String userId - 로그인 한 유저
     * @return : Map<String, Object>
     * @description : 유저가 강의 추천 게시판의 게시글에 좋아요 누른 게시글 불러오기 + 페이징
     */
    @Override
    public Map<String, Object> getMyLikedLectureList(int pageNo, String userId) throws Exception {
	    System.out.println(userId + "가 서비스단 : " + pageNo + "강의 추천 게시판에 작성한 게시글 확인!");

	    List<LectureBoardVO> lectureLikeList = null;

	    // 검색 조건과 검색어를 입력 안하고, 검색 필터를 선택 안 한 경우
	    makePagingInfo(pageNo, userId);
	    lectureLikeList = myPageDao.getMyPageLecBoardList(userId, pi);
	        
	    System.out.println("마이페이지에서 " + userId + "가 강의 추천 게시판에 작성한 게시글 : " + lectureLikeList.toString() + "확인");


	    Map<String, Object> returnMap = new HashMap<String, Object>();
	    returnMap.put("lectureLikeList", lectureLikeList);
	    returnMap.put("pagingInfo", this.pi);

	    System.out.println(pi);

	    return returnMap;
    }

    /**
     * @methodName : makePagingInfo
     * @param : int pageNo - 보여줘야 할 페이지 번호
     * @return : void
     * @description : 페이징 처리 메서드 (마이페이지에서 검색기능 X)
     */
    private void makePagingInfo(int pageNo, String userId) throws Exception {
        // pageNo 값 설정
        pi.setPageNo(pageNo);

        // 페이지 당 보여줄 게시글의 갯수와 블럭당 페이지 갯수 설정
        pi.setViewPostCntPerPage(2);
        pi.setPageCntPerBlock(4);

        // 게시물의 총 갯수를 구해서 멤버 변수에 저장
        int totalPostListCnt = myPageDao.getMyPageLecBoardListCnt(userId);
        int totalPostReplyCnt = myPageDao.getMyPageLecBoardReplyListCnt(userId);
        int totalPostScrapCnt = myPageDao.getMyPageLecBoardScrapListCnt(userId);
        int totalPostLikeCnt = myPageDao.getMyPageLecBoardLikeListCnt(userId);

        // 각각의 게시글 유형에 대한 총 게시물 수를 저장
        pi.setTotalPostCnt(totalPostListCnt);
        pi.setTotalPostCnt(totalPostReplyCnt);
        pi.setTotalPostCnt(totalPostScrapCnt);
        pi.setTotalPostCnt(totalPostLikeCnt);

        // 총 페이지 수 저장
        pi.setTotalPageCnt();

        // 보여주기 시작할 글의 rowIndex 번호 저장
        pi.setStartRowIndex();

        // 전체 페이지 블럭 갯수 저장
        pi.setTotalPageBlockCnt();

        // 현재 페이지가 속한 페이징 블럭 번호 저장
        pi.setPageBlockOfCurrentPage();

        // 현재 페이징 블럭 시작 페이지 번호 저장
        pi.setStartNumOfCurrentPagingBlock();

        // 현재 페이징 블럭 끝 페이지 번호 저장
        pi.setEndNumOfCurrentPagingBlock();
    }
	
}
