package com.dodeveloper.message.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dodeveloper.commons.interceptor.SessionNames;
import com.dodeveloper.etc.FileProcessing;
import com.dodeveloper.etc.UploadPaths;
import com.dodeveloper.message.service.MessageService;
import com.dodeveloper.message.vodto.MessageVO;
import com.dodeveloper.message.vodto.SendMessageDTO;
import com.dodeveloper.message.vodto.MessageFileDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/message")
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("message/home");

		return modelAndView;
	}

	@RequestMapping(value = "/{receiver}/received/{startPoint}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> showReceivedMessages(@PathVariable("receiver") String receiver,
			@PathVariable("startPoint") int startPoint) throws Exception {

		List<MessageVO> receivedMessages = messageService.getReceivedMessages(receiver, startPoint, 30);
		return ResponseEntity.ok(gson.toJson(receivedMessages));
	}

	@RequestMapping(value = "/{writer}/sent/{startPoint}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> showSentMessages(@PathVariable("writer") String writer,
			@PathVariable("startPoint") int startPoint) throws Exception {

		List<MessageVO> sentMessages = messageService.getSentMessages(writer, startPoint, 30);
		return ResponseEntity.ok(gson.toJson(sentMessages));
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> sendMessage(@RequestBody SendMessageDTO sendMessageDTO, HttpServletRequest request)
			throws Exception {

		LinkedList<MessageFileDTO> uploadedFiles = new LinkedList<MessageFileDTO>();
		String tempUploadPath = request.getSession().getServletContext().getRealPath(UploadPaths.tempUploadPath);
		String realUploadPath = request.getSession().getServletContext().getRealPath(UploadPaths.realUploadPath);

		for (MessageFileDTO file : sendMessageDTO.getFileList()) {
			String movedFileName = FileProcessing.moveFile(file.getUploadName(), tempUploadPath,
					realUploadPath);
			
			uploadedFiles.add(new MessageFileDTO(-1, movedFileName, file.getExt(), file.getOriginalName()));
		}
		
		try {
			sendMessageDTO.setFileList(uploadedFiles);
			messageService.sendMessage(sendMessageDTO);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/file", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<MessageFileDTO> uploadFile(@RequestBody MultipartFile file, HttpServletRequest request) {

		logger.info("file upload start");
		String uploadName = null;
		String originalName = file.getOriginalFilename();
		String ext = FileProcessing.getExtension(originalName);
		
		if (originalName.length() > 50) {
			return new ResponseEntity<MessageFileDTO>(HttpStatus.BAD_REQUEST);
		}

		String uploadPath = request.getSession().getServletContext().getRealPath(UploadPaths.tempUploadPath);
		logger.info("file upload path is : " + uploadPath);

		try {
			uploadName = FileProcessing.fileUpload(file, uploadPath);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<MessageFileDTO>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<MessageFileDTO>(new MessageFileDTO(-1, uploadName, ext, originalName), HttpStatus.OK);
	}
}
