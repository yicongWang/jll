package com.zifisense.jll.service;

import java.util.List;

import com.zifisense.jll.dto.MenuRoleDTO;

/**
 * 菜单service
 * @author wyc
 *
 */
public interface MenuService {


    /**
     * 查询角色菜单集合
     * @return
     */
    List<MenuRoleDTO> queryMuneByRoleId(Long roleId);
}
