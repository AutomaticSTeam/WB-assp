package com.assp.modules.website.vo;   

import java.util.List;

import com.assp.modules.module.entity.TemplateModule;

/**
 * 类简述
 * <p>
 *       模块组合视图
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年6月15日 下午5:33:15 
 */
public class ModuleVo {
      private Integer viewType; // 模块视图类型 0 单一模块 1 模块组
      private List<TemplateModule> moudles ; //模块集合
	public Integer getViewType() {
		return viewType;
	}
	public void setViewType(Integer viewType) {
		this.viewType = viewType;
	}
	public List<TemplateModule> getMoudles() {
		return moudles;
	}
	public void setMoudles(List<TemplateModule> moudles) {
		this.moudles = moudles;
	}
      
      
      
}
  