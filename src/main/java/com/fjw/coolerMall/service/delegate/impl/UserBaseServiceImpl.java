package com.fjw.coolerMall.service.delegate.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.fjw.coolerMall.Enum.OrderStatus;
import com.fjw.coolerMall.entry.Order;
import com.fjw.coolerMall.mapper.DeliveryMapper;
import com.fjw.coolerMall.mapper.OrderMapper;
import com.fjw.coolerMall.model.request.AddDeliveryRequest;
import com.fjw.coolerMall.model.request.Delivery;
import com.fjw.coolerMall.model.request.OrderInfo;
import com.fjw.coolerMall.model.request.OrderRequest;
import com.fjw.coolerMall.model.response.CommonResponse;
import com.fjw.coolerMall.model.response.DeliveryResponse;
import com.fjw.coolerMall.model.response.OrderResponse;
import com.fjw.coolerMall.service.delegate.UserSerivceDelegate;
import com.fjw.coolerMall.util.OrderUtil;
import com.fjw.coolerMall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;


/**
 * userBaseService
 * @author jiawei
 * 2018年7月29日下午5:36:20
 */
@Component
public class UserBaseServiceImpl implements UserSerivceDelegate {
	
	Logger log = LogManager.getLogger(UserBaseServiceImpl.class);
	
	@Autowired
	private DeliveryMapper deliveryMapper;

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public CommonResponse updateOrCreateDeliveryInfo(AddDeliveryRequest deliveryInfo) {
		String userName = request.getHeader("x-user-name");
		if (StringUtils.isAllEmpty(userName) && deliveryInfo == null) {
			return getErrorResponse("invalid param!");
		}
		try {
			Delivery delivery = formatDeliveryRequest(deliveryInfo);
			if (StringUtils.isEmpty(deliveryInfo.getId())) {
				//id为空新增
				deliveryMapper.createDelivery(delivery,userName);
			} else {
				//有id说明是修改
				deliveryMapper.updateDelivery(delivery);
			} 
			return new CommonResponse();
		} catch (Exception e) {
			log.error("updateOrCreateDelivery was failed the reason:"+ e.toString());
			return getErrorResponse("内部错误");
		}
		
	}
	
	private Delivery formatDeliveryRequest(AddDeliveryRequest deliveryInfo) {
		Delivery delivery = new Delivery();
		List<String> city = deliveryInfo.getCity();
		StringBuffer address = new StringBuffer();
		//把完整地址拼接起来
		for (String data : city) {
			address.append(data);
		}
		address.append(deliveryInfo.getDesc());
		String gender = deliveryInfo.getGender();
		if (gender.equals("true")) {
			delivery.setIsDefault(true);
		}else {
			delivery.setIsDefault(false);
		}
		if (StringUtils.isNotEmpty(deliveryInfo.getId())) {
			delivery.setId(deliveryInfo.getId());
		}
		delivery.setAddress(address.toString());
		delivery.setLabel(deliveryInfo.getTotile());
		delivery.setOwnerId(deliveryInfo.getName());
		delivery.setPersonName(deliveryInfo.getName());
		delivery.setPhoneNumber(deliveryInfo.getPhone());
		return delivery;
	}

	@Override
	public CommonResponse deleteDeliveryInfo(String deliveryId) {
		if (StringUtils.isEmpty(deliveryId)) {
			return getErrorResponse("invalid param!");
		}
		try {
			deliveryMapper.deleteDelivery(deliveryId);
			return new CommonResponse();
		} catch (Exception e) {
			log.error("deleteDelivery was failed! the reason:" + e.toString());
			return getErrorResponse("内部错误");
		}
		
	}

	@Override
	public DeliveryResponse getDelivery(String user) {
		DeliveryResponse deliveryResponse = new DeliveryResponse();
		if (StringUtils.isEmpty(user)) {
			deliveryResponse.setResult("error");
			deliveryResponse.setMessage("invalid param!");
			return deliveryResponse;
		}
		try {
			List<Delivery> deliveryList = deliveryMapper.getDeliveryResult(user);
			deliveryResponse.setResult("OK");
			deliveryResponse.setMessage("success");
			deliveryResponse.setDeliveryList(deliveryList);
			return deliveryResponse;
		} catch (Exception e) {
			log.error("getDelivery was failed the reason: "+ e.toString());
			deliveryResponse.setResult("error");
			deliveryResponse.setMessage("内部错误");
			return deliveryResponse;
		}
		
	}
	
	private CommonResponse getErrorResponse(String reason) {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setCode(500);
		commonResponse.setMessage(reason);
		return commonResponse;
	}

	@Override
	public CommonResponse commitOrder(OrderRequest orderRequest) {
		String user = request.getHeader("x-user-name");
		List<Map<String, Object>> productList = orderRequest.getProductList();
		List<Map<String, Object>> cartList = orderRequest.getCartList();
		if (orderRequest == null && StringUtils.isNotEmpty(user) && productList.size()>0) {
			 getErrorResponse("invalid param!");
		}
		productList.removeAll(Collections.singleton(null));
		for (int i = 0; i < cartList.size(); i++) {
			Integer count = (Integer)cartList.get(i).get("count");
			productList.get(i).put("count", count);
		}
		try {
			Order order = new Order();
			BeanUtils.copyProperties(orderRequest, order);
			//获取一个UUID
			String commodityId = OrderUtil.getFormatUUId();
			order.setOwnerId(user);
			order.setCommodityId(commodityId);
			orderMapper.createOrder(order);
			orderMapper.insertOrderCommodity(commodityId, productList);
			log.info("订单号：" + order.getId());
			redisUtil.set(order.getId().toString(), OrderStatus.WAITING_PAY,5);
		} catch (DataAccessException e) {
			log.error("commitOrder was failed! the reason:" + e.toString());
			getErrorResponse(e.getMessage());
		}
		return new CommonResponse();
	}

	@Override
	public OrderResponse getOrderList(String status, String user, String date, String orderNo) {
		OrderResponse result = new OrderResponse();
		if (StringUtils.isEmpty(user)) {
			result.setResult("error");
			result.setMessage("invalid param!");
			return result;
		}
		List<OrderInfo> orderList = orderMapper.getOrderList(status, user, date, orderNo);
		if (orderList.size()>0) {
			for (OrderInfo orderInfo : orderList) {
				List<Map<String, Object>> orderCommodityList = orderMapper.getOrderCommodityList(orderInfo.getCommodityId());
				orderInfo.setProductList(orderCommodityList);
			}
			result.setEntities(orderList);
		}
		result.setResult("success");
		result.setMessage("success!");
		return result;
	}

	@Override
	public CommonResponse deleteOrderById(String id) {
		if (StringUtils.isEmpty(id)) {
			return getErrorResponse("invalid param!");
		}
		try {
			orderMapper.deleteOrder(id);
		} catch (DataAccessException e) {
			log.error("deleteOrderById was failed! the reason:" + e.toString());
		}
		return new CommonResponse();
	}
}
