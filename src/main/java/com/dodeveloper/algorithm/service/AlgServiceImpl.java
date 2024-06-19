package com.dodeveloper.algorithm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.algorithm.dao.AlgDAO;
import com.dodeveloper.algorithm.vodto.AlgBoardDTO;
import com.dodeveloper.algorithm.vodto.AlgBoardWithDetailVO;
import com.dodeveloper.algorithm.vodto.AlgClassificationDTO;
import com.dodeveloper.algorithm.vodto.AlgDetailDTO;
import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.member.dto.LoginDTO;
import com.dodeveloper.report.dto.ReportDTO;

@Service
public class AlgServiceImpl implements AlgService {

	@Autowired
	private AlgDAO aDao;
	
	@Autowired
	private PagingInfo pi;
	
	@Override
	public List<AlgBoardDTO> getListAllBoard() throws Exception {
		// algBoard 리스트 받아옴
		//System.out.println("게시판조회(서브스)");
		List<AlgBoardDTO> algBoardList = null;
		
		algBoardList = aDao.selectAlgBoard();
		
		//System.out.println(algBoardList.toString()+"(service)");
		
		return algBoardList;
	}
	
	
	@Override
	public List<AlgBoardDTO> getListAllBoard(PagingInfo pagingInfo) throws Exception {
		// algBoard 리스트 받아옴
		//System.out.println("게시판조회(서브스)");
		List<AlgBoardDTO> algBoardList = null;
		
		algBoardList = aDao.selectAlgBoard(pagingInfo);
		
		//System.out.println(algBoardList.toString()+"(service)");
		
		return algBoardList;
	}


	@Override
	public List<AlgDetailDTO> getListDetail(int boardNo) {
		// boardNo 에 해당하는 algDetail 리스트 받아오기
		List<AlgDetailDTO> algDetailList = null;
		//System.out.println(boardNo+"번의 알고리즘 조회");
//		LoginDTO loginDTO = null;
//		System.out.println(loginDTO.getUserId());
		
		try {
			algDetailList = aDao.selectAlgDetail(boardNo);
			
		} catch (Exception e) {
			// 
			e.printStackTrace();
		}
		//System.out.println(algDetailList);
		
		
		return algDetailList;
	}

	@Override
	public List<AlgDetailDTO> getListDetail(String writer) {
	    // writer 에 해당하는 algDetail 리스트 받아오기
	    return aDao.selectAlgDetail(writer);
	}

	@Override
	public void writeAlgBoard(AlgBoardDTO algBoardDTO) throws Exception {
		//System.out.println("글쓰기(서비스)"); 
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
	public boolean insertReport(ReportDTO reportDTO) {
	    // 게시글 신고 시 자기 자신이 작성한 게시글 이면서 이미 작성한 신그글이면 false 반환 아닐 경우 true 반환
	    
	    
	    boolean result = false;
	    
	    //System.out.println(reportDTO.toString());
	    //System.out.println("신고글 인서트");
	    //System.out.println(reportDTO.getBtypeNo());
	    String reporter = (String)reportDTO.getReporter();
	    String writer = (String)reportDTO.getWriter();
	   
	    if(reporter.equals(writer)) {
		// 신고자와 게시글 작성자가 같을 때 false 반환
		//System.out.println("reporter = writer");
		//System.out.println("자기 글에 신고 불가");
	    }
	    else {
		
		if(aDao.countReporter(reportDTO) >= 1) {
		    // 신고자와 게시글 작성자가 같지 않고 신고자가 이미 신고한 게시글에 신고함
		    //System.out.println("이미 신고함");
		} else {
		    
		    aDao.insertReport(reportDTO);
		    result = true;
		}
		
	    }
	    
	    return result;
	   
	}


	@Override
	public AlgDetailDTO getAlgDetail( int algDetailNo) throws Exception {
	    //
	    AlgDetailDTO algDetail = null;
	    
	   //System.out.println("service codeDetail");
	    
	    algDetail = aDao.selectOneAlgDetail( algDetailNo);
	    
	    
	    return algDetail;
	}


	@Override
	public boolean remBoard(int algDetailNo) {
	    boolean result = false;
	    //System.out.println("서비스 에서 "+algDetailNo+"번 글을 삭제하자");
		if (aDao.deleteAlgDetailDelete(algDetailNo) == 1) {
			result = true;
			System.out.println(result);
		}
		return result;
	}


	/**
		* @author : yeonju
		* @date : 2024. 6. 11.
		* @return : List<AlgBoardWithDetailVO>
		* @description : 알고리즘 게시판 최신 5개글 가져온다
	 */
	@Override
	public List<AlgBoardWithDetailVO> getAlgTop5() throws Exception {
		return aDao.getAlgTop5();
	}


	@Override
	public PagingInfo getPagingInfo(int pageNo) throws Exception {
	    // TODO Auto-generated method stub
	    //System.out.println("서비스단 : "+pageNo+"번게시물 조회");
	    
	    
	    PagingInfo page = makePagingInfo(pageNo);
	    
	    
	    
	    return page;
	    
	    
	    
	    
	}
	
	@Override
	public PagingInfo makePagingInfo(int pageNo) throws Exception {
		
	    // pageNo값 세팅
		this.pi.setPageNo(pageNo);
		this.pi.setViewPostCntPerPage(5);
		this.pi.setPageCntPerBlock(5);

		
		
		// 게시물의 데이터 갯수 구하기
		int totalBoardCnt = aDao.selectTotalAlgBoardCnt();
		System.out.println(totalBoardCnt+"개의 게시물이 있음");
		this.pi.setTotalPostCnt(totalBoardCnt);
		
		// 총 페이지 수 구해 저장하기
		this.pi.setTotalPageCnt();
		
		// 보여주기 시작할 글의 rowIndexx 번호 구해서 저장하기
		this.pi.setStartRowIndex();
		
		//===========================
		
		// 전체 페이지 블럭 갯수 구해 저장하기
		this.pi.setTotalPageBlockCnt();
		// 현재페이자가 속한 페이지 블럭 번호 구해 저장하기
		this.pi.setPageBlockOfCurrentPage();
		// 현재 페이징 블럭 시작 페이지 번호 구해 저장하기
		this.pi.setStartNumOfCurrentPagingBlock();
		// 현재 페이징 블럭 ed\n 페이지 번호 구해 저장하기
		this.pi.setEndNumOfCurrentPagingBlock();
		
		//System.out.println(pi);
		
		return pi;
	}



}
