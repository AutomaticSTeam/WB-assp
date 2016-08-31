package com.assp.modules.frame.entity;   

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类简述
 * <p>
 *      框架版式
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月10日 下午5:02:05 
 */
@Table(name="wms_template_frames")
public class Frames implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer framesId; //框架版式id
    private String framesName; //框架版式名称
    private Integer framesType; //框架类型 0 预置框架 1 自定义框架
    private String framesAttachmentImg; //预览图
    private String framesDesc; //框架描述
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private Integer operatorId;//操作人id
    private String operatorName;//操作人姓名
    private Integer dataStatus; // '数据状态 0 正常 1 删除',
	public Integer getFramesId() {
		return framesId;
	}
	public void setFramesId(Integer framesId) {
		this.framesId = framesId;
	}
	public String getFramesName() {
		return framesName;
	}
	public void setFramesName(String framesName) {
		this.framesName = framesName;
	}
	public Integer getFramesType() {
		return framesType;
	}
	public void setFramesType(Integer framesType) {
		this.framesType = framesType;
	}
	public String getFramesAttachmentImg() {
		return framesAttachmentImg;
	}
	public void setFramesAttachmentImg(String framesAttachmentImg) {
		this.framesAttachmentImg = framesAttachmentImg;
	}
	public String getFramesDesc() {
		return framesDesc;
	}
	public void setFramesDesc(String framesDesc) {
		this.framesDesc = framesDesc;
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
		this.operatorName = operatorName;
	}
	public Integer getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}
    
    
}
  