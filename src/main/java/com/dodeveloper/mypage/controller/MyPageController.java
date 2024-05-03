package com.dodeveloper.mypage.controller;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodeveloper.mypage.dto.ProfileDTO;
import com.dodeveloper.mypage.service.MyPageService;
import com.dodeveloper.mypage.vo.ProfileVO;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyPageController.class);

	@Autowired
	private MyPageService myPageService;
	
	@GetMapping("/myProfile")
	public void myProfileGet() {
		logger.info("myProfile View.");
	}
	
	@ResponseBody
	@RequestMapping(value = "/setProfileImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<String, Object>> setProfileImage(@RequestBody Map<String, Object> param) throws Exception {
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
		MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON_UTF8, utf8);
		headers.setContentType(mediaType);
		
		return ResponseEntity.ok().headers(headers).body(returnMap);
	}

	@ResponseBody
	@RequestMapping(value = "/getProfileImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<String, Object>> getProfileImage(@RequestBody Map<String, String> param) throws Exception {

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
	public ResponseEntity<Map<String, Object>> removeProfileImage(@RequestBody Map<String, Object> param) throws Exception {
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
		MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON_UTF8, utf8);
		headers.setContentType(mediaType);
		
		return ResponseEntity.ok().headers(headers).body(returnMap);
	}
}