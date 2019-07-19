package com.fjw.coolerMall.model.response;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RegisterResponse implements Serializable {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("nickName")
	private String nickName;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("message")
	private String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("class RegisterResponse {").append("\n");
		sBuffer.append("status:").append(status).append(",").append("\n");;
		sBuffer.append("message:").append(message).append(",").append("\n");
		sBuffer.append("userName:").append(userName).append("\n");
		sBuffer.append("}");
		return super.toString();
	}
	
	
}
