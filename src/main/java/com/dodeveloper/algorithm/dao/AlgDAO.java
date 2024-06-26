package com.dodeveloper.algorithm.dao;

import java.util.List;

import com.dodeveloper.algorithm.vodto.AlgBoardDTO;
import com.dodeveloper.algorithm.vodto.AlgBoardWithDetailVO;
import com.dodeveloper.algorithm.vodto.AlgClassificationDTO;
import com.dodeveloper.algorithm.vodto.AlgDetailDTO;
import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.member.dto.LoginDTO;
import com.dodeveloper.report.dto.ReportDTO;

public interface AlgDAO {
	
	// 알고리즘게시판 조회
	List<AlgBoardDTO> selectAlgBoard() throws Exception;
	
	List<AlgBoardDTO> selectAlgBoard(PagingInfo pagingInfo) throws Exception;

	// boardNo 에 해당하는 algDetail 테이블 조회
	List<AlgDetailDTO> selectAlgDetail(int boardNo) throws Exception;

	// writer 에 해당하는 algDetail 테이블 조회
	List<AlgDetailDTO> selectAlgDetail(String writer);
	
	// algBoard 항목 작성
	int insertAlgBoard(AlgBoardDTO algBoardDTO) throws Exception;

	// 알고리즘분류 테이블 조회
	List<AlgClassificationDTO> selectAlgClassification() throws Exception;

	int insertAlgClassification(String algClassification) throws Exception;

	int updateAlgBoard(AlgBoardDTO algBoardDTO);

	// algDetail 항목 작성
	int insertAlgDetail(AlgDetailDTO algDetailDTO);

	// algDetail 업데이트
	int updateAlgDetail(AlgDetailDTO algDetailDTO);

	// reportBoard 업데이트
	int insertReport(ReportDTO reportDTO);


	// algDetailNo 에 해당하는 AlgDetail 테이블 조회
	AlgDetailDTO selectOneAlgDetail( int algDetailNo);

	// algDetail 테이블의 isDeleted 칼럼을 Y로 업데이트
	int deleteAlgDetailDelete(int boardNo);

	// 알고리즘 게시판 최신 5개글 가져오는 메서드
	List<AlgBoardWithDetailVO> getAlgTop5() throws Exception;

	// classificationCode 에 해당하는 알고리즘 항목들을 얻어오기
	List<AlgBoardDTO> selectAlgListByClassificationCode(int val);

	// algBoard 테이블이 몇개 인지 알려주는 쿼리문 실행
	int selectTotalAlgBoardCnt();

	// reporter 가 같은 게시글에 이미 신고했는지 알려주는 쿼리문 실행
	int countReporter(ReportDTO reportDTO);

	


}
