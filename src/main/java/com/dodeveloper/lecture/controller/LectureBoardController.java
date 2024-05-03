package com.dodeveloper.lecture.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodeveloper.lecture.service.LectureBoardService;
import com.dodeveloper.lecture.vodto.LectureBoardVO;

@Controller // �Ʒ��� Ŭ������ ��Ʈ�ѷ� ��ü���� ���
@RequestMapping("/lecture") // "/lecture"�� GET������� ��û�� �� �Ʒ��� Ŭ������ ���۵ǵ��� ������
public class LectureBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(LectureBoardController.class);
	
	@Autowired
	private LectureBoardService lService; // ������ �����̳ʿ��� LectureService ��ü�� ã�� ����
	
	/**
	 * @methodName : listAllGet
	 * @author : kde
	 * @date : 2024.05.02
	 * @param : Model model : ��ü �Խñ��� ���ε� �� �� �Խ��� ��ü �������� �̵���Ű�� ��ü
	 * @return : void
	 * @throws Exception 
	 * @description : ���� ��õ �Խ��� ��ü �� ��ȸ�� ����ϴ� controller �޼���
	 */
	@GetMapping(value = "/listAll")
	public void listAllBoardGet(Model model) throws Exception {
		logger.info("���� ��õ �Խ��� ��ü �Խñ� ��ȸ : listAll View");
		
		// ���񽺴� ȣ�� (getListAllBoard() �޼��� ȣ��)
		List<LectureBoardVO> lectureBoardList = lService.getListAllBoard();
		
		// ���ε�
		model.addAttribute("lectureBoardList", lectureBoardList);
	}
}
