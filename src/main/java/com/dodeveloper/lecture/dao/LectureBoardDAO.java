package com.dodeveloper.lecture.dao;

import java.util.List;

import com.dodeveloper.lecture.vodto.LectureBoardVO;

public interface LectureBoardDAO {
	
	// �Խ����� ���� ��ȸ�ϴ� �޼���
	List<LectureBoardVO> selectListAllLecBoard() throws Exception;
}
