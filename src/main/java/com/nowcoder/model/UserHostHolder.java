package com.nowcoder.model;

import org.springframework.stereotype.Component;

//表明当前用户，为其创建一个线程
@Component
public class UserHostHolder {

	private static ThreadLocal<User> users=new ThreadLocal<User>();
	
	//提取用户
	public User getUser() {
		return users.get();
		
	}
	
	//存用户
	public void setUser(User user) {
		users.set(user);
	}
	
	public void clear() {
		users.remove();
	}
}
