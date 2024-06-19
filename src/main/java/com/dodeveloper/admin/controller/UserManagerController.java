package com.dodeveloper.admin.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodeveloper.admin.dto.UserDTO;
import com.dodeveloper.admin.dto.UserStatusDTO;
import com.dodeveloper.admin.service.AdminService;
import com.dodeveloper.member.vo.MemberVO;

@Controller
@RequestMapping("/admin")
public class UserManagerController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService aService;

	// 회원 전체 조회하는 메서드
	@RequestMapping("/userList")
	public void userList(Model model) throws Exception {
	//	logger.info("userList 페이지 호출");

		List<UserDTO> returnMap = null;

		String resultPage = null;

		returnMap = aService.getAllUser();
		model.addAttribute("userList", returnMap);

		resultPage = "redirect:/adminUserlist/userList";
	}

	@PostMapping("/status")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateUserStatus(@RequestParam("newStatus") String newStatus,
	                                                           @RequestParam("userId") String userId) {
	//	System.out.println("controller단 넘겨받은 변경된 상태 : " + newStatus);
	//	System.out.println("controller단 넘겨받은 변경된 유저 : " + userId);
		try {
            boolean success = aService.modifyUserStatus(userId, newStatus);
            Map<String, Object> response = new HashMap<>();
            response.put("success", success);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
	}

}
