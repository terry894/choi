package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

public interface NoticeDao {

	List<NoticeView> getList();
	List<NoticeView> getList(int page); //->#{page}
	List<NoticeView> getList(int page, String field, String query); //-> #{param1}, #{param2}, #{param3}
	Notice get(int id);
	
	int insert(Notice notice);
	int update(Notice notice);
	int delete(int id);
	int openList(int[] ids);
	Notice getPrevById(int id);
	int getListCount(String field, String query);
	
}
