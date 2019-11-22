package com.newlecture.web.controller.member;

import com.newlecture.web.dao.MemberDao.MemberDao;
import com.newlecture.web.dao.jdbc.JdbcMemberDao;
import com.newlecture.web.entitiy.Member;
import com.newlecture.web.service.MemberService;

	public class NewlecMemberService implements MemberService {
	   
	   private MemberDao memberDao;
	   
	   public NewlecMemberService() {
	      memberDao = new JdbcMemberDao();
	   }
	   
	   @Override
	   public boolean isValidMember(String id, String pwd) {
	      Member member = memberDao.get(id);
	      
	      if(member == null) 
	         return false;
	      else if(!member.getPwd().equals(pwd)) 
	         return false;
	         
	      return true;
	   }


	@Override
	public boolean isDuplicated(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	}