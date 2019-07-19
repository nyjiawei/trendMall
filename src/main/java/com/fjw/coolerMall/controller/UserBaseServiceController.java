package com.fjw.coolerMall.controller;

import com.fjw.coolerMall.model.request.AddDeliveryRequest;
import com.fjw.coolerMall.model.request.OrderRequest;
import com.fjw.coolerMall.model.response.CommonResponse;
import com.fjw.coolerMall.model.response.DeliveryResponse;
import com.fjw.coolerMall.model.response.OrderResponse;
import com.fjw.coolerMall.service.UserBaseService;
import com.fjw.coolerMall.service.delegate.UserSerivceDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * API
 * @author jiawei
 * 2018年7月29日下午6:23:36
 */
@RestController
public class UserBaseServiceController implements UserBaseService {
	
	@Autowired
	private UserSerivceDelegate userSerivceDelegate;

	@Override
	public String loginOut(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponse createrOrUpdateDelivery(@RequestBody AddDeliveryRequest deliveryInfo) {
		return userSerivceDelegate.updateOrCreateDeliveryInfo(deliveryInfo);
	}

	@Override
	public DeliveryResponse getDelivery(@RequestHeader(value = "x-user-name",required=true)String userName) {
		return userSerivceDelegate.getDelivery(userName);
	}

	@Override
	public CommonResponse commitToOrder(@RequestBody OrderRequest order) {
		return userSerivceDelegate.commitOrder(order);
	}

	@Override
	public OrderResponse getOrderList(@RequestParam(value = "status",required=false)String status, @RequestHeader(value = "x-user-name",required=true)String user,
									  @RequestParam(value = "date",required=false)String date, @RequestParam(value = "orderNo",required=false)String orderNo) {
		return userSerivceDelegate.getOrderList(status, user, date, orderNo);
	}

	@Override
	public CommonResponse deleteOrder(@RequestParam(value = "orderId",required=true)String orderId) {
		return userSerivceDelegate.deleteOrderById(orderId);
	}

	@Override
	public String pay(String OrderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addToCart(String userName, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String collection(String id, String isCollect) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponse deleteDelivery(@RequestParam(value = "deliveryId",required=true)String deliveryId) {
		return userSerivceDelegate.deleteDeliveryInfo(deliveryId);
	}
	
}
