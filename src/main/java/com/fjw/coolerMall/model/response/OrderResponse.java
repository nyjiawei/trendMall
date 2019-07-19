package com.fjw.coolerMall.model.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fjw.coolerMall.model.request.OrderInfo;


@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderResponse implements Serializable{
	@JsonProperty("result")
	private String result;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("entities")
	private List<OrderInfo> entities;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<OrderInfo> getEntities() {
		return entities;
	}

	public void setEntities(List<OrderInfo> entities) {
		this.entities = entities;
	}
	
}
