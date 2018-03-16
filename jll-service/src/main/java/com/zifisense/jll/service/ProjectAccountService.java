package com.zifisense.jll.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zifisense.jll.vo.AccountShortVo;

/**
 * 项目账号关联
 * @author wyc
 *
 */
public interface ProjectAccountService {
	/**
	 * 新项目角色关联
	 * 移除之前的关联
	 * 新增关联(用户关联项目)
	 * @param accountId
	 * @param projectIds(多个项目用逗号隔开)
	 * @return
	 */
	void addAccountProject(Long accountId,String projectIds);
	
	/**
	 * 根据账号ID ||或者 &&项目ID移除关联
	 * @param accountId
	 * @param businessGroupId
	 */
	void removeProjectAccount(Long accountId, Long projectId);
	
	
	/**
	 * 根据用户ID获取关联项目个数
	 * @param accountId
	 */
	int findProjectCountByAccountId(Long accountId);
	
	/**
	 * 新增关联(项目关联用户)
	 * @param projectId
	 * @param accountIds(多个用户用逗号隔开)
	 * @return
	 */
	void addProjectAccount(Long projectId,String accountIds);
	
	/**
    * 获取负责该项目的用户
    * @param projectId
    * @return
    */    
	List<AccountShortVo> getProjectAcountList(Long projectId);
}
