package com.zifisense.jll.qo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zifisense.jll.common.BasicQo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportAListQo  extends BasicQo{
	@ApiModelProperty(name = "系统类型代号,多个中间用“,”隔开未传则为全部")
   public String appSourceCodes;
	@ApiModelProperty(name = "项目I,未传则为全部")
   public Long projectId;
	
	@ApiModelProperty(name = "查询年月")
	public String searchYm;

	@ApiModelProperty(name = "系统类型代号数组",hidden =true)
	public String[] appSourceCodeArray;


	public String getAppSourceCodes() {
		return appSourceCodes;
	}

	public void setAppSourceCodes(String appSourceCodes) {
		this.appSourceCodes = appSourceCodes;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getSearchYm() {
		return searchYm;
	}

	public void setSearchYm(String searchYm) {
		this.searchYm = searchYm;
	}

	public String[] getAppSourceCodeArray() {
		return appSourceCodeArray;
	}

	public void setAppSourceCodeArray(String[] appSourceCodeArray) {
		this.appSourceCodeArray = appSourceCodeArray;
	}
	

   
}
