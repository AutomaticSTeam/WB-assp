package com.assp.modules.module.mapper;   

import java.util.List;

import com.assp.modules.module.entity.TemplateModuleRelContent;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 *    模块数据关联
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月6日 下午5:07:42 
 */
public interface TemplateModuleRelContentMapper extends Mapper<TemplateModuleRelContent>{

	/**批量插入
	 * @param allModuleRelContents
	 */
	void insertList(List<TemplateModuleRelContent> allModuleRelContents);

}
  