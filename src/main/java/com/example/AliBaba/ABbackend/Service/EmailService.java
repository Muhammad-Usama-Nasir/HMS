package com.example.AliBaba.ABbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.AliBaba.ABbackend.ORM.EmailDetails;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	
	@Value("${spring.mail.username}") private String sender;
	public void sendConfirmationMail(String email, String verificationCode) {
	    SimpleMailMessage message = new SimpleMailMessage();
	    String subject = "Email Verification";
	    String body = "Dear user,\n\nPlease use the following code to verify your account: " + verificationCode +
	            "\n\nOr click the link below to verify your account:\n" +
	            "http://localhost:8080/auth/confirm-verification?code=" + verificationCode;
	    
	    message.setFrom(sender);
	    message.setTo(email);
	    message.setSubject(subject);
	    message.setText(body);

	    mailSender.send(message);
	}

}
