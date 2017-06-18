package com.lyj.laughing.model;

public class PvUv
{
	private Integer id;
	private String pageName;
	private Integer pv;
	private Integer uv;
	private Integer flag;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getPageName()
	{
		return pageName;
	}

	public void setPageName(String pageName)
	{
		this.pageName = pageName;
	}

	public Integer getPv()
	{
		return pv;
	}

	public void setPv(Integer pv)
	{
		this.pv = pv;
	}

	public Integer getUv()
	{
		return uv;
	}

	public void setUv(Integer uv)
	{
		this.uv = uv;
	}

	public Integer getFlag()
	{
		return flag;
	}

	public void setFlag(Integer flag)
	{
		this.flag = flag;
	}

	@Override
	public String toString()
	{
		return "PvUv [id=" + id + ", pageName=" + pageName + ", pv=" + pv + ", uv=" + uv + ", flag=" + flag + "]";
	}
}
