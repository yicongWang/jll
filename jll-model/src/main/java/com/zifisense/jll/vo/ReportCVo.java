package com.zifisense.jll.vo;

import java.math.BigDecimal;

import com.zifisense.jll.common.BasicVo;

/**
 *  年月统计返回对象
 * @author wyc
 *
 */
public class ReportCVo extends BasicVo {
	private BigDecimal countNum;
	private String alarmYm;
	private String groupName;
	private Long groupId;
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getAlarmYm() {
		return alarmYm;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setAlarmYm(String alarmYm) {
		this.alarmYm = alarmYm;
	}
	public BigDecimal getCountNum() {
		return countNum;
	}
	public void setCountNum(BigDecimal countNum) {
		this.countNum = countNum;
	}
	
}
