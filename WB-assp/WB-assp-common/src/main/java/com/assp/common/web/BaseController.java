package com.assp.common.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 基础控制器
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxwl100.com)
 * @version 1.0
 * @CreateDate 2016年4月6日 下午1:10:14
 */
public abstract class BaseController {

	/**
	 * 日志对象
	 */
	protected Logger logger = Logger.getLogger(getClass());

	/**
	 * 返回JSON 带时间格式处理的
	 */
	public void printJSONWithDateFormat(HttpServletResponse response, Object obj) {
		try {
			String jsonStrShow = JSONObject.toJSONStringWithDateFormat(obj,
					"yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonStrShow);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: printJSON
	 * @Description: 打印josn
	 * @param @param response
	 * @param @param obj
	 * @return void
	 * @throws
	 */
	public void printJSON(HttpServletResponse response, Object obj) {
		try {
			String jsonStrShow = JSONObject.toJSONString(obj);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonStrShow);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
