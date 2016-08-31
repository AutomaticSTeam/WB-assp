package com.assp.modules.template.mapper;   

import com.assp.modules.aspectjlog.mapper.BasicBatchMapper;
import com.assp.modules.template.entity.TemplateFrameRelModule;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 *     版块与模板关系
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月18日 下午4:44:03 
 */
public interface TemplateFrameRelModuleMapper extends Mapper<TemplateFrameRelModule>,BasicBatchMapper<TemplateFrameRelModule> {

	public TemplateFrameRelModule querySortNumMaxObj(Integer frameId);
}
  