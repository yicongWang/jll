package com.zifisense.jll.vo;

import java.math.BigDecimal;

import com.zifisense.jll.common.BasicVo;

/**
 *  年月统计返回对象
 * @author wyc
 *
 */
public class ReportAVo extends BasicVo {
	private BigDecimal countNum;
	private String alarmYm;
	public String getAlarmYm() {
		return alarmYm;
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
