package com.zifisense.jll.vo;

/**
 * 项目对接系统码
 * @author wyc
 *
 */
public class ProjectSysSourceVo extends ShortVo{
	//系统对接吗
	private String joinCode;
	//项目系统对接Id
	private Long sysProjectId;
	public Long getSysProjectId() {
		return sysProjectId;
	}

	public void setSysProjectId(Long sysProjectId) {
		this.sysProjectId = sysProjectId;
	}

	public String getJoinCode() {
		return joinCode;
	}

	public void setJoinCode(String joinCode) {
		this.joinCode = joinCode;
	}
}
