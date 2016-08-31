package com.assp.modules.common.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.assp.common.utils.WildcardPatternBuilder;
/**
 * 
  * 类简述
  * <p>
  *  用于过滤未登录用户
  * </p>
  * @Company 动力威视
  * @Copyright
  * @author (shx@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年4月6日 下午1:43:51
 */
public class LoginForwarFilter implements Filter {
	private Log log = LogFactory.getLog(getClass());
	
	/** 需要排除（不拦截）的URL的正则表达式 */
	private Pattern excepUrlPattern;

	public void init(FilterConfig cfg) throws ServletException {
		/** 获取web.xml配置的文件信息 */
		String excepUrlRegex = cfg.getInitParameter("excepUrlRegex");
		if (!StringUtils.isBlank(excepUrlRegex)) {
			excepUrlPattern = WildcardPatternBuilder.build(excepUrlRegex);
		}
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		// 访问路径
		String servletPath = request.getServletPath();
		String resetPasswordUrl="/jsp/common/resetPassWord.jsp";

		// 如果请求的路径与排除检查的路径相同放过
		if (excepUrlPattern.matcher(servletPath).matches()) {
			if (servletPath.equals(resetPasswordUrl)) {//如果请求密码重置页面，做判断
				if (session!=null) {
					if (!"success".equals(session.getAttribute("result")) ) {//若checkMessage的校验结果不为success，也包含session的arrtibute为空的情况
						response.sendRedirect(request.getContextPath() + "/jsp/common/login.jsp");
						return;
					}else{
						chain.doFilter(req, resp);
						return;	
					}					
				}
			}else{
				chain.doFilter(req, resp);
				return;	
			}
		}
		
		log.info("请求链接：" + servletPath);
		log.info("请求参数：" + request.getQueryString());
		if (session != null) {
			if (null == session.getAttribute("loginUser")) {
				if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
		                .getHeader("X-Requested-With")!= null && request  
		                .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
					response.getWriter().write("<script>top.location.href=\""+request.getContextPath()
							+ "/jsp/common/login.jsp\"</script>");
				}else{
					Map<String, Object> returnMap = new HashMap<String, Object>();
					returnMap.put("result", "login");
					String jsonStrShow = JSONObject.toJSONString(returnMap);
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().print(jsonStrShow);
					response.getWriter().flush(); 
					response.getWriter().close();
				}
			} else {// 登录用户不校验
				chain.doFilter(req, resp);
				return;
			}
		} else {
			if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
	                .getHeader("X-Requested-With")!= null && request  
	                .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
				response.getWriter().write("<script>top.location.href=\""+request.getContextPath()
						+ "/jsp/common/login.jsp\"</script>");
			}else{
				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("result", "login");
				String jsonStrShow = JSONObject.toJSONString(returnMap);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(jsonStrShow);
				response.getWriter().flush(); 
				response.getWriter().close();
			}
		}
	}

	public void destroy() {
	}

}
