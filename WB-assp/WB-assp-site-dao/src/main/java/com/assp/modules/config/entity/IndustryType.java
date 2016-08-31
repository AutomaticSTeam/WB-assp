package com.assp.modules.config.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 类简述
 * <p>
 * 行业分类实体
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月19日 下午5:48:41
 */
@Table(name="wms_industry_type")
public class IndustryType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Id
	private Integer industryTypeId; // '行业类型',
	private String industryTypeName; // '行业类型名称',
	private Integer industryTypePid; // '行业类型父id',
	private Date createTime; // '创建时间',
	private Date updateTime; // '更新时间',
	private Integer operator_id; // '操作人id',
	private String operatorName; // '操作人姓名',
	private String remark; // '备注',
	private int data_status; // '数据状态 0 有效 1 失效',

	public Integer getIndustryTypeId() {
		return industryTypeId;
	}

	public void setIndustryTypeId(Integer industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	public String getIndustryTypeName() {
		return industryTypeName;
	}

	public void setIndustryTypeName(String industryTypeName) {
		this.industryTypeName = industryTypeName;
	}

	public Integer getIndustryTypePid() {
		return industryTypePid;
	}

	public void setIndustryTypePid(Integer industryTypePid) {
		this.industryTypePid = industryTypePid;
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

	public Integer getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(Integer operator_id) {
		this.operator_id = operator_id;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getData_status() {
		return data_status;
	}

	public void setData_status(int data_status) {
		this.data_status = data_status;
	}

}
