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

import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.lookup.dao.LookupDAO;
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

	@Autowired
	LookupDAO lDao;

	@Autowired
	private PagingInfo pi;

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : SearchStudyDTO sDTO - 스터디 모임글 검색 내용 담는 DTO
	 * @param : int pageNo - 페이지 번호
	 * @param : String status - 스터디 모집상태 (모집중, 모집마감)
	 * @return : Map<String, Object> - 스터디 리스트 객체와 페이징 객체가 담긴 Map
	 * @description : 
	 * 1) 검색어가 있고 + 필터가 있는 경우 
	 * 2) 검색어가 있고 + 필터가 없는 경우 
	 * 3) 검색어가 없고 + 필터가 있는 경우 
	 * 4) 검색어가 없고 + 필터가 없는 경우 스터디 리스트 불러오기
	 */
	@Override
	public Map<String, Object> selectAllList(SearchStudyDTO sDTO, int pageNo, String status) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		// 검색어가 있을 경우의 게시글 목록과 없는 경우의 게시글 목록이 다르다.
		List<StudyBoardVO> lst = null;
		System.out.println("service단: "+ status + pageNo + sDTO.toString());

		if (sDTO.getSearchType() != null && sDTO.getSearchValue() != null && !(status.equals(""))) {
			// 1) 검색어가 있고 + 필터가 있는 경우
			makingPagingInfo(sDTO, pageNo, status);
			lst = sDao.selectAllListWithsDTOWithStatusFilter(sDTO, pi, status);
		} else if (sDTO.getSearchType() != null && sDTO.getSearchValue() != null) {
			// 2) 검색어가 있고 + 필터가 없는 경우
			makingPagingInfo(sDTO, pageNo);
			lst = sDao.selectAllListWithsDTO(sDTO, pi);
		} else if (!(status.equals(""))) {
			// 3) 검색어가 없고 + 필터가 있는 경우
			makingPagingInfo(pageNo, status);
			lst = sDao.selectAllListWithStatusFilter(pi, status);
		} else {
			// 4) 검색어가 없고 + 필터가 없는 경우
			makingPagingInfo(pageNo);
			lst = sDao.selectAllList(pi);
		}

		// 다오단에서 전체 페이지 리스트를 불러오면서 페이징 객체 pi를 같이 넘겨준다
		result.put("studyList", lst);
		result.put("pagingInfo", this.pi);

		return result;
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : int stuNo
	 * @return : List<StuStackDTO> - 스터디 언어(스택) 정보를 담고있는 객체
	 * @description : stuNo번째 스터디 언어 정보를 불러온다
	 */
	@Override
	public List<StuStackDTO> selectAllStudyStack(int stuNo) throws Exception {
		return sDao.selectAllStudyStack(stuNo);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @return : int - 다음 스터디 모임글 번호
	 * @description : 다음 스터디 모임글의 번호가 몇번인지 select
	 */
	@Override
	public int selectNextStuNo() throws Exception {
		return sDao.selectNextStuNo();
	}

	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : StudyBoardDTO newStudy - 새로 추가할 스터디 모임글
		* @param : StuStackVO newStack - 새로 추가할 스터디 모임글의 스터디 언어(스택)
		* @return : int - 작업 성공했으면 1, 실패했으면 0 반환
		* @description : 스터디 모임글을 먼저 insert 성공하면 스터디 언어를 insert 한다.
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public int insertStudyWithStack(StudyBoardDTO newStudy, StuStackVO newStack) {

		int result = 0;

		try {

			int[] chooseStacks = newStack.getChooseStack();

			if (sDao.insertNewStudy(newStudy) == 1) {
				System.out.println("스터디글추가성공");

				System.out.println("insertStack: 새로 추가할 스터디 스터디 모집글" + newStudy.toString());

				// StuStackVO의 stuBoardNo값 세팅
				newStack.setStuBoardNo(sDao.selectNextStuNo());
				System.out.println("insertStack: 추가할 스터디 스택 게시글 번호" + newStack.getStuBoardNo());
				System.out.println("insertStack: 새로 추가할 스터디 스택가져오자" + newStack.toString());

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

	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : int stuNo
		* @return : StudyBoardVO - 스터디 모임글 정보를 담고있는 객체
		* @description : stuNo번째 상세페이지로 이동하기 위해 스터디 모임글 정보를 select
	 */
	@Override
	public StudyBoardVO selectStudyByStuNo(int stuNo) throws Exception {
		return sDao.selectStudyByStuNo(stuNo);
	}

	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : int stuNo
		* @param : String userId
		* @param : int bType
		* @return : Map<String, Object> - 스터디 모임글 리스트와 스터디 언어 리스트를 담고있는 Map 
		* @description : 1) bType 게시판의 stuNo번째 스터디 모임글을 userId가 조회한적이 있는지 체크후
		* 2) 읽은적이 한번도 없다면 조회 기록 테이블에 조회 기록 insert
		* 3) 그 다음 studyBoard 테이블에 조회수 update 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public Map<String, Object> selectStudyByStuNo(int stuNo, String userId, int bType) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		// System.out.println(stuNo + "번째 글을" + userId + "가 조회한다 - 서비스단");
		System.out.println(lDao.selectDiff(userId, stuNo, bType));

		// 하루이내에 같은 유저가 현재 글을 본적이 있는지 체크
		if (lDao.selectDiff(userId, stuNo, bType) == -1) {
			// 읽은적이 한번도 없다면
			lDao.insertLookup(userId, stuNo, bType);
			lDao.updateLookupStudyBoard(stuNo);
		} else {
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

	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @return : List<StackVO>
		* @description : DB 스터디 언어 테이블의 정보를 담고있는 객체를 반환한다.
	 */
	@Override
	public List<StackVO> selectAllStack() throws Exception {
		return sDao.selectAllStack();
	}

	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : StudyBoardDTO newStudy - 수정할 스터디 언어 모임글
		* @param : List<StuStackDTO> modifyStack - 수정할 스터디 언어들
		* @return : int - 성공하면 1, 실패하면 0 반환
		* @description : 1) 스터디글 update 
		* 2) 스터디 언어 플래그 변수 체크해서 삭제할것이 있다면 delete
		* 3) 스터디 언어 플래그 변수 체크해서 새로 추가 할것이 있다면 insert
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public int modifyStudyWithStack(StudyBoardDTO newStudy, List<StuStackDTO> modifyStack) throws Exception {

		int result = 0;

		System.out.println("modifyStack: 수정할 스터디 스택" + modifyStack.toString());
		System.out.println("insertStack: 수정할 스터디 스터디 모집글" + newStudy.toString());

		if (sDao.modifyStudy(newStudy) == 1) {
			System.out.println("스터디글수정성공");

			for (StuStackDTO s : modifyStack) {
				if (s.isDelete()) {
					sDao.deleteStudyStack(s.getStuStackNo());
					System.out.println(s.getStuStackNo() + "번째 스터디언어 삭제 성공");
					result = 1;
				}
				if (s.isNew()) {
					sDao.insertNewStack(s.getStuBoardNo(), s.getChooseStack());
					System.out.println(s.getChooseStack() + "스터디언어 추가 성공");
					result = 1;
				}

				result = 1;
			}

		}

		return result;
	}

	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : int stuNo
		* @return : int - 성공하면 1, 실패하면 0 반환
		* @description : stuNo번째 스터디 모임글을 삭제한다.
	 */
	@Override
	public int deleteStudyBoard(int stuNo) throws Exception {
		return sDao.deleteStudyBoard(stuNo);
	}

	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : List<String> studyStackList - 필터링할 스터디 언어 List
		* @return : Map<String, Object> - 스터디 모임글 리스트와 해당 모임글의 스터디 언어 리스트가 담긴 Map
		* @description : 유저가 필터링할 스터디 언어를 여러개 선택하면, 
		* 해당하는 스터디 모임글 리스트와 해당 모임글의 스터디 언어 리스트를 반환해준다.
	 */
	@Override
	public Map<String, Object> searchStudyByStack(List<String> studyStackList) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		List<StudyBoardVO> lst = sDao.searchStudyByStack(studyStackList);

		for (StudyBoardVO l : lst) {
			System.out.println("service단 게시글제목:" + l.getStuTitle());
		}
		System.out.println("service단 게시글갯수:" + lst.size());

		// 스터디 No번째글 스터디 언어 목록
		List<StuStackDTO> stuStackList = new ArrayList<StuStackDTO>();

		for (StudyBoardVO s : lst) {
			// stuNo를 넘겨주어 공부할 언어 정보를 가져오자
			stuStackList.addAll(sDao.selectAllStudyStack(s.getStuNo()));

			// System.out.println(s.getStuNo());
		}
		// stack테이블의 모든 값들을 가져오자
		List<StackVO> stackList = sDao.selectAllStack();

		// System.out.println(stuStackList.toString());

		result.put("studyList", lst);
		result.put("stuStackList", stuStackList);
		// result.put("stackList", stackList);

		return result;

	}

	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : int pageNo
		* @return : void
		* @description : 1~4번의 경우에서 공통적으로 처리해야할 부분 처리
	 */
	private void makingPagingInfoCommons(int pageNo) {
		// 지역변수 PageNo의 값을 pagingInfo클래스 멤버변수 pageNo에 세팅
		this.pi.setPageNo(pageNo);

		this.pi.setViewPostCntPerPage(11);
		this.pi.setPageCntPerBlock(5);

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

 
	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : SearchStudyDTO sDTO
		* @param : int pageNo
		* @param : String status
		* @return : void
		* @description : 검색어가 있고 필터가 있는 경우 pi 세팅
	 */
	private void makingPagingInfo(SearchStudyDTO sDTO, int pageNo, String status) throws Exception {

		makingPagingInfoCommons(pageNo);

		// 게시물 총 데이터 갯수를 구해 저장
		System.out.println(sDao.selectTotalBoardCntWithSdtoWithStatusFilter(sDTO, status));
		this.pi.setTotalPostCnt(sDao.selectTotalBoardCntWithSdtoWithStatusFilter(sDTO, status));

	}

	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : SearchStudyDTO sDTO
		* @param : int pageNo
		* @return : void
		* @description : 검색어가 있고 필터가 없는 경우 경우 pi 세팅
	 */
	private void makingPagingInfo(SearchStudyDTO sDTO, int pageNo) throws Exception {

		makingPagingInfoCommons(pageNo);

		// 게시물 총 데이터 갯수를 구해 저장
		System.out.println(sDao.selectTotalBoardCntWithSdto(sDTO));
		this.pi.setTotalPostCnt(sDao.selectTotalBoardCntWithSdto(sDTO));

	}

	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : int pageNo
		* @param : String status
		* @return : void
		* @description : 검색어가 없고 필터가 있는 경우 pi 세팅
	 */
	private void makingPagingInfo(int pageNo, String status) throws Exception {

		makingPagingInfoCommons(pageNo);

		System.out.println("service단 검색어가 없고 필터가 있는 경우: " + status);
		// 게시물 총 데이터 갯수를 구해 저장
		System.out.println(sDao.selectTotalBoardCntWithStatusFilter(status));
		this.pi.setTotalPostCnt(sDao.selectTotalBoardCntWithStatusFilter(status));

	}

	/**
		* @author : yeonju
		* @date : 2024. 5. 24.
		* @param : int pageNo 
		* @return : void
		* @description : 검색어가 없고 필터가 없는 경우 경우 pi 객체 세팅
	 */
	private void makingPagingInfo(int pageNo) throws Exception {

		makingPagingInfoCommons(pageNo);

		// 게시물 총 데이터 갯수를 구해 저장
		System.out.println(sDao.selectTotalBoardCnt());
		this.pi.setTotalPostCnt(sDao.selectTotalBoardCnt());

	}

}
