package com.assp.modules.template.entity;

import java.io.Serializable;

/**
 * 类简述
 * <p>
 * 模板公用页框架接口
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wzp@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年7月18日 
 */
public interface TemplateRelFrame extends Serializable {
	
	/**获取排序号
	 * @return
	 */
	Integer getSortNum();
	
	/**样式的class类
	 * @return
	 */
	String getCssClass();
}
