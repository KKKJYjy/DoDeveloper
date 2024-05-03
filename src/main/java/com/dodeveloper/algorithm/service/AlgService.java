package com.dodeveloper.algorithm.service;

import java.util.List;

import com.dodeveloper.algorithm.vodto.AlgBoardDTO;
import com.dodeveloper.algorithm.vodto.AlgDetailDTO;
import com.dodeveloper.member.dto.LoginDTO;

public interface AlgService {
	// 게시판 전체 조회
	List<AlgBoardDTO> getListAllBoard() throws Exception;
	
	// alg상세게시판 조회
	List<AlgDetailDTO> getListDetail(int boardNo) throws Exception;
}
