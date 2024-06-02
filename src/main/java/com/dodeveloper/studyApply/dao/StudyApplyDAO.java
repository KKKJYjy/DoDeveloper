package com.dodeveloper.studyApply.dao;

import com.dodeveloper.studyApply.vodto.StudyApplyDTO;

public interface StudyApplyDAO {

	//참여신청 insert 하는 메서드
	int insertApply(StudyApplyDTO newApply) throws Exception;

}
