package com.zomato.dto;

public class UserLoginResponse {

	private String emailId;
	private String token;
	
	
	
	public UserLoginResponse(String emailId,String token) {
		super();
		this.emailId=emailId;
		this.token=token;
	}
	
	public UserLoginResponse() {
		super();
		
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
