package com.newlecture.web.service;

import java.util.List;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

public interface NoticeService {

	List<NoticeView> getNoticeList();
	List<NoticeView> getNoticeList(int page);
	List<NoticeView> getNoticeList(int page, String field, String query);
	int openNoticeList(int[] ids);
	int deleteNoticeList(int[] ids);
	Notice getNotice(int id);
	
	Notice getPrevNoticeByCurrentId(int id);
	Notice getNextNoticeByCurrentId(int id);
	
	int insertNotice(Notice notice);
	int updateNotice(Notice notice);
	int deleteNotice(int id);
	int getNoticeListCount(String field, String query);
	
	
}
