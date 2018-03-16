package com.zifisense.jll.qo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zifisense.jll.common.BasicQo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportCListQo  extends BasicQo{
	@ApiModelProperty(name = "系统类型代号,多个中间用“,”隔开未传则为全部")
   public String appSourceCodes;
	@ApiModelProperty(name = "业务组Id")
	public String groupId;
	@ApiModelProperty(name = "开始年月")
	public String startYm;
	
	@ApiModelProperty(name = "结束年月")
	public String endYm;
	
	@ApiModelProperty(name = "查询年月")
	public String searchYm;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getSearchYm() {
		return searchYm;
	}

	public void setSearchYm(String searchYm) {
		this.searchYm = searchYm;
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

	@ApiModelProperty(name = "系统类型代号数组",hidden =true)
	public String[] appSourceCodeArray;
	
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

	

}
