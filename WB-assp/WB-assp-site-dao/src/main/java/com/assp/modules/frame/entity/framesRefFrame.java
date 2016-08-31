package com.assp.modules.frame.entity;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="wms_template_frames_ref_frame")
public class framesRefFrame implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer framesRelFrameId; //框架关联板式id
	private Integer framesRelFramePid; //父级id  0 顶级框架
	private Integer framesId; //框架id
	private Integer frameId; //板式id
    private Integer sortNum;//排序
    private Integer relStatus; // 关联关系是否有效 0 有效 1 失效
	public Integer getFramesRelFrameId() {
		return framesRelFrameId;
	}
	public void setFramesRelFrameId(Integer framesRelFrameId) {
		this.framesRelFrameId = framesRelFrameId;
	}
	public Integer getFramesRelFramePid() {
		return framesRelFramePid;
	}
	public void setFramesRelFramePid(Integer framesRelFramePid) {
		this.framesRelFramePid = framesRelFramePid;
	}
	public Integer getFramesId() {
		return framesId;
	}
	public void setFramesId(Integer framesId) {
		this.framesId = framesId;
	}
	public Integer getFrameId() {
		return frameId;
	}
	public void setFrameId(Integer frameId) {
		this.frameId = frameId;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public Integer getRelStatus() {
		return relStatus;
	}
	public void setRelStatus(Integer relStatus) {
		this.relStatus = relStatus;
	}
    
}
