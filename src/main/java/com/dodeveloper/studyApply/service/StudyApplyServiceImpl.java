package com.dodeveloper.studyApply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.studyApply.dao.StudyApplyDAO;
import com.dodeveloper.studyApply.vodto.StudyApplyDTO;

@Service
public class StudyApplyServiceImpl implements StudyApplyService {

	@Autowired
	StudyApplyDAO saDao;

	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : StudyApplyVO newApply
		* @return : int - 성공하면 1 반환
		* @description : 해당 스터디 모임글에 신청 내용을 insert 한다.
	 */
	@Override
	public int insertApply(StudyApplyDTO newApply) throws Exception {
		return saDao.insertApply(newApply);
	}

	
	/**
		* @author : yeonju
		* @date : 2024. 6. 3.
		* @param : int applyNo
		* @return : String 
		* @description : applyNo번째 스터디 신청을 수락한다
	*/
	@Override
	public int acceptApply(int applyNo) throws Exception{
		return saDao.acceptApply(applyNo);
	}

}
