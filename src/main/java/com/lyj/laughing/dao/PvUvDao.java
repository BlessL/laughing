package com.lyj.laughing.dao;

import java.util.Map;

import com.lyj.laughing.model.PvUv;

public interface PvUvDao
{
	public PvUv getPvUv(Map<String, String> paramMap);

	public void incrPv(Map<String, String> paramMap);
}
