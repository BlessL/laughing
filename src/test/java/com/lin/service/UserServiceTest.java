package com.lin.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.lin.baseTest.SpringTestCase;
import com.lyj.laughing.model.User;
import com.lyj.laughing.service.UserService;

/**
 * 功能概要：UserService单元测试
 * 
 * @author linbingwen
 * @since 2015年9月28日
 */
public class UserServiceTest extends SpringTestCase
{
	@Resource
	private UserService userService;
	Logger logger = Logger.getLogger(UserServiceTest.class);

	@Test
	public void selectUserByIdTest()
	{
		User user = userService.selectUserById(10);
		logger.debug("查找结果" + user);
	}

}
