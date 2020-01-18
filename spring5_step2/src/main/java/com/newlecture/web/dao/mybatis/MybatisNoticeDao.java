package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

@Repository
public class MybatisNoticeDao implements NoticeDao {

	private SqlSession sqlSession;
	private NoticeDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(NoticeDao.class);
	}

	@Override
	public List<NoticeView> getList() {
		
		//NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		
		List<NoticeView> list = mapperDao.getList(1, "title", "");
		
		return list;
	}

	@Override
	public List<NoticeView> getList(int page) {
		// TODO Auto-generated method stub
		return mapperDao.getList(page, "title", "");
	}

	@Override
	public List<NoticeView> getList(int page, String field, String query) {
		// TODO Auto-generated method stub
		return mapperDao.getList(page, field, query);
	}

	@Override
	public Notice get(int id) {
		// TODO Auto-generated method stub
		return mapperDao.get(id);
	}

	@Override
	public int insert(Notice notice) {
		// TODO Auto-generated method stub
		return mapperDao.insert(notice);
	}

	@Override
	public int update(Notice notice) {
		// TODO Auto-generated method stub
		return mapperDao.update(notice);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int openList(int[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Notice getPrevById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getListCount(String field, String query) {
		// TODO Auto-generated method stub
		return 0;
	}

}
