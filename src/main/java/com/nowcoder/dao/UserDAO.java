package com.nowcoder.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nowcoder.model.User;

@Mapper
public interface UserDAO {

	String TABLE_NAME=" user ";
	String INSERT_FIELDS=" u_name,u_password,head_url,salt ";
	String SELECT_FIELDS=" u_id,u_name,u_password,head_url,salt ";
	@Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") Values(#{uName},#{uPassword},#{headUrl},#{salt})",})
	int addUser(User user);
	
	
	@Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where u_id=#{uId}"})
	User selectById(int uId);
	
	@Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where u_name=#{uName}"})
	User selectByuName(String uName);
	
	@Update({"update",TABLE_NAME,"set u_name=#{uName} where u_id=#{uId}"})
	void updateuName(User user);
	
	@Delete({"delete from",TABLE_NAME,"where u_id=#{uId}"})
	void deleteById(int uId);
	
	
	
}


