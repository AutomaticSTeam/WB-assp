package com.assp.modules.template.mapper;   

import java.util.List;

import com.assp.modules.common.BasicBatchMapper;
import com.assp.modules.template.entity.Template;
import com.assp.modules.template.entity.TemplateColumns;
import com.assp.modules.template.entity.TemplateFooterRelColumns;
import com.assp.modules.template.entity.TemplateRelColumns;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 *    模板栏目数据处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月26日 下午3:26:51 
 */
public interface TemplateColumnsMapper extends Mapper<TemplateColumns>,BasicBatchMapper<TemplateColumns> {

	/**
	 * 
	* @Title: selectTemplateColumnsByTemplateId 
	* @Description:   根据模板id获取模板栏目信息
	*@author (shx@sxw100.com)
	* @param @param templateId
	* @param @return    设定文件 
	* @return List<TemplateColumns>    返回类型 
	* @throws
	 */
	public List<TemplateColumns> selectTemplateColumnsByTemplateId(Integer templateId);
	
	/**
	 * 
	* @Title: selectTemplateColumnsByTemplateFooterId 
	* @Description: 根据底部id 获取栏目列表
	*@author (shx@sxw100.com)
	* @param @param templateFooterId
	* @param @return    设定文件 
	* @return List<TemplateColumns>    返回类型 
	* @throws
	 */
	public List<TemplateColumns> selectTemplateColumnsByTemplateFooterId(Integer templateFooterId);

	/**
	 * 
	* @Title: selectTemplateColumnsByTemplateRelColumns 
	* @Description: 根据模板导航关联关系条件 查询相应集合
	*@author (wzp@sxw100.com)
	* @param @param TemplateRelColumns
	* @return List<TemplateColumns>    返回类型 
	* @throws
	 */
	public List<TemplateColumns> selectTemplateColumnsByTemplateRelColumns(
			TemplateRelColumns trc);

	/**添加记录，并且返回主键
	 * @param templateColumns
	 * @return
	 */
	public int addGetprimaryKey(TemplateColumns templateColumns);

	/**返回站脚导航列表
	 * @param dataColumnId
	 * @return
	 */
	public List<TemplateColumns> selectTemplateColumnsInFooterByTemplateFooterId(
			Integer dataColumnId);

	/**根据条件查询站脚
	 * @param trc
	 * @return
	 */
	public List<TemplateColumns> selectTemplateColumnsByTemplateFooterRelColumns(
			TemplateFooterRelColumns trc);
	
	/**返回站脚导航列表
	 * @param dataColumnId
	 * @return
	 */
	public List<TemplateColumns> selectTemplateColumnsInFooterByTemplateVO(
			Template template);
	
}
  