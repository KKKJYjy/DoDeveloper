package com.dodeveloper.studyApply.dao;

import com.dodeveloper.studyApply.vodto.StudyApplyDTO;

public interface StudyApplyDAO {

	//참여신청 insert 하는 메서드
	int insertApply(StudyApplyDTO newApply) throws Exception;

	//applyNo번째 스터디 신청을 수락하는 메서드
	int acceptApply(int applyNo) throws Exception;

	//applyNo번째 스터디 신청을 거절하는 메서드
	int refuseApply(int applyNo) throws Exception;

	//applyNo번째 스터디 신청을 삭제하는 메서드
	int deleteApply(int applyNo) throws Exception;

	//스터디 신청을 수정하는 메서드
	int modifyApply(StudyApplyDTO modifyApply) throws Exception;

	//스터디 신청을 한 적이 있는지 검사하는 메서드
	int isDuplicate(StudyApplyDTO newApply) throws Exception;

}
