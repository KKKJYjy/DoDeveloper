package com.dodeveloper.lecture.service;

import java.util.List;

import com.dodeveloper.lecture.vodto.LectureBoardVO;

public interface LectureBoardService {
	
	// �Խ����� lecNo�� �������� ���� ��ȸ�ϴ� �޼���
	List<LectureBoardVO> getListAllBoard() throws Exception;
}
