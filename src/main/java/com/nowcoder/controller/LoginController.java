package com.nowcoder.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowcoder.service.NewsService;
import com.nowcoder.service.UserService;
import com.nowcoder.util.TingUtil;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;
	
	//注册
	@RequestMapping(path={"/reg/"},method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String reg(Model model,@RequestParam("username") String uName,
									@RequestParam("password") String uPassword,
									@RequestParam(value="rember",defaultValue="0") int rememberme,
									HttpServletResponse response) {
		
		try{
			//返回的是一个json char("{"code":0,"msg:""xxxx"}")
			Map<String, Object> map=userService.register(uName, uPassword);
			//将信息返回至前端
			if(map.containsKey("ticket")){
				Cookie cookie=new Cookie("ticket", map.get("ticket").toString());
				cookie.setPath("/");//设置cookie全站有效
				if(rememberme>0){
					cookie.setMaxAge(3600*24*5);
				}
				response.addCookie(cookie);
				return TingUtil.getJSONString(0,"注册成功");
			}
			else{
				return TingUtil.getJSONString(1,map);
			}
		}catch(Exception e){
			logger.error("注册异常"+e.getMessage());
			return TingUtil.getJSONString(1, "注册异常");
		}
		
	}
	
	

	//登录
	@RequestMapping(path={"/login/"},method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String login(Model model,@RequestParam("username") String uName,
									@RequestParam("password") String uPassword,
									@RequestParam(value="rember",defaultValue="0") int rememberme,
									HttpServletResponse response) {
		
		try{
			//返回的是一个json char("{"code":0,"msg:""xxxx"}")
			Map<String, Object> map=userService.login(uName, uPassword);
			if(map.containsKey("ticket")){
				Cookie cookie=new Cookie("ticket", map.get("ticket").toString());
				cookie.setPath("/");//设置cookie全站有效
				if(rememberme>0){
					cookie.setMaxAge(3600*24*5);
				}
				response.addCookie(cookie);
				return TingUtil.getJSONString(0,"登录成功");
			}
			else{
				return TingUtil.getJSONString(1,map);
			}
		}catch(Exception e){
			logger.error("登录异常"+e.getMessage());
			return TingUtil.getJSONString(1, "登录异常");
		}
	}
	
	//登出
	@RequestMapping(path={"/logout/"},method={RequestMethod.GET,RequestMethod.POST})
	public String logout(@CookieValue("ticket") String ticket){
		userService.logout(ticket);
		return "redirect:/";
	}
	
}
