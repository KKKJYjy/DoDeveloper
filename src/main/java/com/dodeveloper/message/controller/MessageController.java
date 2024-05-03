package com.dodeveloper.message.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.dodeveloper.message.service.MessageService;
import com.dodeveloper.message.vodto.MessageBoxVO;
import com.dodeveloper.message.vodto.MessageVO;
import com.dodeveloper.message.vodto.SendMessageDTO;
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
	
	@RequestMapping(value = "/{receiver}/received/{startPoint}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public ResponseEntity<String> showReceivedMessages(@PathVariable("receiver") String receiver, 
    		@PathVariable("startPoint") int startPoint) throws Exception {
		
		List<MessageVO> receivedMessages = messageService.getReceivedMessages(receiver, startPoint, 30);
        return ResponseEntity.ok(gson.toJson(receivedMessages));
    }
	
	@RequestMapping(value = "/{writer}/sent/{startPoint}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public ResponseEntity<String> showSentMessages(@PathVariable("writer") String writer, 
    		@PathVariable("startPoint") int startPoint) throws Exception {
		
		List<MessageVO> sentMessages = messageService.getSentMessages(writer, startPoint, 30);
        return ResponseEntity.ok(gson.toJson(sentMessages));
    }
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
    public ResponseEntity<String> sendMessage(@RequestBody SendMessageDTO sendMessageDTO) throws Exception {
		
		try{
			messageService.sendMessage(sendMessageDTO.getMessage(), sendMessageDTO.getReceiverIdList());
		}catch (Exception e) {
			return new ResponseEntity<String>("fail" , HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<String>("success" , HttpStatus.OK);
    }
}
