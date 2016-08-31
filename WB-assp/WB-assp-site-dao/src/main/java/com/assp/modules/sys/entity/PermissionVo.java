package com.assp.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
 * @CreateDate 2016年4月20日 上午10:59:47
 */
public class PermissionVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer permissionId; // '权限id',
	private String permissionCode; // '权限编码',
	private String permissionName; // '权限名称',
	private Integer permissionPid; // '权限父级id',
	private String permissionUrl; // '该资源允许访问的URL(如果是菜单级别就写jsp路径，如果是按钮级就写)',
	private String permissionDesc; // '权限描述',
	private Date createTime; // '创建时间',
	private Date updateTime; // '更新时间',
	private Integer operatorId; // '操作人id',
	private String operatorName; // '操作人姓名',
	private int permissionStatus; // '资源状态;0:可用；1：不可用',
	private int permissionDeleteFlag; // '是否删除;0:未删除；1：已删除',
	private String remark; // '备注',
	
	private List<Integer> roleIds;
	
	

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Integer getPermissionPid() {
		return permissionPid;
	}

	public void setPermissionPid(Integer permissionPid) {
		this.permissionPid = permissionPid;
	}

	public String getPermissionUrl() {
		return permissionUrl;
	}

	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}

	public String getPermissionDesc() {
		return permissionDesc;
	}

	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
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

	public int getPermissionStatus() {
		return permissionStatus;
	}

	public void setPermissionStatus(int permissionStatus) {
		this.permissionStatus = permissionStatus;
	}

	public int getPermissionDeleteFlag() {
		return permissionDeleteFlag;
	}

	public void setPermissionDeleteFlag(int permissionDeleteFlag) {
		this.permissionDeleteFlag = permissionDeleteFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
