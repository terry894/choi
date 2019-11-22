package com.newlecture.web.controller.admin.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newlecture.web.entitiy.Notice;
import com.newlecture.web.service.NoticeService;
import com.newlecture.web.service.newlec.NewlecNoticeService;

import javafx.application.Application;

@WebServlet("/admin/board/notice/reg")
@MultipartConfig(
		location = "",
		fileSizeThreshold =  1024*1024, //1메가까지는 메모리에 잡는다 넘을경우 임시디렉토리로
		maxFileSize = 1024*1024*100,
		maxRequestSize = 1024*1024*100*5
		)

public class NoticeRegController extends HttpServlet{
    //톰캣이 서블릿 객체생성 -> init() -> service() -> doXXX()
	//-> timeout 카운트 다운 -> 소멸 
	private NoticeService noticeService;
	
	
	public NoticeRegController() {
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
		.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp")
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

	  Collection<Part> parts = request.getParts();
	  
	  String fileNames ="";
	  for(Part p :parts)
	  {
		  if(!p.getName().equals("file")) continue; // continue를 실행하면 위로 백 넌 아니고 패스 
	//	  Part titlePart = request.getPart("title");
	//	  Part contentPart = request.getPart("content");
	//	  Part filePart = request.getPart("file");
		  Part filePart = p;
		  String fileName = filePart.getSubmittedFileName();
		  fileNames += fileName + ",";
	
		  ServletContext appication = request.getServletContext();
		  String urlPath = "/upload";
		  String realPath = appication.getRealPath(urlPath);
		 
		  File file = new File(realPath);
		  if(file.exists())
			  file.mkdirs();
		  else
			  System.out.println("경로가 존재합니다.");
		  
		  
		  InputStream fis = filePart.getInputStream();
		  OutputStream fos = new FileOutputStream(realPath+File.separator+fileName);
		  
		  System.out.println(fileName);
		  
		  byte[] buf = new byte[1024];
		  int size = fis.read(buf);
		  if((size = fis.read(buf)) != -1)
			  fos.write(buf,0,size);
		
		  
		  fos.close();
	  }
	  
	  
//	  System.out.println("title:" + title + "content:"+content);

	  
	  //fileNames에서 마지막 , 때기연산 수행후
	  
	  fileNames = fileNames.substring(0,fileNames.length()-1);
	  
	  int result = noticeService.insertNotice(
				new Notice(title, content,"newlec", fileNames));
//	  System.out.println(title+""+ content);

//	  int result=1;
	  if(result ==0)
		  response.sendRedirect("/error?code=2");
	  else
	      response.sendRedirect("list");
	  
//		response
//		.sendRedirect("/admin/board/notice/list");
	}
}
