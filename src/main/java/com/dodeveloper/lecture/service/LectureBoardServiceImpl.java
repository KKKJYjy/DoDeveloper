package com.dodeveloper.lecture.service;

import java.util.List;

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
		System.out.println("서비스단 : 페이지 글 조회!");
		
		// DAO단 호출 (selectListAllLecBoard()메서드 호출)
		List<LectureBoardVO> lecBoardList = lDao.selectListAllLecBoard();

		return lecBoardList;
	}

}
