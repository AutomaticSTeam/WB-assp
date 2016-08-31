package com.assp.modules.sys.security.cache;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import com.assp.common.utils.RedisCacheUtil;


/**
 * 
  * 类简述
  * <p>
  * ShiroRedisCache 管理
  * </p>
  * @Company 动力威视
  * @Copyright
  * @author (shx@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年4月20日 上午10:50:13
 */

public class ShiroRedisCacheManager extends AbstractCacheManager {
	private ICache cached;
	@SuppressWarnings("rawtypes")
	@Override
	protected Cache createCache(String cacheName) throws CacheException {
		RedisCacheUtil.setCacheName(cacheName);
		return new ShiroRedisCache<String, Object>(cacheName,cached);
	}
	public ICache getCached() {
		return cached;
	}
	public void setCached(ICache cached) {
		this.cached = cached;
	}

}
