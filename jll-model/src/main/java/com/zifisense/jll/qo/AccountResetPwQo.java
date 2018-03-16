package com.zifisense.jll.qo;

import com.zifisense.jll.common.BasicQo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class AccountResetPwQo  extends BasicQo{
	@ApiModelProperty(name="超级管理员密码")
	private  String adminPwd ; 
	@ApiModelProperty(name="重置账号")
	private String account;
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
}
