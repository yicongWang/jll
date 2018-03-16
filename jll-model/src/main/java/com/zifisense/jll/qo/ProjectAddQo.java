package com.zifisense.jll.qo;

import java.math.BigDecimal;

public class ProjectAddQo {
	private Long id;
	private String projectNameCn;
	private String projectNameEn;
	private String address;
	private BigDecimal floorArea;
    private Integer nameModel;
	private String accountIds;
    private String businessGroupIds;
    private String sysIdJoinCodes;
    private BigDecimal floorHight;
    private String bulidYear;
    private String catchTime;
    private String businessType;
    private String  zipCode;
    private String cityName	;
    
	public BigDecimal getFloorHight() {
		return floorHight;
	}
	public void setFloorHight(BigDecimal floorHight) {
		this.floorHight = floorHight;
	}
	public String getBulidYear() {
		return bulidYear;
	}
	public void setBulidYear(String bulidYear) {
		this.bulidYear = bulidYear;
	}
	public String getCatchTime() {
		return catchTime;
	}
	public void setCatchTime(String catchTime) {
		this.catchTime = catchTime;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProjectNameCn() {
		return projectNameCn;
	}
	public void setProjectNameCn(String projectNameCn) {
		this.projectNameCn = projectNameCn;
	}
	public String getProjectNameEn() {
		return projectNameEn;
	}
	public void setProjectNameEn(String projectNameEn) {
		this.projectNameEn = projectNameEn;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BigDecimal getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(BigDecimal floorArea) {
		this.floorArea = floorArea;
	}
	public Integer getNameModel() {
		return nameModel;
	}
	public void setNameModel(Integer nameModel) {
		this.nameModel = nameModel;
	}
	public String getAccountIds() {
		return accountIds;
	}
	public void setAccountIds(String accountIds) {
		this.accountIds = accountIds;
	}
	public String getBusinessGroupIds() {
		return businessGroupIds;
	}
	public void setBusinessGroupIds(String businessGroupIds) {
		this.businessGroupIds = businessGroupIds;
	}
	public String getSysIdJoinCodes() {
		return sysIdJoinCodes;
	}
	public void setSysIdJoinCodes(String sysIdJoinCodes) {
		this.sysIdJoinCodes = sysIdJoinCodes;
	}
	
    
}
