package com.zifisense.jll.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zifisense.jll.common.BasicVo;
import com.zifisense.jll.common.jsonserializer.YMDHMSDateSerializer;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 告警列表
 * @author wyc
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlarmVo  extends BasicVo{
	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	private Long id;
	@Excel(name = "所属项目",isImportField = "projectName",width =25)
    private String projectName;
  
    private Long projectId;
    private String appSourceCode;
	@Excel(name = "系统类型",isImportField = "appSourceName",width =25)
    private String appSourceName;
	
	@Excel(name = "设备类型",isImportField = "deviceType",width =25)
    private String deviceType;
	
	@Excel(name = "设备地址",isImportField = "deviceAddress",width =25)
    private String deviceAddress;
	@Excel(name = "报警内容",isImportField = "alarmContent",width =25)
    private String alarmContent;
	
    @JsonSerialize(using = YMDHMSDateSerializer.class)
    @Excel(name = "报警日期",isImportField = "alarmTime",format="yyyy-MM-dd HH:mm:ss", width =25)
    private Date alarmTime;

    @JsonSerialize(using = YMDHMSDateSerializer.class)
    @Excel(name = "归档日期",format="yyyy-MM-dd HH:mm:ss", width =25)
    private Date finishTime;
    
    @Excel(name = "数据类型",width =25)
    private String dataType;
    
    private String alarmType;

    private Long alarmState;
    
    private String  groupName;
    
    private String groupId;
    
    private BigDecimal floorArea;
    
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public BigDecimal getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(BigDecimal floorArea) {
		this.floorArea = floorArea;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getAppSourceCode() {
		return appSourceCode;
	}

	public void setAppSourceCode(String appSourceCode) {
		this.appSourceCode = appSourceCode;
	}

	public String getAppSourceName() {
		return appSourceName;
	}

	public void setAppSourceName(String appSourceName) {
		this.appSourceName = appSourceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceAddress() {
		return deviceAddress;
	}

	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}

	public String getAlarmContent() {
		return alarmContent;
	}

	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
	}



	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public Long getAlarmState() {
		return alarmState;
	}

	public void setAlarmState(Long alarmState) {
		this.alarmState = alarmState;
	}

}
