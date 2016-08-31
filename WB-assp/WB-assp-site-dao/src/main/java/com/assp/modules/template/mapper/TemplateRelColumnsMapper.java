package com.assp.modules.template.mapper;   

import java.util.List;

import com.assp.modules.template.entity.TemplateRelColumns;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 *     模板关联模块数据处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wangzhipeng@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年6月27日
 */
public interface TemplateRelColumnsMapper extends Mapper<TemplateRelColumns> {

	/**按顺序查询
	 * @param tem
	 * @return
	 */
	List<TemplateRelColumns> selectByOrder(TemplateRelColumns tem);

	TemplateRelColumns querySortNumMaxObj(Integer templateId);
}
  