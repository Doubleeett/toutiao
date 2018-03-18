package com.nowcoder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nowcoder.model.Message;

@Mapper
public interface MessageDAO {
	String TABLE_NAME = " message ";
    String INSERT_FIELDS = " from_id, to_id, m_content, has_read, conversation_id, m_date ";
    String SELECT_FIELDS = " m_id, from_id, to_id, m_content, m_date, has_read, conversation_id ";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,") values (#{fromId},#{toId},#{mContent},#{hasRead},#{conversationId},#{mDate})"})
    int addMessage(Message message);

    @Select({"select ", INSERT_FIELDS, " ,count(m_id) as m_id from ( select * from ", TABLE_NAME, " where from_id=#{uId} or to_id=#{uId} order by m_id desc) tt group by conversation_id order by m_id desc limit #{offset},#{limit}"})
    List<Message> getConversationList(@Param("uId") int uId, @Param("offset") int offset, @Param("limit") int limit);

    @Select({"select count(m_id) from ", TABLE_NAME, " where has_read = 0 and to_id=#{uId} and conversation_id=#{conversationId}"})
    int getConversationUnReadCount(@Param("uId") int uId, @Param("conversationId") String conversationId);

    @Select({"select count(m_id) from ", TABLE_NAME, " where has_read = 0 and to_id=#{uId}"})
    int getConversationTotalCount(@Param("uId") int uId, @Param("conversationId") String conversationId);

    @Select({"select ", SELECT_FIELDS, "from", TABLE_NAME, "where conversation_id=#{conversationId} order by m_id desc limit #{offset},#{limit}"})
    List<Message> getConversationDetail(@Param("conversationId") String conversationId, @Param("offset") int offset, @Param("limit") int limit);
}
