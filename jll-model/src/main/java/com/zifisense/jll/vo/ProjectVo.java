package com.zifisense.jll.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zifisense.jll.common.BasicVo;
import com.zifisense.jll.common.jsonserializer.YMDDateSerializer;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * 项目
 * @author wyc
 *
 */
@ExcelTarget("ProjectVo")
public class ProjectVo  extends BasicVo {
	private Long id;
	@Excel(name = "中文项目名称", orderNum = "1", mergeVertical = false, isImportField = "projectNameCn",width =25)
	private String projectNameCn;
	@Excel(name = "英文项目名称", orderNum = "2", mergeVertical = false, isImportField = "projectNameEn",width =25)
	private String projectNameEn;
	@Excel(name = "地址", orderNum = "3", mergeVertical = false, isImportField = "address",width =25)
	private String address;
	@Excel(name = "建筑面积（米）", orderNum = "6", mergeVertical = false, isImportField = "floorArea",width =25)
	private BigDecimal floorArea;
	
    private Integer nameModel;
	@Excel(name = "建筑高度（米）", orderNum = "8", mergeVertical = false, isImportField = "floorHight",width =25)
    private BigDecimal floorHight;
	@Excel(name = "建成年份（年）", orderNum = "9", mergeVertical = false, isImportField = "bulidYear",width =25)
    private String bulidYear;
	@Excel(name = "城市", orderNum = "4", mergeVertical = false, isImportField = "cityName",width =25)
    private String cityName;
	@Excel(name = "接管日期", orderNum = "5", mergeVertical = false,format="yyyy-MM-dd", isImportField = "catchTime",width =25)
	private Date catchTime;
	@Excel(name = "业态", orderNum = "7", mergeVertical = false, isImportField = "businessType",width =25)
    private String businessType;
	@Excel(name = "邮编", orderNum = "11", mergeVertical = false, isImportField = "zipCode",width =25)
    private String zipCode;
	private String accountIds;
	@Excel(name = "项目负责人", orderNum = "10", mergeVertical = false, isImportField = "accountNames",width =25)
    private String accountNames;
    private String accountNameIds;
    
    private String businessGroupIds;
	@Excel(name = "所属业务组", orderNum = "12", mergeVertical = false, isImportField = "businessGroupNames",width =25)
    private String businessGroupNames;
    private String businessGroupNameIds;
    
    private String sysIds;
    private String sysNames;
    private String sysNameIds;
    
    public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
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
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
    @JsonSerialize(using = YMDDateSerializer.class)
	public Date getCatchTime() {
		return catchTime;
	}
	public void setCatchTime(Date catchTime) {
		this.catchTime = catchTime;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
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
	public String getAccountNames() {
		return accountNames;
	}
	public void setAccountNames(String accountNames) {
		this.accountNames = accountNames;
	}
	public String getAccountNameIds() {
		return accountNameIds;
	}
	public void setAccountNameIds(String accountNameIds) {
		this.accountNameIds = accountNameIds;
	}
	public String getBusinessGroupIds() {
		return businessGroupIds;
	}
	public void setBusinessGroupIds(String businessGroupIds) {
		this.businessGroupIds = businessGroupIds;
	}
	public String getBusinessGroupNames() {
		return businessGroupNames;
	}
	public void setBusinessGroupNames(String businessGroupNames) {
		this.businessGroupNames = businessGroupNames;
	}
	public String getBusinessGroupNameIds() {
		return businessGroupNameIds;
	}
	public void setBusinessGroupNameIds(String businessGroupNameIds) {
		this.businessGroupNameIds = businessGroupNameIds;
	}
	public String getSysIds() {
		return sysIds;
	}
	public void setSysIds(String sysIds) {
		this.sysIds = sysIds;
	}
	public String getSysNames() {
		return sysNames;
	}
	public void setSysNames(String sysNames) {
		this.sysNames = sysNames;
	}
	public String getSysNameIds() {
		return sysNameIds;
	}
	public void setSysNameIds(String sysNameIds) {
		this.sysNameIds = sysNameIds;
	}
}
