package com.dodeveloper.mypage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.studyApply.vodto.StudyApplyVO;

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

	/**
		* @author : yeonju
		* @date : 2024. 5. 31.
		* @param : String userId - 로그인한 유저
		* @return : List<StudyBoardVO> 
		* @description : userId가 쓴 스터디 모임글 리스트 가져오기
	 */
	@Override
	public List<StudyBoardVO> getMyStudyList(String userId) throws Exception{
		return sqlSession.selectList(NS + ".getMyStudyList" ,userId);
	}

	/**
		* @author : yeonju
		* @date : 2024. 5. 31.
		* @param : String userId
		* @return : List<StudyApplyVO>
		* @description : userId가 쓴 스터디 모임글의 스터디 참여 신청 리스트 가져오기
	 */
	@Override
	public List<StudyApplyVO> getMyStudyApplyList(String userId) {
		return sqlSession.selectList(NS + ".getMyStudyApplyList", userId);
	}

	/**
		* @author : yeonju
		* @date : 2024. 6. 4.
		* @param : String userId
		* @return : List<StudyBoardVO>
		* @description : userId가 참여 신청한 스터디 모임글 리스트 가져오기
	 */
	@Override
	public List<StudyBoardVO> getMyAppliedStudyList(String userId) throws Exception {
		return sqlSession.selectList(NS + ".getMyAppliedStudyList" ,userId);
	}

	/**
		* @author : yeonju
		* @date : 2024. 6. 4.
		* @param : String userId
		* @return : List<StudyApplyVO> 
		* @description : userId가 참여 신청한 스터디 모임글의 참여 신청 리스트 가져오기
	 */
	@Override
	public List<StudyApplyVO> getMyAppliedStudyApplyList(String userId) throws Exception {
		return sqlSession.selectList(NS + ".getMyAppliedStudyApplyList" ,userId);
	}

	/**
		* @author : yeonju
		* @date : 2024. 6. 4.
		* @param : String userId
		* @return : List<StudyBoardVO>
		* @description : userId가 참여중인 스터디 모임글 리스트 가져오기
	 */
	@Override
	public List<StudyBoardVO> getMyjoinedStudyList(String userId) throws Exception {
		return sqlSession.selectList(NS + ".getMyjoinedStudyList" ,userId);
	}

	/**
		* @author : yeonju
		* @date : 2024. 6. 4.
		* @param : String userId
		* @return : List<StudyApplyVO> 
		* @description : userId가 참여중인 스터디 모임글의 참여 신청 리스트 가져오기
	 */
	@Override
	public List<StudyApplyVO> getMyjoinedStudyApplyList(String userId) throws Exception {
		return sqlSession.selectList(NS + ".getMyjoinedStudyApplyList" ,userId);
	}
	
	/**
	 * @methodName : getMyPageLecBoardListCnt
	 * @author : kde
	 * @date : 2024.06.12
	 * @return : int - 글의 갯수
	 * @description : 유저가 강의 추천 게시판에 작성한 게시글의 글의 갯수 가져오기
	 */
	@Override
	public int getMyPageLecBoardListCnt(String userId) throws Exception {

		return sqlSession.selectOne(NS + ".getMyPageLecBoardListCnt", userId);
	}
	
	/**
	 * @methodName : getMyPageLecBoardList
	 * @author : kde
	 * @date : 2024.06.11
	 * @param : String userId - 로그인 한 유저
	 * @return : List<LectureBoardVO>
	 * @description : 유저가 강의 추천 게시판에 작성한 게시글 가져오기
	 */
	@Override
	public List<LectureBoardVO> getMyPageLecBoardList(String userId, PagingInfo pi) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("userId", userId);
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());

		return sqlSession.selectList(NS + ".getMyPageLecBoardList", params);
	}
	
	/**
	 * @methodName : getMyPageLecBoardListCnt
	 * @author : kde
	 * @date : 2024.06.12
	 * @return : int - 글의 갯수
	 * @description : 유저가 강의 추천 게시판에 작성한 게시글의 댓글 갯수 가져오기
	 */
	@Override
	public int getMyPageLecBoardReplyListCnt(String userId) throws Exception {

		return sqlSession.selectOne(NS + ".getMyPageLecBoardReplyListCnt", userId);
	}

	/**
	 * @methodName : getMyPageLecBoardReplyList
	 * @author : kde
	 * @date : 2024.06.11
	 * @param : String userId - 로그인 한 유저
	 * @return : List<ReplyVO>
	 * @description : 유저가 강의 추천 게시판의 게시글에 작성한 댓글 가져오기
	 */
	@Override
	public List<ReplyVO> getMyPageLecBoardReplyList(String userId, PagingInfo pi) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("userId", userId);
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return sqlSession.selectList(NS + ".getMyPageLecBoardReplyList", params);
	}
	
	/**
	 * @methodName : getMyPageLecBoardListCnt
	 * @author : kde
	 * @date : 2024.06.12
	 * @return : int - 글의 갯수
	 * @description : 유저가 강의 추천 게시판의 게시글을 스크랩한 게시글의 글의 갯수 가져오기
	 */
	@Override
	public int getMyPageLecBoardScrapListCnt(String userId) throws Exception {

		return sqlSession.selectOne(NS + ".getMyPageLecBoardScrapListCnt", userId);
	}
	
	/**
	 * @methodName : getMyPageLecBoardScrapList
	 * @author : kde
	 * @date : 2024.06.11
	 * @param : String userId - 로그인 한 유저
	 * @return : List<ScrapVO>
	 * @description : 유저가 강의 추천 게시판의 게시글 스크랩한 게시글 가져오기
	 */
	@Override
	public List<ScrapVO> getMyPageLecBoardScrapList(String userId, PagingInfo pi) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("userId", userId);
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return sqlSession.selectList(NS + ".getMyPageLecBoardScrapList", params);
	}
	
	/**
	 * @methodName : getMyPageLecBoardListCnt
	 * @author : kde
	 * @date : 2024.06.12
	 * @return : int - 글의 갯수
	 * @description : 유저가 강의 추천 게시판에 게시글을 좋아요한 게시글의 글의 갯수 가져오기
	 */
	@Override
	public int getMyPageLecBoardLikeListCnt(String userId) throws Exception {

		return sqlSession.selectOne(NS + ".getMyPageLecBoardLikeListCnt", userId);
	}
	
	/**
	 * @methodName : getMyPageLecBoardLikeList
	 * @author : kde
	 * @date : 2024.06.11
	 * @param : String userId - 로그인 한 유저
	 * @return : List<LectureBoardVO>
	 * @description : 유저가 강의 추천 게시판의 게시글에 좋아요 누른 게시글 가져오기
	 */
	@Override
	public List<LectureLikeVO> getMyPageLecBoardLikeList(String userId, PagingInfo pi) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("userId", userId);
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());

		return sqlSession.selectList(NS + ".getMyPageLecBoardLikeList", params);
	}

	/**
	 * @methodName : getMyPageReportCnt
	 * @author : kde
	 * @date : 2024.06.13
	 * @param : String userId - 로그인 한 유저
	 * @return : int
	 * @description : 유저가 게시판마다 신고한 게시글 갯수 가져오기
	 */
	@Override
	public int getMyPageReportCnt(String userId) throws Exception {
		
		return sqlSession.selectOne(NS + ".getMyPageReportCnt", userId);
	}

	/**
	 * @methodName : getMyPageReport
	 * @author : kde
	 * @date : 2024.06.13
	 * @param : String userId - 로그인 한 유저
	 * @param : PagingInfo pi - 페이징
	 * @return : List<ReportVO> - 신고VO
	 * @description : 유저가 게시판마다 신고한 게시글 가져오기
	 */
	@Override
	public List<ReportVO> getMyPageReport(String userId, PagingInfo pi) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("userId", userId);
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return sqlSession.selectList(NS + ".getMyPageReport", params);
	}

	/**
	 * @methodName : getMyPageReport
	 * @author : kde
	 * @date : 2024.06.13
	 * @param : String userId - 로그인 한 유저
	 * @return : int
	 * @description : 유저가 문의 남긴 게시글 갯수 가져오기
	 */
	@Override
	public int getMyPageQnACnt(String userId) throws Exception {

		return sqlSession.selectOne(NS + ".getMyPageQnACnt", userId);
	}

	/**
	 * @methodName : getMyPageReport
	 * @author : kde
	 * @date : 2024.06.13
	 * @param : String userId - 로그인 한 유저
	 * @param : PagingInfo pi - 페이징
	 * @return : List<QnaBoardVO> - 문의VO
	 * @description : 유저가 문의한 게시글 마이페이지에서 확인
	 */
	@Override
	public List<QnaBoardVO> getMyPageQnA(String userId, PagingInfo pi) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("userId", userId);
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return sqlSession.selectList(NS + ".getMyPageQnA", params);
	}
	
}
