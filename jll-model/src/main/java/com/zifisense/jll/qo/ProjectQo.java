package com.zifisense.jll.qo;

import com.zifisense.jll.common.BasicQo;

import io.swagger.annotations.ApiModelProperty;

public class ProjectQo extends BasicQo{
	@ApiModelProperty(name="查询关键字")
	private String keys;

	@ApiModelProperty(name="项目ID")
	private Long projectId;

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
}
