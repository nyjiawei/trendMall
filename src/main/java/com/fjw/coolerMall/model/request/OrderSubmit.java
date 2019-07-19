package com.fjw.coolerMall.model.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fjw.coolerMall.model.request.CommodityEntiry;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderSubmit implements Serializable {
	
	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("commodityList")
	private List<CommodityEntiry> commodityList;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<CommodityEntiry> getCommodityList() {
		return commodityList;
	}

	public void setCommodityList(List<CommodityEntiry> commodityList) {
		this.commodityList = commodityList;
	}
	
}
