package com.fjw.coolerMall.service.delegate.impl;

import java.util.List;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fjw.coolerMall.Enum.StatusCode;
import com.fjw.coolerMall.entry.User;
import com.fjw.coolerMall.mapper.CommodityDao;
import com.fjw.coolerMall.mapper.UserDao;
import com.fjw.coolerMall.model.request.LoginRequest;
import com.fjw.coolerMall.model.request.Product;
import com.fjw.coolerMall.model.request.RegisterRequest;
import com.fjw.coolerMall.model.response.CommonResponse;
import com.fjw.coolerMall.model.response.ProductResponse;
import com.fjw.coolerMall.model.response.RegisterResponse;
import com.fjw.coolerMall.service.delegate.CommonSerivceDelegate;
import com.fjw.coolerMall.util.HttpUtil;
import com.fjw.coolerMall.util.JWTUtil;
import com.fjw.coolerMall.util.PassWordUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CommonSerivceDelegateImpl implements CommonSerivceDelegate {

	private static final String arithmetic = "sha-256";
	Logger log = LogManager.getLogger(CommonSerivceDelegateImpl.class);
	
	@Autowired
	private CommodityDao commodityDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private HttpServletRequest request;	

	@Override
	public CommonResponse userLogin(LoginRequest data) {
		User user = userDao.login(data.getUserName());
		if (user == null) {
			String message = "用户不存在或密码不正确！";
			return getErrorResponse(message);
		} else {
			String salt = user.getSalt();
			String encWord = user.getPassWord();
			PassWordUtil pWordUtil = new PassWordUtil(salt, arithmetic);
			boolean isValid = pWordUtil.isPasswordValid(encWord,data.getPassWord());
			if(isValid) {
				String token = JWTUtil.createToken(user.getUserName(),user.getId());
				return new CommonResponse(StatusCode.SYSTEM_NORMAL.getCode(),
						StatusCode.SYSTEM_NORMAL.getDesc(),token);
			}
			return getErrorResponse("用户不存在或密码不正确!");
		}
	}

	@Override
	public RegisterResponse userRegister(RegisterRequest userInfo) {
		RegisterResponse response = new RegisterResponse();
		boolean codeReview = false;
		if(null == userInfo) {
			log.error("RegisterRequest:" + userInfo + "is null! ");
			return response;
		}
		String name = request.getHeader("x-user-name");
		String ip = HttpUtil.getIpAddress(request);
		codeReview = reviewCode(userInfo.getCode());
		if (codeReview) {
			try {
				String userName = userInfo.getUserName();
				String passWord = userInfo.getPassWord();
				String email = userInfo.getEmail();
				String salt = UUID.randomUUID().toString();
				PassWordUtil pWordUtil = new PassWordUtil(salt, arithmetic);
				String rawWord = pWordUtil.encode(passWord);
				User user = new User();
				user.setUserName(userName);
				user.setPassWord(rawWord);
				user.setEmail(email);
				user.setSalt(salt);
				int result = userDao.register(user);
				if(result>0) {
					response.setStatus("success");
					response.setMessage("恭喜您,注册成功！");
					response.setUserName(userName);
					return response;
				}
				response.setMessage("unknown error");
				response.setStatus("error");
				return response;
			} catch (Exception e) {
				log.error("register is failed [reason]:" + e.toString());
				response.setMessage("An internal error");
				return response;
			}
		} else {
			response.setStatus("error");
			response.setMessage("验证码错误！请重新输入");
			return response;
		}
	}
	
	/**
	 * 验证码比较
	 * @param Code
	 * @return
	 */
	private boolean reviewCode(String Code) {
		String ip = HttpUtil.getIpAddress(request);
		HttpSession session = request.getSession();
		String sCode = (String) session.getAttribute(ip);
		if (StringUtils.isNotEmpty(sCode)) {
			if (sCode.equalsIgnoreCase(Code)) {
				return true;
			}else {
				return false;
			}
		}
		else {
			return false;
		}
		
	}

	@Override
	public ProductResponse getProductResult(String filterName, String classify, String sortKey, String sortDir,
											String brand, String pageNo, String pageSize) {
		ProductResponse response = new ProductResponse();
		if (StringUtils.isAllEmpty(pageNo,pageSize)) {
			response.setResult("error");
			response.setMessage("Invalid params!");
			return response;
		}
		if (StringUtils.isAllEmpty(sortKey,sortDir)) {
			sortKey = "repertory";
			sortDir = "desc";
		}
		try {
			int no = Integer.parseInt(pageNo);
			int size = Integer.parseInt(pageSize);
			List<Product> products = commodityDao.getProductList(filterName, classify, sortKey,
					sortDir, brand,no, size);
			response.setTotal_count(commodityDao.getProductListCount(filterName, classify, brand));
			response.setEntities(products);
			response.setResult("success");
			response.setMessage("success");
			return response;
		} catch (Exception e) {
			log.error("get product list was failed! [reason]" + e.toString());
			response.setResult("error");
			response.setMessage("An internal error");
			return response;
		}
		
	}
	
	private CommonResponse getErrorResponse(String reason) {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setCode(500);
		commonResponse.setMessage(reason);
		return commonResponse;
	}

}
