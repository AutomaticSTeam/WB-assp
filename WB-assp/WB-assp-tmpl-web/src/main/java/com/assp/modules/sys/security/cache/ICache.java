package com.assp.modules.sys.security.cache;

import java.util.List;
import java.util.Set;

/**
 * 类简述
 * <p>
 * 处理缓存的公用接口
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月20日 上午10:13:27
 */
public interface ICache {
	/**
	 * 删除 缓存
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	String deleteCached(byte[] key) throws Exception;

	/**
	 * 更新 缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	Object updateCached(byte[] key, byte[] value, Long expire) throws Exception;

	/**
	 * 获取缓存
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Object getCached(byte[] key) throws Exception;

	/**
	 * 根据 正则表达式key 获取 列表
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	Set getKeys(byte[] keys) throws Exception;

	/**
	 * 根据 正则表达式key 获取 列表
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	Set getHashKeys(byte[] key) throws Exception;

	/**
	 * 更新 缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	Boolean updateHashCached(byte[] key, byte[] mapkey, byte[] value,
			Long expire) throws Exception;

	/**
	 * 获取缓存
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Object getHashCached(byte[] key, byte[] mapkey) throws Exception;

	/**
	 * 删除 缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	Long deleteHashCached(byte[] key, byte[] mapkey) throws Exception;

	/**
	 * 获取 map的长度
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Long getHashSize(byte[] key) throws Exception;

	/**
	 * 获取 map中的所有值
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	List getHashValues(byte[] key) throws Exception;

	/**
	 * 获取 map的长度
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Long getDBSize() throws Exception;

	/**
	 * 清除数据库
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	void clearDB() throws Exception;
}
