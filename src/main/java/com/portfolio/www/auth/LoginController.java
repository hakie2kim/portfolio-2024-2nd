package com.portfolio.www.auth;

import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.dto.LoginForm;
import com.portfolio.www.dto.MemberDto;
import com.portfolio.www.message.Message;
import com.portfolio.www.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;

	@RequestMapping("/auth/loginPage.do")
	public ModelAndView loginPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("auth/login");

		return mv;
	}

	@PostMapping("/auth/login.do")
	public String login(@ModelAttribute LoginForm loginForm, HttpServletRequest req, Model model) {
		MemberDto memberDto = loginService.login(loginForm);

		if (ObjectUtils.isEmpty(memberDto)) {
			model.addAttribute("code", Message.ID_OR_PWD_IS_WRONG.getCode());
			model.addAttribute("desc", Message.ID_OR_PWD_IS_WRONG.getDescription());
			return "auth/login";
		}

		return "redirect:/index.do";
	}

	@RequestMapping("/auth/recoverPassPage.do")
	public ModelAndView recoverPassPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("auth/recover-pass");

		return mv;
	}

}
