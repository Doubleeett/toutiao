package com.nowcoder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowcoder.dao.MessageDAO;
import com.nowcoder.model.Message;

@Service
public class MessageService {
	   @Autowired
	    private MessageDAO messageDAO;

	    public int addMessage(Message message) {
	        return messageDAO.addMessage(message);
	    }

	    public List<Message> getConversationList(int uId, int offset, int limit) {
	        return messageDAO.getConversationList(uId, offset, limit);
	    }

	    public List<Message> getConversationDetail(String conversationId, int offset, int limit) {
	    	return messageDAO.getConversationDetail(conversationId, offset, limit);
	    }

	    public int getUnreadCount(int uId, String conversationId) {
	        return messageDAO.getConversationUnReadCount(uId, conversationId);
	    }
}
