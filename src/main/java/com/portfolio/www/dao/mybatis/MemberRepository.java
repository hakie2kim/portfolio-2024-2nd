package com.portfolio.www.dao.mybatis;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.portfolio.www.dto.JoinForm;

@Repository
public interface MemberRepository {
	
	// public int addMember(HashMap<String, String> params);
	public int addMember(JoinForm joinForm);
}
