package com.nowcoder.service;


import com.nowcoder.dao.LoginTicketDAO;
import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.LoginTicket;
import com.nowcoder.model.User;
import com.nowcoder.util.TingUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private LoginTicketDAO loginTicketDAO;

    //注册，增加user
    public Map<String, Object> register(String uName,String uPassword) {
    	Map<String, Object> map=new HashMap<>();
    	
    	//注册条件的判断
    	if(StringUtils.isBlank(uName)){
    		map.put("msgname", "用户名不能为空");
    		return map;
    	}
    	
    	if(StringUtils.isBlank(uPassword)){
    		map.put("msgpwd", "密码不能为空");
    		return map;
    	}
    	
    	User user=userDAO.selectByuName(uName);
    	if (user!=null) {
    		map.put("msgname", "该用户名已被注册");
    		return map;
		}
  
    	user=new User();
    	user.setuName(uName);
    	//user.setuPassword(uPassword);
    	//密码加密
    	//user.setuPassword(TingUtil.MD5(uPassword));
    	//加密增强
    	user.setSalt(UUID.randomUUID().toString().substring(0, 5));
    	user.setuPassword(TingUtil.MD5(uPassword+user.getSalt()));
    	user.setHeadUrl(String.format("http://localhost:8080/image?name=head1.jpg"));
    	userDAO.addUser(user);
    	user=userDAO.selectByuName(user.getuName());
    	//注册成功后自动登录
    	String ticket=addLoginTicket(user.getuId());
    	map.put("ticket", ticket);
    	
    	return map;
	}
    
    
    
    
    //登录
    public Map<String, Object> login(String uName,String uPassword) {
    	Map<String, Object> map=new HashMap<>();
    	
    	//登录条件的判断
    	if(StringUtils.isBlank(uName)){
    		map.put("msgname", "用户名不能为空");
    		return map;
    	}
    	
    	if(StringUtils.isBlank(uPassword)){
    		map.put("msgpwd", "密码不能为空");
    		return map;
    	}
    	
    	//查找用户
    	User user=userDAO.selectByuName(uName);
    	if (user==null) {
    		map.put("msgname", "该用户名不存在");
    		return map;
		}
    	
    	System.out.println("uid:"+user.getuId());
    	System.out.println("MD5:"+TingUtil.MD5(uPassword+user.getSalt()));
    	System.out.println("pwd:"+user.getuPassword());
    	//验证密码
    	if (!TingUtil.MD5(uPassword+user.getSalt()).equals(user.getuPassword())) {
    		map.put("msgpwd", "密码错误");
    		return map;
		}
    	
    	//密码正确，下发ticket
    	//Login记录，ticket：与u_Id关联的随机字符串
    	//expired:过期时间，status表明该ticket是否有效
    	String ticket=addLoginTicket(user.getuId());
    	System.out.println(ticket);
    	map.put("ticket", ticket);
    	return map;
	}
    
    //生成ticket
    private String addLoginTicket(int uId) {
		LoginTicket loginTicket=new LoginTicket();
		loginTicket.setuId(uId);
		loginTicket.setStatus(0);
		loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
		Date date=new Date();
		date.setTime(date.getTime()+1000*3600*24);
		loginTicket.setExpired(date);
		loginTicketDAO.addLoginTicket(loginTicket);
		return loginTicket.getTicket();
    }
    
    //根据id获取user信息
    public User getUser(int uId) {
        return userDAO.selectById(uId);
    }
    
    
    
    
    //登出
    public void logout(String ticket) {
		loginTicketDAO.updatestatus(ticket, 1);
	}
}
