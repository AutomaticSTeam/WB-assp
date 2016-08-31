package com.assp.modules.template.service;   

import com.assp.common.service.BasicService;
import com.assp.modules.template.entity.TemplateFooter;

/**
 * 类简述
 * <p>
 *      页脚服务接口
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月27日 上午10:00:37 
 */
public interface ITemplateFooterService extends BasicService<TemplateFooter> {

	int insertReturnKey(TemplateFooter templateFooter);
}
  