package com.newlecture.web.service.newlec;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

@Service
public class NewlecNoticeService implements NoticeService {

	@Autowired
	private NoticeDao noticeDao; // Entity�� ��ġ ��Ų��.
	//private CommentDao commentDao;
	// �����ͺ��̽��� ����ϴ� ���� Table�� �̸��� ��ġ��Ų��.
		
	@Override
	public int insertNotice(Notice notice) {
		
		return noticeDao.insert(notice);
		
		// String sql = "insert into "; 
		// ���� �ڹٸ� �̿��� ����ó���� �ϴ� ������
		// �����ͺ��̽� ���α׷� �ִ� ���� �ƴϴ�.
		// ������ �ҽ��� ����� ������ Dao�� ����� ������..
		// ���⼭ ������ �ҽ��� ���� ��ü�� �ƴ� ���� �� �� �� ����
	}

	@Override
	public List<NoticeView> getNoticeList() {
		// TODO Auto-generated method stub
		return getNoticeList(1, "title", "");
	}

	@Override
	public List<NoticeView> getNoticeList(int page) {
		// TODO Auto-generated method stub
		return getNoticeList(page, "title", "");
	}

	@Override
	public List<NoticeView> getNoticeList(int page, String field, String query) {
		// TODO Auto-generated method stub
		return noticeDao.getList(page, field, query);
	}

	@Override
	public int openNoticeList(int[] ids) {
		
		//UPDATE NOTICE SET OPEN=1 WHERE ID in (1,2,3);
		
		int i=0;
		
		i = noticeDao.openList(ids);
		/*
		for(; i<ids.length; i++) {
			Notice notice = noticeDao.get(ids[i]);
			notice.setOpen(true);
			noticeDao.update(notice);
		}*/
		
		return i;
	}

	@Override
	public int deleteNoticeList(int[] ids) {
		int i = 0;
		
		for(; i<ids.length; i++) 		
			noticeDao.delete(ids[i]);
		
		return i;
	}

	@Override
	public Notice getNotice(int id) {
		
		return noticeDao.get(id);
	}

	@Override
	public Notice getPrevNoticeByCurrentId(int id) {
		
		return noticeDao.getPrevById(id);
	}

	@Override
	public Notice getNextNoticeByCurrentId(int id) {
		// TODO Auto-generated method stub
		return null;//noticeDao.getNextById(id);
	}

	@Override
	public int updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.update(notice);
	}

	@Override
	public int deleteNotice(int id) {
		// TODO Auto-generated method stub
		return noticeDao.delete(id);
	}

	@Override
	public int getNoticeListCount(String field, String query) {
		
		return noticeDao.getListCount(field, query);
	}

}
