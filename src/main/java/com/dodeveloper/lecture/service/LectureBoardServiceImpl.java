package com.dodeveloper.lecture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.lecture.dao.LectureBoardDAO;
import com.dodeveloper.lecture.vodto.LectureBoardDTO;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.lecture.vodto.LectureSearchDTO;
import com.dodeveloper.scrap.dao.ScrapDAO;

@Service // 아래의 클래스가 서비스 객체임을 명시하는 것
public class LectureBoardServiceImpl implements LectureBoardService {

	@Autowired
	private LectureBoardDAO lDao; // 스프링 컨테이너에 있는 LectureDAO 객체를 찾아 주입
	
	@Autowired
	private ScrapDAO sDao; // 스프링 컨테이너에 있는 ScrapDAO 객체를 찾아 주입

	@Autowired
	private PagingInfo pi; // 스프링 컨테이너에 있는 PagingInfo 객체를 찾아 주입

	/**
	 * @methodName : getListAllBoard
	 * @author : kde
	 * @date : 2024.05.02
	 * @param : int lecNo - 게시글 번호(처음에 사용하고 페이지 번호로 수정)
	 * @param : int pageNo - 페이지 번호
	 * @param : LectureSearchDTO lsDTO - 검색할 때 가져올 Type, Value(검색조건)
	 * @return : List<LectureBoardVO> -> Map<String, Object> 으로 수정
	 * @description : 1) 게시판 전체 조회에 대한 서비스 메서드
	 * 2) 검색 조건을 선택하고 검색어를 입력했을 때 글을 가져옴 - 검색 조건
	 * 3) 검색 필터(최신순 / 인기순 / 조회순)을 선택했을 때 글을 가져옴 - 검색 필터
	 * 4) 검색 조건을 선택하고 검색어 입력 후 검색 필터(최신순 / 인기순 / 조회순)를 선택했을 때 글을 가져옴
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getListAllBoard(int pageNo, LectureSearchDTO lsDTO) throws Exception {
	    System.out.println("서비스단 : " + pageNo + "페이지 전체 게시글 조회!" + lsDTO);

	    List<LectureBoardVO> lectureBoardList = null;

	    // 1) 검색 조건과 검색 필터 처리를 다 null과 빈값("")이 아니도록 처리를 해놓고
	    // 즉, searchType이 선택되었고, searchValue가 있고, filterType이 선택되었다.
	    boolean searchType = lsDTO.getSearchType() != null && !lsDTO.getSearchType().isEmpty();
	    boolean searchValue = lsDTO.getSearchValue() != null && !lsDTO.getSearchValue().isEmpty();
	    boolean filterType = lsDTO.getFilterType() != null && !lsDTO.getFilterType().isEmpty();

	    // 2) 경우의 수가 있는 경우만 ()안에 넣어둔다.
	    if (searchType && searchValue && filterType) {
	        // 검색 조건과 검색어를 입력하고, 검색 필터를 선택한 경우
	        makePagingInfo(pageNo, lsDTO);
	        lectureBoardList = lDao.lectureBoardSearchAndFilter(lsDTO, pi);
	        
	        // System.out.println("검색조건 검색필터 둘 다 O" + lectureBoardList.toString());
	    } else if (searchType && searchValue) {
	        // 검색 조건과 검색어를 입력하고, 검색 필터를 선택 안 한 경우
	        makePagingInfo(pageNo, lsDTO);
	        lectureBoardList = lDao.lectureBoardListWithSc(lsDTO, pi);
	        
	        // System.out.println("검색 조건만" + lectureBoardList.toString());
	    } else if (filterType) {
	        // 검색 조건과 검색어를 입력 안하고, 검색 필터만 선택한 경우
	        makePagingInfo(pageNo, lsDTO);
	        lectureBoardList = lDao.listAllBoardByFilter(lsDTO, pi);
	        
	        // System.out.println("검색 필터만" + lectureBoardList.toString());
	    } else {
	        // 검색 조건과 검색어를 입력 안하고, 검색 필터를 선택 안 한 경우
	        makePagingInfo(pageNo, lsDTO);
	        lectureBoardList = lDao.selectListAllLecBoard(pi);
	        
	        // System.out.println("검색조건 검색필터 둘 다 X" + lectureBoardList.toString());
	    }

	    Map<String, Object> returnMap = new HashMap<String, Object>();
	    returnMap.put("lectureBoardList", lectureBoardList);
	    returnMap.put("pagingInfo", this.pi);

	    // System.out.println(pi);

	    return returnMap;
	}

	/**
	 * @methodName : makePagingInfo
	 * @date : 2024.05.15
	 * @param : int pageNo - 보여줘야 할 페이지 번호
	 * @param : LectureSearchDTO lsDTO - 검색조건 / 검색필터
	 * @return : void
	 * @description : 페이징 처리하는 메서드
	 * 1) 검색 조건이 있을 경우 검색 조건에 대한 글의 갯수 가져오기
	 * 2) 검색필터의 조건이 있는 경우 글의 갯수 가져오기
	 */
	private void makePagingInfo(int pageNo, LectureSearchDTO lsDTO) throws Exception {

	    // pageNo값
	    this.pi.setPageNo(pageNo);
	    
	    this.pi.setViewPostCntPerPage(10); // 한 페이지당 보여줄 게시글의 갯수
	    this.pi.setPageCntPerBlock(4); // 1개의 블럭에 몇 페이지씩 보여줄 것인지

	    // 게시물의 데이터 갯수 구해 멤버 변수에 저장
	    if (lsDTO.getSearchType() != null && lsDTO.getSearchValue() != null) {
	        // 검색 조건이 있는 경우
	        this.pi.setTotalPostCnt(lDao.lectureBoardCntWithSc(lsDTO)); // 검색 조건에 대한 글의 갯수
	    } else if (lsDTO.getFilterType() != null) {
	        // 검색 필터가 있는 경우
	        this.pi.setTotalPostCnt(lDao.lectureBoardCntFilter(lsDTO)); // 검색 필터에 대한 글의 갯수
	    } else {
	        // 검색 조건과 검색 필터가 모두 없는 경우
	        this.pi.setTotalPostCnt(lDao.selectTotalLectureBoardCnt()); // 전체 글의 갯수
	    }

	    // 총 페이지 수 저장
	    this.pi.setTotalPageCnt();

	    // 보여주기 시작할 글의 rowIndex 번호 구해서 저장
	    this.pi.setStartRowIndex();

	    // ----------------------------------------------------------

	    // 전체 페이지 블럭 갯수 구해서 저장
	    this.pi.setTotalPageBlockCnt();

	    // 현재 페이지가 속한 페이징 블럭 번호 구해서 저장
	    this.pi.setPageBlockOfCurrentPage();

	    // 현재 페이징 블럭 시작 페이지 번호 구해서 저장
	    this.pi.setStartNumOfCurrentPagingBlock();

	    // 현재 페이징 블럭 끝 페이지 번호 구해서 저장
	    this.pi.setEndNumOfCurrentPagingBlock();
	}

	/**
	 * @methodName : makePagingInfo
	 * @author : 
	 * @date : 2024.05.15
	 * @param : int pageNo - 보여줘야 할 페이지 번호
	 * @return : void
	 * @description : 검색어가 없는 경우의 페이징 처리
	 * 1) 페이징 구현할 때 필요한 값을 만들어 paginInfo 객체에 저장해야 한다.
	 * 2) 페이징 블럭까지 처리해야한다.
	 */
	private void makePagingInfo(int pageNo) throws Exception {

		// pageNo값
		this.pi.setPageNo(pageNo);
		
		this.pi.setViewPostCntPerPage(10); // 한 페이지당 보여줄 게시글의 갯수
	    this.pi.setPageCntPerBlock(4); // 1개의 블럭에 몇 페이지씩 보여줄 것인지

		// 게시물의 데이터 갯수 구해 멤버 변수에 저장
		this.pi.setTotalPostCnt(lDao.selectTotalLectureBoardCnt());
//		System.out.println("검색어가 없는 경우 : " + lDao.selectTotalLectureBoardCnt());

		// 총 페이지 수 저장
		this.pi.setTotalPageCnt();

		// 보여주기 시작할 글의 rowIndex 번호 구해서 저장
		this.pi.setStartRowIndex();

		// ----------------------------------------------------------

		// 전체 페이지 블럭 갯수 구해서 저장
		this.pi.setTotalPageBlockCnt();

		// 현재 페이지가 속한 페이징 블럭 번호 구해서 저장
		this.pi.setPageBlockOfCurrentPage();

		// 현재 페이징 블럭 시작 페이지 번호 구해서 저장
		this.pi.setStartNumOfCurrentPagingBlock();

		// 현재 페이징 블럭 끝 페이지 번호 구해서 저장
		this.pi.setEndNumOfCurrentPagingBlock();

	}

	/**
	 * @methodName : getBoardByBoardNo
	 * @author : kde
	 * @date : 2024.05.03
	 * @param : int lecNo - 조회할 글 번호
	 * @param : String user - 조회하는 유저
	 * @return : Map<String, Object>
	 * @description : 1) user가 조회하는 글을 하루 이내에 읽은 적이 있는지 없는지 검사
	 * 2) 하루 이내에 읽은 적이 없다 -> 조회수 증가(update), 조회이력 - 기록을 남긴다(insert), 글 가져옴(유저가 글을 조회하도록)
	 * 3) 하루 이내에 읽은 적이 있다 -> 게시글만 가져옴(유저가 글을 조회하도록)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public Map<String, Object> getBoardByBoardNo(int lecNo, String user) throws Exception {
		System.out.println(user + "가" + lecNo + "번 글을 조회한다.");

		if (lDao.selectDiff(user, lecNo) == -1) {
			// user가 하루 이내에 읽은 적이 없을 경우
			lDao.updateReadCount(lecNo); // 조회수 증가
			lDao.insertReadCountProcess(user, lecNo); // 유저가 조회했다는 이력을 기록
		}

		// 조회된 글 가져오기
		LectureBoardVO lecBoard = lDao.selectBoardLecNo(lecNo);

		System.out.println("조회된 글 : " + lecBoard.toString());

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("lecBoard", lecBoard); // 조회된 글 바인딩

		return result;
	}

	/**
	 * @methodName : getBoardByBoardNo
	 * @author : kde
	 * @date : 2024.05.04
	 * @param : int lecNo - 얻어오려는 글 번호
	 * @return : Map<String, Object>
	 * @description : 게시글 수정을 위해 게시글 번호와 같은 게시글을 반환하는 메서드
	 */
	@Override
	public Map<String, Object> getBoardByBoardNo(int lecNo) throws Exception {

		// DAO단에서 조회된 글 가져오기
		LectureBoardVO lecBoard = lDao.selectBoardLecNo(lecNo);

		System.out.println("조회된 글 : " + lecBoard.toString());

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("lecBoard", lecBoard);

		return result;
	}

	/**
	 * @methodName : writeBoardService
	 * @author : kde
	 * @date : 2024.05.04
	 * @param : LectureBoardDTO newLecBoard - 저장될 게시글
	 * @return : boolean
	 * @description : newLecBoard가 DB에 저장 (insert)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public boolean writeBoardService(LectureBoardDTO newLecBoard) throws Exception {
		
		boolean result = false;
		
		// dao단 호출
	    if (lDao.insertNewLectureBoard(newLecBoard) == 1) { // 실제 게시글 (insert)
	        System.out.println("새로 저장될 게시글 번호 : " + newLecBoard.getLecNo());
	        
	        return true;
	    }
	    
	    return result;
	}

	/**
	 * @methodName : modifyBoard
	 * @author : kde
	 * @date : 2024.05.04
	 * @param : LectureBoardDTO modifyBoard - 수정되어야 할 게시글
	 * @return : boolean
	 * @description : 게시글 수정 시 update 처리
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public boolean modifyBoard(LectureBoardDTO modifyBoard) throws Exception {
		boolean result = false;

		if (lDao.updateLectureBoard(modifyBoard) == 1) {
			result = true;
		}

		return result;
	}

	/**
	 * @methodName : deleteLectureBoard
	 * @author : kde
	 * @date : 2024.05.05
	 * @param : int lecNo - 삭제 처리하려는 게시글 번호
	 * @return : boolean
	 * @description : lecNo번 글을 삭제 처리
	 */
	@Override
	public boolean deleteLectureBoard(int lecNo) throws Exception {

		boolean result = false;

		if (lDao.deleteLectureBoard(lecNo) == 1) {
			result = true;
		}

		return result;
	}
	
	/**
	 * @methodName : checkLikeStaus
	 * @author : kde
	 * @date : 2024.05.23
	 * @param : int lecNo - 좋아요 눌렀는지 안눌렀는지 확인하려는 게시글 번호
	 * @param : String user - 좋아요 누른 유저
	 * @return : boolean
	 * @description : 게시글에 좋아요(눌려있는지/안눌려있는지) 체크하는 메서드
	 */
	@Override
	public boolean checkLikeStatus(int lecNo, String user) throws Exception {
		
		boolean result = false;
		
		if (lDao.selectLikeBoard(lecNo, user) == 1) {
			result = true;
		}
		
		return result;
	}

	/**
	 * @methodName : likeBoard
	 * @author : kde
	 * @date : 2024.05.18
	 * @param : int lecNo - 게시글 번호
	 * @param : String user - 좋아요를 누르는 유저
	 * @return : boolean
	 * @description : 로그인 한 유저인 경우만 좋아요를 누를 수 있다.
     * 유저가 하트를 눌렀을 때 좋아요 수가 1증가 -> ♥
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public boolean likeUpBoard(int lecNo, String user, String lecLikeTitle) throws Exception {
	    
	    boolean result = false; // 초기값 설정
	    
	    // 좋아요를 눌렀는지 안눌렀는지 확인 후 안눌렀을 경우
	    if (lDao.selectLikeBoard(lecNo, user) != 1) {
	        lDao.insertLikeBoard(lecNo, user, lecLikeTitle); // 좋아요를 누른다.
	        lDao.updateLikeCount(lecNo); // 좋아요 수를 1 증가
	        System.out.println("서비스단 : " + lecNo + "번 글에 " + user + "가 좋아요를 눌렀습니다! 제목: " + lecLikeTitle);
	        result = true; // 성공적으로 좋아요를 누른 경우 true 반환
	    }
	    
	    return result;
	}

	/**
	 * @methodName : likeBoard
	 * @author : kde
	 * @date : 2024.05.21
	 * @param : int lecNo - 게시글 번호
	 * @param : String user - 좋아요를 취소하는 유저
	 * @return : boolean
	 * @description : 로그인 한 유저 + 좋아요를 눌렀던 유저일 경우만 좋아요를 취소할 수 있다.
     * 유저가 하트를 한번 더 눌렀을 경우 1감소 -> ♡
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public boolean likeDownBoard(int lecNo, String user, String lecLikeTitle) throws Exception {
		
		boolean result = false; // 초기값 설정
		
		// 좋아요를 눌렀는지 안눌렀는지 확인후 눌렀던 경우
		if (lDao.selectLikeBoard(lecNo, user) == 1) {
			lDao.deleteLikeBoard(lecNo, user, lecLikeTitle); // 눌렀던 좋아요를 취소한다.
			lDao.updateLikeDownCount(lecNo); // 좋아요 수를 1 감소
			System.out.println("서비스단 : " + lecNo + "번 글에 " + user + "가 좋아요를 취소했습니다!" + lecLikeTitle);
			result = true;
		}
		
		return result;
	}

	/**
	 * @methodName : selectAllLectureScrap
	 * @author : kde
	 * @date : 2024.06.08
	 * @param : int lecNo - 스크랩을 눌렀는지 확인 할 게시글 번호
	 * @param : String user - 스크랩을 눌렀는지 확인 할 유저
	 * @return : List<Object>
	 * @description : 유저가 스크랩을 누른적이 있는지 조회 (스크랩 눌렀을 경우 1반환)
	 */
	@Override
	public boolean selectAllLectureScrap(int lecNo, String user) throws Exception {
		
		boolean result = false;
		
		if (lDao.selectAllLectureScrap(lecNo, user) == 1) {
			result = true;
		}
		
		return result;
	}

	/**
	 * @methodName : scrapUpBoard
	 * @author : kde
	 * @date : 2024.06.09
	 * @param : int lecNo - 게시글 번호
	 * @param : String user - 스크랩을 누른 유저
	 * @return : boolean
	 * @description : 게시글에 스크랩 버튼을 눌렀을 경우 - 스크랩 갯수 1개 update (전체 게시글에 보여주기)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public boolean scrapUpBoard(int lecNo, String user) throws Exception {

		boolean result = false; // 초기값 설정
		
		// 스크랩을 눌렀는지 안눌렀는지 확인후 안눌렀을 경우
		if (lDao.selectAllLectureScrap(lecNo, user) != 1) {
			lDao.insertScrap(lecNo, user); // 스크랩을 누른다.
			lDao.updateUpScrap(lecNo); // 스크랩 수를 1 증가
			System.out.println("서비스단 : " + lecNo + "번 글에 " + user + "가 스크랩을 눌렀습니다!");
		}
		
		return result;
	}

	/**
	 * @methodName : scrapDownBoard
	 * @author : kde
	 * @date : 2024.06.09
	 * @param : int lecNo - 게시글 번호
	 * @param : String user - 스크랩을 누른 유저
	 * @return : boolean
	 * @description : 게시글에 스크랩 버튼을 눌렀을 경우 - 스크랩 갯수 1개(down) update (전체 게시글에 보여주기)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public boolean scrapDownBoard(int lecNo, String user) throws Exception {
		
		boolean result = false; // 초기값 설정
		
		// 스크랩을 눌렀는지 안눌렀는지 확인후 눌렀던 경우
		if (lDao.selectAllLectureScrap(lecNo, user) == 1) {
			lDao.deleteScrap(lecNo, user); // 눌렀던 스크랩을 취소한다.
			lDao.updateDownScrap(lecNo); // 스크랩 수를 1 감소
			System.out.println("서비스단 : " + lecNo + "번 글에 " + user + "가 스크랩을 취소했습니다!");
		}
		
		return result;
	}
	
	/**
		* @author : yeonju
		* @date : 2024. 6. 10.
		* @return : List<LectureBoardVO>
		* @description : 최신 5개 강의 게시글을 얻어오는 메서드
	 */
	@Override
	public List<LectureBoardVO> getLectureTop5() throws Exception {
		return lDao.getLectureTop5();
	}


}
