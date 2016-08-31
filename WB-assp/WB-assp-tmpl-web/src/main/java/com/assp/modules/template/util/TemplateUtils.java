package com.assp.modules.template.util;   

import java.util.ArrayList;
import java.util.List;

import com.assp.modules.module.entity.TemplateModule;
import com.assp.modules.template.vo.ModuleVo;

/**
 * 类简述
 * <p>
 *    模板辅助工具类
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年6月15日 下午5:38:23 
 */
public class TemplateUtils {
	
	
	/**
	 * 
	* @Title: transformModuleVo 
	* @Description: 转化为模板模块视图
	*@author (shx@sxw100.com)
	* @param @param moudles
	* @param @return    设定文件 
	* @return List<ModuleVo>    返回类型 
	* @throws
	 */
	public static List<ModuleVo> transformModuleVo(List<TemplateModule> moudles){
		if(moudles == null || moudles.size() == 0) return null;
		List<ModuleVo>  moduleVos = new ArrayList<ModuleVo>();
		for(TemplateModule moudle : moudles){
			 if(moudle.getModulePid().intValue() == Integer.valueOf(0)){
				 ModuleVo  moduleVo = new ModuleVo();
				 List<TemplateModule> tm = setGroupModules(moudle,moudles);
				 moduleVo.setViewType((tm.size() > 1)? 1 : 0);
				 moduleVo.setMoudles(tm);
				 moduleVos.add(moduleVo);
			 }
		}
		return moduleVos;
	}
	
	private  static List<TemplateModule> setGroupModules(TemplateModule moudle , List<TemplateModule> moudles){
		List<TemplateModule> tm = new ArrayList<TemplateModule>();
		tm.add(moudle);
		for(TemplateModule m : moudles){
			 if(m.getModulePid().intValue() == moudle.getModuleId().intValue()){
				 tm.add(m);
			 }
		}
		return tm;
	}

}
  