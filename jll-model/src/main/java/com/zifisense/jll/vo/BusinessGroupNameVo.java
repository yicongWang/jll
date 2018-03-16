package com.zifisense.jll.vo;

import com.zifisense.jll.common.BasicVo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * 业务组
 * @author wyc
 *
 */
@ExcelTarget("BusinessGroupNameVo")
public class BusinessGroupNameVo  extends BasicVo {
	
	@Excel(name = "业务组名称",isImportField = "groupName",width =25)
    private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}

