package com.assp.modules.frame.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="wms_frame")
public class Frame implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer frameId; //框架版式id
    private String frameName; //框架版式名称
    private String frameAttachmentImg; //预览图
    private String frameDesc; //框架板式描述
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private Integer operatorId;//操作人id
    private String operatorName;//操作人姓名
    private Integer dataStatus; // '数据状态 0 正常 1 删除',
	public Integer getFrameId() {
		return frameId;
	}
	public void setFrameId(Integer frameId) {
		this.frameId = frameId;
	}
	public String getFrameName() {
		return frameName;
	}
	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}
	public String getFrameAttachmentImg() {
		return frameAttachmentImg;
	}
	public void setFrameAttachmentImg(String frameAttachmentImg) {
		this.frameAttachmentImg = frameAttachmentImg;
	}
	public String getFrameDesc() {
		return frameDesc;
	}
	public void setFrameDesc(String frameDesc) {
		this.frameDesc = frameDesc;
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
