package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice/list")//아무거나 와도 상관없음
public class NoticeListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";// 사용할 디비 호스트이름,포트,서비스이름
		String sql = "SELECT * FROM notice";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec"); // new 된 결과를 반환 실행하자마자 연결
			Statement st = con.createStatement(); // 커서 생성
			ResultSet rs = st.executeQuery(sql); // 클라이언트에서 받을 공간 생성

			List<Map<String, Object>> list = new ArrayList<>(); //key값은 String 데이터는 아무 자료형이나 다 될수있음 
			// Map<String,Object>은 단순 자료형
			// 자료형이 다른 데이터를 받기 위해 키와 값이 있는 map사용

			while (rs.next()) {
				Map<String, Object> notice = new HashMap<>();
				// Map<키, 데이터>
				notice.put("id", rs.getInt("ID")); // Map에 id라는 이름으로 해당 데이터 저장 키 =id 데이터 = 해당데이터
				notice.put("title", rs.getString("TITLE"));
				notice.put("writer_id", rs.getString("WRITER_ID"));
				notice.put("regdate", rs.getDate("REGDATE"));
				notice.put("hit", rs.getInt("HIT"));
				list.add(notice);
			}

			rs.close();
			st.close();
			con.close();

			request.setAttribute("list", list);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // oracle.jdbc.driver.OracleDriver 객체를 만듬
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(request, response);
	}
}
