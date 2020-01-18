package com.newlecture.web.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.newlecture.web.dao.MemberDao;

public class MyHomeRedirectionHandler implements AuthenticationSuccessHandler {
	
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		SavedRequest savedRequest = 
				(SavedRequest) session
					.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		
		if(savedRequest != null) {
			String returnURL = savedRequest.getRedirectUrl();
			redirectStrategy.sendRedirect(request, response, returnURL);
		}
		else
			redirectStrategy.sendRedirect(request, response, "/member/home");
		
		
		/*
		User user = (User) SecurityContextHolder
									.getContext()
									.getAuthentication()
									.getPrincipal();
		
		String id = user.getUsername();
		*/
			
			
				
	}

}
