package com.zifisense.jll.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zifisense.jll.common.BasicVo;

/**
 * 用户等级
 * @author wyc
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleVo  extends BasicVo{
	private Long id;
    private String roleName;
    private String identity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
    
}
