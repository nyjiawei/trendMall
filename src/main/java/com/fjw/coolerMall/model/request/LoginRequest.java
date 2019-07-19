package com.fjw.coolerMall.model.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest implements Serializable{
	
	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("passWord")
	private String passWord;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{").append("userName").append(":").append(this.userName).append(",");
		sb.append("passWord").append(":").append(this.passWord).append("}");
		return sb.toString();
	}
	
	
}
