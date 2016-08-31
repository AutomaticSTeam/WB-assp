package com.assp.modules.sys.security.filter;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;

import org.apache.log4j.Logger;
import org.apache.shiro.config.Ini;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.Nameable;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.apache.shiro.web.filter.mgt.SimpleNamedFilterList;
import org.springframework.beans.factory.annotation.Autowired;

import com.assp.modules.sys.entity.Permission;
import com.assp.modules.sys.mapper.PermissionMapper;

public class CustomDefaultFilterChainManager extends DefaultFilterChainManager {
	
	private static final Logger logger = Logger.getLogger(CustomDefaultFilterChainManager.class);
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	/**
     * 默认premission字符串
     */
    public static final String PREMISSION_STRING="perms[\"{0}\"]";
	
    private Map<String, String> filterChainDefinitionMap = null;
    
    private String loginUrl;
    
    private String successUrl;
    
    private String unauthorizedUrl;
    
    public CustomDefaultFilterChainManager() {
        setFilters(new LinkedHashMap<String, Filter>());
        setFilterChains(new LinkedHashMap<String, NamedFilterList>());
        addDefaultFilters(true);
    }
    public Map<String, String> getFilterChainDefinitionMap() {
        return filterChainDefinitionMap;
    }
    public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
        this.filterChainDefinitionMap = filterChainDefinitionMap;
    }
    public void setCustomFilters(Map<String, Filter> customFilters) {
        for(Map.Entry<String, Filter> entry : customFilters.entrySet()) {
            addFilter(entry.getKey(), entry.getValue(), false);
        }
    }
    public void setDefaultFilterChainDefinitions(String definitions) {
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        //循环Resource的url,逐个添加到section中。section就是filterChainDefinitionMap,
        //里面的键就是链接URL,值就是存在什么条件才能访问该链接
        //获取所有Resource
        List<Permission> list = permissionMapper.select(null);
        for (int i = 0; i < list.size(); i++) {
        	//如果不为空值添加到section中
        	Permission permission = list.get(i);
        	if((permission.getPermissionCode() != null && permission.getPermissionCode().length() >0)
        			&& (permission.getPermissionUrl() != null && permission.getPermissionUrl().length() >0) ) {
                section.put(permission.getPermissionUrl(), MessageFormat.format(PREMISSION_STRING,permission.getPermissionCode()));
            }
		}
        setFilterChainDefinitionMap(section);
        logger.debug("所拥有的权限路径>>>"+section.toString());
    }
    public String getLoginUrl() {
        return loginUrl;
    }
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
    public String getSuccessUrl() {
        return successUrl;
    }
    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }
    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }
    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }
    
    
    @PostConstruct
    public void init() {
        Map<String, Filter> filters = getFilters();
        if (!CollectionUtils.isEmpty(filters)) {
            for (Map.Entry<String, Filter> entry : filters.entrySet()) {
                String name = entry.getKey();
                Filter filter = entry.getValue();
                applyGlobalPropertiesIfNecessary(filter);
                if (filter instanceof Nameable) {
                    ((Nameable) filter).setName(name);
                }
                addFilter(name, filter, false);
            }
        }
        Map<String, String> chains = getFilterChainDefinitionMap();
        if (!CollectionUtils.isEmpty(chains)) {
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue();
                createChain(url, chainDefinition);
            }
        }
    }
    
    protected void initFilter(Filter filter) {
        //ignore 
    }

    public FilterChain proxy(FilterChain original, List<String> chainNames) {
        NamedFilterList configured = new SimpleNamedFilterList(chainNames.toString());
        for(String chainName : chainNames) {
            configured.addAll(getChain(chainName));
        }
        return configured.proxy(original);
    }
    
    private void applyGlobalPropertiesIfNecessary(Filter filter) {
        applyLoginUrlIfNecessary(filter);
        applySuccessUrlIfNecessary(filter);
        applyUnauthorizedUrlIfNecessary(filter);
    }
    
    private void applyLoginUrlIfNecessary(Filter filter) {
        //请参考源码
    	String loginUrl = getLoginUrl();
    	if (StringUtils.hasText(loginUrl) && (filter instanceof AccessControlFilter)) {
            AccessControlFilter acFilter = (AccessControlFilter) filter;
            //only apply the login url if they haven't explicitly configured one already:
            String existingLoginUrl = acFilter.getLoginUrl();
            if (AccessControlFilter.DEFAULT_LOGIN_URL.equals(existingLoginUrl)) {
                acFilter.setLoginUrl(loginUrl);
            }
        }
    }
    
    private void applySuccessUrlIfNecessary(Filter filter) {
        //请参考源码
    	String successUrl = getSuccessUrl();
    	if (StringUtils.hasText(successUrl) && (filter instanceof AuthenticationFilter)) {
            AuthenticationFilter authcFilter = (AuthenticationFilter) filter;
            //only apply the successUrl if they haven't explicitly configured one already:
            String existingSuccessUrl = authcFilter.getSuccessUrl();
            if (AuthenticationFilter.DEFAULT_SUCCESS_URL.equals(existingSuccessUrl)) {
                authcFilter.setSuccessUrl(successUrl);
            }
        }
    }
    
    private void applyUnauthorizedUrlIfNecessary(Filter filter) {
        //请参考源码
    	String unauthorizedUrl = getUnauthorizedUrl();
    	if (StringUtils.hasText(unauthorizedUrl) && (filter instanceof AuthorizationFilter)) {
            AuthorizationFilter authzFilter = (AuthorizationFilter) filter;
            //only apply the unauthorizedUrl if they haven't explicitly configured one already:
            String existingUnauthorizedUrl = authzFilter.getUnauthorizedUrl();
            if (existingUnauthorizedUrl == null) {
                authzFilter.setUnauthorizedUrl(unauthorizedUrl);
            }
        }
    }
}
