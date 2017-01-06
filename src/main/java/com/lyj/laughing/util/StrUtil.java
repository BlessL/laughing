package com.lyj.laughing.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.lyj.laughing.constant.ResultCode;
import com.lyj.laughing.exception.ParamException;

public class StrUtil
{
	/**
	 * 需要替换的特殊字符
	 */
	private static final String[] SPECIAL_CHARACTER = { "\t", " ", "\n", "\r", "?" };

	/**
	 * 两位小数格式
	 */
	private static final DecimalFormat TWO_DECIMAL_PLACES = new DecimalFormat("0.00");

	/**
	 * String类型转INT
	 * 
	 * @param str 待转换的字符串
	 * @param defaultValue 默认值
	 * @return int 类型
	 */
	public static int toInt(String str, int defaultValue)
	{
		if (isEmpty(str))
		{
			return defaultValue;
		}

		try
		{
			return Integer.parseInt(str);
		}
		catch (NumberFormatException e)
		{
			return defaultValue;
		}
	}

	/**
	 * 检查字符串是否为空
	 * 
	 * @param str 字符串
	 * @param checkSpace 是否检查全空格
	 * @return 字符串是否为空
	 */
	public static boolean isEmpty(String str, boolean checkSpace)
	{
		if (null == str)
		{
			return true;
		}

		if (checkSpace)
		{
			str = str.trim();
		}

		if ("".equals(str))
		{
			return true;
		}
		return false;
	}

	/**
	 * 检查字符串是否为空,为多个空格时依然不为null
	 * 
	 * @param str 字符串
	 * @return 字符串是否为空
	 */
	public static boolean isEmpty(String str)
	{
		return isEmpty(str, false);
	}

	/**
	 * 检查字符串是否不为空,为多个空格时依然不为null
	 * 
	 * @param str 字符串
	 * @return 字符串是否不为空
	 */
	public static boolean isNotEmpty(String str)
	{
		return !isEmpty(str, false);
	}

	/**
	 * 将对象转换为字符串
	 * 
	 * @param obj 需要转换的对象
	 * @param def 默认值
	 * @return 字符串
	 */
	public static String toStr(Object obj, String def)
	{
		String value = def;

		try
		{
			if (null != obj)
			{
				value = String.valueOf(obj);
			}
		}
		catch (Exception e)
		{
		}
		return value;
	}

	/**
	 * 将对象转换为字符串，转换失败返回默认值""空字符串
	 * 
	 * @param obj 需要转换的对象
	 * @return 字符串
	 */
	public static String toStr(Object obj)
	{
		return toStr(obj, "");
	}

	/**
	 * 将对象转换为int,转换失败时返回默认值
	 * 
	 * @param obj 需要转换的对象
	 * @param def 默认值
	 * @return int数字
	 */
	public static int toInt(Object obj, int def)
	{
		int value = def;

		try
		{
			def = Integer.parseInt(toStr(obj));
		}
		catch (NumberFormatException e)
		{
		}

		return value;
	}

	/**
	 * 将对象转换为int,转换失败时返回默认值0
	 * 
	 * @param obj 需要转换的对象
	 * @return int数字
	 */
	public static int toInt(Object obj)
	{
		return toInt(obj, 0);
	}

	/**
	 * 判断列表是否为空
	 * 
	 * @param c 列表Collection
	 * @return 是否为空
	 */
	public static boolean isEmpty(Collection<?> c)
	{
		if (null == c || c.isEmpty())
		{
			return true;
		}
		return false;
	}

	/**
	 * 清除字符串中空格换行符等特殊字符，压缩字符串
	 * 
	 * @param str 需要处理的字符串
	 * @return 压缩后字符串
	 */
	public static String clear(String str)
	{
		if (isEmpty(str))
		{
			return "";
		}

		for (String sc : SPECIAL_CHARACTER)
		{
			str = str.replace(sc, "");
		}

		return str;
	}

	public static float toFloat(Object obj, float defaultValue)
	{
		if (null == obj)
		{
			return defaultValue;
		}

		try
		{
			return Float.parseFloat(obj.toString());
		}
		catch (NumberFormatException e)
		{
			return defaultValue;
		}
	}

	public static String decimalFormat2(float num)
	{
		return TWO_DECIMAL_PLACES.format(num);
	}

	public static double roundHalf(double f)
	{
		BigDecimal b = new BigDecimal(f);
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 对象转字节数组
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] toByte(Object obj)
	{
		if (null == obj)
			throw new IllegalArgumentException("object is null");

		return String.valueOf(obj).getBytes();
	}

	/**
	 * 判断两个对象数学意义上的大小，如果第一个对象大于第二个，则返回true， 如果传入的参数不能转换为数字，则抛出异常，比较数字的范围为
	 * -2147483648～2147483647
	 * 
	 * @author bin.li
	 * @param obj1 待比较参数
	 * @param obj2 待比较参数
	 * @return 判断两个对象数学意义上的大小，如果第一个对象大于第二个，则返回true
	 */
	public static boolean compare(Object obj1, Object obj2)
	{
		int i1, i2;
		try
		{
			i1 = Integer.parseInt(String.valueOf(obj1));
			i2 = Integer.parseInt(String.valueOf(obj2));

			if (i1 > i2)
				return true;
		}
		catch (NumberFormatException e)
		{
			throw new IllegalArgumentException("object can not convert number");
		}
		return false;
	}

	public static String getParameter(HttpServletRequest request, String parameter, ResultCode errCoder)
			throws ParamException
	{
		return getParameter(request, parameter, errCoder, errCoder.getErrorMessage());
	}

	/**
	 * 从request中取出String类型的值，如果为null则抛出异常
	 * 
	 * @param request HttpServletRequest
	 * @param parameter 属性名称
	 * @param errCoder 异常码
	 * @param message 异常信息
	 * @return 从request中取出String类型的值
	 * @throws ParamException 参数异常
	 */
	public static String getParameter(HttpServletRequest request, String parameter, ResultCode errCoder, String message)
			throws ParamException
	{
		String value = request.getParameter(parameter);
		if (isEmpty(value))
			throw new ParamException(errCoder, message);

		return value;
	}

	public static int getIntParameter(HttpServletRequest request, String parameter, ResultCode errCoder)
			throws ParamException
	{
		return getIntParameter(request, parameter, errCoder, errCoder.getErrorMessage());
	}

	/**
	 * 从request中取出int类型的值，如果不能转换为int则抛出异常
	 * 
	 * @param request HttpServletRequest
	 * @param parameter 属性名称
	 * @param errCoder 异常码
	 * @param message 异常信息
	 * @return 从request中取出int类型的值
	 * @throws ParamException 参数异常
	 */
	public static int getIntParameter(HttpServletRequest request, String parameter, ResultCode errCoder, String message)
			throws ParamException
	{
		try
		{
			return Integer.parseInt(request.getParameter(parameter));
		}
		catch (NumberFormatException e)
		{
			throw new ParamException(errCoder, message);
		}
	}
}
