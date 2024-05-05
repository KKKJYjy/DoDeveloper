package com.dodeveloper.lecture.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dodeveloper.lecture.vodto.LectureBoardDTO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;

@Repository // 아래의 클래스가 DAO 객체임을 명시
public class LectureBoardDAOImpl implements LectureBoardDAO {

	@Autowired
	private SqlSession ses; // SqlSession 객체 주입

	private static String ns = "com.dodeveloper.mappers.lectureBoardMapper";

	/**
	 * @methodName : selectListAllLecBoard
	 * @author : kde
	 * @date : 2024.05.02
	 * @param :
	 * @return : List<LectureBoardVO>
	 * @description : 게시판 전체 조회에 대한 DAO 메서드
	 */
	@Override
	public List<LectureBoardVO> selectListAllLecBoard() throws Exception {

		return ses.selectList(ns + ".getAllBoard");
	}

	/**
	 * @methodName : selectBoardByBoardNo
	 * @author : kde
	 * @date : 2024.05.03
	 * @param : int lecNo - 게시글 번호
	 * @return :
	 * @description : ?번 글을 가져오는 메서드
	 */
	@Override
	public LectureBoardVO selectBoardLecNo(int lecNo) throws Exception {

		return ses.selectOne(ns + ".selectBoardLecNo", lecNo);
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
	 * @methodName : insertNewLectureBoard
	 * @author : kde
	 * @date : 2024.05.04
	 * @param : LectureBoardDTO newLecBoard - 유저가 작성한 글을 insert
	 * @return :
	 * @description : 유저가 작성한 글을 insert
	 */
	@Override
	public int insertNewLectureBoard(LectureBoardDTO newLecBoard) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lecTitle", newLecBoard.getLecTitle());
		params.put("lecReview", newLecBoard.getLecReview());
		params.put("lecWriter", newLecBoard.getLecWriter());
		params.put("lecScore", newLecBoard.getLecScore());
		params.put("lecLink", newLecBoard.getLecLink());

		System.out.println("새로 저장될 글 : " + newLecBoard.getLecNo());

		return ses.insert(ns + ".insertLectureBoard", params);
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

}
