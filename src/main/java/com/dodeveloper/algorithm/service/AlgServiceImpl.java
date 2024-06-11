package com.dodeveloper.algorithm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.algorithm.dao.AlgDAO;
import com.dodeveloper.algorithm.vodto.AlgBoardDTO;
import com.dodeveloper.algorithm.vodto.AlgClassificationDTO;
import com.dodeveloper.algorithm.vodto.AlgDetailDTO;
import com.dodeveloper.member.dto.LoginDTO;
import com.dodeveloper.report.dto.ReportDTO;

@Service
public class AlgServiceImpl implements AlgService {

	@Autowired
	private AlgDAO aDao;
	
	
	@Override
	public List<AlgBoardDTO> getListAllBoard() throws Exception {
		// algBoard 리스트 받아옴
		System.out.println("게시판조회(서브스)");
		List<AlgBoardDTO> algBoardList = null;
		
		algBoardList = aDao.selectAlgBoard();
		
		System.out.println(algBoardList.toString()+"(service)");
		
		return algBoardList;
	}


	@Override
	public List<AlgDetailDTO> getListDetail(int boardNo) {
		// boardNo 에 해당하는 algDetail 리스트 받아오기
		List<AlgDetailDTO> algDetailList = null;
		System.out.println(boardNo+"번의 알고리즘 조회");
//		LoginDTO loginDTO = null;
//		System.out.println(loginDTO.getUserId());
		
		try {
			algDetailList = aDao.selectAlgDetail(boardNo);
			
		} catch (Exception e) {
			// 
			e.printStackTrace();
		}
		System.out.println(algDetailList);
		
		
		return algDetailList;
	}


	@Override
	public void writeAlgBoard(AlgBoardDTO algBoardDTO) throws Exception {
		System.out.println("글쓰기(서비스)"); 
		int insert = aDao.insertAlgBoard(algBoardDTO);
	}


	@Override
	public List<AlgClassificationDTO> getAlgClassification() throws Exception {
		// algClassification 테이블 조회
		List<AlgClassificationDTO> algClass = null;
		//System.out.println("classifiaction서비스");
		algClass = aDao.selectAlgClassification();
		
		
		return algClass;
	}


	@Override
	public void writeAlgClassification(String algClassification) throws Exception {
		// algClassification 테이블 글쓰기
		aDao.insertAlgClassification(algClassification);
		
	}


	@Override
	public void updateAlgBoard(AlgBoardDTO algBoardDTO) {
		// algBoard 수정
		
		aDao.updateAlgBoard(algBoardDTO);
		
	}


	@Override
	public void writeAlgDetail(AlgDetailDTO algDetailDTO) {
		// algDetail 글 작성
		
		aDao.insertAlgDetail(algDetailDTO);
		
	}


	@Override
	public void updateAlgDetail(AlgDetailDTO algDetailDTO, int algDetailNo) {
		// algDetail 항목 업데이트
		
		algDetailDTO.setAlgDetailNo(algDetailNo);
		
		//LoginDTO loginDTO = null;
		//System.out.println(loginDTO.getUserId());
		
		aDao.updateAlgDetail(algDetailDTO);
		
	}


	@Override
	public void insertReport(ReportDTO reportDTO) {
	    // TODO Auto-generated method stub
	    System.out.println(reportDTO.toString());
	    System.out.println("신고글 인서트");
	    
	    aDao.insertReport(reportDTO);
	}


	/**
		* @author : yeonju
		* @date : 2024. 6. 11.
		* @return : List<AlgBoardDTO>
		* @description : 알고리즘 게시판 최신 5개글 가져온다
	 */
	@Override
	public List<AlgBoardDTO> getAlgTop5() throws Exception {
		return aDao.getAlgTop5();
	}

}
