package com.assp.modules.sys.security.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import com.assp.common.SerializeUtil;

/**
 * 类简述
 * <p>
 * 对ShiroCache 的改写
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月20日 上午10:34:44
 */
public class ShiroRedisCache<K, V> implements Cache<K, V> {

	private String name;
	private ICache cached;

	public ShiroRedisCache(String name, ICache cached) {
		this.name = name;
		this.cached = cached;
	}

	@Override
	public void clear() throws CacheException {
		try {
			cached.deleteCached(getByteName());
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public V get(K key) throws CacheException {
		try {
			if (key == null) {
				return null;
			} else {
				@SuppressWarnings("unchecked")
				V value = (V) cached.getHashCached(getByteName(),
						getByteKey(key));
				return value;
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		try {
			Set<K> keys = cached.getHashKeys(getByteName());
			return keys;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public V put(K key, V value) throws CacheException {
		try {
			cached.updateHashCached(getByteName(), getByteKey(key),
					SerializeUtil.serialize(value), null);
			return value;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public V remove(K key) throws CacheException {
		try {
			V previous = get(key);
			cached.deleteHashCached(getByteName(), getByteKey(key));
			return previous;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public int size() {
		try {
			Long longSize = new Long(cached.getHashSize(getByteName()));
			return longSize.intValue();
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<V> values() {
		try {
			Collection<V> values = cached.getHashValues(getByteName());
			return values;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/**
	 * 获得byte[]型的key
	 * 
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(K key) {
		if (key instanceof String) {
			String preKey = key.toString();
			return preKey.getBytes();
		} else {
			return SerializeUtil.serialize(key);
		}
	}

	private byte[] getByteName() {
		return name.getBytes();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ICache getCached() {
		return cached;
	}

	public void setCached(ICache cached) {
		this.cached = cached;
	}

}
