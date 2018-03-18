package com.nowcoder.controller;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowcoder.dao.MessageDAO;
import com.nowcoder.model.Message;
import com.nowcoder.model.User;
import com.nowcoder.model.UserHostHolder;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.MessageService;
import com.nowcoder.service.UserService;
import com.nowcoder.util.TingUtil;
@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    UserHostHolder userhostHolder;

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;
    

    @RequestMapping(path = {"/msg/detail"}, method = {RequestMethod.GET})
    public String conversationDetail(Model model, @Param("conversationId") String conversationId) {
    	try {
            List<ViewObject> messages = new ArrayList<>();
            List<Message> conversationList = messageService.getConversationDetail(conversationId, 0, 10);
            for (Message msg : conversationList) {
                ViewObject vo = new ViewObject();
                vo.set("message", msg);
                User user = userService.getUser(msg.getFromId());
                if (user == null) {
                    continue;
                }
                vo.set("headUrl", user.getHeadUrl());
                vo.set("userId", user.getuId());
                messages.add(vo);
            }
            model.addAttribute("messages", messages);
            return "letterDetail";
        } catch (Exception e) {
            logger.error("获取站内信列表失败" + e.getMessage());
        }
        return "letterDetail";
    }

    @RequestMapping(path = {"/msg/list"}, method = {RequestMethod.GET})

   public String conversationList(Model model) {
        try {
            int localUserId = userhostHolder.getUser().getuId();
            List<ViewObject> conversations = new ArrayList<>();
            List<Message> conversationList = messageService.getConversationList(localUserId, 0, 10);
            for (Message msg : conversationList) {
                ViewObject vo = new ViewObject();
                vo.set("conversation", msg);
                int targetId = msg.getFromId() == localUserId ? msg.getToId() : msg.getFromId();
                User user = userService.getUser(targetId);
                vo.set("headUrl", user.getHeadUrl());
                vo.set("userName", user.getuName());
                vo.set("userId", user.getuId());
                vo.set("totalCount", msg.getmId());
                vo.set("unreadCount", messageService.getUnreadCount(localUserId, msg.getConversationId()));
                
                conversations.add(vo);
            }
            model.addAttribute("conversations", conversations);
            return "letter";
        } catch (Exception e) {
            logger.error("获取站内信列表失败" + e.getMessage());
        }
        return "letter";
    }

    @RequestMapping(path = {"/msg/addMessage"}, method = {RequestMethod.POST})
    @ResponseBody
    public String addMessage(@RequestParam("fromId") int fromId,
                                   @RequestParam("toId") int toId,
                                   @RequestParam("content") String content) {
      try{
    	  Message msg = new Message();
          msg.setmContent(content);
          msg.setmDate(new Date());
          msg.setToId(toId);
          msg.setFromId(fromId);
          msg.setConversationId(fromId < toId ? String.format("%d_%d", fromId, toId):String.format("%d_%d", toId, fromId));
          messageService.addMessage(msg);
          return TingUtil.getJSONString(msg.getmId());
      }catch(Exception e){
    	  logger.error("增加评论失败"+e.getMessage());
    	  return TingUtil.getJSONString(1,"插入评论失败");
      }
    	
   
    }
}
