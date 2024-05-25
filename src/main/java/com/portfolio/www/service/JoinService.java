package com.portfolio.www.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.portfolio.www.dao.mybatis.MemberAuthRepository;
import com.portfolio.www.dao.mybatis.MemberRepository;
import com.portfolio.www.domain.SignUpForm;
import com.portfolio.www.dto.EmailDto;
import com.portfolio.www.dto.JoinForm;
import com.portfolio.www.dto.MemberAuthDto;
import com.portfolio.www.message.Message;
import com.portfolio.www.util.EmailUtil;
import com.portfolio.www.util.PasswordUtil;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class JoinService {
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	MemberAuthRepository memberAuthRepository;
	
	@Autowired
	EmailUtil emailUtil;
	
	/*
	 * public int join(@RequestParam HashMap<String, String> params) { 
	 * return memberRepository.addMember(params); 
	 * }
	 */
	
	public Message join(JoinForm joinForm) {
		joinForm.setPasswd(PasswordUtil.encPassword(joinForm.getPasswd()));
		memberRepository.addMember(joinForm);
		
		// member_auth에 데이터 넣기
		MemberAuthDto memberAuthDto = addMemberAuth(joinForm.getMemberSeq());
		
		// EmailDto 만들기
		EmailDto emailDto = emailUtil.makeEmailDto(joinForm, memberAuthDto);
		
		// 이메일 보내기
		return emailUtil.sendEmail(emailDto, true) ? Message.JOIN_SUCCESS : Message.AUTH_EMAIL_SEND_FAIL;
	}
	
	private MemberAuthDto addMemberAuth(int memberSeq) {
		MemberAuthDto memberAuthDto = new MemberAuthDto();
		
		// 1. member_seq 설정
		memberAuthDto.setMemberSeq(memberSeq);
		
		// 2. 인증 링크 설정
		memberAuthDto.setAuthUri(UUID.randomUUID().toString().replace("-", ""));
		
		// 3. 인증 링크 만료일자 설정
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 30); // 30분만 유효
		memberAuthDto.setExpireDtm(cal.getTimeInMillis());
		
		memberAuthRepository.addMemberAuth(memberAuthDto);
		
		return memberAuthDto;
	}
}
