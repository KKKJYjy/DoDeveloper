package com.dodeveloper.study.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dodeveloper.study.vodto.StudyBoardDTO;
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.study.vodto.SearchStudyDTO;
import com.dodeveloper.study.vodto.StackVO;
import com.dodeveloper.study.vodto.StuStackDTO;

@Repository
public class StudyDAOImpl implements StudyDAO {

	@Autowired
	private SqlSession ses;

	private static String ns = "com.dodeveloper.mappers.studyMapper";

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : PagingInfo pi
	 * @return : List<StudyBoardVO>
	 * @description : 페이지에 해당하는 모든 스터디 리스트를 반환
	 */
	@Override
	public List<StudyBoardVO> selectAllList(PagingInfo pi) throws Exception {
		return ses.selectList(ns + ".selectAllList", pi);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : int stuBoardNo
	 * @return : List<StuStackDTO>
	 * @description : stuBoardNo번째 스터디 언어를 반환
	 */
	@Override
	public List<StuStackDTO> selectAllStudyStack(int stuBoardNo) {
		return ses.selectList(ns + ".selectAllstudyStack", stuBoardNo);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @return : int - 다음 스터디 모임글 번호 반환
	 * @description : 다음 스터디 모임글 번호 반환
	 */
	@Override
	public int selectNextStuNo() throws Exception {
		return ses.selectOne(ns + ".selectNextStuNo");
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : int stuBoardNo - 스터디 모임글 번호
	 * @param : int chooseStack - 선택한 스터디 언어 번호
	 * @return : int - 작업 성공했으면 1, 실패했으면 0 반환
	 * @description : stuStack테이블 stuBoardNo번째에 chooseStack을 insert 한다.
	 */
	@Override
	public int insertNewStack(int stuBoardNo, int chooseStack) throws Exception {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("stuBoardNo", stuBoardNo);
		param.put("chooseStack", chooseStack);

		return ses.insert(ns + ".insertNewStack", param);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : StudyBoardDTO newStudyDTO - 새로운 스터디 모임글 객체
	 * @return : int - 작업 성공했으면 1, 실패했으면 0 반환
	 * @description : 새로운 스터디 모임글을 insert 한다.
	 */
	@Override
	public int insertNewStudy(StudyBoardDTO newStudyDTO) throws Exception {
		return ses.insert(ns + ".insertNewStudy", newStudyDTO);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : int stuNo
	 * @return : StudyBoardVO - 스터디 모임글 정보를 담고있는 객체
	 * @description : stuNo번째 상세페이지로 이동하기 위해 스터디 모임글 정보를 select
	 */
	@Override
	public StudyBoardVO selectStudyByStuNo(int stuNo) throws Exception {
		return ses.selectOne(ns + ".selectStudyByStuNo", stuNo);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : SearchStudyDTO sDTO
	 * @param : PagingInfo pi
	 * @return : List<StudyBoardVO>
	 * @description : 검색어가 있고 + 필터가 있는 경우의 스터디 모임글 리스트
	 */
	@Override
	public List<StudyBoardVO> selectAllListWithsDTO(SearchStudyDTO sDTO, PagingInfo pi) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("searchType", sDTO.getSearchType());
		param.put("searchValue", "%" + sDTO.getSearchValue() + "%");
		param.put("startRowIndex", pi.getStartRowIndex());
		param.put("viewPostCntPerPage", pi.getViewPostCntPerPage());

		return ses.selectList(ns + ".selectAllListWithsDTO", param);
	}
	
	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : PagingInfo pi
	 * @param : String status
	 * @return : List<StudyBoardVO>
	 * @description : 검색어가 없고 + 필터가 있는 경우의 스터디 모임글 리스트 select
	 */
	@Override
	public List<StudyBoardVO> selectAllListWithStatusFilter(PagingInfo pi, String status) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", status);
		param.put("startRowIndex", pi.getStartRowIndex());
		param.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		return ses.selectList(ns + ".selectAllListWithStatusFilter", param);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : SearchStudyDTO sDTO
	 * @param : PagingInfo pi
	 * @param : String status
	 * @return : List<StudyBoardVO>
	 * @description : 검색어가 있고 + 필터가 있는 경우의 스터디 모임글 리스트 select
	 */
	@Override
	public List<StudyBoardVO> selectAllListWithsDTOWithStatusFilter(SearchStudyDTO sDTO, PagingInfo pi, String status)
			throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", status);
		param.put("searchType", sDTO.getSearchType());
		param.put("searchValue", "%" + sDTO.getSearchValue() + "%");
		param.put("startRowIndex", pi.getStartRowIndex());
		param.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		return ses.selectList(ns + ".selectAllListWithsDTOWithStatusFilter", param);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @return : List<StackVO>
	 * @description : DB 스터디 언어 테이블의 정보를 담고있는 객체를 반환한다.
	 */
	@Override
	public List<StackVO> selectAllStack() throws Exception {
		return ses.selectList(ns + ".selectAllStack");
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : int stuNo
	 * @return : int - 성공하면 1, 실패하면 0 반환
	 * @description : stuNo번째 스터디 모임글을 삭제한다.
	 */
	@Override
	public int deleteStudyBoard(int stuNo) throws Exception {
		return ses.delete(ns + ".deleteStudyBoard", stuNo);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : StudyBoardDTO modifyStudy - 수정할 스터디 모임글 정보를 담고있는 객체
	 * @return : int - 성공하면 1, 실패하면 0 반환
	 * @description : 스터디 모임글을 update한다.
	 */
	@Override
	public int modifyStudy(StudyBoardDTO modifyStudy) throws Exception {
		return ses.update(ns + ".modifyStudy", modifyStudy);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : int stuStackNo - 스터디 언어 번호 (pk)
	 * @param : int chooseStack - 선택한 스터디 언어 번호
	 * @return : int - 성공하면 1, 실패하면 0 반환
	 * @description : stuStackNo번째 스터디 언어를 update한다.
	 */
	@Override
	public int modifyStack(int stuStackNo, int chooseStack) throws Exception {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("stuStackNo", stuStackNo);
		param.put("chooseStack", chooseStack);
		return ses.update(ns + ".modifyStack", param);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : int stuStackNo - 스터디 언어 번호 (pk)
	 * @return : int - 성공하면 1, 실패하면 0 반환
	 * @description : 스터디 언어 테이블에서 stuStackNo번째 스터디 언어를 삭제한다.
	 */
	@Override
	public int deleteStudyStack(int stuStackNo) throws Exception {
		return ses.delete(ns + ".deleteStudyStack", stuStackNo);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @return : int
	 * @description : 전체 스터디 언어 모임글 갯수 select
	 */
	@Override
	public int selectTotalBoardCnt() throws Exception {
		return ses.selectOne(ns + ".selectTotalBoardCnt");
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : SearchStudyDTO sDTO - 검색한 내용을 담고있는 객체
	 * @return : int
	 * @description : 검색어가 있고 필터가 없는 경우 스터디 모임글 갯수 select
	 */
	@Override
	public int selectTotalBoardCntWithSdto(SearchStudyDTO sDTO) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("searchType", sDTO.getSearchType());
		param.put("searchValue", "%" + sDTO.getSearchValue() + "%");
		return ses.selectOne(ns + ".selectTotalBoardCntWithSdto", param);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : String status
	 * @return : int
	 * @description : 검색어가 없고 필터가 있는 경우의 스터디 모임글 갯수 select
	 */
	@Override
	public int selectTotalBoardCntWithStatusFilter(String status) throws Exception {
		return ses.selectOne(ns + ".selectTotalBoardCntWithStatusFilter", status);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : SearchStudyDTO sDTO
	 * @param : String status
	 * @return : int
	 * @description : 검색어가 있고 필터가 있는 경우의 스터디 모임글 갯수 select
	 */
	@Override
	public int selectTotalBoardCntWithSdtoWithStatusFilter(SearchStudyDTO sDTO, String status) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", status);
		param.put("searchType", sDTO.getSearchType());
		param.put("searchValue", "%" + sDTO.getSearchValue() + "%");
		return ses.selectOne(ns + ".selectTotalBoardCntWithSdtoWithStatusFilter", param);
	}

	/**
		* @author : yeonju
		* @date : 2024. 5. 28.
		* @param : List<String> studyStackList - 필터링할 스터디 언어
		* @return : int
		* @description : 스터디 언어로 필터링했을 때 스터디 모임글 갯수 select
	 */
	@Override
	public int selectTotalBoardCntWithSdtoWithStuStack(List<String> studyStackList) {
		return ses.selectOne(ns + ".selectTotalBoardCntWithSdtoWithStuStack", studyStackList);
	}
	
	/**
	 * @author : yeonju
	 * @date : 2024. 5. 24.
	 * @param : List<String> studyStackList - 필터링할 스터디 언어 List
	 * @return : List<StudyBoardVO> - 필터링한후 반환되는 스터디 모임글 List
	 * @description : 필터링할 스터디 언어를 공부하는 스터디 모임글을 select
	 */
	@Override
	public List<StudyBoardVO> searchStudyByStack(List<String> studyStackList, PagingInfo pi) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("studyStackList", studyStackList);
		param.put("startRowIndex", pi.getStartRowIndex());
		param.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		return ses.selectList(ns + ".searchStudyByStack", param);
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 6. 9.
	 * @return : List<StudyBoardVO>
	 * @description : 스터디 모임글 상위 5개글을 가져온다
	 */
	@Override
	public List<StudyBoardVO> getStudyTop5() {
		return ses.selectList(ns + ".getStudyTop5");
	}


	

}
