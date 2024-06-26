package com.dodeveloper.studyApply.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.studyApply.vodto.StudyApplyDTO;

@Repository
public class StudyApplyDAOImpl implements StudyApplyDAO {

	@Autowired
	private SqlSession ses;
	
	private static String ns = "com.dodeveloper.mappers.studyApplyMapper";
	
	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : StudyApplyVO newApply
		* @return : int - 성공하면 1 반환
		* @description : 해당 스터디 모임글에 신청 내용을 insert 한다.
	 */
	@Override
	public int insertApply(StudyApplyDTO newApply) throws Exception {
		return ses.insert(ns + ".insertApply", newApply);
	}

	/**
		* @author : yeonju
		* @date : 2024. 6. 3.
		* @param : int applyNo
		* @return : String 
		* @description : applyNo번째 스터디 신청을 수락한다
	*/
	@Override
	public int acceptApply(int applyNo) throws Exception {
		return ses.update(ns + ".acceptApply", applyNo);
	}

	/**
		* @author : yeonju
		* @date : 2024. 6. 3.
		* @param : int applyNo
		* @return : String 
		* @description : applyNo번째 스터디 신청을 거절한다
	*/
	@Override
	public int refuseApply(int applyNo) throws Exception {
		return ses.update(ns + ".refuseApply", applyNo);
	}

	/**
		* @author : yeonju
		* @date : 2024. 6. 4.
		* @param : int applyNo
		* @return : int  
		* @description : applyNo번째 스터디 신청을 삭제한다
	 */
	@Override
	public int deleteApply(int applyNo) throws Exception {
		return ses.delete(ns + ".deleteApply", applyNo);
	}

	
	/**
		* @author : yeonju
		* @date : 2024. 6. 4.
		* @param : StudyApplyDTO modifyApply - 수정할 스터디 신청 정보를 담은 객체
		* @return : int
		* @description : 스터디 신청을 수정한다
	 */
	@Override
	public int modifyApply(StudyApplyDTO modifyApply) throws Exception {
		return ses.update(ns + ".modifyApply", modifyApply);
	}

	/**
		* @author : yeonju
		* @date : 2024. 6. 17.
		* @param : StudyApplyDTO newApply
		* @return : int
		* @description : 스터디 신청을 한 적이 있는지 검사하는 메서드
	 */
	@Override
	public int isDuplicate(StudyApplyDTO newApply) throws Exception {
		return ses.selectOne(ns + ".isDuplicate", newApply);
	}


}
