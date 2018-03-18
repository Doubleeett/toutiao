package com.nowcoder.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.nowcoder.model.LoginTicket;

@Mapper
public interface LoginTicketDAO {

	String TABLE_NAME=" login_ticket ";
	String INSERT_FIELDS=" u_id,ticket,expired,status ";
	String SELECT_FIELDS=" l_id,u_id,ticket,expired,status ";
	@Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") Values(#{uId},#{ticket},#{expired},#{status})",})
	int addLoginTicket(LoginTicket loginTicket);
	
	
	@Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where ticket=#{ticket}"})
	LoginTicket selectByticket(String ticket);
	
	@Update({"update",TABLE_NAME,"set status=#{status} where ticket=#{ticket}"})
	void updatestatus(@Param("ticket") String ticket,@Param("status") int status);
	
}
