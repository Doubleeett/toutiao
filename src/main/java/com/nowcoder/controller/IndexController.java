package com.nowcoder.controller;

import com.nowcoder.model.News;
import com.nowcoder.model.UserHostHolder;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.NewsService;
import com.nowcoder.service.ToutiaoService;
import com.nowcoder.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * Created by nowcoder on 2016/6/26.
 */
@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private NewsService newsService;
	
    @Autowired
    private UserService userService;
	
    @Autowired
    UserHostHolder userHostHolder;
	@RequestMapping(value={"/","/index"},method = {RequestMethod.GET, RequestMethod.POST})
	public String index(@RequestParam(value="uId",defaultValue="0") int uId,
						Model model,
						@RequestParam(value="pop",defaultValue="0") int pop){
		model.addAttribute("viewObjects",getNews(0, 0, 10));
		model.addAttribute("pop",pop);
		return "home";
	}
	@RequestMapping(path = {"/user/{uId}/"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String userIndex(@PathVariable("uId") int uId,Model model){
		model.addAttribute("viewObjects",getNews(uId, 0, 10));
		return "home";
	}
	
	private List<ViewObject> getNews(int uId,int offset,int limit){
		List<News> newsList=newsService.getLatestNews(uId,offset,limit);
		List<ViewObject> viewObjects = new ArrayList<>();
	    for (News news : newsList) {
	        ViewObject viewObject = new ViewObject();
	        viewObject.set("news", news);
	        viewObject.set("user", userService.getUser(news.getuId()));
	        viewObjects.add(viewObject);
	    }
	    return viewObjects;
	}
	
}



