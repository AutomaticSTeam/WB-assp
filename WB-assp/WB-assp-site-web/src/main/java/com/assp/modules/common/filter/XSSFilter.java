package com.assp.modules.common.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 
  * 类简述
  * <p>
  *     用于防止脚本攻击
  * </p>
  * @Company 动力威视
  * @Copyright
  * @author (shx@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年4月6日 下午1:44:08
 */
public class XSSFilter implements Filter {
	private Log log = LogFactory.getLog(getClass());

	/*private String[] illegalString = { "<", ">", "&#", "\"", "'", "`", "\\",
			"=", "\r", "\n", "(", ")",";","--","+","SELECT","DROP","FROM","DELETE","JAVASCRIPT","JSCRIPT","VBSCRIPT"};*/
	private String[] illegalString = { "<", ">", "&#", "\"", "'", "`", "\\",
			"=", "\r", "\n", "(", ")","--","+","SELECT","DROP","FROM","DELETE","JAVASCRIPT","JSCRIPT","VBSCRIPT"};
	private String[] passString = {"questionDesc","productIntroduceValue","clobsFunction", "clobsExpenses", "clobsScene", "clobsFaq", "clobsWay","articlecontent","clobsText"};
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request1, ServletResponse response1,
			FilterChain chain) throws IOException, ServletException {
		request1.setCharacterEncoding("UTF-8");
		HttpServletRequest request = (HttpServletRequest) request1;
		HttpServletResponse response = (HttpServletResponse) response1;
		Enumeration<String> enumString = request.getParameterNames();

		boolean containsIllegal = false;
		boolean passFlag = false;
		while (enumString.hasMoreElements()) {
			String s = enumString.nextElement();
			if (s != null) {
				for (int j = 0; j < this.illegalString.length; j++) {
					if (s.contains(this.illegalString[j].toUpperCase())) {
						containsIllegal = true;
						break;
					}
				}
			}
			//html内容不走过滤器
			
			for (int i = 0; i < this.passString.length; i++) {
				if (s.equals(this.passString[i])) {
					passFlag = true;
					break;
				}
			}
			if(passFlag == true) {
				continue;
			}
			
			String[] sv = request.getParameterValues(s);
			
			if (sv != null) {
				for (int i = 0; i < sv.length; i++) {
					for (int j = 0; j < this.illegalString.length; j++) {
						if (sv[i].contains(this.illegalString[j].toLowerCase())) {
							containsIllegal = true;
							break;
						}
					}
				}
			}
		}

		if (containsIllegal) {
			response.sendRedirect(request.getContextPath()+"/jsp/error/illegal.jsp");
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		log.debug("XSS攻击过滤器初始化...");
	}
}