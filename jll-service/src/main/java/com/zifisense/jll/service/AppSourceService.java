package com.zifisense.jll.service;

import java.util.List;

import com.zifisense.jll.model.AppSource;

/**
 * 平台对接系统管理
 * @author wyc
 *
 */
public interface AppSourceService {
	/**
	 * 获取平台对接系统
	 * @param projectId（针对该项目匹配各个系统对接码）
	 * @return
	 */
	List<AppSource> getALLAppSourceInfoList();
}
