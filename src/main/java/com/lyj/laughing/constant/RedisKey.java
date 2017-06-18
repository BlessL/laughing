package com.lyj.laughing.constant;

import com.lyj.laughing.exception.ParamException;

/**
 * @author
 */
public class RedisKey
{
	/** 分隔符 */
	private static final String SEPARATOR = "_";

	/** pvuv : pv_uv_ */
	public static final String pv_uv = "pv_uv_";

	/**
	 * @category puvu redisKey
	 */
	public static String getPvUvKey(String pageName)
	{
		if (null == pageName)
		{
			throw new ParamException(ResultCode.KEY_IS_NOT_NULL);
		}
		return new StringBuffer(pv_uv).append(pageName).toString();
	}

}
