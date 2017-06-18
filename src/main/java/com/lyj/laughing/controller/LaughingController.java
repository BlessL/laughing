package com.lyj.laughing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lyj.laughing.constant.RedisKey;
import com.lyj.laughing.model.Content;
import com.lyj.laughing.model.Pagination;
import com.lyj.laughing.model.PvUv;
import com.lyj.laughing.model.User;
import com.lyj.laughing.service.ContentService;
import com.lyj.laughing.service.PvUvService;
import com.lyj.laughing.service.UserService;
import com.lyj.laughing.util.RedisUtil;

/**
 * @category 内容显示
 */
@Controller
public class LaughingController
{

	@Autowired
	private ContentService contentService;
	@Autowired
	private UserService userService;
	@Autowired
	private PvUvService pvUvService;

	/**
	 * @category 首页
	 * @return
	 */
	@RequestMapping(
	{ "/", "/index" })
	public ModelAndView getIndex()
	{
		ModelAndView mav = new ModelAndView("index");
		List<Content> contentList = contentService.getTop5Content();
		String pvUvKey = RedisKey.getPvUvKey("index");
		Integer pv = (Integer) RedisUtil.getInstance().get(pvUvKey);
		// pv + 1
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("pageName", "index");
		pvUvService.incrPv(paramMap);
		if (null == pv)
		{
			System.out.println("redis 为空");
			PvUv pvUv = pvUvService.getPvUv(paramMap);
			pv = pvUv.getUv();
			RedisUtil.getInstance().set(pvUvKey, pv);
		} else
		{
			System.out.println("redis 不为空");
			pv += 1;
			RedisUtil.getInstance().set(pvUvKey, pv);
		}
		mav.addObject("contentList", contentList);
		mav.addObject("pv", pv);
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
	public ModelAndView addContent(Content content, HttpSession session)
	{
		User user = (User) session.getAttribute("user");
		if (null == user)
		{
			return new ModelAndView("redirect:/login");
		}

		ModelAndView mav = new ModelAndView("addContent");
		if (null != content.getContent() && !content.getContent().isEmpty() && null != content.getTitle()
				&& !content.getContent().isEmpty())
		{
			content.setUserId(user.getUid());
			contentService.addContent(content);
			return new ModelAndView("redirect:/index");
		}
		return mav;
	}

	/**
	 * @category 用户注册
	 * @return
	 */
	@RequestMapping("/register")
	public ModelAndView userRegister(User user)
	{
		ModelAndView mav = new ModelAndView("register");
		if (null != user.getEmail() && !user.getEmail().isEmpty() && null != user.getNick() && !user.getNick().isEmpty()
				&& null != user.getPassword() && !user.getPassword().isEmpty())
		{
			userService.addUser(user);
			return new ModelAndView("redirect:/index");
		}
		return mav;
	}

	/**
	 * @category 用户登录
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView userLogin(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("login");
		String email = null;
		String password = null;
		if (null != request)
		{
			email = request.getParameter("email");
			password = request.getParameter("password");
		}
		if (null == email || email.isEmpty() || null == password || password.isEmpty())
		{
			return mav;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("email", email);
		paramMap.put("password", password);
		User user = userService.getUser(paramMap);
		if (null == user)
		{
			return mav;
		}
		// 设置用户登录session
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		return new ModelAndView("redirect:/index");
	}

	/**
	 * @category 点赞
	 * @param request
	 */
	@RequestMapping("/addLike")
	public void addLike(HttpServletRequest request)
	{
		int contentId = Integer.valueOf(request.getParameter("contentId"));
		if (contentId < 0)
		{
			return;
		}
		contentService.addLike(contentId);
	}

	@RequestMapping("/getPvUv")
	public void getPvuv()
	{
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("pageName", "index");
		PvUv pvUv = pvUvService.getPvUv(paramMap);
		System.out.println(pvUv);
	}
}
