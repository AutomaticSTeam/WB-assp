package com.assp.modules.template.mapper;   

import com.assp.modules.aspectjlog.mapper.BasicBatchMapper;
import com.assp.modules.template.entity.TemplateColumnsRelFrame;
import com.assp.modules.template.entity.TemplateCommonRelFrame;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 *     模板页预加载与公用框架数据处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wzp@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月17日 下午12:01:04 
 */
public interface TemplateCommonRelFrameMapper extends Mapper<TemplateCommonRelFrame>,BasicBatchMapper<TemplateCommonRelFrame> {

	/**插入对象并返回key
	 * @param selfTcrf
	 */
	void insertGetprimaryKey(TemplateColumnsRelFrame selfTcrf);

}
  