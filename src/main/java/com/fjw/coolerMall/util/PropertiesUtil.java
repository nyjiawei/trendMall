package com.fjw.coolerMall.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 配置工具类
 * @author jiawei
 * 2018年8月1日下午9:27:02
 */
public class PropertiesUtil {
	private static Logger log = LogManager.getLogger(PropertiesUtil.class);

    private static Properties properties;

    static {
        String fileName = "jdbc.properties";
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        } catch (IOException e) {
            log.error("配置文件读取异常",e);
        }
    }

    public static String getProperty(String key){
        String value = properties.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key,String defaultValue){

        String value = properties.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }
}
