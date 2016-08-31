package com.assp.modules.website.service;   

import java.util.Map;

import com.assp.common.service.BasicService;
import com.assp.modules.sys.entity.User;
import com.assp.modules.template.entity.Template;
import com.assp.modules.website.entity.Website;

/**
 * 类简述
 * <p>
 *     站点服务接口
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年7月22日 上午11:19:05 
 */
public interface IWebsiteService extends BasicService<Website> {
     
	  /**
	   * 
	  * @Title: addWebsite 
	  * @Description: 添加站点实例并返回站点id
	  *@author (shx@sxw100.com)
	  * @param @param website    设定文件 
	  * @return void    返回类型 
	  * @throws
	   */
	   public void addWebsite(Website website);
	   
	   /**
	 * @throws Exception 
	 * @throws NumberFormatException 
	    * 
	   * @Title: templateToWebsite 
	   * @Description: 模板转化站点 
	   *@author (shx@sxw100.com)
	   * @param @param template
	   * @param @param user
	   * @param @return    设定文件 
	   * @return Map<String,Object>    返回类型 
	   * @throws
	    */
	   public Map<String , Object> templateToWebsite(Template template , User user) throws NumberFormatException, Exception;
	   
}
  