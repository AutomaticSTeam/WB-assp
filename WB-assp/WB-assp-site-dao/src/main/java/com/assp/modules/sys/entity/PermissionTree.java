package com.assp.modules.sys.entity;

import java.io.Serializable;
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
public class PermissionTree extends Permission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer _parentId;
	
	private List<PermissionTree> children;
	
	private String state; //展开状态

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer get_parentId() {
		return _parentId;
	}

	public void set_parentId(Integer _parentId) {
		this._parentId = _parentId;
	}

	public List<PermissionTree> getChildren() {
		return children;
	}

	public void setChildren(List<PermissionTree> children) {
		this.children = children;
	}
	
	
	
	
}
