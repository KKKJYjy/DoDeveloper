package com.dodeveloper.mypage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.lecture.dao.LectureBoardDAO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
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
	 * @param : String userId - 로그인한 유저
	 * @return : Map<String, Object>
	 * @description : userId가 강의 추천 게시판에 작성한 게시글로 이동
	 */
	@Override
	public Map<String, Object> getMyLectureList(String userId) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		List<StudyBoardVO> lectureList = myPageDao.getMyStudyList(userId);
		result.put("lectureList", lectureList);

		return result;
	}
	
}
