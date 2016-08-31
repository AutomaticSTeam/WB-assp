package com.assp.modules.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.assp.common.constant.SystemConstants;
import com.assp.modules.exception.entity.ExceptionLog;
import com.assp.modules.exception.service.IExceptionLogService;
/**
 * 
  * 类简述
  * <p>
  *      异常处理编码器
  * </p>
  * @Company 动力威视
  * @Copyright
  * @author (shx@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年4月6日 下午3:24:06
 */
public class MyExceptionHandler implements HandlerExceptionResolver {
	
	@Autowired
	private IExceptionLogService iExceptionLogService;

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ExceptionLog exceptionLog = new ExceptionLog();
		Map<String, Object> model = new HashMap<String, Object>();
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw,true));
		exceptionLog.setStackTrace(sw.toString());
		exceptionLog.setSource(request.getServletPath());
		exceptionLog.setOccuredTime(new Date());
		exceptionLog.setHints(request.getQueryString());
		exceptionLog.setSystemCode(SystemConstants.WMS_PLATFORM);
		iExceptionLogService.addExceptionLog(exceptionLog);
		model.put("ex", exceptionLog);
		if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
                .getHeader("X-Requested-With")!= null && request  
                .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			// 根据不同错误转向不同页面
			return new ModelAndView("/error/error", model);
		}else{
			try { 
				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("result", "Nologin");
				String jsonStrShow = JSONObject.toJSONString(returnMap);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(jsonStrShow);
				response.getWriter().flush(); 
				response.getWriter().close();
			} catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
}
