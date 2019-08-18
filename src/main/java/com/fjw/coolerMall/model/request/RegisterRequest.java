package com.fjw.coolerMall.model.request;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 序列化的JSON映射对象
 * @author jiawe
 * email fjiawei123@foxmail.com
 * 2018年7月30日下午11:10:50
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequest implements Serializable {
	private static final long serialVersionUID = 8431297676353040322L;

	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("passWord")
	private String passWord;
	
	@JsonProperty("nickName")
	private String nickName;
	
	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("code")
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
