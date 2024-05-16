package com.dodeveloper.study.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dodeveloper.lookup.dao.LookupDAO;
import com.dodeveloper.study.dao.StudyDAO;
import com.dodeveloper.study.etc.PagingInfo;
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

	@Autowired
	LookupDAO lDao;

	@Autowired
	private PagingInfo pi;

	@Override
	public Map<String, Object> selectAllList(SearchStudyDTO sDTO, int pageNo) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		// 검색어가 있을 경우의 게시글 목록과 없는 경우의 게시글 목록이 다르다.
		List<StudyBoardVO> lst = null;

		if (sDTO.getSearchType() != null && sDTO.getSearchValue() != null) {
			// 검색어가 있는 경우
			makingPagingInfo(sDTO, pageNo);
			lst = sDao.selectAllListWithsDTO(sDTO, pi);
		} else {
			// 검색어가 없는 경우
			makingPagingInfo(pageNo);
			lst = sDao.selectAllList(pi);
		}

		// 다오단에서 전체 페이지 리스트를 불러오면서 페이징 객체 pi를 같이 넘겨준다
		result.put("studyList", lst);
		result.put("pagingInfo", this.pi);

		return result;
	}

	// 검색어가 없는 경우 pi 객체 세팅
	private void makingPagingInfo(int pageNo) throws Exception {
		// 지역변수 PageNo의 값을 pagingInfo클래스 멤버변수 pageNo에 세팅
		this.pi.setPageNo(pageNo);

		// 게시물 총 데이터 갯수를 구해 저장
		System.out.println(sDao.selectTotalBoardCnt());
		this.pi.setTotalPostCnt(sDao.selectTotalBoardCnt());

		// 총 페이지 수를 구해 저장.
		this.pi.setTotalPageCnt();

		// 보여주기 시작할 row index값 구해 저장하기
		this.pi.setStartRowIndex();

		// ================================================

		// 전체 페이지 블럭 갯수 구해 저장
		this.pi.setTotalPageBlockCnt();

		// 현재 페이지가 속한 페이징 블럭 번호 구해 저장
		this.pi.setPageBlockOfCurrentPage();

		// 현재 페이징 블럭 시작 페이지 구해 저장
		this.pi.setStartNumOfCurrentPagingBlock();

		// 현재 페이징 블럭 끝 페이지 구해 저장
		this.pi.setEndNumOfCurrentPagingBlock();

		System.out.println(pi.toString());
	}

	// 검색어가 있는 경우 pi 세팅
	private void makingPagingInfo(SearchStudyDTO sDTO, int pageNo) throws Exception {
		// 지역변수 PageNo의 값을 pagingInfo클래스 멤버변수 pageNo에 세팅
		this.pi.setPageNo(pageNo);

		// 게시물 총 데이터 갯수를 구해 저장
		System.out.println(sDao.selectTotalBoardCntWithSdto(sDTO));
		this.pi.setTotalPostCnt(sDao.selectTotalBoardCntWithSdto(sDTO));

		// 총 페이지 수를 구해 저장.
		this.pi.setTotalPageCnt();

		// 보여주기 시작할 row index값 구해 저장하기
		this.pi.setStartRowIndex();

		// ================================================

		// 전체 페이지 블럭 갯수 구해 저장
		this.pi.setTotalPageBlockCnt();

		// 현재 페이지가 속한 페이징 블럭 번호 구해 저장
		this.pi.setPageBlockOfCurrentPage();

		// 현재 페이징 블럭 시작 페이지 구해 저장
		this.pi.setStartNumOfCurrentPagingBlock();

		// 현재 페이징 블럭 끝 페이지 구해 저장
		this.pi.setEndNumOfCurrentPagingBlock();

		System.out.println(pi.toString());
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
	public int insertStudyWithStack(StudyBoardDTO newStudy, StuStackVO newStack) {

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
			} else {
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
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public Map<String, Object> selectStudyByStuNo(int stuNo, String userId, int bType) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		//System.out.println(stuNo + "번째 글을" + userId + "가 조회한다 - 서비스단");
		System.out.println(lDao.selectDiff(userId, stuNo, bType));
		
		// 하루이내에 같은 유저가 현재 글을 본적이 있는지 체크
		if (lDao.selectDiff(userId, stuNo, bType) == -1) {
			// 읽은적이 한번도 없다면
			lDao.insertLookup(userId, stuNo, bType);
			lDao.updateLookupStudyBoard(stuNo);
		}else {
			System.out.println("이미오늘조회했다");
		}

		// stuNo번째 스터디 글
		StudyBoardVO studyList = sDao.selectStudyByStuNo(stuNo);
		
		// 스터디 stuNo번째글 스터디 언어 목록
		List<StuStackDTO> stuStackList = new ArrayList<StuStackDTO>();
		
		// stuNo를 넘겨주어 공부할 언어 정보를 가져오자
		stuStackList.addAll(sDao.selectAllStudyStack(studyList.getStuNo()));

		result.put("studyList", studyList);
		result.put("stuStackList", stuStackList);
		
		return result;

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
