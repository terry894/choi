package com.newlecture.web.service.newlec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MemberService;

@Service
public class NewlecMemberService implements MemberService{
	
	@Autowired
	private MemberDao memberDao;
	//private MemberRoleDao memberRoleDao;
	
	@Override
	public boolean isValidMember(String id, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDuplicatedId(String id) {
		
		Member member = memberDao.get(id);
		
		if(member != null)
			return true;
		
		return false;
	}

	@Override
	public int insertMember(Member member) {
		
		//1.����� �Ƹ�������..
		String pwd = member.getPwd();
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedPwd = encoder.encode(pwd);
		member.setPwd(hashedPwd);
		
		memberDao.insert(member);
		
		//System.out.println(pwd + " : " + hashedPwd);
		
		return 0;
	}

	@Override
	public Member getMember(String id) {
		
		return memberDao.get(id);
	}

	@Override
	public String getDefaultRole(String uid) {
		
		return "ROLE_STUDENT";
	}

}
