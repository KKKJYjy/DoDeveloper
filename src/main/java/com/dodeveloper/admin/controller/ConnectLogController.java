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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodeveloper.admin.service.AdminService;
import com.dodeveloper.admin.vo.ConnectLogVO;
import com.dodeveloper.admin.vo.CountUriVO;

@Controller
@RequestMapping("/admin")
public class ConnectLogController {
	private static final Logger logger = LoggerFactory.getLogger(ConnectLogController.class);

	@Autowired
	private AdminService aService;

	@GetMapping(value = "/totalLog")
	public void showTotalLog() {
			
	}

	@GetMapping(value = "/getLog", produces = "application/json; charset=utf-8")
	public @ResponseBody ResponseEntity<Map<String, Object>> connectLog(@RequestParam("month") int month) {
		



		Map<String, Object> logData = new HashMap<String, Object>();
		try {
			List<ConnectLogVO> connectLog = aService.getDateLog(month);

			logData.put("connectLog", connectLog);

			// System.out.print(logData);
			return new ResponseEntity<Map<String, Object>>(logData, HttpStatus.OK);

		} catch (Exception e) {
			// logger.error("Error retrieving log data", e);
			return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getUri", produces = "application/json; charset=utf-8")
	public @ResponseBody ResponseEntity<Map<String, Object>> getUri() {
		// logger.info("uriData 호출됨");

		Map<String, Object> uriData = new HashMap<String, Object>();
		try {
			List<CountUriVO> uriCount = aService.getPageLogCount();
			uriData.put("uriCount", uriCount);
			// System.out.print(uriData);
			return new ResponseEntity<Map<String, Object>>(uriData, HttpStatus.OK);

		} catch (Exception e) {
			// logger.error("초기 데이터를 가져오는 중 오류 발생", e);
			return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
	}

}
