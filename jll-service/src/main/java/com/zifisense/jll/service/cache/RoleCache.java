package com.zifisense.jll.service.cache;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zifisense.jll.model.Role;
import com.zifisense.jll.service.RoleService;
import com.zifisense.jll.service.common.RedisKeyConstant;
import com.zifisense.jll.util.RedisUtil;

@Service
public class RoleCache {
	 @Autowired
	 private RoleService roleService;
	 @Autowired
	 private RedisUtil redisUtil;
	 
	 /**
	  * 将所有角色加载如redis中
	  */
	public void initAllRoleCache(){
		//获取可用角色
		List<Role> roleList = roleService.findRole(null);
		//将所有角色加载如redis中
		if(CollectionUtils.isNotEmpty(roleList)){
			roleList.forEach(role -> {
				redisUtil.set(redisUtil.generatorKey(RedisKeyConstant.ROLE_KEY, role.getIdentity()), role);
			});
		}
	}
	
	/***
	 * 根据角色代号获取角色信息
	 * @param identity
	 * @return
	 */
	public Role getRoleByCode(String identity){
		Role role = (Role)redisUtil.get(redisUtil.generatorKey(RedisKeyConstant.ROLE_KEY,identity));
		if( null == role){
			role = new Role();
			role.setIdentity(identity);
			List<Role>  roleList = roleService.findRole(role);
			if(CollectionUtils.isNotEmpty(roleList)){
				role = roleList.get(0);
				redisUtil.set(redisUtil.generatorKey(RedisKeyConstant.ROLE_KEY, role.getIdentity()), role);
			}
		}
		return role;
	}
}
