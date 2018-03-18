package com.nowcoder;

import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nowcoder.dao.CommentDAO;
import com.nowcoder.dao.LoginTicketDAO;
import com.nowcoder.dao.NewsDAO;
import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.Comment;
import com.nowcoder.model.EntityType;
import com.nowcoder.model.LoginTicket;
import com.nowcoder.model.News;
import com.nowcoder.model.User;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToutiaoApplication.class)
//@WebAppConfiguration  这行会修改默认的启动路径需要注释掉
@Sql({"/init-schema.sql"})
public class InitDatabaseTests {
    @Autowired
    UserDAO userDAO;
    
    @Autowired
    NewsDAO newsDAO;
    
    @Autowired
    LoginTicketDAO loginTicketDAO;
    
    @Autowired
    CommentDAO commentDAO;

    @SuppressWarnings("deprecation")
	@Test
    public void InitDataBase() {
    	Random r = new Random();
        /*for (int i = 1; i < 6; ++i) {
            User user = new User();
            user.setuName(String.format("user%d", i));
            user.setHeadUrl(String.format("http://localhost:8080/image?name=head%d.jpg", i-1));
            user.setuPassword("");
            user.setSalt("aaa");
            userDAO.addUser(user);
            
            //Update失败
            user.setuName("newName");
            
            System.out.println(user.getuName());
            
            userDAO.updateuName(user);
            
            System.out.println(userDAO.selectById(i).getuName());
            
            News news=new News();
            news.setTitle(String.format("这是第%d个用户发布的一个资讯", i));
            news.setuId(i);
            news.setCommentCount(i+2);
            news.setImage(String.format("http://localhost:8080/image?name=head%d.jpg", i-1));
            news.setLikeCount(i);
            Date date=new Date();
            date.setTime(date.getTime()+1000*3600*5*i);
            news.setnDate(date);
            news.setLink(String.format("localhost:8080/%d.html", i));
            newsDAO.addNews(news);
            
            // 给每个资讯插入3个评论
            for(int j = 0; j < 3; ++j) {
                Comment comment = new Comment();
                comment.setuId(i+1);
                comment.setcDate(new Date());
                comment.setStatus(0);
                comment.setcContent("这里是一个评论啊！" + String.valueOf(j));
                comment.setEntityId(news.getnId());
                comment.setEntityType(EntityType.ENTITY_NEWS);
                commentDAO.addComment(comment);
            }
            
            
            LoginTicket loginTicket=new LoginTicket();
            loginTicket.setStatus(0);
            loginTicket.setuId(i);
            loginTicket.setExpired(date);
            loginTicket.setTicket(String.format("ticket%d", i));
            
            loginTicketDAO.addLoginTicket(loginTicket);
            loginTicketDAO.updatestatus(loginTicket.getTicket(), 1);
        }
        
      // Assert.assertEquals("newName", userDAO.selectById(1).getuName());
        userDAO.deleteById(1);
        Assert.assertNull(userDAO.selectById(1));
        Assert.assertEquals(1, loginTicketDAO.selectByticket("ticket1").getuId());
        Assert.assertEquals(1, loginTicketDAO.selectByticket("ticket1").getStatus());
        System.out.println(commentDAO.getCommentCount(0,1));*/
    }
}

