package com.lyj.laughing.dao;

import com.lyj.laughing.model.User;

/**
 * 功能概要：User的DAO类
 * 
 * @author linbingwen
 * @since 2015年9月28日
 */
public interface UserDao
{
	/**
	 * 
	 * @author linbingwen
	 * @since 2015年9月28日
	 * @param userId
	 * @return
	 */
	public User selectUserById(Integer userId);

}
