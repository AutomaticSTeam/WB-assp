package com.assp.modules.config.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类简述
 * <p>
 * 栏目类型
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月26日 下午2:01:37
 */
@Table(name = "wms_columns_type")
public class ColumnsType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer columnsTypeId; // '导航类型',
	private String columnsTypeName; // '导航栏目类型名称',
	private int dataStatus; // '数据状态 0 正常 1 删除',
	private String remark; // '备注信息',

	public Integer getColumnsTypeId() {
		return columnsTypeId;
	}

	public void setColumnsTypeId(Integer columnsTypeId) {
		this.columnsTypeId = columnsTypeId;
	}

	public String getColumnsTypeName() {
		return columnsTypeName;
	}

	public void setColumnsTypeName(String columnsTypeName) {
		this.columnsTypeName = columnsTypeName;
	}

	public int getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(int dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
