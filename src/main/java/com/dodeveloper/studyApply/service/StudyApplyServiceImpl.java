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
		* @date : 2024. 6. 17.
		* @param : StudyApplyVO newApply
		* @return : int - 성공하면 1 반환
		* @description : 
		* 해당 스터디 모임글에 신청한 적이 없을 경우에만
		* 신청 내용을 insert 한다.
	 */
	@Override
	public int insertApply(StudyApplyDTO newApply) throws Exception {
		
		int result = 0;
		
		if(saDao.isDuplicate(newApply) == 0) {
			
			if(saDao.insertApply(newApply) == 1) {
				result = 1;
			}
		}
		
		return result;
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


	/**
		* @author : yeonju
		* @date : 2024. 6. 3.
		* @param : int applyNo
		* @return : String 
		* @description : applyNo번째 스터디 신청을 거절한다
	*/
	@Override
	public int refuseApply(int applyNo) throws Exception {
		return saDao.refuseApply(applyNo);
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
		return saDao.deleteApply(applyNo);
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
		return saDao.modifyApply(modifyApply);
	}

}
