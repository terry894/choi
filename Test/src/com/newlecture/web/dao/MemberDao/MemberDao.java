package com.newlecture.web.dao.MemberDao;

import java.util.List;

import com.newlecture.web.entitiy.Member;


public interface MemberDao {

	
	List<Member> getList();
	Member get(String id);
	int insert(Member member);
	int update(Member member);
	int delete(String id);
}
