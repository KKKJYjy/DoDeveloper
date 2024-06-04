package com.dodeveloper.mypage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.mypage.dto.ChangePwdDTO;
import com.dodeveloper.mypage.dto.ProfileDTO;
import com.dodeveloper.mypage.vo.ProfileVO;
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
}
