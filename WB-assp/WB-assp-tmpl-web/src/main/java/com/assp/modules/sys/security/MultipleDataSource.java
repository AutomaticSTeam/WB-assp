package com.assp.modules.sys.security;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * 、
  * 类简述
  * <p>
  * 类说明、详细描述(optional)
  * </p>
  * @Company 动力威视
  * @Copyright
  * @author (panlinlin@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年7月13日 下午4:44:48
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

	 private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();
	 
	    public static void setDataSourceKey(String dataSource) {
	        dataSourceKey.set(dataSource);
	    }

	    @Override
	    protected Object determineCurrentLookupKey() {
	        return dataSourceKey.get();
	    }


		

}
