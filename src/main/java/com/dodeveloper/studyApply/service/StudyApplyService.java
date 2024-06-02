package com.dodeveloper.studyApply.service;

import com.dodeveloper.studyApply.vodto.StudyApplyDTO;

public interface StudyApplyService {

	//참여신청 insert 하는 메서드
	int insertApply(StudyApplyDTO newApply) throws Exception;

}
