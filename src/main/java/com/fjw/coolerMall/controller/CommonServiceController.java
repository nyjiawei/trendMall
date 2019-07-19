package com.fjw.coolerMall.controller;


import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fjw.coolerMall.model.request.LoginRequest;
import com.fjw.coolerMall.model.request.RegisterRequest;
import com.fjw.coolerMall.model.response.CommonResponse;
import com.fjw.coolerMall.model.response.ProductResponse;
import com.fjw.coolerMall.model.response.RegisterResponse;
import com.fjw.coolerMall.service.CommonService;
import com.fjw.coolerMall.service.delegate.CommonSerivceDelegate;
import com.fjw.coolerMall.util.CreateVerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommonServiceController implements CommonService {
	
	@Autowired
	private CommonSerivceDelegate commonSerivceDelegate;
	
	@Override
	public CommonResponse userLogin(@RequestBody LoginRequest data) {
		return commonSerivceDelegate.userLogin(data);
	}

	@Override
	public RegisterResponse userRegister(@RequestBody RegisterRequest userInfo) {
		return commonSerivceDelegate.userRegister(userInfo);
	}
	
	@Override
	public ProductResponse getProductResult(@RequestParam(value="filterName",required=false)String filterName, @RequestParam(value="classify",required=false)String classify,
											@RequestParam(value="sortKey",required=false)String sortKey, @RequestParam(value="sortDir",required=false)String sortDir, @RequestParam(value="brand",required=false)String brand,
											@RequestParam(value="pageNo",required=true)String pageNo, @RequestParam(value="pageSize",required=true)String pageSize) {
		return commonSerivceDelegate.getProductResult(filterName, classify, sortKey, sortDir,brand, pageNo, pageSize);
	}
	
	@Override
	public JSONObject getIndexData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/verification", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void verification(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
	    
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		//实例生成验证码对象
		CreateVerificationCode code = new CreateVerificationCode();
		code.write(request, response);
		}
}
