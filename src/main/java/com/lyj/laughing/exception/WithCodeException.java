package com.lyj.laughing.exception;

import java.util.Map;

import com.lyj.laughing.io.ResponseCode;

public class WithCodeException extends RuntimeException
{
	private static final long serialVersionUID = 5256409736701456533L;
	private int code;
	private String append;
	private ResponseCode resultCode;
	private Map<String, Object> errorInfo;

	public ResponseCode getResultCode()
	{
		return this.resultCode;
	}

	public void setResultCode(ResponseCode resultCode)
	{
		this.resultCode = resultCode;
	}

	public int getCode()
	{
		return this.code;
	}

	public Map<String, Object> getErrorInfo()
	{
		return this.errorInfo;
	}

	public void setErrorInfo(Map<String, Object> errorInfo)
	{
		this.errorInfo = errorInfo;
	}

	public String getAppend()
	{
		return this.append;
	}

	public WithCodeException()
	{
	}

	public WithCodeException(int resultCode, String message)
	{
		super(message);
		this.code = resultCode;
		this.append = message;
	}

	public WithCodeException(ResponseCode resultCode)
	{
		super(resultCode.toString());
		this.resultCode = resultCode;
		this.code = resultCode.getErrorCode();
		this.append = resultCode.getErrorMessage();
	}

	public WithCodeException(ResponseCode resultCode, String message)
	{
		super(resultCode.toString() + " APPEND[ " + message + " ]");
		this.resultCode = resultCode;
		this.code = resultCode.getErrorCode();
		this.append = (((message == null) || ("".equals(message.trim()))) ? resultCode.getErrorMessage() : message);
	}

	public WithCodeException(ResponseCode resultCode, Throwable e)
	{
		super(resultCode.toString(), e);
		this.resultCode = resultCode;
		this.code = resultCode.getErrorCode();
		this.append = resultCode.getErrorMessage();
	}

	public WithCodeException(ResponseCode resultCode, String message, Throwable e)
	{
		super(resultCode.toString() + " APPEND[ " + message + " ]", e);
		this.resultCode = resultCode;
		this.code = resultCode.getErrorCode();
		this.append = (((message == null) || ("".equals(message.trim()))) ? resultCode.getErrorMessage() : message);
	}

}
