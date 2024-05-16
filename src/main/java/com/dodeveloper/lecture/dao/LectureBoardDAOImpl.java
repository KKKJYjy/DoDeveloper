package com.dodeveloper.lecture.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dodeveloper.lecture.etc.PagingInfo;
import com.dodeveloper.lecture.vodto.LectureBoardDTO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.lecture.vodto.LectureSearchDTO;

@Repository // 아래의 클래스가 DAO 객체임을 명시
public class LectureBoardDAOImpl implements LectureBoardDAO {

	@Autowired
	private SqlSession ses; // SqlSession 객체 주입

	private static String ns = "com.dodeveloper.mappers.lectureBoardMapper";

	/**
	 * @methodName : selectListAllLecBoard
	 * @author : kde
	 * @date : 2024.05.02
	 * @param : PagingInfo pi - 페이징하기 위한 변수들
	 * @return : List<LectureBoardVO>
	 * @description : 게시판 전체 조회에 대한 DAO 메서드
	 */
	@Override
	public List<LectureBoardVO> selectListAllLecBoard(PagingInfo pi) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		// 유저가 클릭한 페이지에서 보여주기 시작할 row index 번호
		params.put("startRowIndex", pi.getStartRowIndex());
		// 한 페이지당 보여줄 게시글의 갯수
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".getAllBoard", params);
	}

	/**
	 * @methodName : selectBoardByBoardNo
	 * @author : kde
	 * @date : 2024.05.03
	 * @param : int lecNo - 게시글 번호
	 * @return :LectureBoardVO - 게시글을 가져올때 필요한 변수들
	 * @description : ?번 글을 가져오는 메서드
	 */
	@Override
	public LectureBoardVO selectBoardLecNo(int lecNo) throws Exception {

		return ses.selectOne(ns + ".selectBoardLecNo", lecNo);
	}
	
	/**
	 * @methodName : selectDiff
	 * @author : kde
	 * @date : 2024.05.03
	 * @param : String user - 글을 조회한 유저
	 * @param : int lecNo - 게시글
	 * @return : int
	 * @description : 유저가 ?번 글을 언제 읽었는지 select하는 메서드
	 */
	@Override
	public int selectDiff(String user, int lecNo) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("user", user);
		params.put("lecNo", lecNo);

		return ses.selectOne(ns + ".getDateDiff", params);
	}
	
	/**
	 * @methodName : updateReadCount
	 * @author : kde
	 * @date : 2024.05.03
	 * @param : int lecNo - 게시글
	 * @return : int
	 * @description : ?번 글의 조회수를 증가하는 메서드
	 */
	@Override
	public int updateReadCount(int lecNo) throws Exception {

		return ses.update(ns + ".updateReadCount", lecNo);
	}

	/**
	 * @methodName : insertReadCountProcess
	 * @author : kde
	 * @date : 2024.05.03
	 * @param : String user - 글을 조회한 유저
	 * @param : int lecNo - 게시글
	 * @return : int
	 * @description : ?번 글을 ?유저가 조회했다는 이력을 기록하는 메서드
	 */
	@Override
	public int insertReadCountProcess(String user, int lecNo) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("user", user);
		params.put("lecNo", lecNo);

		return ses.insert(ns + ".insertReadCountProcess", params);
	}

	/**
	 * @methodName : insertNewLectureBoard
	 * @author : kde
	 * @date : 2024.05.04
	 * @param : LectureBoardDTO newLecBoard - 유저가 작성한 글을 insert
	 * @return : int
	 * @description : 유저가 작성한 글을 insert
	 */
	@Override
	public int insertNewLectureBoard(LectureBoardDTO newLecBoard) throws Exception {

//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("lecTitle", newLecBoard.getLecTitle());
//		params.put("lecReview", newLecBoard.getLecReview());
//		params.put("lecWriter", newLecBoard.getLecWriter());
//		params.put("lecScore", newLecBoard.getLecScore());
//		params.put("lecLink", newLecBoard.getLecLink());

		System.out.println("새로 저장될 글 : " + newLecBoard.getLecNo());

		return ses.insert(ns + ".insertLectureBoard", newLecBoard);
	}

	/**
	 * @methodName : updateLectureBoard
	 * @author : kde
	 * @date : 2024.05.04
	 * @param : LectureBoardDTO modifyBoard
	 * @return : int
	 * @description : 실제 게시글을 수정하는 메서드
	 */
	@Override
	public int updateLectureBoard(LectureBoardDTO modifyBoard) throws Exception {

		return ses.update(ns + ".updateLectureBoard", modifyBoard);
	}

	/**
	 * @methodName : deleteLectureBoard
	 * @author : kde
	 * @date : 2024.05.05
	 * @param : int lecNo - 삭제될 게시글 번호
	 * @return : int
	 * @description : 게시글을 삭제(delete)하는 메서드
	 */
	@Override
	public int deleteLectureBoard(int lecNo) throws Exception {

		return ses.delete(ns + ".deleteLectureBoard", lecNo);
	}

	/**
	 * @methodName : selectTotalLectureBoardCnt
	 * @author : 
	 * @date : 2024.05.14
	 * @return : int
	 * @description : 검색어가 없을 경우 게시글 전체 글 갯수를 얻어오는 메서드 - 검색조건
	 */
	public int selectTotalLectureBoardCnt() throws Exception {
		
		return ses.selectOne(ns + ".getTotalLectureBoard");
	}
	
	/**
	 * @methodName : getLectureBoardCntWithSc
	 * @author : kde
	 * @date : 2024.05.05
	 * @param : LectureSearchDTO lsDTO - 검색조건의 Type와 value
	 * @return : int
	 * @description : 검색어가 있을 경우 검색된 글의 갯수를 가져오는 메서드 - 검색조건
	 */
	@Override
	public int lectureBoardCntWithSc(LectureSearchDTO lsDTO) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", lsDTO.getSearchType());
		// lsDTO.getSearchValue() 앞 뒤로 "%"를 붙이는 이유
		// ex) 자바를 검색했을 경우 자바라는 글이 포함된 글은 나오지않아서 "%"를 붙여준다.
		params.put("searchValue", "%" + lsDTO.getSearchValue() + "%");

		return ses.selectOne(ns + ".getLectureBoardCntWithSc", params);
	}

	/**
	 * @methodName : getLectureBoardListWithSc
	 * @author : kde
	 * @date : 2024.05.05
	 * @param : LectureSearchDTO lsDTO - 검색조건의 Type와 value
	 * @return : List<LectureBoardVO>
	 * @description : 검색어가 있을 경우 검색된 글을 가져오는 메서드 - 검색조건
	 */
	@Override
	public List<LectureBoardVO> lectureBoardListWithSc(LectureSearchDTO lsDTO, PagingInfo pi) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", lsDTO.getSearchType());
		params.put("searchValue", "%" + lsDTO.getSearchValue() + "%");
		
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());

		return ses.selectList(ns + ".getLectureBoardListWithSc", params);
	}
	
	/**
	 * @methodName : lectureBoardCntFilter
	 * @author : kde
	 * @date : 2024.05.15
	 * @param : LectureSearchDTO lsDTO - 검색필터의 Type
	 * @return : int
	 * @description : 검색필터가 선택이 된 경우 글의 갯수를 가져오는 메서드 - 검색필터
	 */
	public int lectureBoardCntFilter(LectureSearchDTO lsDTO) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("filterType", lsDTO.getFilterType());

		return ses.selectOne(ns + ".lectureBoardCntFilter", params);
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
	public List<LectureBoardVO> listAllBoardByFilter(LectureSearchDTO lsDTO, PagingInfo pi)
			throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("filterType", lsDTO.getFilterType());
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".getLectureBoardListFilter", params);
	}

}
