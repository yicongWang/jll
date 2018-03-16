package com.zifisense.jll.service;

import java.util.List;

import com.zifisense.jll.model.BusinessGroupProject;

/**
 * 业务组项目关联
 * @author wyc
 *
 */
public interface BusinessGroupProjectService {

	/**
	 * 新建业务组项目关联
	 * @param projectId
	 * @param businessGroupIds(多个业务组用逗号隔开)
	 * @return
	 */
	void addBusinessGroupProject(Long projectId,String businessGroupIds);

	/**
	 * 根据项目ID ||或者 &&业务组ID移除关联
	 * @param projectId
	 * @param businessGroupId
	 */
	void removeBusinessGroupProject(Long projectId, Long businessGroupId);

	/**
	 * 根据项目ID ||或者 &&业务组ID获取关联
	 * @param projectId
	 * @param businessGroupId
	 */
	List<BusinessGroupProject> findBusinessGroupProject(Long projectId, Long businessGroupId);
}
