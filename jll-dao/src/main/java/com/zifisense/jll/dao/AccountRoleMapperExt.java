package com.zifisense.jll.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zifisense.jll.dto.AccountRoleDTO;

/**
 * 账号角色
 * @author wyc
 *
 */
@Mapper
public interface AccountRoleMapperExt  extends AccountRoleMapper{
	/**
	 *根据账号id获取账号角色
	 * @param accountId
	 * @return
	 */
	List<AccountRoleDTO> queryAccountRoleByAccountId(Long accountId);
}
