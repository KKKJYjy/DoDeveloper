package com.dodeveloper.studyApply.service;

import com.dodeveloper.studyApply.vodto.StudyApplyDTO;

public interface StudyApplyService {

	//참여신청 insert 하는 메서드
	int insertApply(StudyApplyDTO newApply) throws Exception;

	//applyNo번째 스터디 신청을 수락하는 메서드
	int acceptApply(int applyNo) throws Exception;

}
