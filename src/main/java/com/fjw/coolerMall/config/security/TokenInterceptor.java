package com.fjw.coolerMall.config.security;

import com.alibaba.fastjson.JSONObject;
import com.fjw.coolerMall.Enum.StatusCode;
import com.fjw.coolerMall.model.response.CommonResponse;
import com.fjw.coolerMall.util.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jiawe
 * @description token拦截器。每次请求触发之前从request的头里面取出token，进而校验
 * @date 2019/6/8
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            response.setCharacterEncoding("UTF-8");
            String token = request.getHeader("accessToken");
            if (null != token) {
                boolean result = JWTUtil.verify(token);
                if (result) {
                    return true;
                }
            }
            response.getWriter().write(JSONObject.toJSONString(
                    new CommonResponse(StatusCode.INVALID_PARAM.getCode(),StatusCode.INVALID_PARAM.getDesc(),null)));
        } catch (Exception e) {
            response.getWriter().write(JSONObject.toJSONString(new CommonResponse(
                    StatusCode.INVALID_PARAM.getCode(),e.getMessage(),null)));
        }

        return false;
    }
}
