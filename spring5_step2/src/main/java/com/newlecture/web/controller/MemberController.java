package com.newlecture.web.controller;

import java.security.Principal;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {
			
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Autowired
	private MemberService service;

	@GetMapping("login")
	public String login() {
		
		return "member.login";
	}
	
	@GetMapping("agree")
	public String agree() {
		
		return "member.agree";
	}
	
	@PostMapping("agree")
	public String agree(Boolean agree, HttpServletResponse response) {
				
		Cookie agreeCookie = new Cookie("agree", agree.toString());
		//agreeCookie.setMaxAge(24*60*60);
		//agreeCookie.setPath(uri);
		
		response.addCookie(agreeCookie);
		
		return "redirect:join";
	}
	
	@GetMapping("join")
	public String join(
			@CookieValue(value="agree", defaultValue="false") String agree) {
		
		// SMTP  /  POP3
		
		if(agree.equals("false"))
			return "redirect:agree";
		
		return "member.join";
	}
	
	@PostMapping("join")
	public String join(Member member) {		
		
		service.insertMember(member);
		
		return "redirect:confirm";
	}
	
	@GetMapping("pwd-reset")
	public String pwdReset() {
		
		return "member.pwd-reset";
	}
	
	@PostMapping("pwd-reset")
	public String pwdReset(String id, String email) throws MessagingException {
		
		// �̸��� ����
		//Member member = service.getMember(id);
		String name = "뉴렉";//member.getName();
		
		StringBuilder html = new StringBuilder();
		
		html.append("<html>");
		html.append("<body>");
		html.append("<h1>"+name+"</h1>");
		html.append("<img src=\"http://www.newlecture.com/resource/images/logo.png\">");
		html.append("<a href=\"http://www.newlecture.com/member/reset-pwd?id=newlec\">비번 재설정</a>");
		html.append("</body>");
		html.append("</html>");
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setFrom("noreply@newlecture.com");
		helper.setTo("newlec@namoolab.com");
		helper.setText(html.toString(), true);
		helper.setSubject("[newlecture]비밀번호 재설정 메일");
		
		mailSender.send(message);
		
		/*
		 * MimeMessage message = mailSender.createMimeMessage();
		 * 
		 * mailSender.send(message);
		 */
		
		return "redirect:login";
	}
	
	@ResponseBody
	@GetMapping("duplicated")
	public Boolean getMember(String id) {
		
		Boolean result = service.isDuplicatedId(id);
		
		return result;
	}
	
	@GetMapping("home")
	public String home(Principal principal) {
		
		String uid = principal.getName();
		
		String defaultRole = service.getDefaultRole(uid);
		
		String sendUrl = "/student/index";
		
		switch(defaultRole) {
		case "ROLE_ADMIN":
			sendUrl = "/admin/index";
			break;
		case "ROLE_TEACHER":
			sendUrl = "/teacher/index";
			break;
		}
		
		return "redirect:"+sendUrl;
	}
}







