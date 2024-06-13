package com.dodeveloper.company.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dodeveloper.commons.interceptor.SessionNames;
import com.dodeveloper.company.service.CompanyInfoService;
import com.dodeveloper.company.vodto.CompanyInfoVO;
import com.dodeveloper.company.vodto.RevCompanyBoardVO;
import com.dodeveloper.company.vodto.ScrapVO;
import com.dodeveloper.company.vodto.WrittenCompanyBoardDTO;
import com.dodeveloper.member.vo.MemberVO;

/**
 * @packageName : com.dodeveloper.company.controller
 * @fileName : CompanyController.java
 * @author kimso05
 * @date : 2024.05.02
 * @description : 기업 정보 전체 조회 기능과 관련된 Controller단의 객체 Controller단에서 해야 할 일 : 1)
 *              View에서 보내준 파라메터(유효성 검사 할것이 있다면) 수집 -> 처리 -> (파일->파일 처리) 2) 서비스단
 *              호출 (서비스 => DAO => DB => 서비스 => 컨트롤러) 3) 서비스에서 넘겨받은 데이터가 있다면 바인딩
 *              (바인딩은 Model객체가 필요함) 4) view페이지로 포워딩, 리다이렉트 해야함
 * 
 */
@Controller // CompanyController: 컨트롤러 객체임을 명시
@RequestMapping("/companyInfo") // "/companyInfo"가 GET방식으로 요청
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyInfoService ciService; // CompanyInfoService객체를 찾아 주입
	
	/**
	 * @methodName : companyListAll
	 * @author : kimso05
	 * @date : 2024.05.02
	 * @param : Model : view단으로 옮겨주는 객체
	 * @return : String
	 * @throws Exception
	 * @description : 기업 정보 전체 조회를 담당하는 컨트롤러 메서드
	 */
	@GetMapping(value = "/entire")
	public String companyListAll(Model model) throws Exception {
		logger.info("기업리스트를 모두 보여주자");

		// 서비스단 호출(getEntireCompanyInfo()) : 서비스단에서 예외처리 날 수 있으니까 컨트롤러단에도 예외터지는건 당연함
		List<CompanyInfoVO> ciList = ciService.getEntireCompanyInfo();

		model.addAttribute("ciList", ciList); // 바인딩시킴
		// ciList : listAll.jsp로 간다~

		return "companyInfo/listAll"; // 컨트롤러의 메서드 반환값이 void일때는 매핑된 uri이름과 같은 이름의 jsp로 포워딩 된다!
	}

	/**
	 * @methodName : companyRevBoard
	 * @author : kimso05
	 * @date : 2024.05.05
	 * @param : int companyInfoNo : 유저가 클릭한 해당 기업 리뷰
	 * @return : String
	 * @throws Exception
	 * @description : 각각의 해당 기업 리뷰 상세 페이지
	 */
	@RequestMapping("/revCompanyBoard") // "/companyInfo/revCompanyBoard"가 GET방식으로 요청될 때 호출
	public String companyRevBoard(@RequestParam(name = "companyInfoNo", defaultValue = "-1") int companyInfoNo,
			Model model) throws Exception {
		// /companyInfo/revCompanyBoard.jsp로 포워딩-> 유저가 기업클릭하면 클릭한 기업 리뷰를 볼 수 있다.
		logger.info(companyInfoNo + "기업 리뷰 페이지 조회");

		// 서비스단 호출(getCompanyInfoRev())
		List<RevCompanyBoardVO> revList = ciService.getCompanyInfoRev(companyInfoNo);

		model.addAttribute("revList", revList); // 바인딩
		// revList : revCompanyBoard.jsp로 간다~

		return "/companyInfo/revCompanyBoard";
	}

	/**
	 * @methodName : writtenBoard
	 * @author : kimso05
	 * @date : 2024.05.09
	 * @return : void
	 * @description : "/companyInfo/writtenBoard" (기업 리뷰 작성 페이지)로 포워딩 시키는 메서드
	 */
	@RequestMapping("/writtenBoard") // "/companyInfo/writtenBoard"가 GET방식으로 요청될 때 호출
	public void writtenBoard() {
		// /companyInfo/writtenBoard.jsp로 포워딩
		logger.info("/writtenBoard GET 요청!");
//		return "companyInfo/writtenBoard"; 
	}

	/**
	 * @methodName : writtenBoard
	 * @author : kimso05
	 * @date : 2024.05.09
	 * @param : WrittenCompanyBoardDTO newWrittenCompanyBoardDTO - 유저가 작성한 글 객체
	 * @return : String
	 * @throws Exception
	 * @description : 유저가 작성한 기업리뷰 글을 실제 DB(companyrevboard)에 insert 시키기 위한 컨트롤러 메서드
	 */
	@RequestMapping(value = "/writtenBoard", method = RequestMethod.POST)
	public String writtenBoard(WrittenCompanyBoardDTO newWrittenCompanyBoard) {
		System.out.println(newWrittenCompanyBoard.toString());

		// 저장후에 되돌아갈 페이지 : 리뷰를 작성한 해당 기업(companyInfoNo)의 리뷰페이지로 되돌아가야된다.
		String returnPage = "redirect:/companyInfo/revCompanyBoard?"; // 글 저장하면 기업 리뷰 목록으로 돌아가야함

		try {
			if (this.ciService.writeCompanyBoardService(newWrittenCompanyBoard) == 1) { // 리뷰 저장 성공하면
				// /companyInfo/revCompanyBoard?companyInfoNo=4
				returnPage += "companyInfoNo=" + newWrittenCompanyBoard.getCompanyInfoNo();
			}
		} catch (Exception e) { // 서비스단이나 DAO단에서 예외가 발생한다면..
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnPage += "status=reviewFail";
		}

		return returnPage;
	}

	/**
	 * @methodName : deleteWrittenBoard
	 * @author : kimso05
	 * @date : 2024.05.13
	 * @param : int companyInfoNo, int revNo : 해당 기업리뷰번호와 기업리뷰 게시글 번호 삭제
	 * @return : String
	 * @throws Exception
	 * @description : 기업 리뷰 게시글 삭제 처리 하는 메서드
	 */
	@RequestMapping(value = "/deleteWrittenBoard", method = RequestMethod.GET)
	public String deleteWrittenBoard(@RequestParam("companyInfoNo") int companyInfoNo,
			@RequestParam("revNo") int revNo) {
		logger.info(revNo + "삭제 기능");
		// 삭제후에 되돌아갈 페이지 : 리뷰를 삭제한 해당 기업(companyInfoNo)의 리뷰페이지로 되돌아가야된다.
		String returnPage = "redirect:/companyInfo/revCompanyBoard?";

		try {
			if (ciService.deleteWrittenBoard(revNo) == 1) { // 삭제 됐으면 1을 반환
				// /companyInfo/deleteWrittenBoard?companyInfoNo=?
				returnPage += "companyInfoNo=" + companyInfoNo;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnPage += "status=deleteBoardFail";
		}

		return returnPage;
	}

	/**
	 * @methodName : editWrittenBoard
	 * @author : kimso05
	 * @date : 2024.05.20
	 * @param : int revNo, int companyInfoNo : 해당 기업리뷰번호와 기업리뷰 게시글 번호 수정
	 * @param : Model
	 * @return : String
	 * @throws Exception
	 * @description : "/companyInfo/editWrittenBoard" 리뷰 수정 글 GET방식 요청될 때 호출
	 */
	@GetMapping(value = "/editWrittenBoard")
	public String editWrittenBoard(@RequestParam("companyInfoNo") int companyInfoNo, @RequestParam("revNo") int revNo,
			Model model) throws Exception {
		logger.info("수정페이지 GET 요청!!!");

		RevCompanyBoardVO revBoard = ciService.editWrittenBoard(revNo);

		System.out.println(revBoard.toString());

		// revBoard를 바인딩 시켜서 editWrittenBoard.jsp로 넘겨야함
		model.addAttribute("revBoard", revBoard);

		return "/companyInfo/editWrittenBoard";

	}

	/**
	 * @methodName : revEditWrittenBoard
	 * @author : kimso05
	 * @date : 2024.05.22
	 * @param : WrittenCompanyBoardDTO newEditWrittenBoard 
	 * (새롭게 수정되어야 할 기업 리뷰 게시글 그 자체)
	 * @return : String
	 * @throws Exception
	 * @description :
	 */
	@PostMapping("/revCompanyBoard")
	public String revEditWrittenBoard(@RequestParam("revNo") int revNo, RevCompanyBoardVO newEditWrittenBoard) {
		String returnPage = "redirect:/companyInfo/revCompanyBoard?";
		System.out.println(newEditWrittenBoard.toString() + "수정하자");

		try {
			if (ciService.revEditWrittenBoard(newEditWrittenBoard) == 1) {
				// 수정완료 버튼 누르면 되돌아갈 페이지
				returnPage += "companyInfoNo=" + newEditWrittenBoard.getCompanyInfoNo();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnPage += "status=RevEditWrittenBoardFail";
		}

		return returnPage;
	}
	
	/**
	 * @methodName : insertScrap
	 * @author : kimso05
	 * @date : 2024.05.29
	 * @param : int scrapBoard, int companyInfoNo
	 * @param :
	 * @param :
	 * @return : String
	 * @throws Exception 
	 * @description : 
	 */
	@RequestMapping(value = "/insertScrap", method = RequestMethod.GET)
	public String insertScrap(@RequestParam("scrapBoard") int scrapBoard,
			@RequestParam("companyInfoNo") int companyInfoNo, HttpSession session)  {
		String returnPage = "";
		System.out.println(scrapBoard + "revNo게시글 번호" + companyInfoNo + "scrapBoard 기업리뷰번호");
		// 로그인한 유저의 아이디를 얻어와 scrapId 변수에 저장하고
		MemberVO loginMember = (MemberVO) session.getAttribute(SessionNames.LOGIN_MEMBER);
		System.out.println(loginMember.getUserId());
		// scrapId, scrapBoard, companyInfoNo를 서비스단 메서드에 보내면서 호출
		try {
			if(ciService.insertScrap(scrapBoard, loginMember.getUserId(), 3) == 1) {
				returnPage = "redirect:/mypage/myProfile";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnPage = "redirect:/companyInfo/revCompanyBoard?companyInfoNo=" + companyInfoNo 
					+ "&status=scrapFail";
		}
		
		return returnPage;
	}

}
