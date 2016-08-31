package com.assp.modules.module.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * 
  * 类简述
  * <p>
  * 模块模板类
  * </p>
  * @Company 动力威视
  * @Copyright
  * @author (panlinlin@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年7月14日 下午5:20:23
 */
@Table(name = "wms_template_module_tmpl")
public class TemplateModuleTmpl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    private Integer moduleTmlId; //模块模板id

    private String moduleTmlName;//模块模板名称

    private String moduleTmlKey;//模块模板关键字

    private String moduleTmlAttachmentImg;//模块模板预览图

    private String moduleTmlContent;//模块模板内容
    
    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    private Integer operatorId;//操作人id

    private String operatorName;//操作人姓名

    private Integer dataStatus;//数据状态 0 正常 1 删除

    public Integer getModuleTmlId() {
        return moduleTmlId;
    }

    public void setModuleTmlId(Integer moduleTmlId) {
        this.moduleTmlId = moduleTmlId;
    }

    public String getModuleTmlName() {
        return moduleTmlName;
    }

    public void setModuleTmlName(String moduleTmlName) {
        this.moduleTmlName = moduleTmlName == null ? null : moduleTmlName.trim();
    }

    public String getModuleTmlKey() {
        return moduleTmlKey;
    }

    public void setModuleTmlKey(String moduleTmlKey) {
        this.moduleTmlKey = moduleTmlKey == null ? null : moduleTmlKey.trim();
    }

    public String getModuleTmlAttachmentImg() {
        return moduleTmlAttachmentImg;
    }

    public void setModuleTmlAttachmentImg(String moduleTmlAttachmentImg) {
        this.moduleTmlAttachmentImg = StringUtils.trimToNull(moduleTmlAttachmentImg);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getModuleTmlContent() {
        return moduleTmlContent;
    }

    public void setModuleTmlContent(String moduleTmlContent) {
        this.moduleTmlContent = moduleTmlContent == null ? null : moduleTmlContent.trim();
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}