package com.zifisense.jll.service;

import java.util.List;

import com.zifisense.jll.model.CodeTable;

/**
 * 码表值
 * @author wyc
 *
 */
public interface CodeTableService {
	/**
	 * 获取码表列表
	 * @return
	 */
	List<CodeTable> getAllCodeTableList();
	/**
	 * 保存
	 * @param codeTable
	 * @return
	 */
	int save(CodeTable codeTable);
}
