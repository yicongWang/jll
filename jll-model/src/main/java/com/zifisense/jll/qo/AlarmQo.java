package com.zifisense.jll.qo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zifisense.jll.common.BasicQo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlarmQo  extends BasicQo{
	@ApiModelProperty(name = "系统类型代号,多个中间用“,”隔开未传则为全部")
   public String appSourceCodes;
	@ApiModelProperty(name = "项目I,未传则为全部")
   public Long projectId;
	
	@ApiModelProperty(name = "开始年月")
	public String startYm;
	
	@ApiModelProperty(name = "结束年月")
	public String endYm;
	@ApiModelProperty(name = "告警状态" ,hidden =true,example = "0:告警中 1:解除警报(归档)")
	public Integer alarmState;

	@ApiModelProperty(name = "系统类型代号数组",hidden =true)
	public String[] appSourceCodeArray;
	
	@ApiModelProperty(name = "告警数据类型" ,hidden =true,example = "0:报警信息 1：常规信息;不传则为全部")
	public Integer dataType;

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

/*	public Integer getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}*/


	
	public String getAppSourceCodes() {
		return appSourceCodes;
	}

	public void setAppSourceCodes(String appSourceCodes) {
		this.appSourceCodes = appSourceCodes;
	}


	public String[] getAppSourceCodeArray() {
		return appSourceCodeArray;
	}

	public void setAppSourceCodeArray(String[] appSourceCodeArray) {
		this.appSourceCodeArray = appSourceCodeArray;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Integer getAlarmState() {
		return alarmState;
	}

	public void setAlarmState(Integer alarmState) {
		this.alarmState = alarmState;
	}

	public String getStartYm() {
		return startYm;
	}

	public void setStartYm(String startYm) {
		this.startYm = startYm;
	}

	public String getEndYm() {
		return endYm;
	}

	public void setEndYm(String endYm) {
		this.endYm = endYm;
	}
	

	
   
}
