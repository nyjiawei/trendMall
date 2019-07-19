package com.fjw.coolerMall.mapper;



import com.fjw.coolerMall.entry.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;


/**
 * dao层接口
 * @author jiawe
 * 2018年7月15日下午7:09:52
 */
public interface UserDao {
	
	public User login(@Param(value = "userName")String userName) throws DataAccessException;
	
	public int register(@Param(value="user")User user) throws DataAccessException;
	
}
