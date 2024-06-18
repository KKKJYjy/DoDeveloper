package com.dodeveloper.algorithm.service;

import java.util.List;
import java.util.Map;

import com.dodeveloper.algorithm.vodto.AlgBoardDTO;
import com.dodeveloper.algorithm.vodto.AlgBoardWithDetailVO;
import com.dodeveloper.algorithm.vodto.AlgClassificationDTO;
import com.dodeveloper.algorithm.vodto.AlgDetailDTO;
import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.member.dto.LoginDTO;
import com.dodeveloper.report.dto.ReportDTO;

public interface AlgService {
    
    List<AlgBoardDTO> getListAllBoard() throws Exception;
    // 게시판 전체 조회
    List<AlgBoardDTO> getListAllBoard(PagingInfo pagingInfo) throws Exception;

    // alg상세게시판 조회
    List<AlgDetailDTO> getListDetail(int boardNo) throws Exception;

    // alg상세게시판 조회 codeDetail.jsp 에 출력하기 위해 algDetailNo에 해당하는 algDetailDTO 하나만 출력
    AlgDetailDTO getAlgDetail( int algDetailNo) throws Exception;

    // algBoard 글 쓰기
    void writeAlgBoard(AlgBoardDTO algBoardDTO) throws Exception;

    // algClassification 테이블 조회
    List<AlgClassificationDTO> getAlgClassification() throws Exception;

    // algClassification 글 쓰기
    void writeAlgClassification(String algClassification) throws Exception;

    void updateAlgBoard(AlgBoardDTO algBoardDTO);


    // algDetail 글 쓰기
    void writeAlgDetail(AlgDetailDTO algDetailDTO);

    // algDetail 항목 업데이트
    void updateAlgDetail(AlgDetailDTO algDetailDTO, int algDetailNo);

    boolean insertReport(ReportDTO reportDTO);

    // algDetail 항목 삭제
    boolean remBoard(int boardNo);


	// 알고리즘 게시판 최신 5개글 가져오는 메서드
    List<AlgBoardWithDetailVO> getAlgTop5() throws Exception;

    
    // userId(writer) 가 작성한 algDetail 리스트로 가져오는 메서드
    List<AlgDetailDTO> getListDetail(String userId);

    // pageNo에 해당하는 algBoard 출력
    PagingInfo getPagingInfo(int pageNo) throws Exception;
    
    
    PagingInfo makePagingInfo(int pageNo) throws Exception;
	
	

}
