package com.portfolio.www.forum.notice;

import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.service.BoardService;
import com.portfolio.www.service.NoticeService;
import com.portfolio.www.util.Pagination;

@Controller
public class NoticeController {
	@Autowired
	private BoardService boardService;
	
	private static int NOTICE_BOARD_TYPE_SEQ = 1;
	
	@RequestMapping("/forum//notice/listPage.do")
	public ModelAndView listPage(@RequestParam HashMap<String, String> params,
			@RequestParam(defaultValue="1") Integer currPage,
			@RequestParam(defaultValue="10") Integer postsPerPage
	) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.addObject("list", boardService.findAllBoards(currPage, postsPerPage, NOTICE_BOARD_TYPE_SEQ));
		mv.addObject("pagination", new Pagination(boardService.cntTotalBoards(NOTICE_BOARD_TYPE_SEQ), currPage, postsPerPage));
		
		mv.setViewName("forum/notice/list");
		
		return mv;
	}
	
	@RequestMapping("/forum/notice/writePage.do")
	public ModelAndView writePage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("forum/notice/write");
		
		return mv;
	}
	
	@RequestMapping("/forum/notice/readPage.do")
	public ModelAndView readPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("forum/notice/read");
		
		return mv;
	}

}
