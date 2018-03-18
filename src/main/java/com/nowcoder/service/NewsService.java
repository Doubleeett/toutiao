package com.nowcoder.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nowcoder.dao.NewsDAO;
import com.nowcoder.model.News;
import com.nowcoder.util.TingUtil;

@Service
public class NewsService {
	
	@Autowired
	private NewsDAO newsDAO;
	
	public List<News> getLatestNews(int uId,int offset,int limit){
		return newsDAO.selectByUserIdAndOffset(uId, offset, limit);
	}
	
	public int addNews(News news){
		newsDAO.addNews(news);
		return news.getnId();
	}
	
	public News getBynId(int nId){
		return newsDAO.getBynId(nId);
	}
	
	public String saveImage(MultipartFile file) throws IOException{
		int doPos=file.getOriginalFilename().lastIndexOf(".");
		if(doPos<0){
			return null;
		}
		String fileExt=file.getOriginalFilename().substring(doPos+1).toLowerCase();
		if(!TingUtil.isFileAllowed(fileExt)){
			return null;
		}
		
		String fileName=UUID.randomUUID().toString().replaceAll("-", "")+"."+fileExt;
		Files.copy(file.getInputStream(), new File(TingUtil.IMAGE_DIR+fileName).toPath(),
						StandardCopyOption.REPLACE_EXISTING);
		return TingUtil.DOMAIN+"image?name="+fileName;
	}
	
	public int updateCommentCount(int nId,int count) {
		return newsDAO.updateCommentCount(nId,count);
	}

}
