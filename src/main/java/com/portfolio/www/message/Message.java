package com.portfolio.www.message;

public enum Message {
	ID_OR_PWD_IS_WRONG(1000, "아이디 또는 비밀번호를 잘못 입력했습니다."),
	
	JOIN_SUCCESS(2000, "회원 가입에 성공했습니다."),
	AUTH_EMAIL_SEND_FAIL(2001, "인증 메일 발송에 실패했습니다. 관리자에게 문의하세요.");

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
