package com.zifisense.jll.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zifisense.jll.dto.MenuRoleDTO;
import com.zifisense.jll.vo.AlarmVo;

/**
 * 角色菜单Mapper
 * @author wyc
 *
 */
@Mapper
public interface MenuRoleMapperExt extends MenuMapper {
    /**
     * 查询角色菜单集合
     * @return
     */
    List<MenuRoleDTO> queryMuneByRoleId(Long roleId);
    
    List<AlarmVo> getAlarmListByQuery(Map<String,Object> map);
}
