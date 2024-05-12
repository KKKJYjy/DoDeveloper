package com.dodeveloper.lecture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dodeveloper.lecture.dao.LectureBoardDAO;
import com.dodeveloper.lecture.vodto.LectureBoardDTO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.lecture.vodto.LectureSearchDTO;

@Service // 아래의 클래스가 서비스 객체임을 명시하는 것
public class LectureBoardServiceImpl implements LectureBoardService {

	@Autowired
	private LectureBoardDAO lDao; // 스프링 컨테이너에 있는 LectureDAO 객체를 찾아 주입

	/**
	 * @methodName : getListAllBoard
	 * @author : kde
	 * @date : 2024.05.02
	 * @param : int lecNo - 게시글 번호
	 * @return : List<LectureBoardVO>
	 * @description : 게시판 전체 조회에 대한 서비스 메서드
	 */
	@Override
	public List<LectureBoardVO> getListAllBoard(int lecNo) throws Exception {
		System.out.println("서비스단 : 페이지 전체 게시글 조회!");

		// DAO단 호출 (selectListAllLecBoard() 메서드 호출)
		List<LectureBoardVO> lecBoardList = null;

		return lDao.selectListAllLecBoard();

	}

	/**
	 * @methodName : listAllBoardBySearch
	 * @author : kde
	 * @date : 2024.05.06
	 * @param : int lecNo - 게시글 번호
	 * @param : LectureSearchDTO lsDTO - 검색할 때 가져올 Type, Value
	 * @return : List<LectureBoardVO>
	 * @description : 검색 조건을 선택하고 검색어를 입력했을 때 글을 가져오는 메서드 - 검색 조건
	 */
	@Override
	public List<LectureBoardVO> listAllBoardBySearch(int lecNo, LectureSearchDTO lsDTO) throws Exception {
		System.out.println("검색조건을 선택하고 검색어를 입력했습니다!");

		// 검색 조건
		// 검색어가 있는지 없는지 판단
		if (lsDTO.getSearchType() != null && lsDTO.getSearchValue() != null) {
			// 검색어가 있는 경우
			return lDao.lectureBoardListWithSc(lsDTO);
		} else {
			// 검색어가 없는 경우
			return lDao.selectListAllLecBoard();

		}
	}

	/**
	 * @methodName : listAllBoardByFilter
	 * @author : kde
	 * @date : 2024.05.06
	 * @param : List<LectureBoardVO> lectureBoardList - 게시글 목록
	 * @param : String filterType - 필터 타입(최신순 / 인기순 / 조회순)
	 * @return : List<LectureBoardVO>
	 * @description : 검색 필터(최신순 / 인기순 / 조회순)을 선택했을 때 글을 가져오는 메서드 - 검색 필터
	 */
	@Override
	public List<LectureBoardVO> listAllBoardByFilter(List<LectureBoardVO> lectureBoardList, String filterType)
			throws Exception {

		return lDao.listAllBoardByFilter(lectureBoardList, filterType);
	}

	/**
	 * @methodName : getBoardByBoardNo
	 * @author : kde
	 * @date : 2024.05.03
	 * @param : int boardNo - 조회할 글 번호
	 * @param : String user - 조회하는 유저 (로그인 하지 않았을 경우 session id)
	 * @return : Map<String, Object>
	 * @description : 1) user가 조회하는 글을 하루 이내에 읽은 적이 있는지 없는지 검사
	 * 2) 하루 이내에 읽은 적이 없다 -> 조회수 증가(update), 조회이력 - 기록을 남긴다(insert), 글 가져옴(유저가 글을 조회하도록)
	 * 3) 하루 이내에 읽은 적이 있다 -> 게시글만 가져옴(유저가 글을 조회하도록)
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getBoardByBoardNo(int lecNo, String user) throws Exception {
		System.out.println(lDao.selectDiff(user, lecNo));

		// 조회된 글 가져오기
		LectureBoardVO lecBoard = lDao.selectBoardLecNo(lecNo);

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("lecBoard", lecBoard); // 조회된 글 바인딩

		return result;
	}

	/**
	 * @methodName : getBoardByBoardNo
	 * @author : kde
	 * @date : 2024.05.04
	 * @param : int lecNo - 얻어오려는 글 번호
	 * @return : Map<String, Object>
	 * @description : 게시글 수정을 위해 게시글 번호와 같은 게시글을 반환하는 메서드
	 */
	@Override
	public Map<String, Object> getBoardByBoardNo(int lecNo) throws Exception {

		// DAO단에서 조회된 글 가져오기
		LectureBoardVO lecBoard = lDao.selectBoardLecNo(lecNo);

		System.out.println("조회된 글 : " + lecBoard.toString());

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("lecBoard", lecBoard);

		return result;
	}

	/**
	 * @methodName : writeBoardService
	 * @author : kde
	 * @date : 2024.05.04
	 * @param : LectureBoardDTO newLecBoard - 저장될 게시글
	 * @return : boolean
	 * @description : newLecBoard가 DB에 저장 (insert)
	 */
	@Override
	public boolean writeBoardService(LectureBoardDTO newLecBoard) throws Exception {

		boolean result = false;

		newLecBoard.setLecReview(newLecBoard.getLecReview().replace("\r\n", "</ br>"));

		// dao단 호출
		if (lDao.insertNewLectureBoard(newLecBoard) == 1) { // 실제 게시글 (insert)
			System.out.println("새로 저장될 게시글 번호 : " + newLecBoard.getLecNo());

			result = true;
		}

		return result;
	}

	/**
	 * @methodName : modifyBoard
	 * @author : kde
	 * @date : 2024.05.04
	 * @param : LectureBoardDTO modifyBoard - 수정되어야 할 게시글
	 * @return : boolean
	 * @description : 게시글 수정 시 update 처리
	 */
	@Override
	public boolean modifyBoard(LectureBoardDTO modifyBoard) throws Exception {
		boolean result = false;

		if (lDao.updateLectureBoard(modifyBoard) == 1) {
			result = true;
		}

		return result;
	}

	/**
	 * @methodName : deleteLectureBoard
	 * @author : kde
	 * @date : 2024.05.05
	 * @param : int lecNo - 삭제 처리하려는 게시글 번호
	 * @return : boolean
	 * @description : lecNo번 글을 삭제 처리
	 */
	@Override
	public boolean deleteLectureBoard(int lecNo) throws Exception {

		boolean result = false;

		if (lDao.deleteLectureBoard(lecNo) == 1) {
			result = true;
		}

		return result;
	}

}
