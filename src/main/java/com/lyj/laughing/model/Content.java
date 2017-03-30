package com.lyj.laughing.model;

public class Content
{
	/** 内容id */
	private Integer contentId;
	/** 发送内容的用户id */
	private Integer userId;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 图片 */
	private String picUrl;
	/** 赞值 */
	private Integer laughValue;
	/** 创建时间 */
	private int createTime;

	public Integer getContentId()
	{
		return contentId;
	}

	public void setContentId(Integer contentId)
	{
		this.contentId = contentId;
	}

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getPicUrl()
	{
		return picUrl;
	}

	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}

	public Integer getLaughValue()
	{
		return laughValue;
	}

	public void setLaughValue(Integer laughValue)
	{
		this.laughValue = laughValue;
	}

	public int getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(int createTime)
	{
		this.createTime = createTime;
	}

	@Override
	public String toString()
	{
		return "Content [contentId=" + contentId + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", picUrl=" + picUrl + ", laughValue=" + laughValue + ", createTime=" + createTime + "]";
	}
}
