package com.zifisense.jll.service.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zifisense.jll.dao.AccountPasswordRecordMapperExt;
import com.zifisense.jll.model.AccountPasswordRecord;
import com.zifisense.jll.model.AccountPasswordRecordExample;
import com.zifisense.jll.model.AccountPasswordRecordExample.Criteria;
import com.zifisense.jll.service.AccountPasswordRecordService;
import com.zifisense.jll.util.DateUtil;

/**
 *  账号密码更新记录
 * @author wyc
 *
 */
@Service
public class AccountPasswordRecordServiceImpl implements AccountPasswordRecordService {

    private Logger logger = LoggerFactory.getLogger(AccountPasswordRecordServiceImpl.class);
    
    @Autowired
    private AccountPasswordRecordMapperExt accountPasswordRecordMapperExt;

    @Transactional
	@Override
	public int save(AccountPasswordRecord record) {
		return accountPasswordRecordMapperExt.insert(record);
	}

	@Override
	public boolean validatePw(Long accountId, String pw) {
		AccountPasswordRecordExample example = new AccountPasswordRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountIdEqualTo(accountId);
		criteria.andPasswordOldEqualTo(pw);
		Calendar calendar = Calendar.getInstance();   
		calendar.add(Calendar.YEAR, -1);
		criteria.andCreateTimeGreaterThan(calendar.getTime());
		List<AccountPasswordRecord>  list = accountPasswordRecordMapperExt.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)){
			return false;
		}else{
			return true;
		}
	}
}
