package com.zifisense.jll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zifisense.jll.dao.MenuMapperExt;
import com.zifisense.jll.dao.MenuRoleMapperExt;
import com.zifisense.jll.dto.MenuRoleDTO;
import com.zifisense.jll.service.MenuService;

/**
 * 菜单ServiceImpl
 * @author wyc
 *
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapperExt menuMapperExt;

    @Autowired
    private MenuRoleMapperExt menuRoleMapperExt;

	@Override
	public List<MenuRoleDTO> queryMuneByRoleId(Long roleId) {
		return menuRoleMapperExt.queryMuneByRoleId(roleId);
	}

}
