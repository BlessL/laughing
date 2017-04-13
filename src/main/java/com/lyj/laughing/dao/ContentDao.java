package com.lyj.laughing.dao;

import java.util.List;
import java.util.Map;

import com.lyj.laughing.model.Content;

public interface ContentDao
{
	public List<Content> getTop5Content();

	public List<Content> getAllContent();

	public List<Content> getContentByPage(Map<String, Object> paramMap);

	public Integer getContentTotalNum();

	public void addContent(Content content);
}
