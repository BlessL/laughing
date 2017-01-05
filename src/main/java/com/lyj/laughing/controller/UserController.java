package com.lyj.laughing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lyj.laughing.model.User;
import com.lyj.laughing.service.UserService;

/**
 * 功能概要：UserController
 * 
 */
@Controller
public class UserController
{
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public ModelAndView getIndex()
	{
		ModelAndView mav = new ModelAndView("index");
		User user = userService.selectUserById(1);
		mav.addObject("user", user);
		return mav;
	}
}
