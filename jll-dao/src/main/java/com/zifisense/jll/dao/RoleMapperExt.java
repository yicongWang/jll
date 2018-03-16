package com.zifisense.jll.dao;

import com.zifisense.jll.dto.RoleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色Mapper
 * Created by DW on 2017/6/28.
 */
@Mapper
public interface RoleMapperExt extends RoleMapper {

    /**
     * 获取最大排序号
     * @return  当前最大的排序号
     */
    int getMaxSort();


    /**
     * 获取系统所有的角色集合
     * @return
     */
    List<RoleDTO> findRoleDTOList();


    /**
     * 获取最大的角色对象信息 （非超管用户）
     * @param accountId 账户id
     * @return
     */
    RoleDTO getMaxRoleDTO(Long accountId);

    /**
     * 根据用户id 查询角色集合(非超管用户)
     * @param accountId
     * @return
     */
    List<RoleDTO> findRoleList(Long accountId);
}
