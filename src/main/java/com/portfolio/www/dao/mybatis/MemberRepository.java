package com.portfolio.www.dao.mybatis;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.portfolio.www.dto.JoinForm;
import com.portfolio.www.dto.LoginForm;
import com.portfolio.www.dto.MemberAuthDto;
import com.portfolio.www.dto.MemberDto;

@Repository
public interface MemberRepository {
	// public int addMember(HashMap<String, String> params);
	public int addMember(JoinForm joinForm);

	public MemberDto findMemberByMemberId(String memberId);
	public int findMemberSeq(String memberId);
}
