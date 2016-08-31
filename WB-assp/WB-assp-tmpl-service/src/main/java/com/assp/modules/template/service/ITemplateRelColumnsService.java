package com.assp.modules.template.service;   

import com.assp.common.service.BasicService;
import com.assp.modules.template.entity.TemplateRelColumns;

/**
 * 类简述
 * <p>
 *     栏目管理服务接口
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wzp@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年7月8日 
 */
public interface ITemplateRelColumnsService extends BasicService<TemplateRelColumns>{

	public TemplateRelColumns querySortNumMaxObj(Integer templateId);
}
  