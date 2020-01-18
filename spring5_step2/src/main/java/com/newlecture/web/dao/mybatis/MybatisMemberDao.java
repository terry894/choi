package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;

@Repository
public class MybatisMemberDao implements MemberDao {

	private SqlSession sqlSession;
	private MemberDao mapperDao;
	
	@Autowired
	public MybatisMemberDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		this.mapperDao = sqlSession.getMapper(MemberDao.class);
	}
	
	@Override
	public List<Member> getList() {
		
		return mapperDao.getList();
	}

	@Override
	public Member get(String id) {
		// TODO Auto-generated method stub
		return mapperDao.get(id);
	}

	@Override
	public int insert(Member member) {
		// TODO Auto-generated method stub
		return mapperDao.insert(member);
	}

	@Override
	public int update(Member member) {
		// TODO Auto-generated method stub
		return mapperDao.update(member);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return mapperDao.delete(id);
	}

}
