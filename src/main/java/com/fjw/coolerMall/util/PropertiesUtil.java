package com.fjw.coolerMall.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 读取配置工具类
 * @author jiawei
 * 2018年8月1日下午9:27:02
 */
public class PropertiesUtil {
	private static Logger log = LogManager.getLogger(PropertiesUtil.class);

    private static Properties properties;

    static {
        //要读取的文件名
        String fileName = "jdbc.properties";
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        } catch (IOException e) {
            log.error("配置文件读取异常",e);
        }
    }

    /**
     * 读取配置文件中的key
     * @param key
     * @return
     */
    public static String getProperty(String key){
        String value = properties.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    /**
     * 读取配置文件中的key带默认参数版
     * @param key
     * @return
     */
    public static String getProperty(String key,String defaultValue){

        String value = properties.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }
}
