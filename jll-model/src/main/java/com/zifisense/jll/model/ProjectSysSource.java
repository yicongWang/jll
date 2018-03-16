package com.zifisense.jll.model;

import java.io.Serializable;

public class ProjectSysSource implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_sys_source.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_sys_source.project_id
     *
     * @mbggenerated
     */
    private Long projectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_sys_source.sys_app_source_id
     *
     * @mbggenerated
     */
    private Long sysAppSourceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_sys_source.sys_project_code
     *
     * @mbggenerated
     */
    private String sysProjectCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_sys_source.id
     *
     * @return the value of project_sys_source.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_sys_source.id
     *
     * @param id the value for project_sys_source.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_sys_source.project_id
     *
     * @return the value of project_sys_source.project_id
     *
     * @mbggenerated
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_sys_source.project_id
     *
     * @param projectId the value for project_sys_source.project_id
     *
     * @mbggenerated
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_sys_source.sys_app_source_id
     *
     * @return the value of project_sys_source.sys_app_source_id
     *
     * @mbggenerated
     */
    public Long getSysAppSourceId() {
        return sysAppSourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_sys_source.sys_app_source_id
     *
     * @param sysAppSourceId the value for project_sys_source.sys_app_source_id
     *
     * @mbggenerated
     */
    public void setSysAppSourceId(Long sysAppSourceId) {
        this.sysAppSourceId = sysAppSourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_sys_source.sys_project_code
     *
     * @return the value of project_sys_source.sys_project_code
     *
     * @mbggenerated
     */
    public String getSysProjectCode() {
        return sysProjectCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_sys_source.sys_project_code
     *
     * @param sysProjectCode the value for project_sys_source.sys_project_code
     *
     * @mbggenerated
     */
    public void setSysProjectCode(String sysProjectCode) {
        this.sysProjectCode = sysProjectCode == null ? null : sysProjectCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectId=").append(projectId);
        sb.append(", sysAppSourceId=").append(sysAppSourceId);
        sb.append(", sysProjectCode=").append(sysProjectCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}