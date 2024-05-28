package com.portfolio.www.forum.notice.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.www.dto.BoardDto;
import com.portfolio.www.service.BoardService;
import com.portfolio.www.service.LoginService;

@RestController
public class RestNoticeController {
	@Autowired
	BoardService boardService;
	@Autowired
	LoginService loginService;
	
	@PostMapping("/forum/notice/write.do")
	public BoardDto write(@RequestBody BoardDto boardDto, HttpServletRequest req) {
		String memberId = String.valueOf(req.getSession().getAttribute("memberId"));
		System.out.println("=====================================================" + memberId);
		boardDto.setRegMemberSeq(loginService.findMemberSeq(memberId));
		boardService.addBoard(boardDto);
		return boardDto;
	}
}