package com.dodeveloper.algorithm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodeveloper.algorithm.dao.AlgDAO;
import com.dodeveloper.algorithm.service.AlgService;
import com.dodeveloper.algorithm.vodto.AlgBoardDTO;
import com.dodeveloper.algorithm.vodto.AlgClassificationDTO;
import com.dodeveloper.algorithm.vodto.AlgDetailDTO;
import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.member.dto.LoginDTO;

@Controller
@RequestMapping("/algorithm")
public class AlgorithmController {

    @Autowired
    AlgService aService;
    
    @Autowired
    AlgDAO aDao;

    @Autowired
    HttpSession ses;

    private static final Logger logger = LoggerFactory.getLogger(AlgorithmController.class);

    @GetMapping(value = "/listAll")
    public void listAllGet(Model model, @RequestParam(value = "pageNo" , defaultValue = "1") int pageNo) throws Exception {
	//logger.info("listAll View.");
	//System.out.println("!!!컨트롤러!!!");

	List<AlgBoardDTO> returnMap = null;
	List<AlgClassificationDTO> returnMap2 = null;
	PagingInfo returnMap3 = new PagingInfo(0);
	
	System.out.println(pageNo+"페이지");
	if(pageNo < 1) {
		pageNo = 1;
	}
	
	
	returnMap3 = aService.getPagingInfo(pageNo);
	//System.out.println(returnMap3);

	// 멤버테이블 출력함
	returnMap = aService.getListAllBoard(returnMap3); // PagingInfo 를 받아 페이지에 해당하는 게시글들을 출력
	returnMap2 = aService.getAlgClassification();

	model.addAttribute("algBoardList", returnMap);
	model.addAttribute("algClassification",returnMap2);
	model.addAttribute("pagingInfo", returnMap3);
    }

    @GetMapping("/algDetail")
    public void getAlgDetail(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
	System.out.println("알고리즘상세 : " + boardNo + " 번 알고리즘");

	List<AlgDetailDTO> returnMap = null;

	// LoginDTO loginDTO = null;

	returnMap = aService.getListDetail(boardNo);

	// System.out.println(returnMap);

	model.addAttribute("algDetailList", returnMap);
	// model.addAttribute("login", loginDTO);

	// 세션에 boardNo 저장해서 인터셉터에서 가져올 수 있도록 함
	ses.setAttribute("boardNum", boardNo);
    }

    ////////////////////////

    @RequestMapping(value = "/writePOST", method = RequestMethod.POST)
    public String writeAlg(AlgBoardDTO algBoardDTO) throws Exception {
	//System.out.println("글 작성()");
	//System.out.println(algBoardDTO);

	if (algBoardDTO.getTitle() == "" || algBoardDTO.getClassificationCode() <= 0) {
	    System.out.println("유효성x");
	    return "redirect:listAll";
	}

	aService.writeAlgBoard(algBoardDTO);

	return "redirect:listAll";
    }

    @RequestMapping("/writePOST") // "/algorithm/write"가 get 방식으로 요청될 때... 호출
    public String writeBoard(Model model) throws Exception {
	// //algorithm/writeBoard.jsp로 포워딩
	//System.out.println("글작성");

	List<AlgClassificationDTO> returnMap = null;

	// 알고리즘코드번호 테이블 받아와야 함
	returnMap = aService.getAlgClassification();
	//System.out.println(returnMap.toString());
	model.addAttribute("algClassification", returnMap);

	return "/algorithm/writeBoard";
    }

    ////////////////////

    @RequestMapping(value = "/newClassification", method = RequestMethod.GET)
    public String newClassification(@RequestParam("algClassification") String algClassification) {
	// algClassification 항목 작성
	//System.out.println(algClassification);
	try {
	    aService.writeAlgClassification(algClassification);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return "redirect:writePOST";
    }

    ///////////////////

    @RequestMapping(value = "/modifyAlg", method = RequestMethod.GET)
    public void modifyBoard(AlgBoardDTO algBoardDTO, Model model) throws Exception {
	// 알고리즘게시판 수정페이지로 이동할 때 algBoard 를 modifyAlg.jsp 로 전달
	//System.out.println("!!!!!!!!!!!!");
	List<AlgBoardDTO> returnMap = null;

	// AlgClassification 항목을 수정하려면 ㄱList<AlgClassificationDTO>도 modifyAlg.jsp 로
	// 전달(GET)
	List<AlgClassificationDTO> returnMap2 = null;

	returnMap = aService.getListAllBoard();
	returnMap2 = aService.getAlgClassification();

	model.addAttribute("algBoardList", returnMap);
	model.addAttribute("algClassification", returnMap2);

    }

    @RequestMapping(value = "/modifyAlg", method = RequestMethod.POST)
    public String modifyAlg(AlgBoardDTO algBoardDTO) {
	//System.out.println("modmod");
	//System.out.println(algBoardDTO);

	aService.updateAlgBoard(algBoardDTO);

	return "redirect:listAll";
    }

    ////////////////////////////

    @RequestMapping("/writeDetailPOST") // "/algorithm/writeDetail"가 get 방식으로 요청될 때... 호출
    public String writeDetailBoard(Model model, HttpServletRequest req, HttpSession ses) throws Exception {
	// //algorithm/writeBoard.jsp로 포워딩

	List<AlgDetailDTO> algDetailDTO = null;

	//System.out.println("상세글작성");

	//System.out.println(ses.getAttribute("boardNo"));

	int boardNo = Integer.parseInt((String) ses.getAttribute("boardNo"));

	System.out.println(boardNo);

	// db 에서 algDetail 정보를 받어서 서비스 단으로
	algDetailDTO = aService.getListDetail(boardNo);

	model.addAttribute("boardNo", boardNo);
	model.addAttribute("algDetailList", algDetailDTO);

	return "/algorithm/writeDetail";
    }

    @RequestMapping(value = "/writeDetailPOST", method = RequestMethod.POST)
    public String writeAlgDetail(AlgDetailDTO algDetailDTO) {
	// jsp 에서 post로 받아온 정보를 algDetail 에 insert
	// System.out.println("$$$$$$$$$$$");
	//System.out.println(algDetailDTO);

	int boardNo = algDetailDTO.getAlgBoardNo();

	aService.writeAlgDetail(algDetailDTO);

	return "redirect:algDetail?boardNo=" + boardNo;
	// return "/algorithm/algDetail?boardNo="+boardNo;

    }

    @RequestMapping("/modifyAlgDetail")
    public String modifyAlgDetail(Model model, HttpSession ses) throws Exception {
	// algDetail 을 수정하기 위해 버튼을 눌렀을 때 호출 (GET)
	//System.out.println("********");

	//System.out.println("상세글수정");

	List<AlgDetailDTO> returnMap = null;
	//System.out.println(ses.getAttribute("boardNo"));

	int boardNo = Integer.parseInt((String) ses.getAttribute("boardNo")); // 수정할 AlgDetail의 AlgBoardNo(AlgBoard 에서
									      // 참조한 변수)

	returnMap = aService.getListDetail(boardNo);

	model.addAttribute("algDetail", returnMap);

	return "/algorithm/modifyDetail";

    }

    @RequestMapping(value = "/modifyAlgDetail", method = RequestMethod.POST)
    public String modifyAlgDetail(AlgDetailDTO algDetailDTO) {
	//System.out.println("*!*!*!*!");
	//System.out.println(algDetailDTO);
	int algDetailNo = algDetailDTO.getAlgDetailNo();

	aService.updateAlgDetail(algDetailDTO, algDetailNo);

	return "/algorithm/listAll";

    }

    @RequestMapping("codeDetail")
    public void viewCodeDetail(@RequestParam("algDetailNo") int algDetailNo, Model model) {
	// System.out.println(algBoard);
	// System.out.println(algDetailNo);
	AlgDetailDTO returnMap = null;

	try {
	    returnMap = aService.getAlgDetail(algDetailNo);
	    //System.out.println("controll codeDetail");

	    //System.out.println(returnMap);
	    model.addAttribute("algDetailList", returnMap);
	    
	 // 세션에 algDetailNo 저장해서 인터셉터에서 가져올 수 있도록 함
	    ses.setAttribute("detailNum", algDetailNo);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    @RequestMapping("removeAlgDetail")
    public String removeAlgDetail(@RequestParam("algDetailNo") int algDetailNo, @RequestParam("algBoardNo") int algBoardNo) {
	 //System.out.println(algDetailNo);
	 
	// AuthInterceptor.preHandle() 호출 - 글 삭제 권한이 있는지 검사하고 옴
	String result = null;
	//System.out.println(algDetailNo + "번글을 삭제하자");

	if (aService.remBoard(algDetailNo)) {
	    result = "redirect:/algorithm/algDetail?boardNo="+algBoardNo;
	} else {
	    result = "redirect:/listAll";
	}

//	 		if(((MemberVO)ses.getAttribute("ioginMember")).getUserId().equals(writer)) {
//	 			// 서비스단 -> DAO 단 호출
//	 			
//	 			
//	 		} else {
//	 			redirectPage = "/board/listAll?status=removeFail";
//	 		}

	return result;
    }
    
    @RequestMapping("getClassification")
    @ResponseBody
    public List<AlgBoardDTO> getClassification(HttpServletRequest req, HttpServletResponse resp, HttpSession session, @RequestParam("val") int val, Model model) {
	//System.out.println("ajax 호출");
	//System.out.println(val);
	
	List<AlgBoardDTO> dto = aDao.selectAlgListByClassificationCode(val);
	
	System.out.println(dto);
	
	return dto;
	
    }
    
    
    
    
    @RequestMapping("redirect")
    public void redirect() {
	
    }
    
    
    @RequestMapping("Template")
    
    public void template() {
	
    }

}
