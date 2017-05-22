package com.lyj.laughing.dao;

import java.util.Map;

import com.lyj.laughing.model.User;

public interface UserDao
{
	public User getUser(Map<String, Object> paramMap);

	public Integer addUser(User user);
}
