package com.zifisense.jll.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zifisense.jll.model.BusinessGroupAccount;
import com.zifisense.jll.vo.AccountShortVo;

/**
 * 业务组账号关联
 * @author wyc
 *
 */
public interface BusinessGroupAccountService {

	/**
	 * 新建业务组角色关联
	 * @param accountId
	 * @param businessGroupIds(多个业务组用逗号隔开)
	 * @return
	 */
	void addBusinessGroupAccount(Long accountId,String businessGroupIds);

	/**
	 * 根据账号ID ||或者 &&业务组ID移除关联
	 * @param accountId
	 * @param businessGroupId
	 */
	void removeBusinessGroupAccount(Long accountId, Long businessGroupId);
	

	/**
	 * 根据账号ID ||或者 &&业务组ID获取关联
	 * @param accountId
	 * @param businessGroupId
	 * @return 
	 */
	List<BusinessGroupAccount> findBusinessGroupAccount(Long accountId, Long businessGroupId);
	
	/**
	 * 获取项目归宿业务组的负责人
	 * @param projectId
	 * @return
	 */
	List<AccountShortVo> getBusinessGroupAcountList(Long projectId);
}
