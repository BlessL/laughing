package com.lyj.laughing.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyj.laughing.dao.UserDao;
import com.lyj.laughing.model.User;
import com.lyj.laughing.service.UserService;

/**
 * 功能概要：UserService实现类
 * 
 * @author linbingwen
 * @since 2015年9月28日
 */
@Service("userService")
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;

	public User selectUserById(Integer userId)
	{
		return userDao.selectUserById(userId);

	}

}
