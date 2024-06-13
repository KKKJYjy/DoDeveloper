package com.dodeveloper.message.controller;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.dodeveloper.member.vo.MemberVO;
import com.dodeveloper.message.service.MessageService;
import com.dodeveloper.message.vodto.MessageVO;
import com.dodeveloper.message.vodto.SendMessageDTO;
import com.dodeveloper.message.vodto.MessageBoxVO;
import com.dodeveloper.message.vodto.MessageFileDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	private static final Gson gson = new GsonBuilder().create();

	RestTemplate restTemplate = new RestTemplate();

	private MessageService messageService;
	private FileProcessing fileProcessing;

	@Autowired
	public MessageController(MessageService messageService, FileProcessing fileProcessing) {
		this.messageService = messageService;
		this.fileProcessing = fileProcessing;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("message/home");
		
		MemberVO loginMember = (MemberVO) session.getAttribute(SessionNames.LOGIN_MEMBER);
		if(loginMember != null) {
			modelAndView.addObject("loginUser", loginMember.getUserId());
		}
		
		session.setAttribute(SessionNames.UNREAD_MESSAGE_CNT, 0);
		
		return modelAndView;
	}

	@RequestMapping(value = "/{userId}/{messageNo}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> getMessage(@PathVariable("userId") String userId,
			@PathVariable("messageNo") int messageNo, HttpSession session) throws Exception {
		
		if(authorityCheck(userId, session)== false) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		MessageVO message = messageService.getMessageByNo(messageNo);
		List<MessageFileDTO> messageFiles = messageService.getMessageFilesByMessageNo(messageNo);
		List<MessageBoxVO> messageBoxs = messageService.getReceivedLogsOfMessage(messageNo);
		List<String> receivers = new LinkedList<String>();

		System.out.println(messageBoxs);
		for (MessageBoxVO messageBox : messageBoxs) {
			receivers.add(messageBox.getReceiver());
		}

		JsonObject jsonToSend = new JsonObject();

		jsonToSend.add("message", gson.toJsonTree(message));
		jsonToSend.add("messageFiles", gson.toJsonTree(messageFiles));
		jsonToSend.add("receivers", gson.toJsonTree(receivers));

		return ResponseEntity.ok(gson.toJson(jsonToSend));
	}

	@RequestMapping(value = "/{receiver}/received/{startPoint}/{amountToShow}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> showReceivedMessages(@PathVariable("receiver") String receiver,
			@PathVariable("startPoint") int startPoint, @PathVariable(required = false) Optional<Integer> amountToShow, HttpSession session)
			throws Exception {
		
		if(authorityCheck(receiver, session)== false) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		List<MessageVO> receivedMessages = messageService.getReceivedMessages(receiver, startPoint,
				amountToShow.orElse(30));
		int receivedMessageCnt = messageService.getReceivedMessageCnt(receiver);

		JsonObject jsonToSend = new JsonObject();

		jsonToSend.addProperty("messageCnt", receivedMessageCnt);
		jsonToSend.add("messages", gson.toJsonTree(receivedMessages));

		return ResponseEntity.ok(gson.toJson(jsonToSend));
	}

	@RequestMapping(value = "/{receiver}/received/{startPoint}/{amountToShow}/title/{title}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> searchReceivedMessagesByTitle(@PathVariable("receiver") String receiver,
			@PathVariable("startPoint") int startPoint, @PathVariable(required = false) Optional<Integer> amountToShow,
			@PathVariable("title") String title, HttpSession session) throws Exception {

		if(authorityCheck(receiver, session)== false) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		if (title == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		List<MessageVO> receivedMessages = messageService.searchReceivedMessagesByTitle(receiver, title, startPoint,
				amountToShow.orElse(30));
		int receivedMessageCnt = messageService.getReceivedMessagesCntByTitle(receiver, title);

		JsonObject jsonToSend = new JsonObject();

		jsonToSend.addProperty("messageCnt", receivedMessageCnt);
		jsonToSend.add("messages", gson.toJsonTree(receivedMessages));

		return ResponseEntity.ok(gson.toJson(jsonToSend));
	}

	@RequestMapping(value = "/{receiver}/received/{startPoint}/{amountToShow}/content/{content}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> searchReceivedMessagesByContent(@PathVariable("receiver") String receiver,
			@PathVariable("startPoint") int startPoint, @PathVariable(required = false) Optional<Integer> amountToShow,
			@PathVariable("content") String content, HttpSession session) throws Exception {

		if(authorityCheck(receiver, session)== false) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		if (content == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		List<MessageVO> receivedMessages = messageService.searchReceivedMessagesByContent(receiver, content, startPoint,
				amountToShow.orElse(30));
		int receivedMessageCnt = messageService.getReceivedMessagesCntByContent(receiver, content);

		JsonObject jsonToSend = new JsonObject();

		jsonToSend.addProperty("messageCnt", receivedMessageCnt);
		jsonToSend.add("messages", gson.toJsonTree(receivedMessages));

		return ResponseEntity.ok(gson.toJson(jsonToSend));
	}

	@RequestMapping(value = "/{receiver}/received/{startPoint}/{amountToShow}/writer/{writer}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> searchReceivedMessagesByWriter(@PathVariable("receiver") String receiver,
			@PathVariable("startPoint") int startPoint, @PathVariable(required = false) Optional<Integer> amountToShow,
			@PathVariable("writer") String writer, HttpSession session) throws Exception {

		if(authorityCheck(receiver, session)== false) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}

		
		if (writer == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		List<MessageVO> receivedMessages = messageService.searchReceivedMessagesByWriter(receiver, writer, startPoint,
				amountToShow.orElse(30));
		int receivedMessageCnt = messageService.getReceivedMessagesCntByWriter(receiver, writer);

		JsonObject jsonToSend = new JsonObject();

		jsonToSend.addProperty("messageCnt", receivedMessageCnt);
		jsonToSend.add("messages", gson.toJsonTree(receivedMessages));

		return ResponseEntity.ok(gson.toJson(jsonToSend));
	}

	@RequestMapping(value = "/{writer}/sent/{startPoint}/{amountToShow}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> showSentMessages(@PathVariable("writer") String writer,
			@PathVariable("startPoint") int startPoint, @PathVariable(required = false) Optional<Integer> amountToShow, HttpSession session)
			throws Exception {

		if(authorityCheck(writer, session)== false) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		List<MessageVO> sentMessages = messageService.getSentMessages(writer, startPoint, amountToShow.orElse(30));
		int sentMessageCnt = messageService.getSentMessageCnt(writer);

		JsonObject jsonToSend = new JsonObject();

		jsonToSend.addProperty("messageCnt", sentMessageCnt);
		jsonToSend.add("messages", gson.toJsonTree(sentMessages));

		return ResponseEntity.ok(gson.toJson(jsonToSend));
	}

	@RequestMapping(value = "/{writer}/sent/{startPoint}/{amountToShow}/title/{title}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> searchSentMessagesByTitle(@PathVariable("writer") String writer,
			@PathVariable("startPoint") int startPoint, @PathVariable(required = false) Optional<Integer> amountToShow,
			@PathVariable("title") String title, HttpSession session) throws Exception {

		if(authorityCheck(writer, session)== false) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		if (title == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		List<MessageVO> sentMessages = messageService.searchWrittenMessagesByTitle(writer, title, startPoint,
				amountToShow.orElse(30));
		
		int sentMessageCnt = messageService.getSentMessagesCntByTitle(writer, title);
		
		JsonObject jsonToSend = new JsonObject();

		jsonToSend.addProperty("messageCnt", sentMessageCnt);
		jsonToSend.add("messages", gson.toJsonTree(sentMessages));

		return ResponseEntity.ok(gson.toJson(jsonToSend));
	}

	@RequestMapping(value = "/{writer}/sent/{startPoint}/{amountToShow}/content/{content}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> searchSentMessagesByContent(@PathVariable("writer") String writer,
			@PathVariable("startPoint") int startPoint, @PathVariable(required = false) Optional<Integer> amountToShow,
			@PathVariable("content") String content, HttpSession session) throws Exception {

		if(authorityCheck(writer, session)== false) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (content == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		List<MessageVO> sentMessages = messageService.searchWrittenMessagesByContent(writer, content, startPoint,
				amountToShow.orElse(30));
		int sentMessageCnt = messageService.getSentMessagesCntByContent(writer, content);

		JsonObject jsonToSend = new JsonObject();

		jsonToSend.addProperty("messageCnt", sentMessageCnt);
		jsonToSend.add("messages", gson.toJsonTree(sentMessages));

		return ResponseEntity.ok(gson.toJson(jsonToSend));
	}

	@RequestMapping(value = "/{writer}/sent/{startPoint}/{amountToShow}/receiver/{receiver}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> searchSentMessagesByReceiver(@PathVariable("writer") String writer,
			@PathVariable("startPoint") int startPoint, @PathVariable(required = false) Optional<Integer> amountToShow,
			@PathVariable("receiver") String receiver, HttpSession session) throws Exception {

		if(authorityCheck(writer, session)== false) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (receiver == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		List<MessageVO> receivedMessages = messageService.searchReceivedMessagesByWriter(receiver, writer, startPoint,
				amountToShow.orElse(30));
		int receivedMessageCnt = messageService.getReceivedMessagesCntByWriter(receiver, writer);
		
		JsonObject jsonToSend = new JsonObject();

		jsonToSend.addProperty("messageCnt", receivedMessageCnt);
		jsonToSend.add("messages", gson.toJsonTree(receivedMessages));

		return ResponseEntity.ok(gson.toJson(jsonToSend));
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> sendMessage(@RequestBody SendMessageDTO sendMessageDTO, HttpSession session) throws Exception {
		String writer = sendMessageDTO.getMessage().getWriter();
		if(authorityCheck(writer, session) == false) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}

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
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/file", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file) {

		logger.info("file upload start : " + file.getOriginalFilename());
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

		if (uploadName == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>(uploadName, HttpStatus.OK);
	}

	@RequestMapping(value = "/file/{fileName}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadFile(@PathVariable("fileName") String fileName) {

		logger.info("file download start: " + fileProcessing.getPermaUploadPath() + File.separator + fileName);

		File downloadDir = new File(fileProcessing.getPermaUploadPath());
		FileFilter fileFilter = new WildcardFileFilter(fileName + ".*");
		File[] sameNamedFiles = downloadDir.listFiles(fileFilter);
		File sameNamedFileButHasNoExt = new File(fileProcessing.getPermaUploadPath() + File.separator + fileName);

		if (sameNamedFiles.length <= 0 && !sameNamedFileButHasNoExt.exists()) {
			logger.info("file to download is not found!1");
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}

		File downloadFile = sameNamedFileButHasNoExt;
		if (!downloadFile.exists()) {
			downloadFile = sameNamedFiles[0];
			if (!downloadFile.exists()) {
				logger.info("file to download is not found!2");
				return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
			}
		}

		try {
			String contentType = new Tika().detect(downloadFile);
			byte[] fileByteArray = Files.readAllBytes(downloadFile.toPath());

			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.valueOf(contentType));
			header.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(downloadFile.getName(), "UTF-8"));

			return new ResponseEntity<byte[]>(fileByteArray, header, HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<byte[]>(HttpStatus.CONFLICT);
		}
	}

	private boolean authorityCheck(String userId, HttpSession session) {
		System.out.println("세션 아이디: " + session.getId());
		MemberVO loginMember = (MemberVO) session.getAttribute(SessionNames.LOGIN_MEMBER);
		
		return loginMember != null && userId.equals(loginMember.getUserId());
	}
	
}
