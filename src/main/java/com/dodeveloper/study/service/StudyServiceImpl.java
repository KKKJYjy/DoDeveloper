package com.dodeveloper.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dodeveloper.study.dao.StudyDAO;
import com.dodeveloper.study.vodto.StuStackVO;
import com.dodeveloper.study.vodto.StudyBoardDTO;
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.study.vodto.SearchStudyDTO;
import com.dodeveloper.study.vodto.StackVO;
import com.dodeveloper.study.vodto.StuStackDTO;
import com.dodeveloper.study.vodto.StuStackModifyDTO;

@Service
public class StudyServiceImpl implements StudyService {

	@Autowired
	StudyDAO sDao;

	@Override
	public List<StudyBoardVO> selectAllList(SearchStudyDTO sDTO) throws Exception {

		// 검색어가 있을 경우의 게시글 목록과 없는 경우의 게시글 목록이 다르다.
		List<StudyBoardVO> lst = null;

		if (sDTO.getSearchType() != null && sDTO.getSearchContent() != null) {
			// 검색어가 있는 경우
			lst = sDao.selectAllListWithsDTO(sDTO);
		} else {
			// 검색어가 없는 경우
			lst = sDao.selectAllList();
		}

		return lst;
	}

	@Override
	public List<StuStackDTO> selectAllStudyStack(int stuNo) throws Exception {
		// System.out.println("서비스단" + sDao.selectAllStudyStack(stuNo).toString());
		return sDao.selectAllStudyStack(stuNo);
	}

	@Override
	public int selectNextStuNo() throws Exception {
		return sDao.selectNextStuNo();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public int insertStudyWithStack(StudyBoardDTO newStudy, StuStackVO newStack)  {

		int result = 0;



		try {
			// StuStackVO의 stuBoardNo값 세팅
			newStack.setStuBoardNo(sDao.selectNextStuNo() + 1);
			System.out.println("insertStack: 추가할 스터디 스택 게시글 번호" + newStack.getStuBoardNo());
			
			int[] chooseStacks = newStack.getChooseStack();
			
			if (sDao.insertNewStudy(newStudy) == 1) {
				System.out.println("스터디글추가성공");

				System.out.println("insertStack: 새로 추가할 스터디 스택가져오자" + newStack.toString());
				System.out.println("insertStack: 새로 추가할 스터디 스터디 모집글" + newStudy.toString());
				
				for (int chooseStack : chooseStacks) {
					if (sDao.insertNewStack(newStack.getStuBoardNo(), chooseStack) == 1) {
						System.out.println("스택추가성공");
						result = 1;
					}
				}
			}else {
				System.out.println("스터디글추가실패");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public StudyBoardVO selectStudyByStuNo(int stuNo) throws Exception {
		return sDao.selectStudyByStuNo(stuNo);
	}

	@Override
	public List<StackVO> selectAllStack() throws Exception {
		return sDao.selectAllStack();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public int modifyStudyWithStack(StudyBoardDTO newStudy, StuStackModifyDTO modifyStack) throws Exception {

		int result = 0;

		// StuStackVO의 stuBoardNo값 세팅
		modifyStack.setStuBoardNo(newStudy.getStuNo());

		System.out.println("modifyStack: 수정할 스터디 스택" + modifyStack.toString());
		System.out.println("insertStack: 수정할 스터디 스터디 모집글" + newStudy.toString());
		
		int[] chooseStacks = modifyStack.getChooseStack();
		int[] deleteStackNo = modifyStack.getStuStackNo();
		

		if (sDao.modifyStudy(newStudy) == 1) {
			System.out.println("스터디글수정성공");

			// 전에 있었던 스터디 언어 애들 지우기
			for (int deleteNo : deleteStackNo) {
				System.out.println("지울 스터디 스택번호" + deleteNo);
				
				if (sDao.deleteStudyStack(deleteNo) == 1) {					
					System.out.println("스택 지우기 성공");					
				}

			}
			
			// 스터디 언어 새로 인서트하기
			for (int chooseStack : chooseStacks) {
				if (sDao.insertNewStack(modifyStack.getStuBoardNo(), chooseStack) == 1) {
					System.out.println("스택수정성공");
					result = 1;
				}

			}

		}

		return result;
	}

	@Override
	public int deleteStudyBoard(int stuNo) throws Exception {
		return sDao.deleteStudyBoard(stuNo);
	}
}
