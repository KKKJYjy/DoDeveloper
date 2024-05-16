package com.dodeveloper.algorithm.dao;

import java.util.List;

import com.dodeveloper.algorithm.vodto.AlgBoardDTO;
import com.dodeveloper.algorithm.vodto.AlgClassificationDTO;
import com.dodeveloper.algorithm.vodto.AlgDetailDTO;
import com.dodeveloper.member.dto.LoginDTO;

public interface AlgDAO {
	
	// 알고리즘게시판 조회
	List<AlgBoardDTO> selectAlgBoard() throws Exception;

	// boardNo 에 해당하는 algDetail 테이블 조회
	List<AlgDetailDTO> selectAlgDetail(int boardNo) throws Exception;

	// algBoard 항목 작성
	int insertAlgBoard(AlgBoardDTO algBoardDTO) throws Exception;

	// 알고리즘분류 테이블 조회
	List<AlgClassificationDTO> selectAlgClassification() throws Exception;

	int insertAlgClassification(String algClassification) throws Exception;

	int updateAlgBoard(AlgBoardDTO algBoardDTO);

	// algDetail 항목 작성
	int insertAlgDetail(AlgDetailDTO algDetailDTO);
}
