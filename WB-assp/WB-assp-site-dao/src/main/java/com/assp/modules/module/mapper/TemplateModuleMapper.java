package com.assp.modules.module.mapper;   

import java.util.List;

import com.assp.modules.aspectjlog.mapper.BasicBatchMapper;
import com.assp.modules.module.entity.TemplateModule;
import com.assp.modules.module.entity.TemplateModuleVo;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 *    模块数据处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月6日 下午5:06:31 
 */
public interface TemplateModuleMapper extends Mapper<TemplateModule>,BasicBatchMapper<TemplateModule> {
     
	/**
	 * 
	* @Title: selectTemplateModulesByVo 
	* @Description: 通过条件查找模块【仅供模板加载调用，如有需要请另行配置】
	*@author (shx@sxw100.com)
	* @param @param templateModuleVo
	* @param @return    设定文件 
	* @return List<TemplateModule>    返回类型 
	* @throws
	 */
	public List<TemplateModule> selectTemplateModulesByVo(TemplateModuleVo templateModuleVo);

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
  