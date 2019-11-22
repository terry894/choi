package com.newlecture.web.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.web.service.MemberService;

@WebServlet("/member/logout")
public class LogoutController extends HttpServlet{
	
	private MemberService memberservice;
	 
	public LogoutController() {
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect("../index");
		
		super.doGet(request, response);
	}
	
}
