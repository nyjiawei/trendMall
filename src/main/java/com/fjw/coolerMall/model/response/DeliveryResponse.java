package com.fjw.coolerMall.model.response;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fjw.coolerMall.model.request.Delivery;


@JsonIgnoreProperties(ignoreUnknown=true)
public class DeliveryResponse {
	@JsonProperty("result")
	private String result;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("deliveryList")
	private List<Delivery> deliveryList;

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

	public List<Delivery> getDeliveryList() {
		return deliveryList;
	}

	public void setDeliveryList(List<Delivery> deliveryList) {
		this.deliveryList = deliveryList;
	}
}
