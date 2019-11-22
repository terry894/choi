package com.newlecture.web.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin/index")
public class AdminIndexController extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();		

		if(session.getAttribute("userName")==null)//인증이안되면
		{
			
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
			PrintWriter out  = response.getWriter();
			out.println("			<script>\r\n" + 
					"\r\n" + 
					"			window.alert(\"인증이 필요한 요청 입니다. \\r\\n 로그인 페이지로 이동합니다\");\r\n" + 
					"			window.location.href=\"../member/login?returnUrl=../admin/index\";\r\n" + 
					"\r\n" + 
					"			</script>");

			return;
		}
			//메세지 뿌리고(대화상자로 띄우기 학인 [OK]버튼을 누르면)
		    //로그인 페이지 이동 
		
		request.getRequestDispatcher("/WEB-INF/view/admin/index.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

}
