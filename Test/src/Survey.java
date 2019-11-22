import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/survey")
public class Survey extends HttpServlet{
	private static int data[] = new int[8];
	
	@Override
	public void init() throws ServletException {
		super.init();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
//		HttpSession session = req.getSession();
//		Object box = session.getAttributeNames();
//		System.out.println(Integer.parseInt(box.toString()));
		
		
		String[] str = req.getParameterValues("box");
		
		for(String result : str) {
			data[Integer.parseInt(result)] = ++(data[Integer.parseInt(result)]);
		}
		
		int count = 0;
		for(int i : data) {
			count++;
			if(i == 0)
				continue;
			
			switch(count) {
			case 1:
				System.out.print("주식을 쇼핑처럼");
				break;
			case 2:
				System.out.print("주식을 쇼핑하다");
				break;
			case 3:
				System.out.print("Stock Market");
				break;
			case 4:
				System.out.print("틱톡");
				break;
			case 5:
				System.out.print("틱스톡");
				break;
			case 6:
				System.out.print("페닐리스(penniless)");
				break;
			}

			
			System.out.println(":" + i);
		}
		System.out.println("");
		
		
		out.write("<h1>감사합니다</h1>");
	}

}
