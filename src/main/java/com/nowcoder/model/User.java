package com.nowcoder.model;

/**
 * Created by nowcoder on 2016/6/26.
 */
public class User {
	private int uId;
	private String uName;
	private String uPassword;
	private String headUrl;
	private String salt;
	
	public User(){
		
	}
	
	public User(String name){
		this.uName=name;
		this.uPassword="  ";
		this.salt="  ";
		this.headUrl="";
	}
	
	
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String upassword) {
		this.uPassword = upassword;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
}
