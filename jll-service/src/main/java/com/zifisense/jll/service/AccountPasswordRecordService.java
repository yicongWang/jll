package com.zifisense.jll.service;

import com.zifisense.jll.model.AccountPasswordRecord;

/**
 * 账号密码更新记录
 * @author wyc
 *
 */
public interface AccountPasswordRecordService {
	/**
	 * 保存更新密码记录
	 * @param record
	 * @return
	 */
	int save (AccountPasswordRecord  record);
	
	/**
	 * 校验密码使用情况
	 * 一年内该密码是否被该用户使用过
	 * @param accountId
	 * @param pw
	 * @return
	 */
	boolean validatePw(Long accountId,String pw);
}
