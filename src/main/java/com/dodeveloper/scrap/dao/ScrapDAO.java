package com.dodeveloper.scrap.dao;

import java.util.List;

import com.dodeveloper.company.vodto.ScrapRevJoinVO;
import com.dodeveloper.company.vodto.ScrapVO;

public interface ScrapDAO {

	// 기업 리뷰 글 관련 스크랩
	List<ScrapRevJoinVO> selectAllScrap(String scrapId) throws Exception;

	// 어떤 유저가 해당 스크랩한 게시글들만 전체 볼 수 있는 메서드 (select)
	List<ScrapVO> selectScrap(String scrapId, int bType) throws Exception;

	// 유저가 ?번 스크랩 글을 취소하는 메서드 (delete)
	int deleteScrap(int scrapNo) throws Exception;

}