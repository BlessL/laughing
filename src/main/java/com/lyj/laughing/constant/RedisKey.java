package com.lyj.laughing.constant;

import com.lyj.laughing.exception.ParamException;

/**
 * @author
 */
public class RedisKey
{
	/** 分隔符 */
	private static final String SEPARATOR = "_";

	/** 用户发言间隔 key : too_short_to_wallup_id_aid_weimobid */
	public static final String TOO_SHORT_TO_WALLUP = "too_short_to_wallup_";

	/**
	 * @category 微信墙用户 redisKey
	 */
	public static String getTooShortToWallup(Integer id, Integer aid, String weimobId)
	{
		if (null == id || null == aid || null == weimobId)
		{
			throw new ParamException(ResultCode.KEY_IS_NOT_NULL);
		}
		return new StringBuffer(TOO_SHORT_TO_WALLUP).append(id).append(SEPARATOR).append(aid).append(SEPARATOR)
				.append(weimobId).toString();
	}

}
