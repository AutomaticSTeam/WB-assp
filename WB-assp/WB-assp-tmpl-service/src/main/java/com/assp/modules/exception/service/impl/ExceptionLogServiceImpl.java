package com.assp.modules.exception.service.impl;   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.exception.entity.ExceptionLog;
import com.assp.modules.exception.mapper.ExceptionLogMapper;
import com.assp.modules.exception.service.IExceptionLogService;

/**
 * 类简述
 * <p>
 * 类说明、详细描述(optional)
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月29日 下午1:34:50 
 */
@Service
public class ExceptionLogServiceImpl implements IExceptionLogService {

	@Autowired
	private ExceptionLogMapper exceptionLogMapper;

	@Override
	public void addExceptionLog(ExceptionLog exceptionLog) {
		exceptionLogMapper.insertExceptionLog(exceptionLog);
	}
	
	
}
  