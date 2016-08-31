package com.assp.modules.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

import com.assp.common.utils.RedisCacheUtil;
import com.assp.modules.sys.entity.User;


public class MyEnterpriseCacheSessionDAO extends EnterpriseCacheSessionDAO {
	@Override
	protected void uncache(Session session) {
		// TODO Auto-generated method stub
        if (session != null &&session.getAttribute("loginUser") != null ) {
        	User loginUser = (User)session.getAttribute("loginUser");
        	Cache<String, Object> redisCache = RedisCacheUtil.getRedisCache();
            if (redisCache != null && session.getId() != null &&StringUtils.equals(session.getId().toString(), (String)redisCache.get(loginUser.getNickName()))) {
            	redisCache.remove(loginUser.getNickName());
            }
        }
		super.uncache(session);
	}
}
