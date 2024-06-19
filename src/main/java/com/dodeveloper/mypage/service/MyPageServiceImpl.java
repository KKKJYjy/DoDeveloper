package com.dodeveloper.mypage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.dodeveloper.admin.vo.QnaBoardVO;
import com.dodeveloper.admin.vo.ReportVO;
import com.dodeveloper.company.vodto.ScrapVO;
import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.lecture.dao.LectureBoardDAO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.lecture.vodto.LectureLikeVO;
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
//	    System.out.println(userId + "가 서비스단 : " + pageNo + "강의 추천 게시판에 작성한 게시글 확인!");

	    List<LectureBoardVO> lectureList = null;

	    listMakePagingInfo(pageNo, userId);
	    lectureList = myPageDao.getMyPageLecBoardList(userId, pi);
	        
//	    System.out.println("마이페이지에서 " + userId + "가 강의 추천 게시판에 작성한 게시글 : " + lectureList.toString() + "확인");

	    Map<String, Object> returnMap = new HashMap<String, Object>();
	    returnMap.put("lectureList", lectureList);
	    returnMap.put("pagingInfo", this.pi);

//	    System.out.println(pi);

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
//	    System.out.println(userId + "가 서비스단 : " + pageNo + "강의 추천 게시판의 게시글에 댓글 작성 확인!");

	    List<ReplyVO> lectureReplyList = null;

	    replyMakePagingInfo(pageNo, userId);
	    lectureReplyList = myPageDao.getMyPageLecBoardReplyList(userId, pi);
	        
//	    System.out.println("마이페이지에서 " + userId + "가 강의 추천 게시판의 게시글에 댓글 작성한 게시글 : " + lectureReplyList.toString() + "확인");

	    Map<String, Object> returnMap = new HashMap<String, Object>();
	    returnMap.put("lectureReplyList", lectureReplyList);
	    returnMap.put("pagingInfo", this.pi);

//	    System.out.println(pi);

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
//	    System.out.println(userId + "가 서비스단 : " + pageNo + "강의 추천 게시판의 게시글 스크랩 확인!");

	    List<ScrapVO> lectureScrapList = null;

	    scrapMakePagingInfo(pageNo, userId);
	    lectureScrapList = myPageDao.getMyPageLecBoardScrapList(userId, pi);
	        
//	    System.out.println("마이페이지에서 " + userId + "가 강의 추천 게시판의 게시글 스크랩 : " + lectureScrapList.toString() + "확인");

	    Map<String, Object> returnMap = new HashMap<String, Object>();
	    returnMap.put("lectureScrapList", lectureScrapList);
	    returnMap.put("pagingInfo", this.pi);

//	    System.out.println(pi);

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
//	    System.out.println(userId + "가 서비스단 : " + pageNo + "강의 추천 게시판의 게시글 좋아요 확인!");

	    List<LectureLikeVO> lectureLikeList = null;

	    likeMakePagingInfo(pageNo, userId);
	    lectureLikeList = myPageDao.getMyPageLecBoardLikeList(userId, pi);
	        
//	    System.out.println("마이페이지에서 " + userId + "가 강의 추천 게시판의 게시글 좋아요 : " + lectureLikeList.toString() + "확인");

	    Map<String, Object> returnMap = new HashMap<String, Object>();
	    returnMap.put("lectureLikeList", lectureLikeList);
	    returnMap.put("pagingInfo", this.pi);

//	    System.out.println(pi);

	    return returnMap;
    }

    /**
     * @methodName : listMakePagingInfo
     * @param : int pageNo - 보여줘야 할 페이지 번호
     * @return : void
     * @description : 페이징 처리 메서드 (마이페이지에서 검색기능 X)
     */
    private void listMakePagingInfo(int pageNo, String userId) throws Exception {
        // pageNo 값 설정
        pi.setPageNo(pageNo);

        // 페이지 당 보여줄 게시글의 갯수와 블럭당 페이지 갯수 설정
        pi.setViewPostCntPerPage(5);
        pi.setPageCntPerBlock(4);

        // 게시물의 총 갯수를 구해서 멤버 변수에 저장
        int totalPostListCnt = myPageDao.getMyPageLecBoardListCnt(userId);

        // 각각의 게시글 유형에 대한 총 게시물 수를 저장
        pi.setTotalPostCnt(totalPostListCnt);

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
    
    /**
     * @methodName : replyMakePagingInfo
     * @param : int pageNo - 보여줘야 할 페이지 번호
     * @return : void
     * @description : 페이징 처리 메서드 (마이페이지에서 검색기능 X)
     */
    private void replyMakePagingInfo(int pageNo, String userId) throws Exception {
        // pageNo 값 설정
        pi.setPageNo(pageNo);

        // 페이지 당 보여줄 게시글의 갯수와 블럭당 페이지 갯수 설정
        pi.setViewPostCntPerPage(5);
        pi.setPageCntPerBlock(4);

        // 게시물의 총 갯수를 구해서 멤버 변수에 저장
        int totalPostReplyCnt = myPageDao.getMyPageLecBoardReplyListCnt(userId);

        // 각각의 게시글 유형에 대한 총 게시물 수를 저장
        pi.setTotalPostCnt(totalPostReplyCnt);

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
    
    /**
     * @methodName : scrapMakePagingInfo
     * @param : int pageNo - 보여줘야 할 페이지 번호
     * @return : void
     * @description : 페이징 처리 메서드 (마이페이지에서 검색기능 X)
     */
    private void scrapMakePagingInfo(int pageNo, String userId) throws Exception {
        // pageNo 값 설정
        pi.setPageNo(pageNo);

        // 페이지 당 보여줄 게시글의 갯수와 블럭당 페이지 갯수 설정
        pi.setViewPostCntPerPage(5);
        pi.setPageCntPerBlock(4);

        // 게시물의 총 갯수를 구해서 멤버 변수에 저장
        int totalPostScrapCnt = myPageDao.getMyPageLecBoardScrapListCnt(userId);

        // 각각의 게시글 유형에 대한 총 게시물 수를 저장
        pi.setTotalPostCnt(totalPostScrapCnt);

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
    
    /**
     * @methodName : likeMakePagingInfo
     * @param : int pageNo - 보여줘야 할 페이지 번호
     * @return : void
     * @description : 페이징 처리 메서드 (마이페이지에서 검색기능 X)
     */
    private void likeMakePagingInfo(int pageNo, String userId) throws Exception {
        // pageNo 값 설정
        pi.setPageNo(pageNo);

        // 페이지 당 보여줄 게시글의 갯수와 블럭당 페이지 갯수 설정
        pi.setViewPostCntPerPage(5);
        pi.setPageCntPerBlock(4);

        // 게시물의 총 갯수를 구해서 멤버 변수에 저장
        int totalPostLikeCnt = myPageDao.getMyPageLecBoardLikeListCnt(userId);

        // 각각의 게시글 유형에 대한 총 게시물 수를 저장
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

    /**
     * @methodName : getMyLikedLectureList
     * @author : kde
	 * @date : 2024.06.11
	 * @param : int pageNo - 페이징 하기 위한 pageNo
     * @param : String userId - 로그인 한 유저
     * @return : Map<String, Object>
     * @description : 유저가 신고한 게시글 마이페이지에서 확인 + 페이징
     */
	@Override
	public Map<String, Object> getMyPageReportList(int pageNo, String userId) throws Exception {
//		System.out.println(userId + "가 서비스단 : " + pageNo + "신고한 게시글 확인!");

	    List<ReportVO> reportList = null;

	    reportMakePagingInfo(pageNo, userId);
	    reportList = myPageDao.getMyPageReport(userId, pi);
	        
//	    System.out.println("마이페이지에서 " + userId + "가 신고한 게시글 : " + reportList.toString() + "확인");

	    Map<String, Object> returnMap = new HashMap<String, Object>();
	    returnMap.put("reportList", reportList);
	    returnMap.put("pagingInfo", this.pi);

//	    System.out.println(pi);

	    return returnMap;
	}
	
    /**
     * @methodName : reportMakePagingInfo
     * @param : int pageNo - 보여줘야 할 페이지 번호
     * @return : void
     * @description : 페이징 처리 메서드 (마이페이지에서 검색기능 X)
     */
    private void reportMakePagingInfo(int pageNo, String userId) throws Exception {
        // pageNo 값 설정
        pi.setPageNo(pageNo);

        // 페이지 당 보여줄 게시글의 갯수와 블럭당 페이지 갯수 설정
        pi.setViewPostCntPerPage(5);
        pi.setPageCntPerBlock(4);

        // 게시물의 총 갯수를 구해서 멤버 변수에 저장
        int totalPostLikeCnt = myPageDao.getMyPageReportCnt(userId);

        // 각각의 게시글 유형에 대한 총 게시물 수를 저장
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

    /**
     * @methodName : getMyLikedLectureList
     * @author : kde
	 * @date : 2024.06.11
	 * @param : int pageNo - 페이징 하기 위한 pageNo
     * @param : String userId - 로그인 한 유저
     * @return : Map<String, Object>
     * @description : 유저가 문의한 게시글 마이페이지에서 확인 + 페이징
     */
	@Override
	public Map<String, Object> getMyPageQnAList(int pageNo, String userId) throws Exception {
//		System.out.println(userId + "가 서비스단 : " + pageNo + "문의한 게시글 확인!");

	    List<QnaBoardVO> myPageQnAList = null;

	    qnaMakePagingInfo(pageNo, userId);
	    myPageQnAList = myPageDao.getMyPageQnA(userId, pi);
	        
//	    System.out.println("마이페이지에서 " + userId + "가 문의한 게시글 : " + myPageQnAList.toString() + "확인");

	    Map<String, Object> returnMap = new HashMap<String, Object>();
	    returnMap.put("myPageQnAList", myPageQnAList);
	    returnMap.put("pagingInfo", this.pi);

//	    System.out.println(pi);

	    return returnMap;
	}

    /**
     * @methodName : reportMakePagingInfo
     * @param : int pageNo - 보여줘야 할 페이지 번호
     * @return : void
     * @throws Exception 
     * @description : 페이징 처리 메서드 (마이페이지에서 검색기능 X)
     */
	private void qnaMakePagingInfo(int pageNo, String userId) throws Exception {
        // pageNo 값 설정
        pi.setPageNo(pageNo);

        // 페이지 당 보여줄 게시글의 갯수와 블럭당 페이지 갯수 설정
        pi.setViewPostCntPerPage(5);
        pi.setPageCntPerBlock(4);

        // 게시물의 총 갯수를 구해서 멤버 변수에 저장
        int totalPostLikeCnt = myPageDao.getMyPageQnACnt(userId);

        // 각각의 게시글 유형에 대한 총 게시물 수를 저장
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
	
	/**
	 * @methodName : getMyReplyListGo
	 * @author : kde
	 * @date : 2024.06.14
	 * @param : int replyNo - 댓글 테이블의 게시판 번호
	 * @param : int bno - 게시판의 글 번호
	 * @return : List<ReplyVO>
	 * @description : 마이페이지에서 유저가 강의 추천 게시글에 남긴 댓글 클릭시 그 게시글로 이동
	 */
	@Override
    public List<ReplyVO> getMyReplyListGo(int replyNo, int bno) throws Exception {
		
		List<ReplyVO> replyList = (List<ReplyVO>)myPageDao.getMyReplyListGo(replyNo, bno);
		
		return replyList;
    }
	
	/**
	 * @methodName : getMyScrapListGo
	 * @author : kde
	 * @date : 2024.06.15
	 * @param : int bType - 게시판 구분
	 * @param : int scrapBoard - (스크랩에서의 : 쿼리문에서 join)게시글 번호
	 * @return : List<ScrapVO>
	 * @description : 마이페이지에서 유저가 강의 추천 게시글에 스크랩 남긴 게시글로 이동
	 */
	@Override
	public List<ScrapVO> getMyScrapListGo(int bType, int scrapBoard) throws Exception {
		
		List<ScrapVO> scrapList = (List<ScrapVO>)myPageDao.getMyScrapListGo(bType, scrapBoard);
		
        return scrapList;
	}
	
	/**
	 * @methodName : getMyLikeListGo
	 * @author : kde
	 * @date : 2024.06.15
	 * @param : int lecNo - 게시글 번호
	 * @param : int lecLikeNo - 좋아요 누른 번호
	 * @return : List<LectureLikeVO>
	 * @description : 마이페이지에서 유저가 강의 추천 게시글에 좋아요 남긴 게시글로 이동
	 */
	@Override
	public List<LectureLikeVO> getMyLikeListGo(int lecNo, int lecLikeNo) throws Exception {
		
		List<LectureLikeVO> likeList = (List<LectureLikeVO>)myPageDao.getMyLikeListGo(lecNo, lecLikeNo);
		
        return likeList;
	}
	
    /**
     * @methodName : getReportNO
     * @author : kde
     * @date : 2024.06.14
     * @param : int btypeNo - 게시판 구분
     * @param : int reportNo - 게시글 번호
     * @return : List<ReportVO>
     * @description : 마이페이지의 신고 게시글 -> 게시판마다의 유저가 신고한 게시글로 이동
     */
	@Override
    public List<ReportVO> getReportNO(int btypeNo, int reportNo) throws Exception {
		
		List<ReportVO> reportList = (List<ReportVO>)myPageDao.getMyReportListGo(btypeNo, reportNo);
		
        return reportList;
    }

}
