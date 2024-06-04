package com.dodeveloper.scrap.dao;

import java.util.List;

import com.dodeveloper.company.vodto.ScrapVO;

public interface ScrapDAO {

	// 어떤 유저가 본인이 스크랩한 글들을 전체 볼 수 있는 메서드 (select)
	// 강의, 스터디, 기업리뷰, 알고리즘 관련 스크랩한 게시글 확인 가능
	List<ScrapVO> selectAllScrap(String scrapId) throws Exception;

	// 어떤 유저가 해당 스크랩한 게시글들만 전체 볼 수 있는 메서드 (select)
	List<ScrapVO> selectScrap(String scrapId, int bType) throws Exception;

	// 유저가 ?번 스크랩 글을 취소하는 메서드 (delete)
	int deleteScrap(int scrapNo) throws Exception;

}