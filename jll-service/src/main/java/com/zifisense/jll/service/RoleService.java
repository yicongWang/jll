package com.zifisense.jll.service;

import com.zifisense.jll.dto.RoleDTO;
import com.zifisense.jll.model.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 角色Service 接口
 * Created by DW on 2017/6/28.
 */
public interface RoleService {


    /**
     * 新增角色
     *
     * @param role   角色
     * @return  影响的行数
     */
    int addRole(Role role);

    /**
     * 分页查询角色集合
     * @param role      查询条件
     * @param pageNum       当前页
     * @param pageSize      每页显示条数
     * @return  请求响应
     */
    PageInfo<Role> findRolePage(Role role, Integer pageNum, Integer pageSize);

    /**
     * 根据条件返回角色集合
     * @param role   查询条件
     * @return  角色集合
     */
    List<Role> findRole(Role role);

    /**
     * 修改角色
     * @param role   角色
     */
    void updateRole(Role role);

    /**
     * 根据id 获取角色
     * @param id   角色id
     * @return  请求响应
     */
    Role getRole(Long id);

    /**
     * 获取系统所有的角色集合
     * @return
     */
    List<RoleDTO> findRoleDTOList();

    /**
     * 通过等级代码获取用户等级列表
     * @param values
     * @return
     */
	List<Role> findRoleListByInCode(List<String> values);
	
	/**
	 * 通过用户角色名称获取代号
	 * @param roleName
	 * @return
	 */
	String getRoleCodeByName(String roleName);

}
