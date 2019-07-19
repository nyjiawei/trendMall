package com.fjw.coolerMall.model.request;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderRequest implements Serializable{
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("productList")
	private List<Map<String, Object>> productList;
	
	@JsonProperty("cartList")
	private List<Map<String, Object>> cartList;
	
	@JsonProperty("person")
	private String person;
	
	@JsonProperty("phoneNumber")
	private String phoneNumber;
	
	@JsonProperty("totalPrices")
	private double totalPrices;
	
	public double getTotalPrices() {
		return totalPrices;
	}
	public void setTotalPrices(double totalPrices) {
		this.totalPrices = totalPrices;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Map<String, Object>> getProductList() {
		return productList;
	}
	public void setProductList(List<Map<String, Object>> productList) {
		this.productList = productList;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<Map<String, Object>> getCartList() {
		return cartList;
	}
	public void setCartList(List<Map<String, Object>> cartList) {
		this.cartList = cartList;
	}
	
}
