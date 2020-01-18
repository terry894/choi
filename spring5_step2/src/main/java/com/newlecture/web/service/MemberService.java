package com.newlecture.web.service;

import com.newlecture.web.entity.Member;

public interface MemberService {
	boolean isValidMember(String id, String pwd);
	boolean isDuplicatedId(String id);
	int insertMember(Member member);
	Member getMember(String id);
	String getDefaultRole(String uid);
}
