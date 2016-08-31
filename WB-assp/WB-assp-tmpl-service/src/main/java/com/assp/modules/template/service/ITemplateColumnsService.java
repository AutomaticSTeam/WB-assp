package com.assp.modules.template.service;   

import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.template.entity.TemplateColumns;

/**
 * 类简述
 * <p>
 *     栏目服务接口
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月27日 上午9:43:38 
 */
public interface ITemplateColumnsService extends BasicService<TemplateColumns>{
	
    /**
     *  
    * @Title: queryTemplateColumnsByTemplateId 
    * @Description: 通过模板id查询模板列
    *@author (shx@sxw100.com)
    * @param @param templateId
    * @param @return    设定文件 
    * @return List<TemplateColumns>    返回类型 
    * @throws
     */
	public List<TemplateColumns> queryTemplateColumnsByTemplateId(Integer templateId);
	
	/**
	 * 
	* @Title: queryTemplateColumnsByTemplateFooterId 
	* @Description: 通过页脚id查询模板列
	*@author (shx@sxw100.com)
	* @param @param templateFooterId
	* @param @return    设定文件 
	* @return List<TemplateColumns>    返回类型 
	* @throws
	 */
	public List<TemplateColumns> queryTemplateColumnsByTemplateFooterId(Integer templateFooterId);
	/**
	 * 
	* @Title: addGetprimaryKey 
	* @Description: insert并返回主键
	*@author (wzp@sxw100.com)
	* @param @param templateColumns
	* @param @return    设定文件 
	* @throws
	 */
	public int addGetprimaryKey(TemplateColumns templateColumns);

	/**
	 * 
	* @Title: selectTemplateColumnsInFooterByTemplateId 
	* @Description: 根据TemplateFooterId
	*@author (wzp@sxw100.com)
	* @param @param templateColumns
	* @param @return    设定文件 
	* @throws
	 */
	public List<TemplateColumns> selectTemplateColumnsInFooterByTemplateFooterId(
			Integer dataColumnId);


	/**
	 * 
	 * 描述：columns批量添加
	 * 创建人： zhangtl
	 * 创建时间: 2016-4-20 上午10:22:11
	 * @version
	 */
	public int insertList(List<TemplateColumns> list);
}
  