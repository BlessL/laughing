package com.lyj.laughing.model;

public class User
{
	private Integer uid;
	private String nick;
	private String password;
	private String email;
	private String phone;
	private String createTime;

	public Integer getUid()
	{
		return uid;
	}

	public void setUid(Integer uid)
	{
		this.uid = uid;
	}

	public String getNick()
	{
		return nick;
	}

	public void setNick(String nick)
	{
		this.nick = nick;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	@Override
	public String toString()
	{
		return "User [uid=" + uid + ", nick=" + nick + ", password=" + password + ", email=" + email + ", phone="
				+ phone + ", createTime=" + createTime + "]";
	}
}
