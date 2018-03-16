package com.zifisense.jll.vo;

import com.zifisense.jll.common.BasicVo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * 业务组
 * @author wyc
 *
 */
@ExcelTarget("BusinessGroupVo")
public class BusinessGroupVo  extends BasicVo {

	private Long id;
	@Excel(name = "业务组名称", orderNum = "1", mergeVertical = false, isImportField = "groupName",width =25)
    private String groupName;

    private String projectNameIds;
    @Excel(name = "下辖项目", orderNum = "2", mergeVertical = false,width =25)
    private String projectNames;
    
    @Excel(name = "用户名称", orderNum = "3", mergeVertical = false,width =25)
    private String accountNames;
    
	private String accountNameIds;
    
	public Long getId() {
		return id;
	}

	public String getProjectNameIds() {
		return projectNameIds;
	}

	public void setProjectNameIds(String projectNameIds) {
		this.projectNameIds = projectNameIds;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getProjectNames() {
		return projectNames;
	}

	public void setProjectNames(String projectNames) {
		this.projectNames = projectNames;
	}

	public String getAccountNames() {
		return accountNames;
	}

	public void setAccountNames(String accountNames) {
		this.accountNames = accountNames;
	}

	public String getAccountNameIds() {
		return accountNameIds;
	}

	public void setAccountNameIds(String accountNameIds) {
		this.accountNameIds = accountNameIds;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
