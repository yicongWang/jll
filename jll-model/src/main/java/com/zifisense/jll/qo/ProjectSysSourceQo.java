package com.zifisense.jll.qo;

import com.zifisense.jll.common.BasicQo;

import io.swagger.annotations.ApiModelProperty;

public class ProjectSysSourceQo extends BasicQo{

	@ApiModelProperty(name="系统对接码信息")
	private String sysIdJoinCodes;

	@ApiModelProperty(name="项目ID")
	
	public String getSysIdJoinCodes() {
		return sysIdJoinCodes;
	}

	public void setSysIdJoinCodes(String sysIdJoinCodes) {
		this.sysIdJoinCodes = sysIdJoinCodes;
	}
	
	private Long projectId;


	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
}
