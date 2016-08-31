package com.assp.modules.website.mapper;

import com.assp.modules.website.entity.Website;
import com.github.abel533.mapper.Mapper;

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
  * @CreateDate 2016年6月17日 下午7:21:50
 */
public interface WebsiteMapper extends Mapper<Website>{

	/**
	 * 
	* @Title: executeSync 
	* @Description: 站点同步
	* @author (panlinlin@sxw100.com)
	* @param @param parame    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public Integer executeSync(Website website);
	
	/**
	 * 
	* @Title: insertWebsite 
	* @Description: 添加website实例并返回主键id
	*@author (shx@sxw100.com)
	* @param @param website    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void insertWebsite(Website website);
	
	//void changeSyncSql();
	//void creaetMainSyncSql();
	
	/**
	 * 
	* @Title: batchSyncSQL 
	* @Description: 批量更新状态
	*@author (shx@sxw100.com)
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void batchSyncSQL();
   
}