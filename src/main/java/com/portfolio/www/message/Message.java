package com.portfolio.www.message;

public enum Message {
	ID_OR_PWD_IS_WRONG(1000, "아이디 또는 비밀번호를 잘못 입력했습니다.");

	Message(int code, String description) {
		this.code = code;
		this.description = description; 
	}
	
	private int code;
	private String description;
	
	public int getCode() {
		return code;
	}
	public String getDescription() {
		return description;
	}
}
