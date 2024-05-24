package com.portfolio.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.www.dao.mybatis.MemberRepository;
import com.portfolio.www.dto.LoginForm;
import com.portfolio.www.dto.MemberDto;
import com.portfolio.www.util.PasswordUtil;

@Service
public class LoginService {
	@Autowired
	MemberRepository memberRepository;

	public MemberDto login(LoginForm loginForm) {
		MemberDto memberDto = memberRepository.findMemberByMemberId(loginForm.getMemberId());
		
		boolean result = false;
		if (memberDto != null) {
			result = PasswordUtil.verifyPassword(loginForm.getPasswd(), memberDto.getPasswd());
		}
		
		return result ? memberDto : null;
	}

}
