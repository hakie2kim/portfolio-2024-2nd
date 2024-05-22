package com.portfolio.www.auth;

import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.dto.JoinForm;
import com.portfolio.www.service.JoinService;

@Controller
public class JoinController {
	@Autowired
	private JoinService joinService;
		
	@RequestMapping("/auth/joinPage.do")
	public ModelAndView joinPage(@RequestParam HashMap<String, String> params) {		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("auth/join");
		
		return mv;
	}
	
	@PostMapping("/auth/join.do")
	public String join(@ModelAttribute JoinForm joinForm) {
		joinService.join(joinForm);
		return "redirect:/auth/loginPage.do";
	}

}
