package com.fjw.coolerMall.mapper;

import com.fjw.coolerMall.model.request.Delivery;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import java.util.List;

/**
 * dao层接口
 * @author jiawe
 * 2018年7月15日下午7:09:52
 */
public interface DeliveryMapper {
	
	public int createDelivery(@Param(value="delivery") Delivery deliveryInfo, @Param(value="user")String user) throws DataAccessException;
	
	public int updateDelivery(@Param(value="delivery")Delivery deliveryInfo) throws DataAccessException;
	
	public void deleteDelivery(@Param(value="id")String id) throws DataAccessException;
	
	public List<Delivery> getDeliveryResult(@Param(value="user")String user)throws DataAccessException;
}
