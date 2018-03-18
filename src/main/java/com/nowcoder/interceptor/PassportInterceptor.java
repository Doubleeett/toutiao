package com.nowcoder.interceptor;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nowcoder.dao.LoginTicketDAO;
import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.LoginTicket;
import com.nowcoder.model.User;
import com.nowcoder.model.UserHostHolder;

@Component
public class PassportInterceptor implements HandlerInterceptor{


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
		userHostHolder.clear();
		
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, ModelAndView modelAndView)
			throws Exception {
		// TODO Auto-generated method stub
		if (modelAndView!=null && userHostHolder.getUser()!=null) {
			//后端代码与前端渲染交互的地方
			modelAndView.addObject("user",userHostHolder.getUser());
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
		// TODO Auto-generated method stub
		String ticket=null;
		if (httpServletRequest.getCookies()!=null){
			for(Cookie cookie:httpServletRequest.getCookies()){
				if (cookie.getName().equals("ticket")) {
					ticket=cookie.getValue();
					break;
				}
			}
		}
		
		if (ticket!=null) {
			LoginTicket loginTicket=loginTicketDAO.selectByticket(ticket);
			if (loginTicket==null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus()!=0) {
				return true;
			}		
			User user=userDAO.selectById(loginTicket.getuId());
			userHostHolder.setUser(user);
		}
		return true;
	}
}
