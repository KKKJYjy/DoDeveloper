package com.dodeveloper.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodeveloper.company.dao.CompanyInfoDAO;
import com.dodeveloper.company.vodto.CompanyInfoVO;
import com.dodeveloper.company.vodto.RevCompanyBoardVO;
import com.dodeveloper.company.vodto.ScrapVO;
import com.dodeveloper.company.vodto.WrittenCompanyBoardDTO;
import com.dodeveloper.scrap.dao.ScrapDAO;

/**
 * @packageName : com.dodeveloper.company.service
 * @fileName : CompanyInfoServiceImpl.java
 * @author kimso05
 * @date : 2024.05.03
 * @description : 게시판 서비스단(비즈니스 로직에 의해 트랜잭션 처리를 하며, DAO단을 호출하고, DAO에서 넘겨받는 데이터를
 *              Controller단으로 반환) 서비스단에서 해야 할 일 1) 기업 정보 전체 보기 -> 해당 기업 클릭하면 리뷰
 *              볼 수 있도록 한다 -> 리뷰 작성 2) 비즈니스 로직 처리 하려면 -> 트랜잭션 관리 3) dao단 호출 4)
 *              dao단에서 넘겨받은 결과를 다시 Controller단으로 반환
 */
@Service // 아래의 클래스가 서비스 객체임을 명시해주는 어노테이션
public class CompanyInfoServiceImpl implements CompanyInfoService {

	@Autowired // root-context.xml 객체의 '타입'에 해당하는 bean을 찾아 의존 주입(CompanyInfoDAO)하는 어노테이션
	private CompanyInfoDAO ciDao;

	@Autowired
	private ScrapDAO sDao;  // 스크랩DAO단 객체 생성
	
	/**
	 * @methodName : getEntireCompanyInfo
	 * @author : kimso05
	 * @date : 2024.05.03
	 * @return : List<CompanyInfoVO> : 기업 정보 전체 게시글 조회
	 * @throws Exception
	 * @description : 기업 정보 전체 조회에 대한 서비스 메서드
	 */
	@Override
	public List<CompanyInfoVO> getEntireCompanyInfo() throws Exception {
		System.out.println("서비스단 : 기업정보 전체 조회!");

		// DAO단 호출 (selectEntireCompanyInfo())
		// static 하지 않으니까 객체명.메서드 로 호출해야함
		// 스프링에서의 서비스단에는 request, response객체가 없기 때문에 예외처리 해줘야함
		List<CompanyInfoVO> ciList = ciDao.selectEntireCompanyInfo();

		return ciList;
	}

	/**
	 * @methodName : getCompanyInfoRev
	 * @author : kimso05
	 * @date : 2024.05.06
	 * @return : List<revCompanyBoardVO> : 기업 리뷰 게시글
	 * @throws Exception
	 * @description : 클릭한 기업 리뷰 정보 조회에 대한 서비스 메서드
	 */
	@Override
	public List<RevCompanyBoardVO> getCompanyInfoRev(int companyInfoNo) throws Exception {
		System.out.println("서비스단 : 클릭한 기업 리뷰 게시글!!!!!!");

		// DAO단 호출 (selectCompanyInfoRev())
		List<RevCompanyBoardVO> revList = ciDao.selectCompanyInfoRev(companyInfoNo);

		return revList;
	}

	/**
	 * @methodName : writeCompanyBoardService
	 * @author : kimso05
	 * @date : 2024.05.09
	 * @param : WrittenCompanyBoardDTO newWrittenCompanyBoard - 저장될 기업 리뷰 게시글
	 * @return : 리뷰 저장 성공하면 1을 반환 , 실패하면 -1 반환
	 * @description : newWrittenCompanyBoard가 DB에 저장(insert)될 수 있도록 DAO단 호출
	 */
	@Override
	public int writeCompanyBoardService(WrittenCompanyBoardDTO newWrittenCompanyBoard) throws Exception {
		System.out.println("작성 호출 확인!!!!!!!");
		int resultWriteBoard = ciDao.insertRevWrittenBoard(newWrittenCompanyBoard);

		return resultWriteBoard; 
	}

	/**
	 * @methodName : deleteWrittenBoard
	 * @author : kimso05
	 * @date : 2024.05.17
	 * @param : int revNo : 유저가 클릭한 해당 기업 리뷰 게시글번호 삭제
	 * @return : 게시글 삭제 성공 1 반환, 실패했다면 -1 반환
	 * @description : revNo가 DB에 삭제(delete) 될 수 있도록 DAO단 호출
	 */
	@Override
	public int deleteWrittenBoard(int revNo) throws Exception {
		System.out.println("서비스단 : 게시글 삭제");
		int deleteRevBoard = ciDao.deleteWrittenBoard(revNo);

		return deleteRevBoard;

	}

	/**
	 * @methodName : editWrittenBoard
	 * @author : kimso05
	 * @date : 2024.06.01
	 * @param : int revNo : 리뷰글 번호 
	 * @return : RevCompanyBoardVO : 수정할 게시글 
	 * @description : 수정 버튼 누르면 수정할 페이지로 이동하는 메서드
	 */
	@Override
	public RevCompanyBoardVO editWrittenBoard(int revNo) throws Exception {
		System.out.println("서비스단 : 수정할 게시글");

		return ciDao.selectEditWrittenBoard(revNo);

	}

	
	/**
	 * @methodName : revEditWrittenBoard
	 * @author : kimso05
	 * @date : 2024.06.01
	 * @param : RevCompanyBoardVO newEditWrittenBoard : 수정 글 
	 * @return : revEditWrittenBoard : 수정 글 성공 하면 이동할 페이지 
	 * @description :  수정 글 완료 하는 메서드
	 */
	@Override
	public int revEditWrittenBoard(RevCompanyBoardVO newEditWrittenBoard) throws Exception {
		System.out.println("서비스단 : 수정 글 완료");
		int updateRevBoard = ciDao.updateEditWrittenBoard(newEditWrittenBoard);

		return updateRevBoard;
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
		
		return ciDao.insertScrap(scrapBoard, scrapId, bType);
	}
	

	/**
	 * @methodName : selectAllScrap
	 * @author : kimso05
	 * @date : 2024.06.01
	 * @param : String scrapId : 스크랩한 사람
	 * @return : List<ScrapVO> : bType(게시판 구분) 없이 스크랩한 글들 전체 조회   
	 * @description : 어떤 유저가 본인이 스크랩한 글들을 전체 볼 수 있는 메서드 (select)
	 * 강의, 스터디, 기업리뷰, 알고리즘 관련 스크랩한 게시글 모두 확인 가능
	 */
	@Override
	public List<ScrapVO> selectAllScrap(String scrapId) throws Exception {
		System.out.println(scrapId + "!!!!!!!!");
		for (ScrapVO s : sDao.selectAllScrap(scrapId)) {
			System.out.println(s.toString());
		}
		return sDao.selectAllScrap(scrapId);
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
		
		return sDao.selectScrap(scrapId, bType);
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
		
		return sDao.deleteScrap(scrapNo);
	}	

}
