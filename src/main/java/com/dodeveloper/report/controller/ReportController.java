package com.dodeveloper.report.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodeveloper.algorithm.service.AlgService;
import com.dodeveloper.report.dto.ReportDTO;
import com.dodeveloper.report.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
    
    @Autowired
    HttpSession ses;
    
   
    
    @Autowired
    AlgService aService;
    
    @SuppressWarnings("null")
    @RequestMapping("insertReport")
    public void insertReprot(@RequestParam Map<String, Object> map ) {
	
	int boardNo = Integer.parseInt((String)map.get("boardNo"));
	int btypeNo = Integer.parseInt((String)map.get("btypeNo"));
	
	String writer = (String)map.get("writer");
	String reporter = (String)map.get("reporter");
	String reoprtReason = (String)map.get("reportReason");
	
	System.out.println(writer);
	System.out.println(reporter);
	System.out.println(reoprtReason);
	System.out.println(boardNo);
	System.out.println(btypeNo);
	
	
	ReportDTO reportDTO = new ReportDTO(btypeNo, reoprtReason, boardNo, writer, null, reoprtReason, reporter, reoprtReason, btypeNo);
	reportDTO.setBoardNo(boardNo);
	reportDTO.setBtypeNo(btypeNo);
	reportDTO.setReporter(reporter);
	reportDTO.setWriter(writer);
	reportDTO.setReportReason(reoprtReason);
	
	aService.insertReport(reportDTO);
	
	
    }
    
}