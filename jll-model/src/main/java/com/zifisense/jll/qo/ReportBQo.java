package com.zifisense.jll.qo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zifisense.jll.common.BasicQo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportBQo  extends BasicQo{
	@ApiModelProperty(name = "系统类型代号,多个中间用“,”隔开未传则为全部")
   public String appSourceCodes;
	@ApiModelProperty(name = "项目I,多个中间用“,”隔开未传则为全部")
   public String projectIds;
	
	@ApiModelProperty(name = "开始年月")
	public String startYm;
	
	@ApiModelProperty(name = "结束年月")
	public String endYm;
	

	@ApiModelProperty(name = "系统类型代号数组",hidden =true)
	public String[] appSourceCodeArray;
	
	@ApiModelProperty(name = "项目ID数组",hidden =true)
	public String[] projectIdArray;


	
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

	

	public String getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}


	public String[] getProjectIdArray() {
		return projectIdArray;
	}

	public void setProjectIdArray(String[] projectIdArray) {
		this.projectIdArray = projectIdArray;
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
