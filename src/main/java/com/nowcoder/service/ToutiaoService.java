package com.nowcoder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowcoder.dao.NewsDAO;
import com.nowcoder.model.News;

/**
 * Created by nowcoder on 2016/6/26.
 */
@Service
public class ToutiaoService {
    
    @Autowired
    private NewsDAO newsDAO;

    public List<News> getLatestNews(int uId, int offset, int limit) {
        return newsDAO.selectByUserIdAndOffset(uId, offset, limit);
    }
}
