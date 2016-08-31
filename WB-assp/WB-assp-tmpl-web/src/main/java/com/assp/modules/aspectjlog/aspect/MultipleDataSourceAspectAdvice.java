package com.assp.modules.aspectjlog.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;

import com.assp.common.constant.SystemConstants;
import com.assp.modules.sys.security.MultipleDataSource;

/**
 * 
  * 类简述
  * <p>
  * 类说明、详细描述(optional)
  * </p>
  * @Company 动力威视
  * @Copyright
  * @author (panlinlin@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年7月13日 下午5:47:51
 */

/*@Component
@Aspect*/
public class MultipleDataSourceAspectAdvice {

	private static final Logger logger = Logger. getLogger(MultipleDataSourceAspectAdvice.class);
	
    @Around("execution(* com.dlws.modules.*.*.*.*(..))")
    public Object   doAround(ProceedingJoinPoint jp) throws Throwable {
    	
    	 MethodSignature ms = (MethodSignature)jp.getSignature();
    	
    	 if(ms!=null&&ms.getMethod()!=null){
    		 String method = ms.getMethod().toString();
			 if(method.indexOf("template.web") != -1){  
	    	      MultipleDataSource.setDataSourceKey(SystemConstants.DATA_SOURCE_TEMPLATE);
	    	      logger.debug("***********************数据源已切换至template*******************************");
	    	 }else{
	    		 MultipleDataSource.setDataSourceKey(SystemConstants.DATA_SOURCE_SITE);
	    		 logger.debug("***********************数据源已切换至site*******************************");
	    	 }
    	 }
    	
        return jp.proceed();
    }
}
