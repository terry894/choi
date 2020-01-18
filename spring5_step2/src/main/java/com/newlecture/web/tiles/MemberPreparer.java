package com.newlecture.web.tiles;

import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MemberService;

public class MemberPreparer implements ViewPreparer{

	@Autowired
	private MemberService service;
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			String uid = auth.getName();
			Member member = service.getMember(uid);
			
			Map<String, Object> model = tilesContext.getContext("request");
			model.put("phone", member.getPhone());
		}
		
	}
	
}
