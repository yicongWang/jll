package com.zifisense.jll.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zifisense.jll.common.jsonserializer.YMDHMSDateSerializer;
import com.zifisense.jll.model.Account;

/**
 * 用户扩展信息DTO
 * Created by DW on 2017/6/27.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO extends Account {

    /**
     * 归属项目ID集合
     */
    private List<Long> projectIdList;

    /**
     * 归属项目集合
     */
    private List<ProjectDTO> projectDTOList;

    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 角色信息(在系统已知晓用户在哪个应用的情况下)
     */
    private RoleDTO roleDTO;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 菜单集合
     */
    private List<MenuDTO> menuDTOList;
    /**
     * 菜单id集合
     */
    private List<Long> menuIdList;

    /**
     * 创建人
     */
    private String creater;

    /**
     * 项目id字符串（逗号分隔）
     */
    private String projectIds;
    /**
     * 项目名称字符串（逗号分隔）
     */
    private String projectNames;


 public List<Long> getProjectIdList() {
        return projectIdList;
    }

    public void setProjectIdList(List<Long> projectIdList) {
        this.projectIdList = projectIdList;
    }

    

    public List<ProjectDTO> getProjectDTOList() {
        return projectDTOList;
    }

    public void setProjectDTOList(List<ProjectDTO> projectDTOList) {
        this.projectDTOList = projectDTOList;
    }

    public List<MenuDTO> getMenuDTOList() {
        return menuDTOList;
    }

    public void setMenuDTOList(List<MenuDTO> menuDTOList) {
        this.menuDTOList = menuDTOList;
    }

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    @JsonSerialize(using = YMDHMSDateSerializer.class)
    @Override
    public Date getCreateTime() {
        return super.getCreateTime();
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds;
    }

    public String getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String projectNames) {
        this.projectNames = projectNames;
    }

    
}
