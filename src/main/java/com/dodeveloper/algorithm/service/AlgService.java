package com.dodeveloper.algorithm.service;

import java.util.List;

import com.dodeveloper.algorithm.vodto.AlgBoardDTO;
import com.dodeveloper.algorithm.vodto.AlgClassificationDTO;
import com.dodeveloper.algorithm.vodto.AlgDetailDTO;
import com.dodeveloper.member.dto.LoginDTO;
import com.dodeveloper.report.dto.ReportDTO;

public interface AlgService {
    // 게시판 전체 조회
    List<AlgBoardDTO> getListAllBoard() throws Exception;

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

    void insertReport(ReportDTO reportDTO);

    // algDetail 항목 삭제
    boolean remBoard(int boardNo);

}
