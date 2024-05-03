package com.dodeveloper.algorithm.dao;

import java.util.List;

import com.dodeveloper.algorithm.vodto.AlgBoardDTO;
import com.dodeveloper.algorithm.vodto.AlgDetailDTO;
import com.dodeveloper.member.dto.LoginDTO;

public interface AlgDAO {
	
	// 알고리즘게시판 조회
	List<AlgBoardDTO> selectAlgBoard() throws Exception;

	// boardNo 에 해당하는 algDetail 테이블 조회
	List<AlgDetailDTO> selectAlgDetail(int boardNo) throws Exception;
}
