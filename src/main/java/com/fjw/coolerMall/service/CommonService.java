package com.fjw.coolerMall.service;

import com.alibaba.fastjson.JSONObject;

import com.fjw.coolerMall.model.request.LoginRequest;
import com.fjw.coolerMall.model.request.RegisterRequest;
import com.fjw.coolerMall.model.response.CommonResponse;
import com.fjw.coolerMall.model.response.ProductResponse;
import com.fjw.coolerMall.model.response.RegisterResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.annotations.ApiOperation;


public interface CommonService {
	
	@RequestMapping(value = "/login", method = RequestMethod.POST,produces="application/json")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public CommonResponse userLogin(@RequestBody LoginRequest userdata);
	    
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces="application/json")
    @ApiOperation(value = "用户注册", notes = "新用户注册")
	public RegisterResponse userRegister(@RequestBody RegisterRequest userInfo);
	
    @RequestMapping(value = "/index", method = RequestMethod.GET,produces="application/json")
    @ApiOperation(value = "获取首页", notes = "获取首页数据")
	public JSONObject getIndexData();
    
    @RequestMapping(value = "/product", method = RequestMethod.GET,produces="application/json")
    @ApiOperation(value = "获取商品信息", notes = "获取商品信息")
	public ProductResponse getProductResult(@RequestParam(value="filterName",required=false)String filterName, @RequestParam(value="classify",required=false)String classify,
                                            @RequestParam(value="sortKey",required=false)String sortKey, @RequestParam(value="sortDir",required=false)String sortDir, @RequestParam(value="brand",required=false)String brand,
                                            @RequestParam(value="pageNo",required=true)String pageNo, @RequestParam(value="pageSize",required=true)String pageSize);
}
