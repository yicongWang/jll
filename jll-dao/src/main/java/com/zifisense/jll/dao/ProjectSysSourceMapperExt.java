package com.zifisense.jll.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zifisense.jll.vo.ProjectSysSourceInfoVo;
import com.zifisense.jll.vo.ProjectSysSourceVo;

@Mapper
public interface ProjectSysSourceMapperExt extends ProjectSysSourceMapper{
	/**
	 * 获取平台对接系统
	 * @param projectId（针对该项目匹配各个系统对接码）
	 * @return
	 */
	List<ProjectSysSourceVo> getProjectSysCodeList(@Param(value="projectId")  Long projectId);
	
	/**
	 * 通过项目接入码获取对应系统项目信息
	 * @param sysProjectCode
	 * @return
	 */
	List<ProjectSysSourceInfoVo> getProjectSysCodeInfoByCode(@Param(value="sysProjectCode")  String sysProjectCode);
	
}