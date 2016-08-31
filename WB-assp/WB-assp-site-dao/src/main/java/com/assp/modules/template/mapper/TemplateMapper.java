package com.assp.modules.template.mapper;   

import java.util.List;

import com.assp.modules.template.entity.Template;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 *     模板数据处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月26日 下午3:22:46 
 */
public interface TemplateMapper extends Mapper<Template> {

	/**根据查询条件查询
	 * @param example
	 * @return
	 */
	List<Template> queryTemplateByCondition(Template example);
	
	/**根据查询条件查询收藏
	 * @param example
	 * @return
	 */
	List<Template> queryTemplateIncollectByCondition(Template example);
	
	/**
	   * 
	  * @Title: batchUpdateMedia 
	  * @Description: 批量更新视频属性
	  *@author (shx@sxw100.com)
	  * @param @param media    设定文件 
	  * @return void    返回类型 
	  * @throws
	   */
	  public void batchUpdateTemplate(Template example);

	int insertKey(Template template);
	
	Template queryMaxTemplateCode(Template template);
}
  