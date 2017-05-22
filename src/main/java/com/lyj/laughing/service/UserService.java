package com.lyj.laughing.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyj.laughing.dao.UserDao;
import com.lyj.laughing.model.User;

@Service("userService")
public class UserService
{
	@Autowired
	UserDao userDao;

	/**
	 * @category 查询用户
	 * @param paramMap
	 * @return
	 */
	public User getUser(Map<String, Object> paramMap)
	{
		User user = userDao.getUser(paramMap);
		return user;
	}

	/**
	 * @category 添加用户
	 * @param user
	 * @return
	 */
	public Integer addUser(User user)
	{
		return userDao.addUser(user);
	}

}
