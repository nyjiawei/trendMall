package com.fjw.coolerMall.model.response;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fjw.coolerMall.model.request.Product;


@JsonIgnoreProperties(ignoreUnknown=true)
public class ProductResponse {
	
	@JsonProperty("result")
	private String result;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("total_count")
	private int total_count;
	
	@JsonProperty("entities")
	private List<Product> entities;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public List<Product> getEntities() {
		return entities;
	}

	public void setEntities(List<Product> entities) {
		this.entities = entities;
	}
}
