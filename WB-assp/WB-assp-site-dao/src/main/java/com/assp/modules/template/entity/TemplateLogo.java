package com.assp.modules.template.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 类简述
 * <p>
 * 模板logo
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月13日 下午3:20:08
 */
@Table(name = "wms_template_logo")
public class TemplateLogo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer templateLogoId; // 'logo id',
	private String logoImgUrl; // 'logo路径',
	private String logoTitle; // 'logo 图片 alt title属性',
	private String fisrtTitle; // '主标题',
	private String secondTitle; // '二级标题',
	private Integer logoImgHide; // 'logo是否可见',1位可见
	private String logoImgHotLine; // 'logo链接地址',
	private String imgHeight; // '图片高',
	private String imgWidth; // '图片宽',

	@Transient
	private Integer siteId;//站点id，用作查询用
	
	public Integer getTemplateLogoId() {
		return templateLogoId;
	}

	public void setTemplateLogoId(Integer templateLogoId) {
		this.templateLogoId = templateLogoId;
	}

	public String getLogoImgUrl() {
		return logoImgUrl;
	}

	public void setLogoImgUrl(String logoImgUrl) {
		this.logoImgUrl = logoImgUrl;
	}

	public String getLogoTitle() {
		return logoTitle;
	}

	public void setLogoTitle(String logoTitle) {
		this.logoTitle = logoTitle;
	}

	public String getFisrtTitle() {
		return fisrtTitle;
	}

	public void setFisrtTitle(String fisrtTitle) {
		this.fisrtTitle = fisrtTitle;
	}

	public String getSecondTitle() {
		return secondTitle;
	}

	public void setSecondTitle(String secondTitle) {
		this.secondTitle = secondTitle;
	}

	public String getLogoImgHotLine() {
		return logoImgHotLine;
	}

	public void setLogoImgHotLine(String logoImgHotLine) {
		this.logoImgHotLine = logoImgHotLine;
	}

	public Integer getLogoImgHide() {
		return logoImgHide;
	}

	public void setLogoImgHide(Integer logoImgHide) {
		this.logoImgHide = logoImgHide;
	}
	
	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}

	public String getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}

}
