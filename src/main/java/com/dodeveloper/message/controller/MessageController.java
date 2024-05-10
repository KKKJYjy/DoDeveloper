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
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/message")
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	RestTemplate restTemplate = new RestTemplate();

	private MessageService messageService;
	private FileProcessing fileProcessing;
	
	/*
	 * TODO : 메세지 발송인과 로그인 된 회원의 id가 일치하는지 확인하는 작업
	 * 만약 메세지 발송인과 로그인 된 회원의 id가 일치하지 않는다면,
	 * 악의적으로 변조된 패킷을 받았거나, 뭔가 예상치 못한 문제가 생긴 것이다.
	 * 이런 예외를 따로 처리해주어야 한다.
	 */
	
	@Autowired
	public MessageController(MessageService messageService, FileProcessing fileProcessing) {
		this.messageService = messageService;
		this.fileProcessing = fileProcessing;
	}
	
	
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
		int receivedMessageCnt = messageService.getReceivedMessageCnt(receiver);
		
		JsonObject jsonToSend = new JsonObject();
		
		jsonToSend.addProperty("messageCnt", receivedMessageCnt);
		jsonToSend.add("messages", gson.toJsonTree(receivedMessages));
		
		return ResponseEntity.ok(gson.toJson(jsonToSend));
	}

	
	@RequestMapping(value = "/{writer}/sent/{startPoint}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> showSentMessages(@PathVariable("writer") String writer,
			@PathVariable("startPoint") int startPoint) throws Exception {

		List<MessageVO> sentMessages = messageService.getSentMessages(writer, startPoint, 30);
		int sentMessageCnt = messageService.getSentMessageCnt(writer);
		
		JsonObject jsonToSend = new JsonObject();
		
		jsonToSend.addProperty("messageCnt", sentMessageCnt);
		jsonToSend.add("messages", gson.toJsonTree(sentMessages));
		
		return ResponseEntity.ok(gson.toJson(jsonToSend));
	}

	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> sendMessage(@RequestBody SendMessageDTO sendMessageDTO)
			throws Exception {

		LinkedList<MessageFileDTO> uploadedFiles = new LinkedList<MessageFileDTO>();
	
		try {
			for (MessageFileDTO file : sendMessageDTO.getFileList()) {
				String movedFileName = fileProcessing.saveTempFilePermanantly(file.getUploadName());
				
				uploadedFiles.add(new MessageFileDTO(-1, movedFileName, fileProcessing.getExtension(movedFileName), 
						fileProcessing.getOriginalName(movedFileName)));
			}
			
			sendMessageDTO.setFileList(uploadedFiles);
			messageService.sendMessage(sendMessageDTO);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	
	@RequestMapping(value = "/file", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file) {

		logger.info("file upload start");
		String uploadName = null;
		
		if (file.getOriginalFilename().length() > 50) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		try {
			uploadName = fileProcessing.uploadFileTemporarily(file);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		if(uploadName == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);			
		}
		
		return new ResponseEntity<String>(uploadName, HttpStatus.OK);
	}
}
