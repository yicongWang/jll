package com.zifisense.jll.qo;

import com.zifisense.jll.common.BasicQo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class AccountUpdateMobileQo  extends BasicQo{
	@ApiModelProperty(name="账号ID")
	private  Long accountId ; 
	@ApiModelProperty(name="手机号")
	private String mobilePhone;
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
}
