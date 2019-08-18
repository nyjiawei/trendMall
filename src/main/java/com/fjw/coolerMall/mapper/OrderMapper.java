package com.fjw.coolerMall.mapper;

import java.util.List;
import java.util.Map;


import com.fjw.coolerMall.entry.Order;
import com.fjw.coolerMall.model.request.OrderInfo;
import com.fjw.coolerMall.model.request.OrderRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;


public interface OrderMapper {

	public void createOrder (Order order) throws DataAccessException;
	
	public void insertOrderCommodity (@Param(value = "id")String id,
			@Param(value = "commodityList") List<Map<String, Object>> commodityList) throws DataAccessException;
	
	public List<OrderInfo> getOrderList (@Param(value = "status")String status, @Param(value = "date")String date,
										 @Param(value = "user") String user, @Param(value = "orderNo")String orderNo) throws DataAccessException;
	
	public List<Map<String, Object>> getOrderCommodityList(@Param(value = "id")String id) throws DataAccessException;
	
	public void deleteOrder(@Param(value = "id")String id) throws DataAccessException;
}
