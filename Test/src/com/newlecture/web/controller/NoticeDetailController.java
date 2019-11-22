package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*@WebServlet("/notice/detail")*/
public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int hit = 0;
		int noticeId = 0;
		String title = null;
		String writer_id = null;
		String regdate = null;
		String content = null;

		Integer id = Integer.parseInt(request.getParameter("id"));

		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";// 사용할 디비 호스트이름,포트,서비스이름
		String sql = "SELECT * FROM notice WHERE ID=?";
		
		//내가만든코드는 소슨든 바이너리든 재사요에 신경씁시다 

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//오라클 DB에 연결
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec"); // new 된 결과를 반환 실행하자마자 연결
			PreparedStatement st = con.prepareStatement(sql); 
			st.setInt(1, id); //SELECT * FROM notice WHERE ID=뒤에 id 붙어서 st에 추가
			ResultSet rs = st.executeQuery(); // 클라이언트에서 받을 공간 생성 //커서 생성

			rs.next(); //다음 커서
			title = rs.getString("TITLE");
			writer_id = rs.getString("WRITER_ID");
			regdate = rs.getString("REGDATE");
			hit = rs.getInt("HIT");
			content = rs.getString("CONTENT");

			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) { //예외처리 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // oracle.jdbc.driver.OracleDriver 객체를 만듬
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		response.sendRedirect("detail.jsp");
		//모델을 전달
		
		request.setAttribute("title", title); //title에 있는 값을 "title"이라는 이름으로 request에 저장 
		request.setAttribute("writer_id", writer_id);
		request.setAttribute("regdate", regdate);
		request.setAttribute("hit", hit);
		request.setAttribute("content", content);
		
		request.getRequestDispatcher("detail.jsp").forward(request, response); //detail.jsp 호출 할때 request 값을 보냄 
		//Dispatcher는 request, response를 detail.jsp 에게 전달 해서 값을 이어가는 역할
		//redirect는 상관없는 파일을 데이터 전달 없이 호출 
	}
}
