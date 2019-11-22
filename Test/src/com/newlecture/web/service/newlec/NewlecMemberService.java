package com.newlecture.web.service.newlec;

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
		
		if(member == null)//사용자 없다면?
			return false;
		else if(!member.getPwd().equals(pwd))//비밀번호 불일치
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
