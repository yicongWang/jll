package com.zifisense.jll.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zifisense.jll.dao.AccountRoleMapperExt;
import com.zifisense.jll.dto.AccountRoleDTO;
import com.zifisense.jll.model.AccountRole;
import com.zifisense.jll.model.AccountRoleExample;
import com.zifisense.jll.service.AccountRoleService;

/**
 * 账户角色接口实现类
 * Created by DW on 2017/6/28.
 */
@Service
public class AccountRoleServiceImpl implements AccountRoleService {
	
	@Autowired
	private AccountRoleMapperExt  accountRoleMapperExt;
		
	/**
	 *根据账号id获取账号角色
	 * @param accountId
	 * @return
	 */
	@Override
	public AccountRoleDTO queryAccountRoleByAccountId(Long accountId){
		AccountRoleDTO accountRoleDTO = null;
		if(null != accountId){
			List<AccountRoleDTO> list =  accountRoleMapperExt.queryAccountRoleByAccountId(accountId);
			if(CollectionUtils.isNotEmpty(list)){
				accountRoleDTO = list.get(0);
			}
		}
		return accountRoleDTO;
	}

	@Override
	public int addAccountRole(Long accountId, Long roleId) {
		AccountRole accountRole = new AccountRole();
		accountRole.setAccountId(accountId);
		accountRole.setRoleId(roleId);
		return accountRoleMapperExt.insert(accountRole);
	}

	@Override
	public int updateAccountRole(Long accountId, Long roleId) {
		AccountRole accountRole = new AccountRole();
		accountRole.setRoleId(roleId);
		AccountRoleExample example = new AccountRoleExample();
		example.createCriteria().andAccountIdEqualTo(accountId);
		return accountRoleMapperExt.updateByExampleSelective(accountRole, example);
	}
}
