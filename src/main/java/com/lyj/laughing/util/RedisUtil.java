package com.lyj.laughing.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil implements InitializingBean
{
	/**
	 * 日志
	 */
	private static final Log logger = LogFactory.getLog(RedisUtil.class);

	/**
	 * REDIS连接池
	 */
	private volatile static JedisPool pool;

	/**
	 * 单例
	 */
	private volatile static RedisUtil instance;

	/**
	 * 最大连接数，如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个JEDIS实例，则此时pool的状态为exhausted(耗尽
	 * )。
	 */
	private Integer link;

	/**
	 * 控制一个pool最多有多少个状态为idle(空闲的)的JEDIS实例
	 */
	private Integer free;

	/**
	 * 最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
	 */
	private Integer holed;

	/**
	 * redis服务端口
	 */
	private Integer port;

	/**
	 * redis服务地址
	 */
	private String host;

	/**
	 * redis连接超时时间
	 */
	private Integer timeout;

	/**
	 * redis连接密码
	 */
	private String password;

	/**
	 * 
	 */
	private Integer DB;

	public void setLink(Integer link)
	{
		this.link = link;
	}

	public void setFree(Integer free)
	{
		this.free = free;
	}

	public void setHoled(Integer holed)
	{
		this.holed = holed;
	}

	public void setPort(Integer port)
	{
		this.port = port;
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	public void setTimeout(Integer timeout)
	{
		this.timeout = timeout;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setDB(Integer dB)
	{
		DB = dB;
	}

	/**
	 * 返回工具类实例
	 * 
	 * @return
	 */
	public static RedisUtil getInstance()
	{
		return instance;
	}

	/**
	 * 初始化redisUtil实例，配置连接池
	 */
	public void afterPropertiesSet() throws Exception
	{

		JedisPoolConfig config = new JedisPoolConfig();

		config.setMaxTotal(link);
		config.setMaxIdle(free);
		config.setMaxWaitMillis(holed);
		config.setTestOnBorrow(true);

		pool = new JedisPool(config, host, port, timeout, password);

	}

	/**
	 * 初始化redisUtil实例，配置连接池
	 */
	private RedisUtil()
	{
		instance = this;
	}

	/**
	 * 从连接池获得一个redis连接
	 * 
	 * @return
	 */
	public Jedis getConnent()
	{
		Jedis jedis = pool.getResource();
		jedis.select(DB);
		return jedis;
	}

	public Long incr(String key, int second)
	{
		Jedis jedis = null;
		Long ret = 0L;
		try
		{
			jedis = getConnent();
			ret = jedis.incr(key);

			if (second > 0)
				jedis.expire(key, second);
		} catch (Exception e)
		{
			logger.error("redis incr data failed!", e);
			return -1L;
		} finally
		{
			close(jedis);
		}
		return ret;
	}

	public Long decr(String key, int second)
	{
		Jedis jedis = null;
		Long ret = 0L;
		try
		{
			jedis = getConnent();
			ret = jedis.decr(key);

			if (second > 0)
				jedis.expire(key, second);
		} catch (Exception e)
		{
			logger.error("redis decr data failed!", e);
			return -1L;
		} finally
		{
			close(jedis);
		}
		return ret;
	}

	public int getInt(String key)
	{
		return StrUtil.toInt(getOrigin(key));
	}

	/**
	 * 却出redis中的key对应的值
	 * 
	 * @param key
	 * @return
	 */
	public String getOrigin(String key)
	{
		Jedis jedis = null;

		try
		{
			jedis = getConnent();
			return jedis.get(key);
		} catch (Exception e)
		{
			logger.error("redis get data failed!", e);
			return null;
		} finally
		{
			close(jedis);
		}
	}

	/**
	 * 向redis中传入基本类型的值，不做序列化，当seconds大于0时，代表设置失效时间，单位秒
	 * 
	 * @param key
	 *            键值
	 * @param object
	 *            值
	 * @param seconds
	 *            秒
	 * @return
	 */
	public String setOrigin(String key, Object object, int seconds)
	{
		String ret = null;
		Jedis jedis = null;
		try
		{
			jedis = getConnent();
			ret = jedis.set(key.getBytes(), StrUtil.toByte(object));
			if (seconds > 0)
				jedis.expire(key.getBytes(), seconds);
		} catch (Exception e)
		{
			logger.error("redis set data failed!", e);
		} finally
		{
			close(jedis);
		}
		return ret;
	}

	/**
	 * 向redis中存入数据
	 * 
	 * @param key
	 *            键值
	 * @param object
	 *            数据
	 * @return
	 */
	public boolean set(String key, Object object, int seconds)
	{
		Jedis jedis = null;
		try
		{
			jedis = getConnent();
			jedis.set(key.getBytes(), ConvertUtil.serialize(object));
			if (seconds > 0)
				jedis.expire(key.getBytes(), seconds);
		} catch (Exception e)
		{
			logger.error("redis set data failed!", e);
			return false;
		} finally
		{
			close(jedis);
		}
		return true;
	}

	/**
	 * 向redis中存入数据
	 * 
	 * @param key
	 *            键值
	 * @param object
	 *            数据
	 * @return
	 */
	public boolean set(String key, Object object)
	{
		return set(key, object, -1);
	}

	public boolean set(String key, String object)
	{
		return set(key, object, -1);
	}

	public boolean set(String key, String object, int seconds)
	{
		Jedis jedis = null;
		try
		{

			jedis = getConnent();
			jedis.set(key, object);
			if (seconds > 0)
				jedis.expire(key, seconds);
		} catch (Exception e)
		{
			logger.error("redis set data failed!", e);
			return false;
		} finally
		{
			close(jedis);
		}
		return true;
	}

	public void expire(String key, int seconds)
	{
		Jedis jedis = null;
		try
		{
			jedis = getConnent();
			if (seconds > 0)
				jedis.expire(key, seconds);
		} catch (Exception e)
		{
			logger.error("redis set data failed!", e);
		} finally
		{
			close(jedis);
		}
	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key)
	{
		Jedis jedis = null;

		try
		{
			jedis = getConnent();
			byte[] value = jedis.get(key.getBytes());

			if (value != null)
			{
				return ConvertUtil.unserialize(value);
			} else
			{
				return null;
			}
		} catch (Exception e)
		{
			logger.error("redis get data failed!", e);
			return null;
		} finally
		{
			close(jedis);
		}
	}

	/**
	 * 获取字符串数据
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key)
	{
		Jedis jedis = null;

		try
		{
			jedis = getConnent();

			return jedis.get(key);

		} catch (Exception e)
		{
			logger.error("redis get data failed!", e);
			return null;
		} finally
		{
			close(jedis);
		}
	}

	/**
	 * 删除redis中key对应数据
	 * 
	 * @param key
	 *            键值
	 * @return 成功\失败
	 */
	public boolean delete(String key)
	{
		Jedis jedis = null;
		try
		{
			jedis = getConnent();
			jedis.del(key.getBytes());
		} catch (Exception e)
		{
			logger.error("redis delete data failed!", e);
			return false;
		} finally
		{
			close(jedis);
		}
		return true;
	}

	/**
	 * 删除redis中key对应数据
	 * 
	 * @param key
	 * @return 删除的条数
	 */
	public long deleteRegEx(String key)
	{
		Jedis jedis = null;
		long count = 0;

		try
		{
			jedis = getConnent();
			Set<String> keys = jedis.keys(key);

			if (keys == null || keys.isEmpty())
			{
				return 0;
			}

			for (String sigleKey : keys)
			{
				jedis.del(sigleKey);
				count++;
			}
			return count;

		} catch (Exception e)
		{
			return -1;
		} finally
		{
			close(jedis);
		}
	}

	/**
	 * 存储REDIS队列 顺序存储,可设置过期时间，过期时间以秒为单位
	 * 
	 * @param key
	 *            reids键名
	 * @param value
	 *            键值
	 * @param second
	 *            过期时间(秒)
	 */
	public Long lpush(String key, Object value, int second)
	{
		Jedis jedis = null;
		Long ret = null;
		try
		{
			jedis = getConnent();
			ret = jedis.lpush(key, JSON.toJSONString(value));

			if (second > 0)
			{
				jedis.expire(key, second);
			}

		} catch (Exception e)
		{
			logger.error("redis lpush data failed , key = " + key, e);
		} finally
		{
			close(jedis);
		}

		return ret;
	}

	public Long rpush(String key, Object value, int second)
	{
		Jedis jedis = null;
		Long ret = null;
		try
		{
			jedis = getConnent();
			ret = jedis.rpush(key, JSON.toJSONString(value));

			if (second > 0)
			{
				jedis.expire(key, second);
			}

		} catch (Exception e)
		{
			logger.error("redis rpush data failed , key = " + key, e);
		} finally
		{
			close(jedis);
		}

		return ret;
	}

	public void lpushStr(String key, String value, int second)
	{
		Jedis jedis = null;
		try
		{
			jedis = getConnent();
			jedis.lpush(key, value);

			if (second > 0)
			{
				jedis.expire(key, second);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			close(jedis);
		}
	}

	/**
	 * 保存数据 类型为 Map 向redis中传入基本类型的值，不做序列化，当seconds大于0时，代表设置失效时间，单位秒
	 * 
	 * @param flag
	 * @param mapData
	 */
	public String setMapData(String key, Map<String, String> mapData, int second)
	{
		Jedis jedis = null;
		String hmset = null;
		try
		{
			jedis = getConnent();
			hmset = jedis.hmset(key, mapData);
			if (second > 0)
			{
				jedis.expire(key, second);
			}
		} catch (Exception e)
		{
			logger.error("redis set map data failed! map = " + mapData, e);
		} finally
		{
			close(jedis);
		}

		return hmset;
	}

	/**
	 * 获取Map数据
	 * 
	 * @param flag
	 * @return
	 */
	public Map<String, String> getMapData(String key)
	{
		Map<String, String> dataMap = null;
		Jedis jedis = null;

		try
		{
			jedis = getConnent();
			dataMap = jedis.hgetAll(key);
		} catch (Exception e)
		{
			logger.error("redis get map data failed! ", e);
		} finally
		{
			close(jedis);
		}
		return dataMap;
	}

	/**
	 * 哈希存储
	 */
	public boolean hset(String key, String domain, String value, int seconds)
	{
		Jedis jedis = null;
		try
		{
			jedis = getConnent();
			jedis.hset(key.getBytes(), domain.getBytes(), value.getBytes());
			if (seconds > 0)
				jedis.expire(key.getBytes(), seconds);
		} catch (Exception e)
		{
			logger.error("redis hset data failed!", e);
			return false;
		} finally
		{
			close(jedis);
		}
		return true;
	}

	public String hget(String key, String domain)
	{
		Jedis jedis = null;
		try
		{
			jedis = getConnent();
			byte[] value = jedis.hget(key.getBytes(), domain.getBytes());

			if (value != null)
			{
				return new String(value);
			} else
			{
				return null;
			}
		} catch (Exception e)
		{
			logger.error("redis hget data failed!", e);
			return null;
		} finally
		{
			close(jedis);
		}
	}

	public List<String> hvals(String key)
	{
		Jedis jedis = null;
		try
		{
			jedis = getConnent();
			List<String> value = jedis.hvals(key);

			if (value != null)
			{
				return value;
			} else
			{
				return null;
			}
		} catch (Exception e)
		{
			logger.error("redis hget data failed!", e);
			return null;
		} finally
		{
			close(jedis);
		}
	}

	public Long hincrby(String key, String field, long value)
	{
		Jedis jedis = null;
		try
		{
			jedis = getConnent();
			Long result = jedis.hincrBy(key, field, value);

			if (result != null)
			{
				return result;
			} else
			{
				return null;
			}
		} catch (Exception e)
		{
			logger.error("redis hget data failed!", e);
			return null;
		} finally
		{
			close(jedis);
		}
	}

	/**
	 * @category 获得所有的key
	 * @author tonghui.li@weimob.com
	 * @return
	 */
	public Set<String> getKeys(String pattern)
	{
		Jedis jedis = null;
		Set<String> keys;
		try
		{
			jedis = getConnent();
			keys = jedis.keys(pattern);
			return keys;
		} catch (Exception e)
		{
			logger.error("redis get keys failed!", e);
			return null;
		} finally
		{
			close(jedis);
		}
	}

	public List<String> lrange(String key, long start, long end)
	{
		Jedis jedis = null;
		List<String> list = null;
		try
		{
			jedis = getConnent();

			list = jedis.lrange(key, start, end);

			return list;
		} catch (Exception e)
		{
			logger.error("redis lrange get data failed!", e);
			return null;
		} finally
		{
			close(jedis);
		}
	}

	/**
	 * @category 返回列表 key 的长度
	 * @param key
	 * @return
	 */
	public Long llen(String key)
	{
		Jedis jedis = null;

		try
		{
			jedis = getConnent();
			return jedis.llen(key);
		} catch (Exception e)
		{
			logger.error("redis llen data failed!", e);
			return null;
		} finally
		{
			close(jedis);
		}
	}

	/**
	 * 更新redis数据，当redis中不存在改键值时，返回false
	 * 
	 * @param key
	 *            键值
	 * @param obj
	 *            需要更新的值
	 * @return 更新结果
	 */
	public boolean update(String key, Object obj)
	{
		if (null == get(key))
		{
			return false;
		} else
		{
			return set(key, obj);
		}
	}

	/**
	 * 关闭当前连接实例，将连接返回连接池
	 * 
	 * @param jedis
	 *            redis连接实例
	 */
	private void close(Jedis jedis)
	{
		try
		{
			if (jedis != null)
			{
				jedis.close();
			}
		} catch (Exception e)
		{
			logger.error("close jedis failed!", e);
		}
	}

	public static void main(String[] args)
	{
		long l = RedisUtil.getInstance().incr("cd", 0);
		System.out.println(l);
	}

}
