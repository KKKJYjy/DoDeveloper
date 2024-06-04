package com.dodeveloper.report.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/report")
public class ReportController {
    
    @Autowired
    HttpSession ses;
    
    @RequestMapping("insertReport")
    public void insertReprot(@RequestParam Map<String, Object> map) {
	System.out.println((String)map.get("writer"));
	System.out.println((String)map.get("reporter"));
	System.out.println((String)map.get("boardNo"));
	System.out.println((String)map.get("btypeNo"));
	System.out.println((String)map.get("reportReason"));
    }
    
}