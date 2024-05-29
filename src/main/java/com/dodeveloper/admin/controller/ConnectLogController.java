package com.dodeveloper.admin.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodeveloper.admin.service.AdminService;
import com.dodeveloper.admin.vo.ConnectLogVO;
import com.dodeveloper.admin.vo.CountUriVO;

@Controller
@RequestMapping("/admin")
public class ConnectLogController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService aService;

    @RequestMapping("/totalLog")
    public ResponseEntity connectLog(Model model) {
	logger.info("totalLog 페이지 호출");

	List<ConnectLogVO> connectLog = null;
	List<CountUriVO> uriCount = null;
	Map<String, Object> logData = new HashMap<>();
	ResponseEntity<Map<String, Object>> result = null;

	try {
	    connectLog = aService.getDateLog();
	    uriCount = aService.getPageLogCount();

	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	logData.put("connectLog", connectLog);
	logData.put("uriCount", uriCount);

	model.addAttribute("logData", logData);

	result = new ResponseEntity<Map<String, Object>>(logData, HttpStatus.OK);

	logger.info("json 변환");
	return result;

    }
}
