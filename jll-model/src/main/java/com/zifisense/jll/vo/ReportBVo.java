package com.zifisense.jll.vo;

import java.math.BigDecimal;

import com.zifisense.jll.common.BasicVo;

/**
 *  多项目B
 * @author wyc
 *
 */
public class ReportBVo extends BasicVo {
	private BigDecimal countNum;
	private Long projectId;
	private String projectName;
	public BigDecimal getCountNum() {
		return countNum;
	}
	public void setCountNum(BigDecimal countNum) {
		this.countNum = countNum;
	}
	
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
