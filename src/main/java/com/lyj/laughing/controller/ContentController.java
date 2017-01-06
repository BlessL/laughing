package com.lyj.laughing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lyj.laughing.model.Content;
import com.lyj.laughing.service.ContentService;

/**
 * 功能概要：UserController
 * 
 */
@Controller
public class ContentController
{

	@Autowired
	private ContentService contentService;

	@RequestMapping("/")
	public ModelAndView getIndex()
	{
		ModelAndView mav = new ModelAndView("index");
		Content content = contentService.getAllContent();
		mav.addObject("content", content);
		return mav;
	}
}
