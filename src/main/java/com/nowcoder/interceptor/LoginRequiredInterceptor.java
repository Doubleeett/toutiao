package com.nowcoder.interceptor;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nowcoder.dao.LoginTicketDAO;
import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.LoginTicket;
import com.nowcoder.model.User;
import com.nowcoder.model.UserHostHolder;

public class LoginRequiredInterceptor implements HandlerInterceptor{
	@Autowired
	private LoginTicketDAO loginTicketDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserHostHolder userHostHolder;
	
	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, Exception exception)
			throws Exception {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, ModelAndView modelAndView)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
		// TODO Auto-generated method stub
		if(userHostHolder.getUser()==null){
			httpServletResponse.sendRedirect("/?pop=1");//弹出登录框
			return false;
		}
		return true;
	}
}

