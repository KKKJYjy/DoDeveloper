package com.dodeveloper.lecture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.lecture.dao.LectureBoardDAO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;

@Service // �Ʒ��� Ŭ������ ���� ��ü���� ���
public class LectureBoardServiceImpl implements LectureBoardService {

	@Autowired
	private LectureBoardDAO lDao; // ������ �����̳ʿ� �ִ� LectureDAO ��ü�� ã�� ����

	/**
	 * @methodName : getListAllBoard
	 * @author : kde
	 * @date : 2024.05.02
	 * @param : 
	 * @return : List<LectureBoardVO>
	 * @description : �Խ��� ��ü ��ȸ�� ���� ���� �޼���
	 */
	@Override
	public List<LectureBoardVO> getListAllBoard() throws Exception {
		System.out.println("���񽺴� ��ü �Խñ� ȣ��!");
		
		// DAO�� ȣ�� (selectListAllLecBoard()�޼��� ȣ��)
		List<LectureBoardVO> lecBoardList = lDao.selectListAllLecBoard();

		return lecBoardList;
	}

}
