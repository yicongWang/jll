package com.zifisense.jll.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zifisense.jll.model.AccountRole;

/**
 * 账号角色扩展DTO
 * @author wyc
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountRoleDTO extends AccountRole{
   private String identity;
   private String roleName;
public String getIdentity() {
	return identity;
}
public void setIdentity(String identity) {
	this.identity = identity;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
}
