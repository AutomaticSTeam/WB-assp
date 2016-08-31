package com.assp.modules.module.service;   

import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.module.entity.TemplateModule;
import com.assp.modules.template.entity.TemplateRelColumns;

/**
 * 类简述
 * <p>
 *      模块服务
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月6日 下午5:19:00 
 */
public interface ITemplateModuleService extends BasicService<TemplateModule> {
   
	/**
	 * 
	* @Title: queryTemplateModulesByIds 
	* @Description: 根据模块id集合获取模块信息
	*@author (shx@sxw100.com)
	* @param @param templateModuleIds
	* @param @return    设定文件 
	* @return List<TemplateModule>    返回类型 
	* @throws
	 */
	public List<TemplateModule> queryTemplateModulesByIds(List<Integer> templateModuleIds);

	/**
	 * 
	* @Title: queryTemplateModuleById 
	* @Description: 根据模块id获取单个模块信息（不包含子模块儿）
	*@author (wzp@sxw100.com)
	* @param @param moduleId
	* @param @return    设定文件 
	* @return TemplateModule    返回类型 
	* @throws
	 */
	public TemplateModule queryTemplateModuleByTemplateModule(TemplateModule templateModule);
	/**
	 * 
	* @Title: addModuleForTemplateColumns 
	* @Description: 为新添加的导航添置预设框架模块组
	*@author (wzp@sxw100.com)
	* @param @param moduleId
	* @param @return    设定文件 
	* @return TemplateModule    返回类型 
	* @throws
	 */
	public void addModuleForTemplateColumns(
			TemplateRelColumns templateRelColumns);

	/**
	 * 
	* @Title: addModuleReturnId 
	* @Description: 添加模块并返回主键
	* @author (panlinlin@sxw100.com)
	* @param @param module
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int addModuleReturnId(TemplateModule module);
	
}
  