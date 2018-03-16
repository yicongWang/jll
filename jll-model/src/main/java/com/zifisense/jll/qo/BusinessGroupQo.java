package com.zifisense.jll.qo;

import com.zifisense.jll.common.BasicQo;

public class BusinessGroupQo extends BasicQo{
	
	private String keys;
	
	private Long bussiessGroupId;
	
	

	public Long getBussiessGroupId() {
		return bussiessGroupId;
	}

	public void setBussiessGroupId(Long bussiessGroupId) {
		this.bussiessGroupId = bussiessGroupId;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}
}
