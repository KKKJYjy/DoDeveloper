package com.dodeveloper.lecture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.lecture.dao.LectureBoardDAO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;

@Service // 아래의 클래스가 서비스 객체임을 명시하는 것
public class LectureBoardServiceImpl implements LectureBoardService {

	@Autowired
	private LectureBoardDAO lDao; // 스프링 컨테이너에 있는 LectureDAO 객체를 찾아 주입

	/**
	 * @methodName : getListAllBoard
	 * @author : kde
	 * @date : 2024.05.02
	 * @param : 
	 * @return : List<LectureBoardVO>
	 * @description : 게시판 전체 조회에 대한 서비스 메서드
	 */
	@Override
	public List<LectureBoardVO> getListAllBoard() throws Exception {
		System.out.println("서비스단 : 페이지 전체 게시글 조회!");
		
		// DAO단 호출 (selectListAllLecBoard()메서드 호출)
		List<LectureBoardVO> lecBoardList = lDao.selectListAllLecBoard();

		return lecBoardList;
	}

	/**
	 * @methodName : getBoardByBoardNo
	 * @author : kde
	 * @date : 2024.05.03
	 * @param : int boardNo - 조회할 글 번호
	 * @param : String user - 조회하는 유저 (로그인 하지 않았을 경우 session id)
	 * @return : Map<String, Object>
	 * @description : 
	 * 1) user가 조회하는 글을 하루 이내에 읽은 적이 있는지 없는지 검사
	 * 2) 하루 이내에 읽은 적이 없다 -> 조회수 증가(update), 조회이력 - 기록을 남긴다(insert), 글 가져옴(유저가 글을 조회하도록)
	 * 3) 하루 이내에 읽은 적이 있다 -> 게시글만 가져옴(유저가 글을 조회하도록)
	 */
	@Override
	public Map<String, Object> getBoardByBoardNo(int lecNo, String user) throws Exception {
		System.out.println(lDao.selectDiff(user, lecNo));
		
		// 조회된 글 가져오기
		LectureBoardVO lecBoard = lDao.selectBoardLecNo(lecNo);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("lecBoard", lecBoard); // 조회된 글 바인딩
		
//		
//		if (lDao.selectDiff(user, lecNo) == -1) {
//			// 하루 이내에 읽은 적이 없을 경우
//		}
		
		return result;
	}

}