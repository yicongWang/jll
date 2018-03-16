package com.zifisense.jll.service;

import com.zifisense.jll.dto.AccountRoleDTO;

/**
 *  账户角色接口
 * @author wyc
 *
 */
public interface AccountRoleService {
	/**
	 * 根据账号id获取账号角色
	 * @param accountId
	 * @return
	 */
	AccountRoleDTO queryAccountRoleByAccountId(Long accountId);
	
	/**
	 * 更新账号角色关联
	 * @param accountId
	 * @param roleId
	 * @return
	 */
	int updateAccountRole(Long accountId,Long roleId);
	
	/**
	 * 新建账号角色关联
	 * @param accountId
	 * @param roleId
	 * @return
	 */
	int addAccountRole(Long accountId,Long roleId);
}
