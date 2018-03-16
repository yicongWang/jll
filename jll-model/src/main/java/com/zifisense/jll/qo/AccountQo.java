package com.zifisense.jll.qo;

import com.zifisense.jll.common.BasicQo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class AccountQo  extends BasicQo{
	@ApiModelProperty(name="查询关键字")
	private String keys;

	@ApiModelProperty(name="账号ID")
	private Long accountId;
	
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}
	
}
