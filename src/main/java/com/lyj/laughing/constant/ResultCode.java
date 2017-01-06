package com.lyj.laughing.constant;

import com.lyj.laughing.io.ResponseCode;

public enum ResultCode implements ResponseCode
{

	/**
	 * 异常码信息
	 * 
	 */

	/** 处理成功 */
	SUCCESS(0, "处理成功"),

	/** 数据异常(如从数据库中取出的对象，某个字段应该是非空的，但是取到空了 */
	DATA_ERROR(101, "数据异常"),

	/** 系统异常 */
	FAIL(102, "系统异常"),

	/******************************* 业务异常 3开头 *******************************/

	/** 获取公众号信息失败 */
	GET_PLCACCOUNT_FAIL(301, "获取公众号信息失败"),

	/******************************* 缓存异常 5开头 *******************************/
	/** 组成缓存-key的值不能为null */
	KEY_IS_NOT_NULL(501, "组成缓存-key的值不能为null"),

	/******************************* 参数异常4, 6开头 *******************************/

	/** 必要参数为空 */
	REQUEST_IS_REQUIRED(600, "必要参数为空"),

	/*************************** 操作响应异常 7开头 ******************************/

	/** 活动不存在 */
	ACTIVITY_IS_NOT_EXIST(701, "该活动不存在"),

	/** 奖品不存在 */
	PRIZE_IS_NOTEXIST(702, "奖品信息不存在"),

	/*************************** 数据库操作异常 8开头 ******************************/

	/** 更新信息失败 */
	UPDATE_IS_FAIL(801, "更新信息失败");

	private int errorCode;

	private String errorMessage;

	private ResultCode(int errorCode, String errorMessage)
	{
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode()
	{
		return errorCode;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * 通过枚举<code>code</code>获得枚举
	 * 
	 * @param code
	 * @return
	 */
	public static ResultCode getByCode(int code)
	{
		for (ResultCode value : values())
		{
			if (value.getErrorCode() == code)
			{
				return value;
			}
		}
		return null;
	}

	public String toString()
	{
		return "ERROR CODE = " + errorCode + " , message = " + errorMessage;
	}

}
