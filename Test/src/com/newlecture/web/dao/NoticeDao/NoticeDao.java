package com.newlecture.web.dao.NoticeDao;

import java.util.List;

import com.newlecture.web.entitiy.Notice;
import com.newlecture.web.entitiy.NoticeView;

public interface NoticeDao {

	
	List<NoticeView> getList();
	List<NoticeView> getList(int page);
	List<NoticeView> getList(int page, String field,String query);
	Notice get(int id);
	
	int insert(Notice notice);
	int update(Notice notice);
	int delete(int id);
	int openList(int[] ids);
	Notice getPrevById(int id);
	Notice getNextById(int id);
	int getListCount(String field, String query);

	
	
}
