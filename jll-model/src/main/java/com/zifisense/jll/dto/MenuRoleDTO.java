package com.zifisense.jll.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zifisense.jll.model.Menu;

/**
 * 角色菜单DTO
 * Created by DW on 2017/6/29.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuRoleDTO extends Menu {
	private Long roleId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
