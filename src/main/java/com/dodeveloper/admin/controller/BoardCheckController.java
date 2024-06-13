package com.dodeveloper.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.dodeveloper.admin.service.AdminBoardService;
import com.dodeveloper.admin.service.AdminService;
import com.dodeveloper.admin.vo.ReportVO;
import com.dodeveloper.study.vodto.StudyBoardVO;

@Controller
public class BoardCheckController {
	
	@Autowired
	private AdminBoardService boardService;

	@GetMapping("/viewBoardDetail")
    public RedirectView viewBoardDetail(@RequestParam("btypeNo") int btypeNo, @RequestParam("boardNo") int boardNo) throws Exception {
        // btypeNo와 boardNo를 사용하여 게시글 정보를 가져옴.
		List<ReportVO> board = boardService.getReportNO(btypeNo, boardNo);

        // btypeNo에 따라 다른 상세 페이지 URL을 생성.
        String redirectUrl = "";
        switch (btypeNo) {
            case 1:
                redirectUrl = "/lecture/viewBoard?lecNo=" + boardNo;
                break;
            case 2:
                redirectUrl = "/study/viewStudyBoard?stuNo=" + boardNo;
                break;
            case 4:
                redirectUrl = "/algorithm/algDetail?boardNo=" + boardNo;
                break;
            default:
                redirectUrl = "/errorPage"; // 예외 처리
                break;
        }

        // 리디렉션 URL을 반환합니다.
        return new RedirectView(redirectUrl);
    }
}