package com.zifisense.jll.service;

/**
 * 联系管理员
 * @author wyc
 *
 */
public interface ContactAdminService {
	/**
	 * 保存联系管理员
	 * @param title
	 * @param content
	 * @param createrId
	 * @return
	 */
	void save(String title,String content,Long createrId);
}
