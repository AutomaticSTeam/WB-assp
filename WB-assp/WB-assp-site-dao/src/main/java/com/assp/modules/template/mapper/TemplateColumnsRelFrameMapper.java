package com.assp.modules.template.mapper;   

import com.assp.modules.template.entity.TemplateColumnsRelFrame;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 *     模板页框架数据处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月17日 下午12:01:04 
 */
public interface TemplateColumnsRelFrameMapper extends Mapper<TemplateColumnsRelFrame> {

	/**插入对象并返回主键
	 * @param selfTcrf
	 */
	void insertGetprimaryKey(TemplateColumnsRelFrame selfTcrf);

	TemplateColumnsRelFrame querySortNumMaxObj(Integer templateId);
}
  