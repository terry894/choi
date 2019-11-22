package com.newlecture.web.entitiy;

import java.sql.Date;

public class NoticeView extends Notice {
	private int cmtCount;


	public NoticeView() {

	}
	
	public NoticeView(int id, String title, String content, String writerId, Date regdate, String files, int hit,
			boolean open, int cmtCount) {
		super(id, title, content, writerId, regdate, files, hit, open);
		this.cmtCount = cmtCount;
	}

	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}

}
