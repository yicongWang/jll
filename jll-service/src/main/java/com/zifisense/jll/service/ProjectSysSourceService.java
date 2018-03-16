package com.zifisense.jll.service;

import java.util.List;

import com.zifisense.jll.model.ProjectSysSource;
import com.zifisense.jll.model.ProjectSysSourceExample;
import com.zifisense.jll.vo.ProjectSysSourceInfoVo;
import com.zifisense.jll.vo.ProjectSysSourceVo;

/**
 * 项目系统对接码关联
 * @author wyc
 *
 */
public interface ProjectSysSourceService {
	/**
	 * 获取项目系统对接码列表
	 * @param projectId
	 * @return
	 */
	List<ProjectSysSourceVo> getProjectSysCodeList(Long projectId);
	
	/**
	 * 保存更新
	 * 
	 * projectId-sysId 确认唯一
	 * @param projectId
	 * @param sysId
	 * @param joinCode
	 * @return
	 */
	int saveOrUpdate(Long projectId,Long sysId,String joinCode);

	/**
	 * 保存
	 * @param projectSysSource
	 * @return
	 */
	int save(ProjectSysSource projectSysSource);

	/**
	 * 更加条件更新
	 * @param projectSysSource
	 * @param example
	 * @return
	 */
	int update(ProjectSysSource projectSysSource, ProjectSysSourceExample example);
	/**
	 * 批量关联系统对接码
	 * @param projectId
	 * @param sysIdJoinCodes
	 * @return
	 */
	void saveOrUpdateBatch(Long projectId,String sysIdJoinCodes);
	
	/**
	 * 通过项目接入码获取对应系统项目信息
	 * @param sysProjectCode
	 * @return
	 */
	List<ProjectSysSourceInfoVo> getProjectSysCodeInfoByCode(String sysProjectCode);
}
