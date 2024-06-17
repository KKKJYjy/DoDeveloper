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
	 * @description : 1) 검색어가 있고 + 필터가 있는 경우 2) 검색어가 있고 + 필터가 없는 경우 3) 검색어가 없고 + 필터가
	 *              있는 경우 4) 검색어가 없고 + 필터가 없는 경우 스터디 리스트 불러오기
	 */
	@Override
	public Map<String, Object> selectAllList(SearchStudyDTO sDTO, int pageNo) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		// 검색어가 있을 경우의 게시글 목록과 없는 경우의 게시글 목록이 다르다.
		List<StudyBoardVO> lst = null;
		System.out.println("service단 pageNo : " + pageNo + ", sDTO:" + sDTO.toString());

		if ((sDTO.getSearchType() == null || sDTO.getSearchType().equals(""))
				&& (sDTO.getSearchValue() == null || sDTO.getSearchValue().equals(""))
				&& (sDTO.getStatusFilter() == null || sDTO.getStatusFilter().equals(""))) {

			// 첫 화면 and 전체글 클릭했을 때 검색어가 없는 경우, 모두 null값이 들어오고
			// 첫 화면 and 전체글 클릭했을 때 페이지 클릭했을 땐, 모두 빈 값이 들어온다
			// 4) 검색어가 없고 필터가 없는 경우
			System.out.println("첫화면 ");
			makingPagingInfo(pageNo);
			lst = sDao.selectAllList(pi);

		} else if (sDTO.getSearchType().equals("") && sDTO.getSearchType().equals("")
				&& sDTO.getStatusFilter() == null) {

			// 전체글 클릭했을 때
			// 4) 검색어가 없고 필터가 없는 경우
			System.out.println("전체글 ");
			makingPagingInfo(pageNo);
			lst = sDao.selectAllList(pi);

		} else if (sDTO.getSearchType().equals("") && sDTO.getSearchValue().equals("")
				&& !(sDTO.getStatusFilter().equals(""))) {

			// 3) 모집중 or 모집마감 클릭했을 때 - 검색어가 없고 필터가 있는 경우
			System.out.println("검색어가 없고 + 필터가 있는 경우");
			makingPagingInfo(pageNo, sDTO.getStatusFilter());
			lst = sDao.selectAllListWithStatusFilter(pi, sDTO.getStatusFilter());

		} else if (!(sDTO.getSearchType().equals("")) && !(sDTO.getSearchValue().equals(""))
				&& sDTO.getStatusFilter() != null && !(sDTO.getStatusFilter().equals(""))) {

			// 1) 모집중, 모집마감 클릭했을 때 - 검색어가 있고 필터가 있는 경우
			System.out.println("검색어가 있고 + 필터가 있는 경우");
			makingPagingInfo(sDTO, pageNo, sDTO.getStatusFilter());
			lst = sDao.selectAllListWithsDTOWithStatusFilter(sDTO, pi, sDTO.getStatusFilter());

		} else if (!(sDTO.getSearchType().equals("")) && !(sDTO.getSearchValue().equals(""))
				&& sDTO.getStatusFilter() == null || sDTO.getStatusFilter().equals("")) {

			// 2) 첫화면 or 전체글 클릭했을 때 - 검색어가 있고 필터가 없는 경우
			System.out.println("검색어가 있고 필터가 없는 경우");
			makingPagingInfo(sDTO, pageNo);
			lst = sDao.selectAllListWithsDTO(sDTO, pi);

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
				System.out.println("insertStack: 추가할 스터디 언어 게시글 번호" + newStack.getStuBoardNo());

				for (int chooseStack : chooseStacks) {
					if (sDao.insertNewStack(newStack.getStuBoardNo(), chooseStack) == 1) {
						System.out.println("스터디언어추가성공");
						result = 1;
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("스터디글추가, 스터디언어추가실패");
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
	 * @description : 1) bType 게시판의 stuNo번째 스터디 모임글을 userId가 조회한적이 있는지 체크후 2) 읽은적이
	 *              한번도 없다면 조회 기록 테이블에 조회 기록 insert 3) 그 다음 studyBoard 테이블에 조회수
	 *              update
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
	 * @description : 1) 스터디글 update 2) 스터디 언어 플래그 변수 체크해서 삭제할것이 있다면 delete 3) 스터디
	 *              언어 플래그 변수 체크해서 새로 추가 할것이 있다면 insert
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
	 * @description : 유저가 필터링할 스터디 언어를 여러개 선택하면, 해당하는 스터디 모임글 리스트와 해당 모임글의 스터디 언어
	 *              리스트를 반환해준다.
	 */
	@Override
	public Map<String, Object> searchStudyByStack(List<String> studyStackList, int pageNo) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		// pi객체 설정
		makingPagingInfo(studyStackList, pageNo);
		List<StudyBoardVO> lst = sDao.searchStudyByStack(studyStackList, pi);

		for (StudyBoardVO l : lst) {
			System.out.println("service단 게시글제목:" + l.getStuTitle());
		}
		System.out.println("service단 게시글갯수:" + lst.size());

		// 스터디 No번째글 스터디 언어 목록
		List<StuStackDTO> stuStackList = new ArrayList<StuStackDTO>();

		for (StudyBoardVO s : lst) {
			// stuNo를 넘겨주어 공부할 언어 정보를 가져오자
			stuStackList.addAll(sDao.selectAllStudyStack(s.getStuNo()));
		}

		result.put("studyList", lst);
		result.put("stuStackList", stuStackList);
		result.put("pagingInfo", this.pi);

		return result;

	}

	/**
	 * @author : yeonju
	 * @date : 2024. 6. 9.
	 * @return : List<StudyBoardVO>
	 * @description : 스터디 모임글 상위 5개글을 가져온다
	 */
	@Override
	public List<StudyBoardVO> getStudyTop5() throws Exception {
		return sDao.getStudyTop5();
	}
	

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 28.
	 * @param : List<String> studyStackList
	 * @param : int pageNo
	 * @return : void
	 * @description : 스터디 언어로 검색했을 때 pi 객체 설정
	 */
	private void makingPagingInfo(List<String> studyStackList, int pageNo) {

		this.pi.setPageNo(pageNo);
		this.pi.setViewPostCntPerPage(11);
		this.pi.setPageCntPerBlock(5);

		System.out.println(sDao.selectTotalBoardCntWithSdtoWithStuStack(studyStackList));
		this.pi.setTotalPostCnt(sDao.selectTotalBoardCntWithSdtoWithStuStack(studyStackList));

		// 총 페이지 수를 구해 저장.
		this.pi.setTotalPageCnt();
		// 보여주기 시작할 row index값 구해 저장하기
		this.pi.setStartRowIndex();

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

		// 지역변수 PageNo의 값을 pagingInfo클래스 멤버변수 pageNo에 세팅
		this.pi.setPageNo(pageNo);
		this.pi.setViewPostCntPerPage(11);
		this.pi.setPageCntPerBlock(5);

		// 총 페이지 수를 구해 저장.
		this.pi.setTotalPageCnt();
		// 보여주기 시작할 row index값 구해 저장하기
		this.pi.setStartRowIndex();

		// 게시물 총 데이터 갯수를 구해 저장
		System.out.println(sDao.selectTotalBoardCntWithSdtoWithStatusFilter(sDTO, status));
		this.pi.setTotalPostCnt(sDao.selectTotalBoardCntWithSdtoWithStatusFilter(sDTO, status));

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
	 * @return : void
	 * @description : 검색어가 있고 필터가 없는 경우 경우 pi 세팅
	 */
	private void makingPagingInfo(SearchStudyDTO sDTO, int pageNo) throws Exception {

		// 지역변수 PageNo의 값을 pagingInfo클래스 멤버변수 pageNo에 세팅
		this.pi.setPageNo(pageNo);
		this.pi.setViewPostCntPerPage(11);
		this.pi.setPageCntPerBlock(5);

		// 게시물 총 데이터 갯수를 구해 저장
		System.out.println(sDao.selectTotalBoardCntWithSdto(sDTO));
		this.pi.setTotalPostCnt(sDao.selectTotalBoardCntWithSdto(sDTO));

		// 총 페이지 수를 구해 저장.
		this.pi.setTotalPageCnt();
		// 보여주기 시작할 row index값 구해 저장하기
		this.pi.setStartRowIndex();

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
	 * @param : int pageNo
	 * @param : String status
	 * @return : void
	 * @description : 검색어가 없고 필터가 있는 경우 pi 세팅
	 */
	private void makingPagingInfo(int pageNo, String status) throws Exception {

		// 지역변수 PageNo의 값을 pagingInfo클래스 멤버변수 pageNo에 세팅
		this.pi.setPageNo(pageNo);
		this.pi.setViewPostCntPerPage(11);
		this.pi.setPageCntPerBlock(5);

		System.out.println("service단 검색어가 없고 필터가 있는 경우: " + status);
		// 게시물 총 데이터 갯수를 구해 저장
		System.out.println(sDao.selectTotalBoardCntWithStatusFilter(status));
		this.pi.setTotalPostCnt(sDao.selectTotalBoardCntWithStatusFilter(status));

		// 총 페이지 수를 구해 저장.
		this.pi.setTotalPageCnt();
		// 보여주기 시작할 row index값 구해 저장하기
		this.pi.setStartRowIndex();

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
	 * @param : int pageNo
	 * @return : void
	 * @description : 검색어가 없고 필터가 없는 경우 경우 pi 객체 세팅
	 */
	private void makingPagingInfo(int pageNo) throws Exception {

		// 지역변수 PageNo의 값을 pagingInfo클래스 멤버변수 pageNo에 세팅
		this.pi.setPageNo(pageNo);
		this.pi.setViewPostCntPerPage(11);
		this.pi.setPageCntPerBlock(5);

		// 게시물 총 데이터 갯수를 구해 저장
		System.out.println(sDao.selectTotalBoardCnt());
		this.pi.setTotalPostCnt(sDao.selectTotalBoardCnt());

		// 총 페이지 수를 구해 저장.
		this.pi.setTotalPageCnt();
		// 보여주기 시작할 row index값 구해 저장하기
		this.pi.setStartRowIndex();

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

}
