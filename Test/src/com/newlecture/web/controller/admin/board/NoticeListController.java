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

//공지 사항 목록 요청 받음 
@WebServlet("/admin/board/notice/list")
public class NoticeListController extends HttpServlet {

	// 톰캣이 서블릿 객체생성 -> init() -> service() -> doXXX()
	// -> timeout 카운트 다운 -> 소멸
	private NoticeService noticeService;

	public NoticeListController() {
		noticeService = new NewlecNoticeService();// 큰 범주 NewlecNoticeService안에 noticeService인터페이스 메소드를 오버라이드한것만 쓸 수 있다.

	}

	@Override
	public void init() throws ServletException {
		// 초기화를 여기서

		System.out.println("설정에서 읽어오기");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int page = 1;
		String field = "title";
		String query = "";

		String page_ = request.getParameter("p");
		System.out.println(page_);
		if (page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);

		String field_ = request.getParameter("f");
		if (field_ != null &&
				!field_.equals(""))
			field = field_;

		String query_ = request.getParameter("q");
		if (query_ != null && !query_.equals(""))
			query = query_;

		request.setAttribute("list", noticeService.getNoticeList(page, field, query));
		request.setAttribute("listCount", noticeService.getNoticeListCount(field, query));
		// 컨트롤러 할일은 데이터 준비
		// 서비스에게 list 를 받아서

		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(request, response);
        //getRequestDispatcher는 이함수를 호출하고 다시여기로
		// 개인 프로젝트
		/*
		 * String sql= "insert sql" 1 소규모 2 전문가들 같이 참여안함 3 수정 소스코드 수정
		 * 
		 */
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int page = 1;
		String field = "title";
		String query = "";
		String cmd = request.getParameter("cmd");

		String page_ = request.getParameter("p");
		System.out.println(page_);
		if (page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);

		String field_ = request.getParameter("f");
		if (field_ != null && !field_.equals(""))
			field = field_;

		String query_ = request.getParameter("q");
		if (query_ != null && !query_.equals(""))
			query = query_;
		
		switch (cmd) {
		case "일괄공개":
			break;
		case "일괄삭제":
			
			String[] ids_ = request.getParameterValues("del");
			System.out.println("ids:"+ids_.length);
			int[] ids = new int[ids_.length];

			for (int i = 0; i < ids.length; i++) {
				ids[i] = Integer.parseInt(ids_[i]);
			}

			noticeService.deleteNoticeListByIds(ids);
			break;
		// int[] =? i/o stream <-> stream API 컬렉션에 sql같은 기능이 포함된 API
		}

		response.sendRedirect("list?p="+ page +"&f="+field+"&q="+query);
		//sendRedirect는 호출한 주소로 이동
	}
}
