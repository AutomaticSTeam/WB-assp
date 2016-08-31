package com.assp.modules.template.entity;

import java.util.List;

import com.assp.modules.commondata.entity.Picture;

/**
 * 类简述
 * <p>
 * 模板视图(固有模块)
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月26日 下午5:24:00
 */
public class TemplateVo {
	
	private TemplateLogo templateLogo;
	
	private List<TemplateColumns> templateColumns;

	private List<TemplateBanner> templateBanners;   //  优化掉
	
	private List<Picture> bannerPictures; // 轮播图图片
	
	private TemplateFooter templateFooter;
	
    
	public List<Picture> getBannerPictures() {
		return bannerPictures;
	}

	public void setBannerPictures(List<Picture> bannerPictures) {
		this.bannerPictures = bannerPictures;
	}

	public TemplateLogo getTemplateLogo() {
		return templateLogo;
	}

	public void setTemplateLogo(TemplateLogo templateLogo) {
		this.templateLogo = templateLogo;
	}

	public List<TemplateBanner> getTemplateBanners() {
		return templateBanners;
	}

	public void setTemplateBanners(List<TemplateBanner> templateBanners) {
		this.templateBanners = templateBanners;
	}

	public List<TemplateColumns> getTemplateColumns() {
		return templateColumns;
	}

	public void setTemplateColumns(List<TemplateColumns> templateColumns) {
		this.templateColumns = templateColumns;
	}

	public TemplateFooter getTemplateFooter() {
		return templateFooter;
	}

	public void setTemplateFooter(TemplateFooter templateFooter) {
		this.templateFooter = templateFooter;
	}

}
