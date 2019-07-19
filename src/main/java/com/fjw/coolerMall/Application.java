package com.fjw.coolerMall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SpringBoot启动主程序
 * @author jiawei
 * 2018年7月15日下午5:58:37
 */
@SpringBootApplication(scanBasePackages= {"com.fjw.coolerMall.*"})
@EnableSwagger2
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}