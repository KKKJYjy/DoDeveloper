package com.dodeveloper.company.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.company.vodto.CompanyInfoVO;
import com.dodeveloper.company.vodto.CompanyInfoWithRevVO;
import com.dodeveloper.company.vodto.RevCompanyBoardVO;
import com.dodeveloper.company.vodto.ScrapVO;
import com.dodeveloper.company.vodto.WrittenCompanyBoardDTO;

/**
 * @packageName : com.dodeveloper.company.dao
 * @fileName : CompanyInfoDAOImpl.java
 * @author kimso05
 * @date : 2024.05.03
 * @description : 기업 정보 전체 게시글 기능 DAO 클래스 1) 서비스단에서 보낸 파라메터를 가지고 2) 쿼리문 mapper에
 *              작성하고, 작성된 mapper를 호출 (파라메터 있다면 세팅) 3) SqlSessionTemplate객체가 가지고
 *              있는 (selectOne, selectList, insert, update, delete)를 이용해서 쿼리문 실행
 *              4) 반환된 값을 다시 서비스단으로 반환해준다.
 * 
 */
@Repository // CompanyInfoDAOImpl : DAO객체임을 명시
public class CompanyInfoDAOImpl implements CompanyInfoDAO {

	@Autowired
	private SqlSession ses; // 객체 생성

	private static String ns = "com.dodeveloper.mappers.companyMapper"; // ns가 namespace임

	@Override
	public List<CompanyInfoVO> selectEntireCompanyInfo() throws Exception {

		return ses.selectList(ns + ".getEntireCompanyInfo"); // 파라메터 없음 (쿼리문은 : ns + ".id")
	}

	@Override
	public List<RevCompanyBoardVO> selectCompanyInfoRev(int companyInfoNo) throws Exception {

		return ses.selectList(ns + ".getRevCompanyList", companyInfoNo);

	}

	/**
	 * @methodName : insertRevWrittenBoard
	 * @author : kimso05
	 * @date : 2024.05.14
	 * @return : insert가 잘 되면 1을 반환한다 row갯수(= 저장되는 리뷰 글의 갯수)
	 * @description :
	 */
	@Override
	public int insertRevWrittenBoard(WrittenCompanyBoardDTO newWrittenCompanyBoard) throws Exception {
//		System.out.println("게시글 작정 저장하려고 DAO단까지 호출 ~!!!!");
		return ses.insert(ns + ".insertRevWrittenBoard", newWrittenCompanyBoard); // 리뷰 저장 성공하면 1을 반환
	}

	@Override
	public int deleteWrittenBoard(int revNo) throws Exception {
//		System.out.println("DAO단 삭제 글 호출");

		return ses.delete(ns + ".deleteRevWrittenBoard", revNo);
	}

	@Override
	public RevCompanyBoardVO selectEditWrittenBoard(int revNo) throws Exception {
		System.out.println("DAO단 수정 게시글 조회!");

		return ses.selectOne(ns + ".getEditRevWrittenBoard", revNo);

	}

	@Override
	public int updateEditWrittenBoard(RevCompanyBoardVO newEditWrittenBoard) throws Exception {
		System.out.println("DAO단 게시글 수정함!!!!!");

		return ses.update(ns + ".updateEditWrittenBoard", newEditWrittenBoard);

	}

	/**
	 * @methodName : insertScrap
	 * @author : kimso05
	 * @date : 2024.06.01
	 * @param : int scrapBoard : 게시글 번호
	 * @param : String scrapId : 스크랩한 사람
	 * @param : int bType : 게시판 구분
	 * @return : 스크랩 저장 성공했으면 1 반환, 저장되지 않았다면 -1 반환
	 * @description : 유저가 어떤 게시판의 ?번 글을 스크랩 하는 메서드 (insert)
	 */
	@Override
	public int insertScrap(int scrapBoard, String scrapId, int bType) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("scrapBoard", scrapBoard);
		param.put("scrapId", scrapId);
		param.put("bType", bType);

		return ses.insert(ns + ".getInsertScrap", param);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 6. 10.
	 * @return : List<CompanyInfoWithRevVO>
	 * @description : 최근 5개의 기업 정보 리스트를 불러오는 메서드
	 */
	@Override
	public List<CompanyInfoWithRevVO> getCompanyTop5() throws Exception {
		return ses.selectList(ns + ".getCompanyTop5");
	}

}
