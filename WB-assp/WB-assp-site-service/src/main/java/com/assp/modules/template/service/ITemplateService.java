package com.assp.modules.template.service;   

import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.module.entity.TemplateModule;
import com.assp.modules.template.entity.Template;
import com.assp.modules.template.entity.TemplateColumnsRelFrame;
import com.assp.modules.template.entity.TemplateCommonRelFrame;
import com.assp.modules.template.entity.TemplateRelColumns;
import com.assp.modules.template.entity.TemplateRelFrame;
import com.assp.modules.template.entity.TemplateVo;
import com.assp.modules.website.entity.Website;

/**
 * 类简述
 * <p>
 *     模板的业务服务类
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月26日 下午5:16:20 
 */
public interface ITemplateService extends BasicService<Template>{
    
	/**
	 * 
	* @Title: qeruyTemplatePage 
	* @Description: 获取模板页信息
	*@author (shx@sxw100.com)
	* @param @param templateId
	* @param @param templateFooterId
	* @param @param pageType
	* @param @return    设定文件 
	* @return TemplateVo    返回类型 
	* @throws
	 */
	public TemplateVo qeruyTemplatePage(Template tempalte);
	
	/**
	 * 
	* @Title: queryTemplateDynamicModules 
	* @Description: 加载动态模块数据
	*@author (shx@sxw100.com)
	* @param @param tempalte
	* @param @return    设定文件 
	* @return List<TemplateModule>    返回类型 
	* @throws
	 */
	public List<TemplateModule> queryTemplateDynamicModules(Template tempalte);
	
	/**
	 * 
	* @Title: queryPageFrameInfo 
	* @Description:  查询页面的框架信息
	*@author (shx@sxw100.com)
	* @param @param columnsId
	* @param @return    设定文件 
	* @return List<TemplateColumnsRelFrame>    返回类型 
	* @throws
	 */
	public List<TemplateColumnsRelFrame> queryPageFrameInfo(Integer columnsId);
	
	/**
	 * 
	 * @Title: queryPageFrameInfo 
	 * @Description:  查询页面的框架信息
	 *@author (shx@sxw100.com)
	 * @param @param columnsId
	 * @param @return    设定文件 
	 * @return List<TemplateColumnsRelFrame>    返回类型 
	 * @throws
	 */
	public List<TemplateRelFrame> queryPageFrameInfo(Template template,Integer columnsId);

	/**根据条件查询
	 * @param example
	 * @return
	 */
	public List<Template> queryTemplateByCondition(Template example);
	
	/**根据条件查询收藏
	 * @param example
	 * @return
	 */
	public List<Template> queryTemplateIncollectByCondition(Template example);
	
	/**根据条件查询
	 * @param example
	 * @return
	 */
	public List<TemplateRelColumns> queryTemplateRelColumnsByCondition(Template example);

	/**先取公用，再根据条件取集合
	 * @param template
	 * @param columnsId
	 * @param tcr
	 * @return
	 */
	List<TemplateRelFrame> queryPageFrameInfo(Template template,
			Integer columnsId, List<TemplateCommonRelFrame> tcrList);
	
	/**
	  * 
	 * @Title: batchUpdateMedia 
	 * @Description: 批量更新视频信息
	 *@author (shx@sxw100.com)
	 * @param @param media
	 * @param @param mediaIds    设定文件 
	 * @return void    返回类型 
	 * @throws
	  */
	 public void batchUpdateTemplate(Template example);
	
	/**
	 * 插入返回主键
	 * @param template
	 * @return
	 */
	public int insertKey(Template template);
	
	/**
	 * 查询最大模板编码
	 * @param template
	 * @return
	 */
	public Template queryMaxTemplateCode(Template template);

	/**
	 * 
	* @Title: executeSync 
	* @Description: 站点同步
	* @author (panlinlin@sxw100.com)
	* @param @param parame    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void executeSync(Website website);
}
  