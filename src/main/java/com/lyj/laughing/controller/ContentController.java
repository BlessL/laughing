package com.lyj.laughing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lyj.laughing.model.Content;
import com.lyj.laughing.service.ContentService;

/**
 * @category 内容显示
 */
@Controller
public class ContentController
{

	@Autowired
	private ContentService contentService;

	@RequestMapping("/index")
	public ModelAndView getIndex()
	{
		ModelAndView mav = new ModelAndView("index");
		List<Content> contentList = contentService.getAllContent();
		mav.addObject("contentList", contentList);
		return mav;
	}

	@RequestMapping("users")
	public ModelAndView getUsers()
	{
		ModelAndView mav = new ModelAndView("users");
		return mav;
	}
}
