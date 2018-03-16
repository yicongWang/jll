package com.zifisense.jll.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zifisense.jll.dao.ProjectAccountMapperExt;
import com.zifisense.jll.model.ProjectAccount;
import com.zifisense.jll.model.ProjectAccountExample;
import com.zifisense.jll.model.ProjectAccountExample.Criteria;
import com.zifisense.jll.service.ProjectAccountService;
import com.zifisense.jll.vo.AccountShortVo;

@Service
public class ProjectAccountServiceImpl implements ProjectAccountService {
	@Autowired
	private ProjectAccountMapperExt projectAccountMapperExt;

	@Transactional
	@Override
	public void addAccountProject(Long accountId, String projectIds) {
		if(StringUtils.isNoneBlank(projectIds)){
			//移除之前的关联
			removeProjectAccount(accountId, null);
			//保存关联
			ProjectAccount  projectAccount = null;
			for(String projectId : projectIds.split(",")){
				if(StringUtils.isNoneBlank(projectId)){
					projectAccount = new ProjectAccount();
					projectAccount.setAccountId(accountId);
					projectAccount.setProjectId(Long.valueOf(projectId));
					projectAccountMapperExt.insert(projectAccount);
				}
			}
		}
	}

	@Override
	public void removeProjectAccount(Long accountId, Long projectId) {
		ProjectAccountExample  example = new ProjectAccountExample();
    	Criteria criteria = example.createCriteria();
    	if(null != accountId){
    		criteria.andAccountIdEqualTo(accountId);
    	}
    	
    	if(null != projectId){
    		criteria.andProjectIdEqualTo(projectId);
    	}
    	
    	projectAccountMapperExt.deleteByExample(example);
		
	}

	@Override
	public int findProjectCountByAccountId(Long accountId) {
		/*ProjectAccountExample  example = new ProjectAccountExample();
    	Criteria criteria = example.createCriteria();
    	if(null != accountId){
    		criteria.andAccountIdEqualTo(accountId);
    	}
		return projectAccountMapperExt.countByExample(example);*/
		return projectAccountMapperExt.countProjectByAccountId(accountId);
	}

	@Override
	public void addProjectAccount(Long projectId, String accountIds) {
		if(StringUtils.isNoneBlank(accountIds)){
			//移除之前的关联
			removeProjectAccount(null, projectId);
			//保存关联
			ProjectAccount  projectAccount = null;
			for(String accountId : accountIds.split(",")){
				if(StringUtils.isNoneBlank(accountId)){
					projectAccount = new ProjectAccount();
					projectAccount.setAccountId(Long.valueOf(accountId));
					projectAccount.setProjectId(Long.valueOf(projectId));
					projectAccountMapperExt.insert(projectAccount);
				}
			}
		}
		
	}

	@Override
	public List<AccountShortVo> getProjectAcountList(Long projectId) {
		return projectAccountMapperExt.getProjectAcountList(projectId);
	}
	
}
