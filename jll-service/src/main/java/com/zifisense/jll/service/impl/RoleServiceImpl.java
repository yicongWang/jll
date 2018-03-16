package com.zifisense.jll.service.impl;

import com.zifisense.jll.common.constants.InfoState;
import com.zifisense.jll.common.exception.DataAlreadyExistsException;
import com.zifisense.jll.common.exception.DataNotExistsException;
import com.zifisense.jll.dao.RoleMapperExt;
import com.zifisense.jll.dto.RoleDTO;
import com.zifisense.jll.model.Role;
import com.zifisense.jll.model.RoleExample;
import com.zifisense.jll.service.RoleService;
import com.zifisense.jll.service.common.RoleEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色接口实现类
 * Created by DW on 2017/6/28.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapperExt roleMapperExt;


    @Override
    public int addRole(Role role) {
        this.checkRoleNameIsExists(role.getRoleName());
        role.setSortNumber(roleMapperExt.getMaxSort()+1);
        return roleMapperExt.insertSelective(role);
    }

    @Override
    public PageInfo<Role> findRolePage(Role role, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(this.findRole(role));

    }

    @Override
    public List<Role> findRole(Role role) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        if(role!=null){
            if(!StringUtils.isEmpty(role.getIdentity())){
                criteria.andIdentityEqualTo(role.getIdentity());
            }
            if(!StringUtils.isEmpty(role.getRoleName())){
                criteria.andRoleNameEqualTo(role.getRoleName());
            }
        }
        
        criteria.andStateEqualTo(InfoState.NORMAL);
        roleExample.setOrderByClause("sort_number desc ");
        return roleMapperExt.selectByExample(roleExample);
    }

    @Override
    public void updateRole(Role role) {
        this.getRole(role.getId());
        roleMapperExt.updateByPrimaryKeySelective(role);
    }

    @Override
    public Role getRole(Long id) {
        Role sysRole =  roleMapperExt.selectByPrimaryKey(id);
        if(sysRole!=null && InfoState.NORMAL == sysRole.getState()){
            return sysRole;
        }else {
            throw new DataNotExistsException("根据角色id = " + id + "查询对象为空");
        }
    }

    @Override
    public List<RoleDTO> findRoleDTOList() {
        return roleMapperExt.findRoleDTOList();
    }

    /**
     * 检查角色名称是否存在
     * @param roleName  角色名称
     */
    private void checkRoleNameIsExists(String roleName){
        Role tempSysRole = new Role();
        tempSysRole.setRoleName(roleName);
        List<Role> sysRoles = this.findRole(tempSysRole);
        if(sysRoles!=null && sysRoles.size()>0){
            throw new DataAlreadyExistsException("温馨提示：角色名称已存在");
        }
    }
    
    @Override
    public List<Role> findRoleListByInCode(List<String> values) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        if(CollectionUtils.isNotEmpty(values)){
        	 criteria.andIdentityIn(values);
        }
        criteria.andStateEqualTo(InfoState.NORMAL);
        roleExample.setOrderByClause("sort_number desc");
        return roleMapperExt.selectByExample(roleExample);
    }
    
    @Override
	public String getRoleCodeByName(String roleName){
    	roleName = StringUtils.trim(roleName);
		if(StringUtils.equals(roleName, RoleEnum.ADMIN.getName())){
			return RoleEnum.ADMIN.getCode();
		}else if(StringUtils.equals(roleName, RoleEnum.LEVEL_ONE.getName())){
			return RoleEnum.LEVEL_ONE.getCode();
		}else if(StringUtils.equals(roleName, RoleEnum.LEVEL_TWO.getName())){
			return RoleEnum.LEVEL_TWO.getCode();
		}else if(StringUtils.equals(roleName, RoleEnum.LEVEL_THREE.getName())){
			return RoleEnum.LEVEL_THREE.getCode();
		}else{
			return RoleEnum.LEVEL_THREE.getCode();
		}
	}
}
