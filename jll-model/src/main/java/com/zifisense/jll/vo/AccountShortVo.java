package com.zifisense.jll.vo;

/**
 *账号简称
 * @author wyc
 *
 */
public class AccountShortVo  extends ShortVo {
	private String code;
	private String mobilePhone;
	private String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
    
}
