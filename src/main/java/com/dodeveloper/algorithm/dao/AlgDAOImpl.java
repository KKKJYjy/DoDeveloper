package com.dodeveloper.algorithm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.algorithm.vodto.AlgBoardDTO;
import com.dodeveloper.algorithm.vodto.AlgBoardWithDetailVO;
import com.dodeveloper.algorithm.vodto.AlgClassificationDTO;
import com.dodeveloper.algorithm.vodto.AlgDetailDTO;
import com.dodeveloper.member.dto.LoginDTO;
import com.dodeveloper.report.dto.ReportDTO;

@Repository
public class AlgDAOImpl implements AlgDAO {

	@Autowired
	private SqlSession ses;

	private static String ns = "com.dodeveloper.mappers.algorithmMapper";

	@Override
	public List<AlgBoardDTO> selectAlgBoard() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("!!!!DAO!!!!");
//		return ses.selectList(ns+".selectAlgBoard");
		return ses.selectList(ns + ".selectAlgBoard");
		// return null;
	}

	@Override
	public List<AlgDetailDTO> selectAlgDetail(int boardNo) throws Exception {
		// TODO Auto-generated method stub
		return ses.selectList(ns + ".selectAlgDetail", boardNo);
	}

	@Override
	public int insertAlgBoard(AlgBoardDTO algBoardDTO) {
		// 알고리즘 게시판 글쓰기에 입력된 값을 DB Insert문으로
		System.out.println("글쓰기(DAO)");

		return ses.insert(ns + ".insertAlgBoard", algBoardDTO);

	}

	@Override
	public List<AlgClassificationDTO> selectAlgClassification() throws Exception {
		// TODO Auto-generated method stub

		return ses.selectList(ns + ".selectAlgClassification");

	}

	@Override
	public int insertAlgClassification(String algClassification) throws Exception {
		// TODO Auto-generated method stub
		return ses.insert(ns + ".insertAlgClassification", algClassification);
	}

	@Override
	public int updateAlgBoard(AlgBoardDTO algBoardDTO) {
		// TODO Auto-generated method stub
		return ses.update(ns + ".updateAlgBoard", algBoardDTO);
	}

	@Override
	public int insertAlgDetail(AlgDetailDTO algDetailDTO) {
		// TODO Auto-generated method stub

		return ses.insert(ns + ".insertAlgDetail", algDetailDTO);

	}

	@Override
	public int updateAlgDetail(AlgDetailDTO algDetailDTO) {
		// TODO Auto-generated method stub

		return ses.update(ns + ".updateAlgDetail", algDetailDTO);

	}

	@Override
	public int insertReport(ReportDTO reportDTO) {
		// TODO Auto-generated method stub

		return ses.insert(ns + ".insertReport", reportDTO);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 6. 11.
	 * @return : List<AlgBoardWithDetailVO>
	 * @description : 알고리즘 게시판 최신 5개글 가져온다
	 */
	@Override
	public List<AlgBoardWithDetailVO> getAlgTop5() throws Exception {
		return ses.selectList(ns + ".getAlgTop5");
	}

	@Override
	public AlgDetailDTO selectOneAlgDetail( int algDetailNo) {
	 // algDetailNo 에 해당하는 AlgDetail 테이블 조회
	    Map<String, Integer> map = new HashMap<String, Integer>();
	    
	   
	    map.put("algDetailNo", algDetailNo);
	    
	    return ses.selectOne(ns+".selectAlgCodeDetail", algDetailNo);
	    
	}

	@Override
	public int updateAlgDetailDelete(int algDetailNo) {
	    // TODO Auto-generated method stub
	    System.out.println("DAO 에서"+algDetailNo+"번글을");
	    return ses.update(ns+".", algDetailNo);
	}

}
