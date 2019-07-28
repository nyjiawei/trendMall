package com.fjw.coolerMall.service.delegate;


import com.fjw.coolerMall.model.request.LoginRequest;
import com.fjw.coolerMall.model.request.RegisterRequest;
import com.fjw.coolerMall.model.response.CommonResponse;
import com.fjw.coolerMall.model.response.ProductResponse;
import com.fjw.coolerMall.model.response.RegisterResponse;

/**
 * 代理类
 */
public interface CommonSerivceDelegate {

    /**
     * 用户登录
     * @param data
     * @return
     */
	public CommonResponse userLogin(LoginRequest data);

    /**
     * 用户登录注册
     * @param userInfo
     * @return
     */
    public RegisterResponse userRegister(RegisterRequest userInfo);

    /**
     * 获取商品列表
     * @param filterName 过滤词
     * @param classify 分类信息
     * @param sortKey  排序条件
     * @param sortDir  排序方式
     * @param brand    品牌
     * @param pageNo   页码
     * @param pageSize 条数
     * @return
     */
    public ProductResponse getProductResult(String filterName, String classify, String sortKey, String sortDir,
                                            String brand, String pageNo, String pageSize);
	
}
