package com.fjw.coolerMall.model;

import java.io.Serializable;

/**
 * @author jiawe
 * @description 统一接口返回格式
 * @date 2019/6/8
 */
public class Result implements Serializable {

    private String message;

    private int code;

    private Object data;

    public Result() {};

    public Result(int code,String msg,Object object) {
        this.message = msg;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
