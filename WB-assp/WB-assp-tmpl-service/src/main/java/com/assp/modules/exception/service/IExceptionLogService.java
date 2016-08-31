package com.assp.modules.exception.service;   

import com.assp.modules.exception.entity.ExceptionLog;

/**
 * 类简述
 * <p>
 *     异常服务接口
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月29日 下午1:33:45 
 */
public interface IExceptionLogService  {

	/**
	 * 
	* @Title: insertExceptionLog 
	* @Description: 插入日志数据
	*@author (shx@sxw100.com)
	*@param exceptionLog    
	* @return void    
	* @throws
	 */
	public void addExceptionLog(ExceptionLog exceptionLog);
}
  