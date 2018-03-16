package com.zifisense.jll.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zifisense.jll.dao.ProjectSysSourceMapperExt;
import com.zifisense.jll.model.ProjectSysSource;
import com.zifisense.jll.model.ProjectSysSourceExample;
import com.zifisense.jll.model.ProjectSysSourceExample.Criteria;
import com.zifisense.jll.service.ProjectSysSourceService;
import com.zifisense.jll.vo.ProjectSysSourceInfoVo;
import com.zifisense.jll.vo.ProjectSysSourceVo;

@Service
public class ProjectSysSourceServiceImpl implements ProjectSysSourceService {
	
	@Autowired 
	private ProjectSysSourceMapperExt projectSysSourceMapperExt;
	@Override
	public List<ProjectSysSourceVo> getProjectSysCodeList(Long projectId) {
		return projectSysSourceMapperExt.getProjectSysCodeList(projectId);
	}
	
	@Override
	public int saveOrUpdate(Long projectId, Long sysId, String joinCode) {
		ProjectSysSourceExample example = new ProjectSysSourceExample();
		Criteria criteria = example.createCriteria();
		criteria.andProjectIdEqualTo(projectId);
		criteria.andSysAppSourceIdEqualTo(sysId);
		int count = projectSysSourceMapperExt.countByExample(example);
		
		ProjectSysSource  projectSysSource = new ProjectSysSource();
		projectSysSource.setProjectId(projectId);
		projectSysSource.setSysAppSourceId(sysId);
		projectSysSource.setSysProjectCode(joinCode);
		if(count <= 0){
			//保存
			return this.save(projectSysSource);
		}else{
			//更新操作
			return this.update(projectSysSource, example);
		}
	}
	
	@Override
	public int save(ProjectSysSource  projectSysSource) {
		return projectSysSourceMapperExt.insert(projectSysSource);
	}
	
	@Override
	public int update(ProjectSysSource  projectSysSource,ProjectSysSourceExample example) {
		return projectSysSourceMapperExt.updateByExample(projectSysSource, example);
	}

	@Override
	public void saveOrUpdateBatch(Long projectId, String sysIdJoinCodes) {
		 //移除项目系统对接码
		 removeProjectSysSource(projectId);
		 String[] sysIdJoinCodeAttr = sysIdJoinCodes.split(",");
		 for(String sysIdJoinCode : sysIdJoinCodeAttr){
			 if(StringUtils.isNoneBlank(sysIdJoinCode)){
				    ProjectSysSource  projectSysSource = new ProjectSysSource();
					projectSysSource.setProjectId(projectId);
					projectSysSource.setSysAppSourceId(Long.valueOf(sysIdJoinCode.split(":")[0]));
					projectSysSource.setSysProjectCode(sysIdJoinCode.split(":")[1]);
					save(projectSysSource);
			 }
		 }
	}

	/**
	 * 移除项目系统对接码
	 * @param projectId
	 */
	private void removeProjectSysSource(Long projectId) {
		ProjectSysSourceExample example = new ProjectSysSourceExample();
		Criteria criteria = example.createCriteria();
		criteria.andProjectIdEqualTo(projectId);
		projectSysSourceMapperExt.deleteByExample(example);
		
	}

	@Override
	public List<ProjectSysSourceInfoVo> getProjectSysCodeInfoByCode(String sysProjectCode) {
		return projectSysSourceMapperExt.getProjectSysCodeInfoByCode(sysProjectCode);
	}
	
}
