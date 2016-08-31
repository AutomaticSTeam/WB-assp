package com.assp.modules.order.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="wms_order")
public class Order {
	@Id
	private Integer orderId; //主键
	
	private String orderNo;//订单编号  W+时间戳（13位）+模板CODE(5位)+月份日期  
	
	private String userName; //客户姓名
	
	private String userPhone; //用户手机号
	
	private String userAddress; //用户详细地址
	
	private String userDomainName; //用户域名
	
	private Integer orderStatus; //订单状态 
	
	private Double orderPrice; //产品价格
	
	private String userAccount; //用户账号
	
	private Integer rentedTime; //租用时长
	
	private String rentedUnit; //租用单位 ：年，月，季，半年
	
	private String province; //省
	
	private String city; //市
	
	private String area; //区
	
	private Integer isDelete; //是否删除  0：否 1：是
	
	private Date createTime; //创建时间

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserDomainName() {
		return userDomainName;
	}

	public void setUserDomainName(String userDomainName) {
		this.userDomainName = userDomainName;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public Integer getRentedTime() {
		return rentedTime;
	}

	public void setRentedTime(Integer rentedTime) {
		this.rentedTime = rentedTime;
	}

	public String getRentedUnit() {
		return rentedUnit;
	}

	public void setRentedUnit(String rentedUnit) {
		this.rentedUnit = rentedUnit;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
