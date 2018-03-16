package com.zifisense.jll.vo;

import java.math.BigDecimal;

/**
 * 报表楼板面积对象
 * @author wyc
 *
 */
public class ReportA2Vo extends ReportAVo {
	/**
	 * 楼板面积
	 */
	private BigDecimal floorArea;

	public BigDecimal getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(BigDecimal floorArea) {
		this.floorArea = floorArea;
	}
}
