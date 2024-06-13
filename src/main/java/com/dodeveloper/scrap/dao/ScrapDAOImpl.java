package com.dodeveloper.scrap.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.company.vodto.ScrapRevJoinVO;
import com.dodeveloper.company.vodto.ScrapVO;

@Repository
public class ScrapDAOImpl implements ScrapDAO {
	
	@Autowired
	private SqlSession ses;
	
	private static String ns = "com.dodeveloper.mappers.scrapMapper";
	
	
	/**
	 * @methodName : selectAllScrap
	 * @author : kimso05
	 * @date : 2024.06.01
	 * @param : String scrapId : 스크랩한 사람
	 * @return : List<ScrapRevJoinVO> : 기업리뷰글 관련 스크랩
	 */
	@Override
	public List<ScrapRevJoinVO> selectAllScrap(String scrapId) throws Exception {
		System.out.println(scrapId + "DAO단 ScrapRevJoin 스크랩");
		List<ScrapRevJoinVO>lst = ses.selectList(ns + ".selectAllScrap", scrapId);

		return ses.selectList(ns + ".selectAllScrap", scrapId);
	}
	
	
	/**
	 * @methodName : selectScrap
	 * @author : kimso05
	 * @date : 2024.06.01
	 * @param : String scrapId : 스크랩한 사람
	 * @param : int bType : 게시판 구분  
	 * @return : List<ScrapVO> : bType(게시판 구분)으로 스크랩한 전체 목록 조회 
	 * @description : 
	 * 어떤 유저가 해당 스크랩한 게시글들만 전체 볼 수 있는 쿼리문 (select)  
	 */
	@Override
	public List<ScrapVO> selectScrap(String scrapId, int bType) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("scrapId", scrapId);
		param.put("bType", bType);
		
		return ses.selectList(ns + ".selectScrap", param);
		
	}

	
	/**
	 * @methodName : deleteScrap
	 * @author : kimso05
	 * @date : 2024.06.01
	 * @param : int scrapNo : 스크랩 번호
	 * @return : 삭제가 잘 됐으면 1을 반환, 삭제가 잘 안됐다면 -1 반환 
	 * @description : 유저가 ?번 스크랩 글을 취소하는 메서드  
	 */
	@Override
	public int deleteScrap(int scrapNo) throws Exception {
		
		return ses.delete(ns + ".deleteScrap", scrapNo);
	}

}
