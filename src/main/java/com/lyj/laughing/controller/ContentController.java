package com.lyj.laughing.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lyj.laughing.model.Content;
import com.lyj.laughing.model.Pagination;
import com.lyj.laughing.service.ContentService;

/**
 * @category 内容显示
 */
@Controller
public class ContentController
{

	@Autowired
	private ContentService contentService;

	/**
	 * @category 首页
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView getIndex()
	{
		ModelAndView mav = new ModelAndView("index");
		List<Content> contentList = contentService.getTop5Content();
		mav.addObject("contentList", contentList);
		return mav;
	}

	/**
	 * @category 全部内容
	 * @return
	 */
	@RequestMapping("/allContents")
	public ModelAndView getAllContent(HttpServletRequest request)
	{

		int pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
		int pageSize = Integer.valueOf(request.getParameter("pageSize"));
		pageIndex = pageIndex > 0 ? pageIndex : 1;
		pageSize = pageSize > 0 ? pageSize : 10;
		ModelAndView mav = new ModelAndView("allContents");
		List<Content> allContents = contentService.getContentByPage(pageIndex, pageSize);
		int totalElements = contentService.getContentTotalNum();
		Pagination paginationData = new Pagination(totalElements, pageIndex, pageSize, allContents);
		mav.addObject("paginationData", paginationData);
		return mav;
	}

	/**
	 * @category 用户管理
	 * @return
	 */
	@RequestMapping("/users")
	public ModelAndView getUsers()
	{
		ModelAndView mav = new ModelAndView("users");
		return mav;
	}

	/**
	 * @category 添加内容
	 * @return
	 */
	@RequestMapping("/addContent")
	public ModelAndView addContent(Content content)
	{
		ModelAndView mav = new ModelAndView("addContent");
		if (null != content.getContent() && !content.getContent().isEmpty() && null != content.getTitle()
				&& !content.getContent().isEmpty())
		{
			contentService.addContent(content);
			return new ModelAndView("redirect:/index");
		}
		return mav;
	}
}
