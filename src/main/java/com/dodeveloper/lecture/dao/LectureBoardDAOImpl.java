package com.dodeveloper.lecture.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.etc.PagingInfo;
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
	 * @param : int lecNo - 게시글 번호
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
	 * @param : int lecNo - 게시글 번호
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
	 * @param : int lecNo - 게시글 번호
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
	 * @param : LectureBoardDTO modifyBoard - 유저가 수정할 글
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
	 * @param : PagingInfo pi - 전체 게시글 페이징
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

		return ses.selectOne(ns + ".getTotalLectureBoard", params);
	}

	/**
	 * @methodName : listAllBoardByFilter
	 * @author : kde
	 * @date : 2024.05.06
	 * @param : String filterType - 필터 타입(최신순 / 인기순 / 조회순)
	 * @param : PagingInfo pi - 전체 게시글 페이징
	 * @return : List<LectureBoardVO>
	 * @description : 검색 필터(최신순 / 인기순 / 조회순)을 선택했을 때 글을 가져오는 메서드 - 검색 필터
	 */
	@Override
	public List<LectureBoardVO> listAllBoardByFilter(LectureSearchDTO lsDTO, PagingInfo pi) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("filterType", lsDTO.getFilterType());
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());

		return ses.selectList(ns + ".getLectureBoardListFilter", params);
	}

	/**
	 * @methodName : lectureBoardSearchAndFilterCnt
	 * @author : kde
	 * @date : 2024.05.25
	 * @param : LectureSearchDTO lsDTO - 검색 조건 / 검색어 / 검색 필터 포함된 DTO
	 * @return : int
	 * @description : 검색 필터 + 검색 조건 + 페이징까지 글의 갯수를 가져오는 메서드
	 */
	@Override
	public int lectureBoardSearchAndFilterCnt(LectureSearchDTO lsDTO) throws Exception {

		return ses.selectOne(ns + ".getLectureBoardSearchAndFilterCnt", lsDTO);
	}

	/**
	 * @methodName : lectureBoardSearchAndFilter
	 * @author : kde
	 * @date : 2024.05.25
	 * @param : LectureSearchDTO lsDTO - 검색 조건 / 검색어 / 검색 필터 포함된 DTO
	 * @param : PagingInfo pi - 전체 게시글 페이징
	 * @return :
	 * @description : 검색 필터 + 검색 조건 + 페이징까지 글을 가져오는 메서드
	 */
	@Override
	public List<LectureBoardVO> lectureBoardSearchAndFilter(LectureSearchDTO lsDTO, PagingInfo pi) throws Exception {
		// System.out.println(lsDTO + " 검색필터랑 검색조건 둘 다 되는 중 " + pi + " 페이징까지");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", lsDTO.getSearchType());
		params.put("searchValue", "%" + lsDTO.getSearchValue() + "%");
		params.put("filterType", lsDTO.getFilterType());

		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());

		return ses.selectList(ns + ".getLectureBoardSearchAndFilter", params);
	}

	/**
	 * @methodName : selectLikeBoard
	 * @author : kde
	 * @date : 2024.05.21
	 * @param : int lecNo - 게시글 번호
	 * @param : String user - 좋아요 눌렀는지 안눌렀는지 확인할 유저
	 * @return : int
	 * @description : 게시글에 유저가 좋아요를 눌렀는지 안눌렀는지 확인하는 메서드 selectLikeBoard 쿼리문에서
	 *              count(*)를 사용하여 갯수를 센 후에 좋아요를 눌렀을 경우 1반환 좋아요를 안눌렀을 경우 0반환을 하기때문에
	 *              int를 사용함. 유저가 하트를 눌렀을 때 좋아요 수가 1증가 -> ♥ 유저가 하트를 한번 더 눌렀을 경우 1감소
	 *              -> ♡
	 */
	@Override
	public int selectLikeBoard(int lecNo, String user) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lecNo", lecNo);
		params.put("user", user);

		return ses.selectOne(ns + ".selectLikeBoard", params);
	}

	/**
	 * @methodName : likeBoard
	 * @author : kde
	 * @date : 2024.05.18
	 * @param : int lecNo - 게시글 번호
	 * @param : String user - 좋아요를 누르는 유저
	 * @param : String lecLikeTitle - 좋아요를 누른 게시글 제목
	 * @return : int
	 * @description : 로그인 한 유저인 경우만 좋아요를 누를 수 있다. 유저가 하트를 눌렀을 때 좋아요 수가 1증가 -> ♥
	 */
	@Override
	public int insertLikeBoard(int lecNo, String user, String lecLikeTitle) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lecNo", lecNo);
		params.put("user", user);
		params.put("lecLikeTitle", lecLikeTitle);
		

		return ses.insert(ns + ".insertLikeBoard", params);
	}

	/**
	 * @methodName : updateLikeBoard
	 * @author : kde
	 * @date : 2024.05.20
	 * @param : int lecNo - 게시글 번호
	 * @return : int
	 * @description : 게시글에 좋아요 버튼 눌렀을 경우 갯수 1개 update (전체 게시글에 보여주기)
	 */
	@Override
	public int updateLikeCount(int lecNo) throws Exception {

		return ses.update(ns + ".updateLikeCount", lecNo);
	}

	/**
	 * @methodName : deleteLikeBoard
	 * @author : kde
	 * @date : 2024.05.21
	 * @param : int lecNo - 게시글 번호
	 * @param : String user - 좋아요를 눌렀다가 취소하려는 회원
	 * @param : String lecLikeTitle - 좋아요 취소한 게시글 제목
	 * @return : int
	 * @description : 게시글에 좋아요 한 번 더 눌렀을 경우 취소처리하는 메서드 유저가 하트를 한번 더 눌렀을 경우 1감소 -> ♡
	 */
	@Override
	public int deleteLikeBoard(int lecNo, String user, String lecLikeTitle) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lecNo", lecNo);
		params.put("user", user);
		params.put("lecLikeTitle", lecLikeTitle);
		

		return ses.delete(ns + ".deleteLikeBoard", params);
	}

	/**
	 * @methodName : updateLikeBoard
	 * @author : kde
	 * @date : 2024.05.20
	 * @param : int lecNo - 게시글 번호
	 * @return : int
	 * @description : 유저가 게시글 하트를 한번 더 눌렀을 경우 좋아요 횟수 down하는 update (전체 게시글에 보여주기)
	 */
	@Override
	public int updateLikeDownCount(int lecNo) throws Exception {

		return ses.update(ns + ".updateLikeDownCount", lecNo);
	}

	/**
	 * @methodName : selectAllLectureScrap
	 * @author : kde
	 * @date : 2024.06.08
	 * @param : int lecNo - 스크랩을 눌렀는지 확인 할 게시글 번호
	 * @param : String user - 스크랩을 눌렀는지 확인 할 유저
	 * @return : List<Object>
	 * @description : 유저가 스크랩을 누른적이 있는지 조회 (스크랩 눌렀을 경우 1반환)
	 */
	@Override
	public int selectAllLectureScrap(int lecNo, String user) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lecNo", lecNo);
		params.put("user", user);
		
		return ses.selectOne(ns + ".selectAllLectureScrap", params);
	}

	/**
	 * @methodName : insertScrap
	 * @author : kde
	 * @date : 2024.06.09
	 * @param : int lecNo - 게시글 번호
	 * @param : String user - 스크랩을 누른 유저
	 * @return : int
	 * @description : 게시글에 스크랩 처리하는 메서드
	 */
	@Override
	public int insertScrap(int lecNo, String user) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lecNo", lecNo);
		params.put("user", user);
		
		return ses.insert(ns + ".insertScrap", params);
	}

	/**
	 * @methodName : updateUpScrap
	 * @author : kde
	 * @date : 2024.06.09
	 * @param : int lecNo - 게시글 번호
	 * @return : int
	 * @description : 게시글에 스크랩 버튼을 눌렀을 경우 갯수 1개 update (전체 게시글에 보여주기)
	 */
	@Override
	public int updateUpScrap(int lecNo) throws Exception {
		
		return ses.update(ns + ".updateUpScrap", lecNo);
	}

	/**
	 * @methodName : deleteScrap
	 * @author : kde
	 * @date : 2024.06.09
	 * @param : int lecNo - 게시글 번호
	 * @param : String user - 스크랩을 취소한 유저
	 * @return : int
	 * @description : 게시글에 스크랩 한 번 더 눌렀을 경우 취소처리하는 메서드
	 */
	@Override
	public int deleteScrap(int lecNo, String user) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lecNo", lecNo);
		params.put("user", user);
		
		return ses.delete(ns + ".deleteScrap", params);
	}

	/**
	 * @methodName : updateDownScrap
	 * @author : kde
	 * @date : 2024.06.09
	 * @param : int lecNo - 게시글 번호
	 * @return : int
	 * @description : 게시글에 스크랩 한 번 더 눌렀을 경우 갯수 1개 횟수 down하는 update (전체 게시글에 보여주기)
	 */
	@Override
	public int updateDownScrap(int lecNo) throws Exception {
		
		return ses.update(ns + ".updateDownScrap", lecNo);
  }

	/**
	 * @author : yeonju
	 * @date : 2024. 6. 10.
	 * @return : List<LectureBoardVO>
	 * @description : 최신 5개 강의 게시글을 얻어오는 메서드
	 */
	@Override
	public List<LectureBoardVO> getLectureTop5() throws Exception {
		return ses.selectList(ns + ".getLectureTo5");

	}

}
