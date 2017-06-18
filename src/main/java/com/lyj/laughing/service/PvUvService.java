package com.lyj.laughing.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyj.laughing.dao.PvUvDao;
import com.lyj.laughing.model.PvUv;

@Service("pvUvService")
public class PvUvService
{
	@Autowired
	PvUvDao pvUvDao;

	public PvUv getPvUv(Map<String, String> paramMap)
	{
		PvUv puUv = pvUvDao.getPvUv(paramMap);
		return puUv;
	}

	public void incrPv(Map<String, String> paramMap)
	{
		pvUvDao.incrPv(paramMap);
	}

}
