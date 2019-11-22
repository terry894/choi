package com.newlecture.web.service;

import java.util.List;

import com.newlecture.web.entitiy.Notice;
import com.newlecture.web.entitiy.NoticeView;

public interface NoticeService {
	
	
	List<NoticeView> getNoticeList();
	List<NoticeView> getNoticeList(int page);
	List<NoticeView> getNoticeList(int page,String field,String query);
    int openNoticeListByIds(int[] ids); //byids byㅗit 처럼 사용하면 나중에 수정할 필요없음 
    int deleteNoticeListByIds(int[] ids);
    Notice getNoticeById(int id);
    Notice getPrevNoticeByCurrentId(int id);
    Notice getNextNoticeByCurrentId(int id);
    
	int insertNotice(Notice notice);
    int updateNotice(Notice enotice);
    int delete(int id);
	int getNoticeListCount(String field, String query);
	
}


