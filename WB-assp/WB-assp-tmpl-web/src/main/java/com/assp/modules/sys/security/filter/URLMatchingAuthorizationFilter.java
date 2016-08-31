package com.assp.modules.sys.security.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;


public class URLMatchingAuthorizationFilter extends PermissionsAuthorizationFilter {
	
	private static final Logger logger = Logger.getLogger(URLMatchingAuthorizationFilter.class);
	
	
	public boolean isAccessAllowed(
			ServletRequest request,ServletResponse response, 
			Object mappedValue) {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		logger.debug(">>>validate URL in URLPermissionAuthorizationFilter");
		String servletPath = httpRequest.getServletPath();
		logger.debug("shiro filter url : "+servletPath);
		Subject subject = getSubject(request, response);  
		String[] perms = (String[]) mappedValue;  
		
		boolean isPermitted = true;
	    if (perms != null && perms.length > 0) {
	    	if (perms.length == 1) {
	    		if (!subject.isPermitted(perms[0])) {
	    			isPermitted = false;
	            }
	        } else {
	            if (!subject.isPermittedAll(perms)) {
	                isPermitted = false;
	            }
	        }
	    }
		return isPermitted; 
	}
}
