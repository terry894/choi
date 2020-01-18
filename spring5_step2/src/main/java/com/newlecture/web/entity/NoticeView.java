package com.newlecture.web.entity;

import java.util.Date;

public class NoticeView extends Notice {
	private int cmtCount;
	
	public NoticeView() {
		// TODO Auto-generated constructor stub
	}

	public NoticeView(int id, String title, String content, String writerId, Date regdate, String files, int hit,
			boolean open, int cmtCount) {
		super(id, title, content, writerId, regdate, files, hit, open);
		// TODO Auto-generated constructor stub
		this.cmtCount = cmtCount;
	}

	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}
	
	
	
	
}
