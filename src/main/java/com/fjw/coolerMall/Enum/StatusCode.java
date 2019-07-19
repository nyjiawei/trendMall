package com.fjw.coolerMall.Enum;

public enum StatusCode {
    SYSTEM_NORMAL(200, "SUCEESS"),
    INVALID_PARAM(401,"未授权"),
    SERVICE_NOT_FOUND(404,"服务器端无法找到所请求的资源"),
    SERVICE_ERROR(500, "内部服务器错误"),
    SERVICE_OFF(405,"资源被禁止");

    private int code;
    private String desc;

    StatusCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
