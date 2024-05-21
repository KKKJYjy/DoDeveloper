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

@Service // 아래의 클래스가 서비스 객체임을 명시하는 것
public class LectureBoardServiceImpl implements LectureBoardService {

	@Autowired
	private LectureBoardDAO lDao; // 스프링 컨테이너에 있는 LectureDAO 객체를 찾아 주입

	@Autowired
	private PagingInfo pi; // 스프링 컨테이너에 있는 PagingInfo 객체를 찾아 주입

	/**
	 * @methodName : getListAllBoard
	 * @author : kde
	 * @date : 2024.05.02
	 * @param : int lecNo - 게시글 번호
	 * @param : int pageNo - 페이지 번호
	 * @param : LectureSearchDTO lsDTO - 검색할 때 가져올 Type, Value(검색조건)
	 * @return : List<LectureBoardVO>
	 * @description : 1) 게시판 전체 조회에 대한 서비스 메서드
	 * 2) 검색 조건을 선택하고 검색어를 입력했을 때 글을 가져오는 메서드 - 검색 조건
	 * 3) 검색 필터(최신순 / 인기순 / 조회순)을 선택했을 때 글을 가져오는 메서드 - 검색 필터
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getListAllBoard(int pageNo, LectureSearchDTO lsDTO) throws Exception {
	    System.out.println("서비스단 : " + pageNo + "페이지 전체 게시글 조회!" + lsDTO);

	    // DAO단 호출 (selectListAllLecBoard() 메서드 호출)
	    List<LectureBoardVO> lectureBoardList = null;
	    
	    // 여기서 검색 필터부분에서 2페이지로 넘어갈 경우 filterType=view&searchType=&searchValue= 이렇게 나오니까
	    // 이 부분 수정하기 if 문안에 또 if문 사용해서 searchType / searchValue 는 제외하기
	    // 검색어가 있을 경우 부분 안에 if문 사용해서 filter타입 넣기
	    
	    // 검색 조건 처리
	    // searchType이 null이 아니거나 / (!를 사용하여) searchValue가 비어있지 않은 경우를 isEmpty()를 사용해서 확인
	    // !를 lsDTO.getSearchValue()앞에 붙여서 부정으로 만든 뒤에 
	    // isEmpty() 메서드는 검색어가 비어있으면 true를 반환 비어있지 않으면 false를 반환하도록
	    if (lsDTO.getSearchType() != null && !lsDTO.getSearchValue().isEmpty()) {
	        // 검색어가 있을 경우
	        makePagingInfo(pageNo, lsDTO);
	        lectureBoardList = lDao.lectureBoardListWithSc(lsDTO, pi);
	    } else {
	        // 검색어가 없을 경우
	        if (lsDTO.getFilterType() != null) {
	            // 검색 필터가 있는 경우
	            makePagingInfo(pageNo, lsDTO);
	            lectureBoardList = lDao.listAllBoardByFilter(lsDTO, pi);
	        } else {
	            // 검색 필터가 없는 경우
	            makePagingInfo(pageNo);
	            lectureBoardList = lDao.selectListAllLecBoard(pi);
	        }
	        
	    }

	    Map<String, Object> returnMap = new HashMap<>();
	    // 페이징 된 게시글 바인딩
	    returnMap.put("lectureBoardList", lectureBoardList);
	    // 페이징 정보를 바인딩
	    returnMap.put("pagingInfo", this.pi);

	    System.out.println(pi);

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
	    
	    this.pi.setViewPostCntPerPage(5); // 한 페이지당 보여줄 게시글의 갯수
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
		
		this.pi.setViewPostCntPerPage(5); // 한 페이지당 보여줄 게시글의 갯수
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
	 * @param : int boardNo - 조회할 글 번호
	 * @param : String user - 조회하는 유저
	 * @return : Map<String, Object>
	 * @description : 1) user가 조회하는 글을 하루 이내에 읽은 적이 있는지 없는지 검사
	 * 2) 하루 이내에 읽은 적이 없다 -> 조회수 증가(update), 조회이력 - 기록을 남긴다(insert), 글 가져옴(유저가 글을 조회하도록)
	 * 3) 하루 이내에 읽은 적이 있다 -> 게시글만 가져옴(유저가 글을 조회하도록)
	 */
	@Override
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
	public boolean writeBoardService(LectureBoardDTO newLecBoard) throws Exception {

		boolean result = false;

		newLecBoard.setLecReview(newLecBoard.getLecReview().replace("\r\n", "</ br>"));

		// dao단 호출
		if (lDao.insertNewLectureBoard(newLecBoard) == 1) { // 실제 게시글 (insert)
			System.out.println("새로 저장될 게시글 번호 : " + newLecBoard.getLecNo());

			result = true;
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
	 * @methodName : likeBoard
	 * @author : kde
	 * @date : 2024.05.18
	 * @param : int lecNo - 게시글 번호
	 * @param : String user - 좋아요를 누르는 유저
	 * @return : boolean
	 * @throws Exception 
	 * @description : 로그인 한 유저인 경우만 좋아요를 누를 수 있다.
     * 유저가 하트를 눌렀을 때 좋아요 수가 1증가 -> ♥
     * 유저가 하트를 한번 더 눌렀을 경우 1감소 -> ♡
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public boolean likeBoard(int lecNo, String user) throws Exception {
		
		boolean result = false;
		
	        // 좋아요 버튼 누르기 성공한 경우
	        if (lDao.insertLikeBoard(lecNo, user) == 1) {
	            // 성공한 경우 좋아요 갯수 1개 up
	            lDao.updateLikeCount(lecNo);
	            
	            System.out.println("좋아요 버튼 누르고 갯수 1개 up 성공");
	            
	            result = true;
	        } else {
	        	// 좋아요 버튼 누르기 실패한 경우
	        	System.out.println("좋아요 버튼 누르기 실패!");
	        }
	    return result;
	}

}
