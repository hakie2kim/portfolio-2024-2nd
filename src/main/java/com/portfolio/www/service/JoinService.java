package com.portfolio.www.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.portfolio.www.dao.mybatis.MemberRepository;
import com.portfolio.www.dto.JoinForm;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class JoinService {
	@Autowired
	MemberRepository memberRepository;
	
	/*
	 * public int join(@RequestParam HashMap<String, String> params) { 
	 * return memberRepository.addMember(params); 
	 * }
	 */
	
	public int join(JoinForm joinForm) {
		encPassword(joinForm);
		return memberRepository.addMember(joinForm);
	}
	
	private void encPassword(JoinForm joinForm) {
		String passwd = joinForm.getPasswd();
		String encPasswd = BCrypt.withDefaults().hashToString(12, passwd.toCharArray());
		joinForm.setPasswd(encPasswd);
		
		/*
		 * System.out.println("encPasswd >>>>>>>>> " + encPassword);
		 * 
		 * BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(),
		 * encPassword); System.out.println("result.verified >>>>>>>>> " +
		 * result.verified);
		 */
	}
}
