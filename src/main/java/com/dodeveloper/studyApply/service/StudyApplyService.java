package com.dodeveloper.studyApply.service;

import com.dodeveloper.studyApply.vodto.StudyApplyVO;

public interface StudyApplyService {

	//참여신청 insert 하는 메서드
	int insertApply(StudyApplyVO newApply) throws Exception;

}
