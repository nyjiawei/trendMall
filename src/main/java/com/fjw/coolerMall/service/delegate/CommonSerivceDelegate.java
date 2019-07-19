package com.fjw.coolerMall.service.delegate;


import com.fjw.coolerMall.model.request.LoginRequest;
import com.fjw.coolerMall.model.request.RegisterRequest;
import com.fjw.coolerMall.model.response.CommonResponse;
import com.fjw.coolerMall.model.response.ProductResponse;
import com.fjw.coolerMall.model.response.RegisterResponse;

public interface CommonSerivceDelegate {

	public CommonResponse userLogin(LoginRequest data);
    
    public RegisterResponse userRegister(RegisterRequest userInfo);
    
    public ProductResponse getProductResult(String filterName, String classify, String sortKey, String sortDir,
                                            String brand, String pageNo, String pageSize);
	
}
