package com.zifisense.jll.dto;

import com.zifisense.jll.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 角色扩展DTO
 * Created by DW on 2017/6/29.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO extends Role {
}
