package com.dodeveloper.algorithm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.algorithm.dao.AlgDAO;
import com.dodeveloper.algorithm.vodto.AlgBoardDTO;
import com.dodeveloper.member.dto.LoginDTO;

@Service
public class AlgServiceImpl implements AlgService {

	@Autowired
	private AlgDAO aDao;
	
	
	@Override
	public List<AlgBoardDTO> getListAllBoard() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("게시판조회(서브스)");
		List<AlgBoardDTO> algBoardList = null;
		
		algBoardList = aDao.selectAlgBoard();
		
		System.out.println(algBoardList.toString()+"(service)");
		
		return algBoardList;
	}

}
