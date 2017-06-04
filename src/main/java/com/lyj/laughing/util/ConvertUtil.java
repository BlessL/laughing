package com.lyj.laughing.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConvertUtil
{
	/**
	 * 日志
	 */
	private static final Log LOG = LogFactory.getLog(ConvertUtil.class);
	
	/**
	 * 序列化对象
	 * 
	 * @param obj 需要序列化的对象
	 * @return 字节流
	 */
	public static byte[] serialize(Object obj)
	{
		ObjectOutputStream os = null;
		ByteArrayOutputStream bs = null;
		
		try {
			
			bs = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bs);
			
			os.writeObject(obj);
			byte[] bytes = bs.toByteArray();
			
			return bytes;
		} 
		catch (Exception e) 
		{
			LOG.error("serialize failed , object = "+obj , e);
		}
		
		return null;
	}
	
	/**
	 * 反序列化
	 * 
	 * @param bytes 字节流
	 * @return 反序列化后的对象
	 */
	public static Object unserialize(byte[] bytes) 
	{
		
		ByteArrayInputStream bais = null;

		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			
			return ois.readObject();
		} 
		catch (Exception e) 
		{
			LOG.error("unserialize failed " , e);
		}
		
		return null;
	}

}
