package com.dodeveloper.etc;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailManager {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String receiverEmail, String title, String body) throws MessagingException, UnsupportedEncodingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		
		messageHelper.setFrom("ddev.no-reply@gmail.com", "DDev");
		messageHelper.setSubject(title);
		messageHelper.setTo(receiverEmail);
		messageHelper.setText(body);
		
		javaMailSender.send(mimeMessage);
	}
	
	public void sendValidationCode(String receiverEmail, String code) throws MessagingException, UnsupportedEncodingException {
		String title = "회원 가입용 이메일 인증 코드입니다.";
		String body = "DDev 회원가입용 이메일 인증 코드입니다.\n" + "코드 : " + code + "\n만약 회원가입을 요청하신 적이 없으시다면 본 메일을 무시해주시길 바랍니다.";
		
		sendMail(receiverEmail, title, body);
	}
	
	public void sendUserId(String receiverEmail, List<String> userIds) throws MessagingException, UnsupportedEncodingException {
		String title = "아이디 찾기 메일입니다..";
		String body = "이 이메일로 가입된 DDev 계정 아이디입니다.\n" + "아이디 : ";

		for(String userId : userIds) {
			body += userId + ", ";
		}
		
		body = body.substring(0, body.lastIndexOf(","));
		
		sendMail(receiverEmail, title, body);
	}
	
	
	public void sendPwdResetLink(String receiverEmail, String url) throws MessagingException, UnsupportedEncodingException {
		String title = "비밀번호 재설정 메일입니다..";
		String body = "만약 비밀번호 재설정을 요청하지 않으셨다면 본 이메일을 무시해주시기 바랍니다.\n" + 
				"비밀번호 재설정을 요청하셨다면, 아래 링크를 통해 비밀번호를 재설정 하세요.\n" + 
				"링크 : " + url + "\n" +
				"30분이 지나면 링크가 만료됩니다.\n";

		sendMail(receiverEmail, title, body);
	}
}
