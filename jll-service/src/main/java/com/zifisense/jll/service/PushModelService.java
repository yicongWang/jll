package com.zifisense.jll.service;

import java.util.List;

import com.zifisense.jll.model.PushModel;
import com.zifisense.jll.vo.AccountShortVo;
/**
 * 推送模式
 * @author wyc
 *
 */
public interface PushModelService {
	/**
	 * 查询系统支持的推送模式列表
	 * @return
	 */
	List<PushModel> queryPushModelList();
	/**
	 * 更新推送模式
	 * @param idsStr
	 * @param state 
	 * @param id
	 */
	void updatePushModel(Long id, Integer state, Long modifyId);
	/**
	 * 邮件通知账号
	 * @param adminAccountList
	 */
	void notifyEmailToAccount(String title,String content,List<AccountShortVo> adminAccountList);
}
