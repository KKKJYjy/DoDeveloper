package com.dodeveloper.studyApply.dao;

import com.dodeveloper.studyApply.vodto.StudyApplyVO;

public interface StudyApplyDAO {

	//참여신청 insert 하는 메서드
	int insertApply(StudyApplyVO newApply) throws Exception;

}
