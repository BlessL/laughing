package com.lyj.laughing.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyj.laughing.dao.ContentDao;
import com.lyj.laughing.model.Content;
import com.lyj.laughing.service.ContentService;

@Service("contentService")
public class ContentServiceImpl implements ContentService
{
	@Autowired
	ContentDao contentDao;

	@Override
	public Content getAllContent()
	{
		return contentDao.getAllContent();
	}

}