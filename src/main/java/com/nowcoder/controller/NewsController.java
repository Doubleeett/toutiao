package com.nowcoder.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nowcoder.model.Comment;
import com.nowcoder.model.EntityType;
import com.nowcoder.model.News;
import com.nowcoder.model.UserHostHolder;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.CommentService;
import com.nowcoder.service.NewsService;
import com.nowcoder.service.UserService;
import com.nowcoder.util.TingUtil;

@Controller
public class NewsController {
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	NewsService newsService;
	
	@Autowired
	UserHostHolder userHostHolder;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path={"/news/{newsId}"},method={RequestMethod.GET})
	public String newDetail(@PathVariable("newsId") int newsId,Model model){
		try{
			News news = newsService.getBynId(newsId);
			if(news!=null){
				List<Comment> comments=commentService.getCommentsByEntity(news.getnId(), EntityType.ENTITY_NEWS);
				List<ViewObject> commentVOs=new ArrayList<ViewObject>();
				for(Comment comment:comments){
					ViewObject commentVO=new ViewObject();
					commentVO.set("comment", comment);
					commentVO.set("user",userService.getUser(comment.getuId()));
					commentVOs.add(commentVO);				
					}
				model.addAttribute("comments",commentVOs);
			}
			model.addAttribute("news",news);
			model.addAttribute("owner",userService.getUser(news.getuId()));		
		}catch(Exception e){
			logger.error("获取资讯明细错误"+e.getMessage());
		}
		return "detail";
	}
	
	
	@RequestMapping(path={"/image"},method={RequestMethod.GET})
	@ResponseBody
	public void getImage(@RequestParam("name") String imageName,
							HttpServletResponse response){
		
		try{
			response.setContentType("image/jpg");
			StreamUtils.copy(new FileInputStream(new File(TingUtil.IMAGE_DIR+imageName)),response.getOutputStream());
		}catch(Exception e){
			logger.error("读取图片错误"+imageName+e.getMessage());
		}
	}
	
	@RequestMapping(path={"/uploadImage/"},method={RequestMethod.POST})
	@ResponseBody
	public String uploadImage(@RequestParam("file") MultipartFile file){
		try {
			String fileUrl=newsService.saveImage(file);
			if(fileUrl==null){
				return TingUtil.getJSONString(1, "上传失败");
			}
			System.out.println("上传成功了啊");
			return TingUtil.getJSONString(0,fileUrl);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("上传图片失败"+e.getMessage());
			return TingUtil.getJSONString(1, "上传失败");
		}
	}
	
	@RequestMapping(path={"/user/addNews/"},method={RequestMethod.POST})
	@ResponseBody
	public String addNews(@RequestParam("image") String image,
            @RequestParam("title") String title,
            @RequestParam("link") String link) {
				try {
					News news = new News();
					news.setnDate(new Date());
					news.setTitle(title);
					news.setImage(image);
					news.setLink(link);
				if (userHostHolder.getUser() != null) {
				  news.setuId(userHostHolder.getUser().getuId());
				} else {
				  // 设置一个匿名用户
				  news.setuId(0);
				}
				newsService.addNews(news);
				return TingUtil.getJSONString(0);
				} catch (Exception e) {
				logger.error("添加资讯失败" + e.getMessage());
				return TingUtil.getJSONString(1, "发布失败");
				}
				}
				
				@RequestMapping(path = {"/addComment"}, method = {RequestMethod.POST})
				public String addComment(@RequestParam("newsId") int newsId,
				           @RequestParam("content") String content) {
				try {
					Comment comment = new Comment();
					comment.setuId(userHostHolder.getUser().getuId());
					comment.setcContent(content);
					comment.setEntityType(EntityType.ENTITY_NEWS);
					comment.setEntityId(newsId);
					comment.setcDate(new Date());
					comment.setStatus(0);
					commentService.addComment(comment);
				
					// 更新评论数量
					int count = commentService.getCommentCount(comment.getEntityId(), comment.getEntityType());
					newsService.updateCommentCount(comment.getEntityId(), count);
					
				} catch (Exception e) {
				logger.error("增加评论错误" + e.getMessage());
				}
				return "redirect:/news/" + String.valueOf(newsId);
				}
				}

