package com.assp.modules.website.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类简述
 * <p>
 * 类说明、详细描述(optional)
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年7月23日 下午4:27:40
 */
@Table(name = "wms_website_rel_template")
public class WebsiteRelTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer siteTemplateId; // '站点模板id',
	private Integer siteId;
	private Integer templateId;

	public Integer getSiteTemplateId() {
		return siteTemplateId;
	}

	public void setSiteTemplateId(Integer siteTemplateId) {
		this.siteTemplateId = siteTemplateId;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

}
