package com.newlecture.web.entitiy;

import java.sql.Date;

//notice dao 를  notice entity
public class Notice {
	
	private int id;
    private String title;
    private String content;
    private String writerId;
    private Date regdate;
    private String files;
    private int hit;
	private boolean open;
	
 
	public Notice() {
		
	}
    
	//for select
      public Notice(int id, String title, String content, String writerId, Date regdate, String files, int hit , boolean open) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.writerId = writerId;
		this.regdate = regdate;
		this.files = files;
		this.hit = hit;
	    this.open = open;
	}
      //for inserting
	public Notice(String title, String content, String writerId, String files) {
		this.title = title;
		this.content = content;
		this.writerId = writerId;
		this.files = files;
	}
	public Notice(String title, String content, String writerId) {
		this.content = content;
		this.writerId = writerId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}


	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content + ", writerId=" + writerId
				+ ", regdate=" + regdate + ", files=" + files + ", hit=" + hit + ", open=" + open + "]";
	}


	

      
}
