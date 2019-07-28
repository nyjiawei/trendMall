package com.fjw.coolerMall.util;

import java.util.*;


/**
 * 订单号工具类
 * @author jiawei
 * 2018年8月12日上午9:46:32
 */
public class OrderUtil {
	
	/**
	 * 生成uuid
	 * @return uuid
	 */
	public static String getUUId(){
	    return UUID.randomUUID().toString();
	} 
	
	
	/**
	 * 生成uuid(去“-”版)
	 * @return uuid
	 */
	public static String getFormatUUId(){
		String uuid = UUID.randomUUID().toString();
	    return uuid.replace("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(getFormatUUId());
	}
}
