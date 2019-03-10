package com.pgh.crowdfunding.pojo;

import java.io.Serializable;

public class DeletedUserInfo implements Serializable{
	private int id;
	private int uid;
	private String username;
	private String password;
	private String pname;
	private String salt;
	private String email;
	private String birthday;
	private String rid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	@Override
	public String toString() {
		return "DeleatedUserInfo [id=" + id + ", uid=" + uid + ", username=" + username + ", password=" + password
				+ ", pname=" + pname + ", salt=" + salt + ", email=" + email + ", birthday=" + birthday + ", rid=" + rid
				+ "]";
	}
	public DeletedUserInfo(int id, int uid, String username, String password, String pname, String salt, String email,
			String birthday, String rid) {
		super();
		this.id = id;
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.pname = pname;
		this.salt = salt;
		this.email = email;
		this.birthday = birthday;
		this.rid = rid;
	}
	public DeletedUserInfo() {
		super();
	}
	
}
