package com.nowcoder.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nowcoder.model.Comment;


@Mapper
public interface CommentDAO {
    String TABLE_NAME = " comment ";
    String INSERT_FIELDS = " u_id, c_content, c_date, entity_id, entity_type, status ";
    String SELECT_FIELDS = " c_id, c_content, u_id, entity_id, entity_type, c_date, status ";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{uId},#{cContent},#{cDate},#{entityId},#{entityType},#{status})"})
    int addComment(Comment comment);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where entity_type=#{entityType} and entity_id=#{entityId} order by c_id desc "})
    List<Comment> selectByEntity(@Param("entityId") int entityId, @Param("entityType") int entityType);

    @Select({"select count(c_id) from ", TABLE_NAME, " where entity_type=#{entityType} and entity_id=#{entityId}"})
    int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);
}