package com.newlecture.web.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entitiy.Member;
import com.newlecture.web.service.MemberService;
import com.newlecture.web.service.newlec.NewlecMemberService;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {

	private MemberService memberservice;

	// 여기는 인터페이스
	public LoginController() {
		memberservice = new NewlecMemberService();
		// 여기는 구현 클래스
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String returnUrl = request.getParameter("returnUrl");
		
		request.setAttribute("returnUrl", returnUrl);
		
		request.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("username");
		String pwd = request.getParameter("password");
        String returnUrl = request.getParameter("returnUrl");
		
		boolean isValid = memberservice.isValidMember(id, pwd);

		if (!isValid)
			response.sendRedirect("login?error=1");
		else {
			//이유저가 인증이 되엇음을 알림 
			//인증된 상태를 남기는 것
			//어떤 상태? 어디에?  클라이언트쪽 cookie(요즘은 쿠키),서버쪽 session-> 현재사용자만 로그인 ,application-> 한명이 로그인하면 다른사용자도 로그인 됨 
			
			//  /admin/index
			request.getSession().setAttribute("userName",id);
			if(!returnUrl.equals(""))
			    response.sendRedirect(returnUrl);
			else
			    response.sendRedirect("/index");
		}
	}
}
