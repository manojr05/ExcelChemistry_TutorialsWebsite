package com.excel_chemistry.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import com.excel_chemistry.model.User;

@Component
public class EmailSenderImpl implements EmailSender {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Override
	public void sendOtp(int otp, User user) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setFrom("manojkumar252000@gmail.com");
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("Excel Chemistry: Email Verification");
		mailMessage.setText("Hello " + user.getName() + ", your OTP for mail verification is " + otp);
		
		javaMailSender.send(mailMessage);
	}
	
}
