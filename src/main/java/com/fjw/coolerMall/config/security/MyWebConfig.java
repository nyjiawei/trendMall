package com.fjw.coolerMall.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author jiawe
 * @description 拦截器注册
 * @date 2019/6/8
 */
@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/user/**")
                .excludePathPatterns("/login");
        super.addInterceptors(registry);
    }

}
