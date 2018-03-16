package com.zifisense.jll.vo;

/**
 * 项目对接系统码基础信息
 * @author wyc
 *
 */
public class ProjectSysSourceInfoVo{
	 	private String projectName;
	    private Long projectId;
	    private Long appSourceId;
	    private String appSourceCode;
	    private String appSourceName;
	    private String sysProjectCode;
	    private String  deviceAddress;
		public String getDeviceAddress() {
			return deviceAddress;
		}
		public void setDeviceAddress(String deviceAddress) {
			this.deviceAddress = deviceAddress;
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
		public Long getAppSourceId() {
			return appSourceId;
		}
		public void setAppSourceId(Long appSourceId) {
			this.appSourceId = appSourceId;
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
		public String getSysProjectCode() {
			return sysProjectCode;
		}
		public void setSysProjectCode(String sysProjectCode) {
			this.sysProjectCode = sysProjectCode;
		}
}
