package com.newlecture.web.service;

import com.newlecture.web.entitiy.Member;

public interface MemberService {

	boolean isValidMember(String id, String pwd);
	boolean isDuplicated(String id);
    int insertMember(Member member);
	
	
}
