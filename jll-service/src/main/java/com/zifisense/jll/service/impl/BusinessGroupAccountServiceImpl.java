package com.zifisense.jll.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zifisense.jll.dao.BusinessGroupAccountMapperExt;
import com.zifisense.jll.model.BusinessGroupAccount;
import com.zifisense.jll.model.BusinessGroupAccountExample;
import com.zifisense.jll.model.BusinessGroupAccountExample.Criteria;
import com.zifisense.jll.service.BusinessGroupAccountService;
import com.zifisense.jll.vo.AccountShortVo;

@Service
public class BusinessGroupAccountServiceImpl implements BusinessGroupAccountService {

	
    @Autowired
    private BusinessGroupAccountMapperExt businessGroupAccountMapperExt;

    @Transactional
	@Override
	public void addBusinessGroupAccount(Long accountId, String businessGroupIds) {
		if(StringUtils.isNoneBlank(businessGroupIds)){
			//移除之前的关联
			removeBusinessGroupAccount(accountId, null);
			//保存关联
			BusinessGroupAccount  businessGroupAccount = null;
			for(String businessGroupId : businessGroupIds.split(",")){
				if(StringUtils.isNoneBlank(businessGroupId)){
					businessGroupAccount = new BusinessGroupAccount();
					businessGroupAccount.setAccountId(accountId);
					businessGroupAccount.setBusinessGroupId(Long.valueOf(businessGroupId));
					businessGroupAccountMapperExt.insert(businessGroupAccount);
				}
			}
		}
	}
    
    @Transactional
    @Override
	public void removeBusinessGroupAccount(Long accountId,Long businessGroupId){
    	BusinessGroupAccountExample  example = new BusinessGroupAccountExample();
    	Criteria criteria = example.createCriteria();
    	if(null != accountId){
    		criteria.andAccountIdEqualTo(accountId);
    	}
    	
    	if(null != businessGroupId){
    		criteria.andBusinessGroupIdEqualTo(businessGroupId);
    	}
    	
		businessGroupAccountMapperExt.deleteByExample(example);
	}

	@Override
	public List<BusinessGroupAccount> findBusinessGroupAccount(Long accountId, Long businessGroupId) {
		BusinessGroupAccountExample  example = new BusinessGroupAccountExample();
    	Criteria criteria = example.createCriteria();
    	if(null != accountId){
    		criteria.andAccountIdEqualTo(accountId);
    	}
    	
    	if(null != businessGroupId){
    		criteria.andBusinessGroupIdEqualTo(businessGroupId);
    	}
    	
    	return businessGroupAccountMapperExt.selectByExample(example);
	}

	@Override
	public List<AccountShortVo> getBusinessGroupAcountList(Long projectId) {
		return businessGroupAccountMapperExt.getBusinessGroupAcountList(projectId);
	}

}
