package com.newlecture.web.service.newlec;

import java.util.List;

import com.newlecture.web.dao.NoticeDao.NoticeDao;
import com.newlecture.web.dao.jdbc.JdbcNoticeDao;
import com.newlecture.web.entitiy.Notice;
import com.newlecture.web.entitiy.NoticeView;
import com.newlecture.web.service.NoticeService;

public class NewlecNoticeService implements NoticeService {

	private NoticeDao noticeDao; // Entity와 일치 시킨다
//	private CommentDao commentdao //notice외에 다른가능도 필요할수있다.
	// 데이터베이스를 사용하는 경우는 Table과 이름을 일치시킨다

	public NewlecNoticeService() {
		noticeDao = new JdbcNoticeDao();
	}

	@Override
	public int insertNotice(Notice notice) {

		return noticeDao.insert(notice);
		// 다오를 이용해 여러절차만든다

		// 업무에 대한 처리
		// 순수자바를 이용한 업무처리를 하는곳 디비에 대한 프로그램이 잇는 곳이 아니다
		// 데이터 소스를 숨기는 역할자 DAO 를 스기로 했으니
		// 여기서는 데이터 소스 X
	}

	@Override
	public int openNoticeListByIds(int[] ids) {

		// uodate notice set open=1 where id in (1,2,3)
		// 를 Dao에 만들어 한번에
		int i = 0;
		i = noticeDao.openList(ids);
//		for (; i < ids.length; i++) {
//			Notice notice = noticeDao.get(ids[0]);
//			notice.setOpen(true);
//			noticeDao.update(notice);
//
//		}
		return i;
	}

	@Override
	public int deleteNoticeListByIds(int[] ids) {
		int i = 0;
		for (; i < ids.length; i++) 
			noticeDao.delete(ids[i]);
		return i;
		

	}

	@Override
	public Notice getNoticeById(int id) {
		
	
		return 	noticeDao.get(id);
	}

	@Override
	public Notice getPrevNoticeByCurrentId(int id) {
		
		return noticeDao.getPrevById(id);
	}

	@Override
	public Notice getNextNoticeByCurrentId(int id) {

		return noticeDao.getNextById(id);
	}

	@Override
	public int updateNotice(Notice notice) {

		return noticeDao.update(notice);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<NoticeView> getNoticeList() {

		return getNoticeList(1, "title", "");
	}

	@Override
	public List<NoticeView> getNoticeList(int page) {

		return getNoticeList(page, "title", "");
	}

	@Override
	public List<NoticeView> getNoticeList(int page, String field, String query) {

		return noticeDao.getList(page, field, query);
	}

	@Override
	public int getNoticeListCount(String field, String query) {
	
		return noticeDao.getListCount(field,query);
	}

}
