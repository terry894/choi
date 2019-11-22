package com.newlecture.web.entitiy;

import java.util.Date;

public class Member {
  
	private String id;
	private String pwd;
	private String name;
	private String gender;
	private Date regdate;
	private String phone;
	private String birthday;
	private String email;
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", pwd=" + pwd + ", name=" + name + ", gender=" + gender + ", regdate=" + regdate
				+ ", phone=" + phone + ", birthday=" + birthday + ", email=" + email + "]";
	}

	public Member() {};

	public Member(String id, String pwd, String phone, String email) {
		this.id = id;
		this.pwd = pwd;
		this.phone = phone;
		this.email = email;
	}
	
	public Member(String id, String pwd, String name, String gender, String phone, String birthday,
			String email) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.birthday = birthday;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
