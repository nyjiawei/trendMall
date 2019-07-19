package com.fjw.coolerMall.service.delegate;


import com.fjw.coolerMall.model.request.AddDeliveryRequest;
import com.fjw.coolerMall.model.request.OrderRequest;
import com.fjw.coolerMall.model.response.CommonResponse;
import com.fjw.coolerMall.model.response.DeliveryResponse;
import com.fjw.coolerMall.model.response.OrderResponse;

/**
 * 代理
 * @author jiawe
 * email fjiawei123@foxmail.com
 * 2018年7月30日下午10:13:19
 */
public interface UserSerivceDelegate {
	public CommonResponse updateOrCreateDeliveryInfo(AddDeliveryRequest deliveryInfo);
	
	public CommonResponse deleteDeliveryInfo(String deliveryId);
	
	public DeliveryResponse getDelivery(String user);
	
	public CommonResponse commitOrder(OrderRequest orderRequest);
	
	public OrderResponse getOrderList(String status, String user, String date, String orderNo);
	
	public CommonResponse deleteOrderById(String id);
}
