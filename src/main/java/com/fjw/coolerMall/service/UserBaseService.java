package com.fjw.coolerMall.service;


import com.fjw.coolerMall.model.request.AddDeliveryRequest;
import com.fjw.coolerMall.model.request.OrderRequest;
import com.fjw.coolerMall.model.response.CommonResponse;
import com.fjw.coolerMall.model.response.DeliveryResponse;
import com.fjw.coolerMall.model.response.OrderResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * user基础接口
 * @author jiawei
 * 2018年7月14日下午5:32:09
 */

@RequestMapping(value = "/user")
public interface UserBaseService {
    
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET, produces="application/json")
    @ApiOperation(value = "注销", notes = "注销当前用户")
    public String loginOut(@RequestParam(value = "user",required=false)String user);
	
    @RequestMapping(value = "/delivery", method = RequestMethod.POST, produces="application/json")
    @ApiOperation(value = "新增或更新收货信息", notes = "新增或更新收货信息")
    public CommonResponse createrOrUpdateDelivery(@RequestBody AddDeliveryRequest deliveryInfo);
    
    @RequestMapping(value = "/delivery", method = RequestMethod.DELETE, produces="application/json")
    @ApiOperation(value = "删除收货信息", notes = "用户删除收货信息")
    public CommonResponse deleteDelivery(@RequestParam(value = "deliveryId",required=true)String deliveryId);
    
    @RequestMapping(value = "/delivery", method = RequestMethod.GET, produces="application/json")
    @ApiOperation(value = "获取收货信息", notes = "获取当前用户收货信息")
    public DeliveryResponse getDelivery(@RequestHeader(value = "x-user-name",required=true)String userName);
    
    @RequestMapping(value = "/cart/{userName}", method = RequestMethod.GET, produces="application/json")
    @ApiOperation(value = "添加购物车", notes = "添加商品到购物车")
    @ApiParam(value="id", name="商品id")
    public String addToCart(@PathVariable(value = "userName",required=false)String userName, @RequestParam(value = "id",required=true)String id);
    
    @RequestMapping(value = "/orders", method = RequestMethod.POST, produces="application/json")
    @ApiOperation(value = "提交订单", notes = "提交订单")
    public CommonResponse commitToOrder(@RequestBody OrderRequest order);
    
    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces="application/json")
    @ApiOperation(value = "查看订单", notes = "按条件查看")
    public OrderResponse getOrderList(@RequestParam(value = "status",required=false)String status, @RequestHeader(value = "x-user-name",required=true)String user,
                                      @RequestParam(value = "date",required=false)String date, @RequestParam(value = "orderNo",required=false)String OrderNo);
    
    @RequestMapping(value = "/orders", method = RequestMethod.DELETE, produces="application/json")
    @ApiOperation(value = "删除订单", notes = "删除或者批量订单")
    public CommonResponse deleteOrder(@RequestParam(value = "orderId",required=true)String orderId);
    
    @RequestMapping(value = "/pay", method = RequestMethod.POST, produces="application/json")
    @ApiOperation(value = "支付", notes = "支付")
    public String pay(@RequestParam(value = "OrderId",required=true)String OrderId);

    @RequestMapping(value = "/collect/{userName}", method = RequestMethod.GET, produces="application/json")
    @ApiOperation(value = "收藏", notes = "关注商品或者取消关注")
    public String collection(@RequestParam(value = "id",required=true)String id, @RequestParam(value = "isCollect",required=true)String isCollect);
}
