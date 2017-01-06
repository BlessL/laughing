package com.lyj.laughing.exception;

import com.lyj.laughing.io.ResponseCode;

public class ParamException extends WithCodeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4371083367495977225L;

	public ParamException(ResponseCode resultCode)
	{
		super(resultCode);
	}

	public ParamException(ResponseCode resultCode, Throwable t)
	{
		super(resultCode, t);
	}

	public ParamException(ResponseCode resultCode, String message)
	{
		super(resultCode, message);
	}

	public ParamException(ResponseCode resultCode, String message, Throwable t)
	{
		super(resultCode, message, t);
	}

}
