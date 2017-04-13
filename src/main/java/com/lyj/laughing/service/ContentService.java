package com.lyj.laughing.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyj.laughing.dao.ContentDao;
import com.lyj.laughing.model.Content;

@Service("contentService")
public class ContentService
{
	@Autowired
	ContentDao contentDao;

	public List<Content> getAllContent()
	{
		return contentDao.getAllContent();
	}

	public void addContent(Content content)
	{
		contentDao.addContent(content);
	}

	/**
	 * @category 拿取最高5条
	 * @return
	 */
	public List<Content> getTop5Content()
	{
		List<Content> top5Content = new ArrayList<>();
		List<Content> contentList = contentDao.getTop5Content();
		// 取最高的5条，并升序排序
		for (int i = contentList.size() - 1; i >= 0; i--)
		{
			top5Content.add(contentList.get(i));
		}
		return top5Content;
	}

	/**
	 * @category 分页获取数据
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Content> getContentByPage(int pageIndex, int pageSize)
	{
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("start", (pageIndex - 1) * pageSize);
		paramMap.put("pageSize", pageSize);
		List<Content> contentList = contentDao.getContentByPage(paramMap);
		return contentList;
	}

	public Integer getContentTotalNum()
	{
		return contentDao.getContentTotalNum();
	}

}
