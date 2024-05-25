package com.portfolio.www.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class JoinForm {
	private Integer memberSeq;
	
	@NotBlank
	@Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 4~20자의 영어 소문자, 숫자만 사용 가능합니다")
	private String memberId;
	
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "8~16자의 영문 대/소문자, 숫자를 사용하고, 특수문자를 1개 이상 포함해야 합니다.")
	private String passwd;
	
	@NotBlank
	private String memberNm;
	
	@NotBlank
	@Email
	private String email;
	
	public Integer getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Integer memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
