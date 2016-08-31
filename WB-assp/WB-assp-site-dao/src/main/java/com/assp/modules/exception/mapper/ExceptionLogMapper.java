package com.assp.modules.exception.mapper;

import com.assp.modules.exception.entity.ExceptionLog;

/**
 * 
  *  日志记录数据接口
  * @Company 动力威视
  * @Copyright
  * @author (shx@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年4月6日 下午1:35:06
 */
public interface ExceptionLogMapper{

	/**
	 * 
	* @Title: insertExceptionLog 
	* @Description: 插入日志数据
	*@author (shx@sxw100.com)
	*@param exceptionLog    
	* @return void    
	* @throws
	 */
	public void insertExceptionLog(ExceptionLog exceptionLog);
	
}
