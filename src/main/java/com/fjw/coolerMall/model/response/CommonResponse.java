package com.fjw.coolerMall.model.response;

import java.io.Serializable;


public class CommonResponse implements Serializable {
	private static final long serialVersionUID = -4707714419984617242L;
	private String message = "SUCCESS";

	private int code = 200;

	private Object data;

	public CommonResponse() {};

	public CommonResponse(int code,String msg,Object object) {
		this.message = msg;
		this.code = code;
		this.data = object;
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
