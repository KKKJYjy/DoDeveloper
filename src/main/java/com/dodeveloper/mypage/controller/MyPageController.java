package com.dodeveloper.mypage.controller;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.checkerframework.checker.units.qual.h;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.dodeveloper.commons.interceptor.SessionNames;
import com.dodeveloper.admin.vo.QnaBoardVO;
import com.dodeveloper.admin.vo.ReportVO;

import com.dodeveloper.algorithm.service.AlgService;
import com.dodeveloper.algorithm.vodto.AlgDetailDTO;
import com.dodeveloper.company.vodto.ScrapVO;
import com.dodeveloper.etc.PagingInfo;
import com.dodeveloper.lecture.service.LectureBoardService;
import com.dodeveloper.lecture.vodto.LectureBoardVO;
import com.dodeveloper.lecture.vodto.LectureLikeVO;
import com.dodeveloper.member.service.MemberService;
import com.dodeveloper.member.vo.MemberVO;
import com.dodeveloper.mypage.dto.ChangeEmailDTO;
import com.dodeveloper.mypage.dto.ChangeProfileDTO;
import com.dodeveloper.mypage.dto.ChangePwdDTO;
import com.dodeveloper.mypage.dto.ProfileDTO;
import com.dodeveloper.mypage.service.MyPageService;
import com.dodeveloper.mypage.vo.ProfileVO;
import com.dodeveloper.reply.service.ReplyService;
import com.dodeveloper.reply.vodto.ReplyVO;
import com.dodeveloper.study.vodto.StuStackDTO;
import com.dodeveloper.study.vodto.StudyBoardVO;
import com.dodeveloper.studyApply.vodto.StudyApplyVO;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

	private static final Logger logger = LoggerFactory.getLogger(MyPageController.class);

	@Autowired
	private MyPageService myPageService;

	@Autowired
	private MemberService mService;
	
	@Autowired
	private LectureBoardService lService; // 스프링 컨테이너에서 LectureService 객체를 찾아 주입
	
	@Autowired
	private AlgService aService;  // 스프링 컨테이너에서 AlgService 객체를 찾아 주입

	@GetMapping("/myProfile")
	public void myProfileGet() {
		logger.info("myProfile View.");
	}

	@ResponseBody
	@RequestMapping(value = "/setProfileImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<String, Object>> setProfileImage(@RequestBody Map<String, Object> param)
			throws Exception {
		System.out.println("setProfileImage : " + param.toString());

		String userId = String.valueOf(param.get("userId"));
		String prefix = String.valueOf(param.get("prefix"));
		String base64Encode = String.valueOf(param.get("base64Encode"));
		byte[] profileImage = Base64.getDecoder().decode(base64Encode);

		ProfileDTO profileDTO = new ProfileDTO(userId, prefix, profileImage);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (myPageService.setProfileImage(profileDTO) > 0) {
			returnMap.put("state", "T");
			returnMap.put("message", "Success");
		} else {
			returnMap.put("state", "F");
			returnMap.put("message", "Fail");
		}

		HttpHeaders headers = new HttpHeaders();
		Charset utf8 = Charset.forName("utf-8");
		MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON, utf8);
		headers.setContentType(mediaType);

		return ResponseEntity.ok().headers(headers).body(returnMap);
	}

	@ResponseBody
	@RequestMapping(value = "/getProfileImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<String, Object>> getProfileImage(@RequestBody Map<String, String> param)
			throws Exception {

		ResponseEntity<Map<String, Object>> result = null;
		Map<String, Object> returnMap = new HashMap<String, Object>();

		String userId = String.valueOf(param.get("userId"));
		System.out.println("userId : " + userId);

		ProfileVO profileVO = myPageService.getProfileImage(userId);
		System.out.println("profileVO : " + profileVO);
		if (profileVO != null) {
			System.out.println("reslutMap.get(profileImage) : " + profileVO.getProfileImage());

			byte[] profileImage = profileVO.getProfileImage();
			System.out.println("profileImage.length : " + profileImage.length);
			if (profileImage.length > 0 && profileImage != null) {
				String base64Encode = byteToBase64(profileImage);
				System.out.println("base64Encode : " + base64Encode);

				returnMap.put("state", "T");
				returnMap.put("message", "Success");
				returnMap.put("prefix", profileVO.getPrefix());
				returnMap.put("base64Encode", base64Encode);

				result = new ResponseEntity<Map<String, Object>>(returnMap, HttpStatus.OK);
			}
		} else {
			returnMap.put("state", "F");
			returnMap.put("message", "Fail");
			returnMap.put("prefix", "");
			returnMap.put("base64Encode", "");

			result = new ResponseEntity<Map<String, Object>>(returnMap, HttpStatus.OK);
		}
		return result;
	}

	private String byteToBase64(byte[] arr) {
		String result = "";
		try {
			result = Base64Utils.encodeToString(arr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/removeProfileImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<String, Object>> removeProfileImage(@RequestBody Map<String, Object> param)
			throws Exception {
		System.out.println("removeProfileImage : " + param.toString());

		String userId = String.valueOf(param.get("userId"));

		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (myPageService.removeProfileImage(userId) > 0) {
			returnMap.put("state", "T");
			returnMap.put("message", "Success");
		} else {
			returnMap.put("state", "F");
			returnMap.put("message", "Fail");
		}

		HttpHeaders headers = new HttpHeaders();
		Charset utf8 = Charset.forName("utf-8");
		MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON, utf8);
		headers.setContentType(mediaType);

		return ResponseEntity.ok().headers(headers).body(returnMap);
	}

	@ResponseBody
	@RequestMapping(value = "/changePwd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<String, Object>> changePwd(@RequestBody ChangePwdDTO changePwdDTO) throws Exception {
		System.out.println("changePwdDTO : " + changePwdDTO.toString());

		Map<String, Object> returnMap = new HashMap<String, Object>();

		int result = mService.checkUserPwd(changePwdDTO);
		System.out.println("changePwd result : " + result);

		if (result > 0) {
			if (mService.changeUserPwd(changePwdDTO) > 0) {
				returnMap.put("state", "T");
				returnMap.put("message", "Success");
			}
		} else {
			returnMap.put("state", "F");
			returnMap.put("message", "Fail");
		}

		HttpHeaders headers = new HttpHeaders();
		Charset utf8 = Charset.forName("utf-8");
		MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON, utf8);
		headers.setContentType(mediaType);

		return ResponseEntity.ok().headers(headers).body(returnMap);
	}

	@ResponseBody
	@RequestMapping(value = "/changeProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<String, Object>> changePwd(@RequestBody ChangeProfileDTO changeProfileDTO)
			throws Exception {
		System.out.println("changeProfileDTO : " + changeProfileDTO.toString());

		HttpHeaders headers = new HttpHeaders();
		Charset utf8 = Charset.forName("utf-8");
		MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON, utf8);
		headers.setContentType(mediaType);

		Map<String, Object> returnMap = new HashMap<String, Object>();

		if (mService.changeProfile(changeProfileDTO) > 0) {
			returnMap.put("state", "T");
			returnMap.put("message", "Success");
		} else {
			returnMap.put("state", "F");
			returnMap.put("message", "Fail");
		}

		return ResponseEntity.ok().headers(headers).body(returnMap);
	}
	
	@RequestMapping(value = "/email", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<String, Object>> changeEmail(@RequestBody ChangeEmailDTO changeEmailDTO,
			HttpSession session) throws Exception{
		
		HttpHeaders headers = new HttpHeaders();
		Charset utf8 = Charset.forName("utf-8");
		MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON, utf8);
		headers.setContentType(mediaType);
		
		MemberVO loginMember = (MemberVO)session.getAttribute(SessionNames.LOGIN_MEMBER);
		String verifiedEmail = (String)session.getAttribute(SessionNames.VERIFIED_EMAIL);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if(!loginMember.getUserId().equals(changeEmailDTO.getUserId())) {
			returnMap.put("isSuccess", "false");
			returnMap.put("reason", "유저 아이디가 조작되었음. 다시 시도해주세요.");
		}
		if(!verifiedEmail.equals(changeEmailDTO.getEmail())) {
			returnMap.put("isSuccess", "false");
			returnMap.put("reason", "유저 이메일이 조작되었음. 다시 시도해주세요.");
		}
		if(!mService.changeEmail(changeEmailDTO)) {
			returnMap.put("isSuccess", "false");
			returnMap.put("reason", "서버오류. 잠시 후 다시 시도해주세요.");
		}
		
		returnMap.put("isSuccess", "true");
		
		MemberVO changedMemberVO = mService.getMemberInfo(loginMember.getUserId());
		session.setAttribute(SessionNames.LOGIN_MEMBER, changedMemberVO);
		
		return ResponseEntity.ok().headers(headers).body(returnMap);
	}
		
	

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 30.
	 * @return : void
	 * @param : Model model
	 * @param : HttpServletRequest req
	 * @description : 내가 작성한 스터디 모임글 페이지로 이동
	 */
	@GetMapping("/myStudyList")
	public void getMyStudyList(Model model, HttpServletRequest req) {

		String userId = ((MemberVO) req.getSession().getAttribute("loginMember")).getUserId();
		logger.info(userId + "가 작성한 스터디 모임글 페이지로 이동");

		Map<String, Object> result = null;

		try {

			result = myPageService.getMyStudyList(userId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("studyList", (List<StudyBoardVO>) result.get("studyList"));
		model.addAttribute("stuStackList", (List<StuStackDTO>) result.get("stuStackList"));
		model.addAttribute("stuApplyList", (List<StudyApplyVO>) result.get("stuApplyList"));
	}

	/**
	 * @author : yeonju
	 * @date : 2024. 5. 30.
	 * @param : Model model
	 * @param : HttpServletRequest req
	 * @return : void
	 * @description : 내가 신청한 스터디 모임글 페이지로 이동
	 */
	@GetMapping("/myApplyList")
	public void getMyApplyList(Model model, HttpServletRequest req) {
		String userId = ((MemberVO) req.getSession().getAttribute("loginMember")).getUserId();
		logger.info(userId + "가 신청한 스터디 모임글 페이지로 이동");

		Map<String, Object> result = null;

		try {

			result = myPageService.getMyApplyList(userId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("studyList", (List<StudyBoardVO>) result.get("studyList"));
		model.addAttribute("stuStackList", (List<StuStackDTO>) result.get("stuStackList"));
		model.addAttribute("stuApplyList", (List<StudyApplyVO>) result.get("stuApplyList"));
	}
	
	/**
		* @author : yeonju
		* @date : 2024. 6. 4.
		* @param : Model model
		* @param : HttpServletRequest req
		* @return : void
		* @description : 내가 참여중인 스터디 페이지로 이동
	 */
	@GetMapping("/myJoinedStudyList")
	public void getMyJoinedStudyList(Model model, HttpServletRequest req) {
		String userId = ((MemberVO) req.getSession().getAttribute("loginMember")).getUserId();
		logger.info(userId + "가 참여중인 스터디 모임글 페이지로 이동");
		
		Map<String, Object> result = null;

		try {

			result = myPageService.getMyJoinedStudyList(userId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("studyList", (List<StudyBoardVO>) result.get("studyList"));
		model.addAttribute("stuStackList", (List<StuStackDTO>) result.get("stuStackList"));
		model.addAttribute("stuApplyList", (List<StudyApplyVO>) result.get("stuApplyList"));
	}
	
	/**
	 * @methodName : myLectureList
	 * @author : kde
	 * @date : 2024.06.12
	 * @param : @RequestParam(value = "pageNo", defaultValue = "1") int pageNo
	 * - 뷰단에서 쿼리스트링으로 넘겨 페이징 하기 위한 pageNo
	 * @param : Model model - 컨트롤러에서 뷰로 데이터를 전달
	 * @param : HttpServletRequest req - 로그인한 사용자의 아이디를 가져오기 위해 사용
	 * @return : void
	 * @throws Exception 
	 * @description : userId가 강의 추천 게시판에 작성한 게시글로 이동 + 페이징
	 */
	@GetMapping("/myLectureList")
	public void myLectureList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			Model model, HttpServletRequest req) {
		// 현재 로그인한 사용자의 아이디
		String userId = ((MemberVO) req.getSession().getAttribute("loginMember")).getUserId();
		logger.info(userId + "가 강의 추천 게시판에 작성한 게시글로 이동");
		
	    Map<String, Object> resultMap = null;
	    
	    String resultPage = null;
	    
	    // 페이지 번호가 1이상이 되도록 설정
	 	if (pageNo <= 0) {
	 		pageNo = 1;
	 	}
	 		
	 	// 서비스단 호출(getMyLectureList() 메서드 호출)
		try {
			resultMap = myPageService.getMyLectureList(pageNo, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 게시글 목록 가져오기
		List<LectureBoardVO> lectureList = (List<LectureBoardVO>) resultMap.get("lectureList");
		
		// 게시글 자체를 바인딩
		model.addAttribute("lectureList", (List<LectureBoardVO>) resultMap.get("lectureList"));
		// 페이징 정보를 바인딩
		model.addAttribute("pagingInfo", (PagingInfo) resultMap.get("pagingInfo"));
	}
	
	/**
	 * @methodName : myReplyLectureList
	 * @author : kde
	 * @date : 2024.06.12
	 * @param : @RequestParam(value = "pageNo", defaultValue = "1") int pageNo
	 * - 뷰단에서 쿼리스트링으로 넘겨 페이징 하기 위한 pageNo
	 * @param : Model model - 컨트롤러에서 뷰로 데이터를 전달
	 * @param : HttpServletRequest req - 로그인한 사용자의 아이디를 가져오기 위해 사용
	 * @return : void
	 * @description : 유저가 강의 추천 게시판의 게시글에 작성한 댓글 불러오는 게시글로 이동 + 페이징
	 */
	@GetMapping("/myReplyLectureList")
	public void myReplyLectureList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			Model model, HttpServletRequest req) {
		// 현재 로그인한 사용자의 아이디
		String userId = ((MemberVO) req.getSession().getAttribute("loginMember")).getUserId();
		logger.info(userId + "가 강의 추천 게시판의 게시글에 작성한 댓글 마이페이지의 게시글로 이동");
		
	    Map<String, Object> resultMap = null;
	    
	    String resultPage = null;
	    
	    // 페이지 번호가 1이상이 되도록 설정
	 	if (pageNo <= 0) {
	 		pageNo = 1;
	 	}
	 		
	 	// 서비스단 호출(getMyReplyLectureList() 메서드 호출)
		try {
			resultMap = myPageService.getMyReplyLectureList(pageNo, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 게시글 목록 가져오기
		List<ReplyVO> lectureReplyList = (List<ReplyVO>) resultMap.get("lectureReplyList");
		
		// 게시글 자체를 바인딩
		model.addAttribute("lectureReplyList", (List<ReplyVO>) resultMap.get("lectureReplyList"));
		// 페이징 정보를 바인딩
		model.addAttribute("pagingInfo", (PagingInfo) resultMap.get("pagingInfo"));
	}
	
	/**
	 * @methodName : myScrapLectureList
	 * @author : kde
	 * @date : 2024.06.12
	 * @param : @RequestParam(value = "pageNo", defaultValue = "1") int pageNo
	 * - 뷰단에서 쿼리스트링으로 넘겨 페이징 하기 위한 pageNo
	 * @param : Model model - 컨트롤러에서 뷰로 데이터를 전달
	 * @param : HttpServletRequest req - 로그인한 사용자의 아이디를 가져오기 위해 사용
	 * @return : void
	 * @description : 유저가 강의 추천 게시판의 게시글 스크랩한 게시글로 이동 + 페이징
	 */
	@GetMapping("/myScrapLectureList")
	public void myScrapLectureList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			Model model, HttpServletRequest req) {
		// 현재 로그인한 사용자의 아이디
		String userId = ((MemberVO) req.getSession().getAttribute("loginMember")).getUserId();
		logger.info(userId + "가 강의 추천 게시판의 게시글 스크랩 한 마이페이지의 게시글로 이동");
		
	    Map<String, Object> resultMap = null;
	    
	    String resultPage = null;
	    
	    // 페이지 번호가 1이상이 되도록 설정
	 	if (pageNo <= 0) {
	 		pageNo = 1;
	 	}
	 		
	 	// 서비스단 호출(getMyScrapLectureList() 메서드 호출)
		try {
			resultMap = myPageService.getMyScrapLectureList(pageNo, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 게시글 목록 가져오기
		List<ScrapVO> lectureScrapList = (List<ScrapVO>) resultMap.get("lectureScrapList");
		
		// 게시글 자체를 바인딩
		model.addAttribute("lectureScrapList", (List<ScrapVO>) resultMap.get("lectureScrapList"));
		// 페이징 정보를 바인딩
		model.addAttribute("pagingInfo", (PagingInfo) resultMap.get("pagingInfo"));
	}
	
	/**
	 * @methodName : myLikeLectureList
	 * @author : kde
	 * @date : 2024.06.12
	 * @param : @RequestParam(value = "pageNo", defaultValue = "1") int pageNo
	 * - 뷰단에서 쿼리스트링으로 넘겨 페이징 하기 위한 pageNo
	 * @param : Model model - 컨트롤러에서 뷰로 데이터를 전달
	 * @param : HttpServletRequest req - 로그인한 사용자의 아이디를 가져오기 위해 사용
	 * @return : void
	 * @description : 유저가 강의 추천 게시판의 게시글에 좋아요 누른 게시글로 이동 + 페이징
	 */
	@GetMapping("/myLikeLectureList")
	public void myLikeLectureList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			Model model, HttpServletRequest req) {
		// 현재 로그인한 사용자의 아이디
		String userId = ((MemberVO) req.getSession().getAttribute("loginMember")).getUserId();
		logger.info(userId + "가 강의 추천 게시판의 게시글 좋아요 한 마이페이지의 게시글로 이동");
		
	    Map<String, Object> resultMap = null;
	    
	    String resultPage = null;
	    
	    // 페이지 번호가 1이상이 되도록 설정
	 	if (pageNo <= 0) {
	 		pageNo = 1;
	 	}
	 		
	 	// 서비스단 호출(getMyLikedLectureList() 메서드 호출)
		try {
			resultMap = myPageService.getMyLikedLectureList(pageNo, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 게시글 목록 가져오기
		List<LectureLikeVO> lectureLikeList = (List<LectureLikeVO>) resultMap.get("lectureLikeList");
		
		// 게시글 자체를 바인딩
		model.addAttribute("lectureLikeList", (List<LectureLikeVO>) resultMap.get("lectureLikeList"));
		// 페이징 정보를 바인딩
		model.addAttribute("pagingInfo", (PagingInfo) resultMap.get("pagingInfo"));
	}
	
	
	/**
	 * @author : mji
	 * @param model
	 * @param req
	 */
	@GetMapping("/myAlgList")
	public void getMyAlgList(Model model, HttpServletRequest req) {

		String userId = ((MemberVO) req.getSession().getAttribute("loginMember")).getUserId();
		logger.info(userId + "가 작성한 스터디 모임글 페이지로 이동");

		List<AlgDetailDTO> result = null;

		try {

			result = aService.getListDetail(userId);
			
			System.out.println("!!!!!!!!!!"+result);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		model.addAttribute("algDetailList", result);

//		model.addAttribute("studyList", (List<StudyBoardVO>) result.get("studyList"));
//		model.addAttribute("stuStackList", (List<StuStackDTO>) result.get("stuStackList"));
//		model.addAttribute("stuApplyList", (List<StudyApplyVO>) result.get("stuApplyList"));
	}
	


	/**
	 * @methodName : myLikeLectureList
	 * @author : kde
	 * @date : 2024.06.12
	 * @param : @RequestParam(value = "pageNo", defaultValue = "1") int pageNo
	 * - 뷰단에서 쿼리스트링으로 넘겨 페이징 하기 위한 pageNo
	 * @param : Model model - 컨트롤러에서 뷰로 데이터를 전달
	 * @param : HttpServletRequest req - 로그인한 사용자의 아이디를 가져오기 위해 사용
	 * @return : void
	 * @description : 유저가 신고한 게시글 확인하는 마이페이지의 게시글로 이동 + 페이징
	 */
	@GetMapping("/myReportList")
	public void myReportList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			Model model, HttpServletRequest req) {
		// 현재 로그인한 사용자의 아이디
		String userId = ((MemberVO) req.getSession().getAttribute("loginMember")).getUserId();
		logger.info(userId + "가 신고한 게시글 확인하러 마이페이지의 게시글로 이동");
		
		Map<String, Object> resultMap = null;
		
		String resultPage = null;
		
	    // 페이지 번호가 1이상이 되도록 설정
	 	if (pageNo <= 0) {
	 		pageNo = 1;
	 	}
	 	
	 	// 서비스단 호출(getMyPageReportList() 메서드 호출)
		try {
			resultMap = myPageService.getMyPageReportList(pageNo, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 게시글 목록 가져오기
		List<ReportVO> reportList = (List<ReportVO>) resultMap.get("reportList");
		
		// 게시글 자체를 바인딩
		model.addAttribute("reportList", (List<ReportVO>) resultMap.get("reportList"));
		// 페이징 정보를 바인딩
		model.addAttribute("pagingInfo", (PagingInfo) resultMap.get("pagingInfo"));
	}
	
	/**
	 * @methodName : myLikeLectureList
	 * @author : kde
	 * @date : 2024.06.12
	 * @param : @RequestParam(value = "pageNo", defaultValue = "1") int pageNo
	 * - 뷰단에서 쿼리스트링으로 넘겨 페이징 하기 위한 pageNo
	 * @param : Model model - 컨트롤러에서 뷰로 데이터를 전달
	 * @param : HttpServletRequest req - 로그인한 사용자의 아이디를 가져오기 위해 사용
	 * @return : void
	 * @description : 유저가 문의한 게시글 확인하는 마이페이지의 게시글로 이동 + 페이징
	 */
	@GetMapping("/myQnAList")
	public void myQnAList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			Model model, HttpServletRequest req) {
		// 현재 로그인한 사용자의 아이디
		String userId = ((MemberVO) req.getSession().getAttribute("loginMember")).getUserId();
		logger.info(userId + "가 문의한 게시글 확인하러 마이페이지의 게시글로 이동");
		
		Map<String, Object> resultMap = null;
		
		String resultPage = null;
		
	    // 페이지 번호가 1이상이 되도록 설정
	 	if (pageNo <= 0) {
	 		pageNo = 1;
	 	}
	 	
	 	// 서비스단 호출(getMyLikedLectureList() 메서드 호출)
		try {
			resultMap = myPageService.getMyPageQnAList(pageNo, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 게시글 목록 가져오기
		List<QnaBoardVO> myPageQnAList = (List<QnaBoardVO>) resultMap.get("myPageQnAList");
		
		// 게시글 자체를 바인딩
		model.addAttribute("myPageQnAList", (List<QnaBoardVO>) resultMap.get("myPageQnAList"));
		// 페이징 정보를 바인딩
		model.addAttribute("pagingInfo", (PagingInfo) resultMap.get("pagingInfo"));
	}
	
	/**
	 * @methodName : getRepliesWithBoardInfo
	 * @author : kde
	 * @date : 2024.06.15
	 * @param : @RequestParam("bno") int bno - 강의 추천 게시판의 글 번호
	 * @return : RedirectView
	 * @description : 마이페이지에서 유저가 강의 추천 게시글에 남긴 댓글 클릭시 그 게시글로 이동
	 */
	@GetMapping("/goMyReplyList")
    public RedirectView getRepliesWithBoardInfo(@RequestParam("bno") int bno) throws Exception {
        
	    String redirectUrl = "/lecture/viewBoard?lecNo=" + bno;
	    
	    return new RedirectView(redirectUrl);
    }
	
    /**
     * @methodName : goScrapList
     * @author : kde
     * @date : 2024.06.15
     * @param : @RequestParam("lecNo") int lecNo - 넘어가야할 게시글 번호
     * @return : RedirectView
     * @description : 마이페이지의 스크랩 확인 -> 유저가 스크랩 눌렀던 게시글로 이동
     */
	@GetMapping("/goMyScrapList")
	public RedirectView goScrapList(@RequestParam("scrapBoard") int scrapBoard) throws Exception {
		
	    String redirectUrl = "/lecture/viewBoard?lecNo=" + scrapBoard;
	    
	    return new RedirectView(redirectUrl);
	}
	
    /**
     * @methodName : goReportList
     * @author : kde
     * @date : 2024.06.15
     * @param : @RequestParam("lecNo") int lecNo - 넘어가야할 게시글 번호
     * @return : RedirectView
     * @description : 마이페이지의 좋아요 확인 -> 유저가 좋아요 눌렀던 게시글로 이동
     */
	@GetMapping("/goMyLikeList")
	public RedirectView goLikeList(@RequestParam("lecNo") int lecNo) throws Exception {
		
	    String redirectUrl = "/lecture/viewBoard?lecNo=" + lecNo;
	    
	    return new RedirectView(redirectUrl);
	}
	
    /**
     * @methodName : goReportList
     * @author : kde
     * @date : 2024.06.14
     * @param : @RequestParam("btypeNo") int btypeNo
     * @param : @RequestParam("reportNo") int reportNo
     * @return : RedirectView
     * @description : 마이페이지의 신고 게시글 -> 게시판마다의 유저가 신고한 게시글로 이동
     */
    @GetMapping("/goMyReportList")
    public RedirectView goReportList(@RequestParam("btypeNo") int btypeNo, @RequestParam("reportNo") int reportNo) throws Exception {
        
    	// btypeNo와 boardNo를 사용하여 게시글 정보 가져오기
        List<ReportVO> board = myPageService.getReportNO(btypeNo, reportNo);

        // btypeNo에 따라 다른 상세 페이지 URL을 생성
        String redirectUrl = "";
        switch (btypeNo) {
            case 1:
                redirectUrl = "/lecture/viewBoard?lecNo=" + reportNo; // 강의 추천 게시판
                break;
            case 2:
                redirectUrl = "/study/viewStudyBoard?stuNo=" + reportNo; // 스터디 모임
                break;
            case 4:
                redirectUrl = "/algorithm/algDetail?boardNo=" + reportNo; // 알고리즘
                break;
            default:
                redirectUrl = "/errorPage"; // 예외 처리
                break;
        }

        // 리디렉션 URL을 반환
        return new RedirectView(redirectUrl);
    }
}
