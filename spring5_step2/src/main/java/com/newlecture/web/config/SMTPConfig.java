package com.newlecture.web.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class SMTPConfig {
	
	@Bean
	public JavaMailSender mailSender() {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setDefaultEncoding("UTF-8");
		
		mailSender.setHost("smtp.fmcity.com");
		//gmail을 이용할 때
		//mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("noreply@newlecture.com");
		mailSender.setPassword("(noReply1!)");
		
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", true);		
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.debug", true);
				
		mailSender.setJavaMailProperties(properties);
		
		return mailSender;		
	}
	
}
