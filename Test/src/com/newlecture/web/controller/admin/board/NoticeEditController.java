package com.newlecture.web.controller.admin.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entitiy.Notice;
import com.newlecture.web.service.NoticeService;
import com.newlecture.web.service.newlec.NewlecNoticeService;

@WebServlet("/admin/board/notice/edit")
public class NoticeEditController extends HttpServlet{
    //톰캣이 서블릿 객체생성 -> init() -> service() -> doXXX()
	//-> timeout 카운트 다운 -> 소멸 
	private NoticeService noticeService;
	
	
	public NoticeEditController() {
		noticeService = new NewlecNoticeService();//큰 범주 NewlecNoticeService안에 noticeService인터페이스 메소드를 오버라이드한것만 쓸 수 있다.
		
	}
	
	@Override
	public void init() throws ServletException {
		//초기화를 여기서 

		System.out.println("설정에서 읽어오기");
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
		.getRequestDispatcher("/WEB-INF/view/admin/board/notice/edit.jsp")
		.forward(request, response);
		
		
		//개인 프로젝트
		/*
		 * String sql= "insert sql"
		 * 1 소규모 
		 * 2 전문가들 같이 참여안함
		 * 3 수정 소스코드 수정 
		 * 
		 * */
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String title = request.getParameter("title");
	  String content = request.getParameter("content");
	  
//		noticeService.insertNotice(title,content."");
	  int result = noticeService.insertNotice(
				new Notice(title, content,"newlec"));
	  System.out.println(title+""+ content);

	  if(result ==0)
		  response.sendRedirect("/error?code=2");
	  else
	      response.sendRedirect("list");
	  
	  System.out.println(title);
		
	}
}
