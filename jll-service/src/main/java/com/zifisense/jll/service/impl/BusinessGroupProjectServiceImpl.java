package com.zifisense.jll.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zifisense.jll.dao.BusinessGroupProjectMapperExt;
import com.zifisense.jll.model.BusinessGroupProject;
import com.zifisense.jll.model.BusinessGroupProjectExample;
import com.zifisense.jll.model.BusinessGroupProjectExample.Criteria;
import com.zifisense.jll.service.BusinessGroupProjectService;

@Service
public class BusinessGroupProjectServiceImpl implements BusinessGroupProjectService {

	
    @Autowired
    private BusinessGroupProjectMapperExt businessGroupProjectMapperExt;

    @Transactional
	@Override
	public void addBusinessGroupProject(Long projectId, String businessGroupIds) {
		if(StringUtils.isNoneBlank(businessGroupIds)){
			//移除之前的关联
			removeBusinessGroupProject(projectId, null);
			//保存关联
			BusinessGroupProject  businessGroupProject = null;
			for(String businessGroupId : businessGroupIds.split(",")){
				if(StringUtils.isNoneBlank(businessGroupId)){
					businessGroupProject = new BusinessGroupProject();
					businessGroupProject.setProjectId(projectId);
					businessGroupProject.setBusinessGroupId(Long.valueOf(businessGroupId));
					businessGroupProjectMapperExt.insert(businessGroupProject);
				}
			}
		}
	}
    
    @Transactional
    @Override
	public void removeBusinessGroupProject(Long projectId,Long businessGroupId){
    	BusinessGroupProjectExample  example = new BusinessGroupProjectExample();
    	Criteria criteria = example.createCriteria();
    	if(null != projectId){
    		criteria.andProjectIdEqualTo(projectId);
    	}
    	
    	if(null != businessGroupId){
    		criteria.andBusinessGroupIdEqualTo(businessGroupId);
    	}
    	
		businessGroupProjectMapperExt.deleteByExample(example);
	}

    @Override
	public List<BusinessGroupProject> findBusinessGroupProject(Long projectId,Long businessGroupId){
    	BusinessGroupProjectExample  example = new BusinessGroupProjectExample();
    	Criteria criteria = example.createCriteria();
    	if(null != projectId){
    		criteria.andProjectIdEqualTo(projectId);
    	}
    	
    	if(null != businessGroupId){
    		criteria.andBusinessGroupIdEqualTo(businessGroupId);
    	}
    	
    	return businessGroupProjectMapperExt.selectByExample(example);
    }
    
}
