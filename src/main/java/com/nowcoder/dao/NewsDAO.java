package com.nowcoder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nowcoder.model.News;

@Mapper
public interface NewsDAO {
	
	String TABLE_NAME=" news ";
	String INSERT_FIELDS=" title,link,image,comment_count,u_id,n_date,like_count ";
	String SELECT_FIELDS=" n_id,title,link,image,like_count,comment_count,n_date,u_id ";
	
	@Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{title},#{link},#{image},#{commentCount},#{uId},#{nDate},#{likeCount})",})
	int addNews(News news);
	
	@Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where n_id=#{nId}"})
	News getBynId(int nId);
	
	@Update({"update",TABLE_NAME,"set comment_count=#{commentCount} where n_id=#{nId}"})
	int updateCommentCount(@Param("nId") int nId,@Param("commentCount") int commentCount);
	
	//不用定义怎么写，利用XML
	//从数据库选取一批News，参数是用来分页
	List<News> selectByUserIdAndOffset(@Param("uId") int uId,@Param("offset") int offset,@Param("limit") int limit);
	
	
	
}
