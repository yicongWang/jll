package com.zifisense.jll.vo;

import java.math.BigDecimal;

/**
 *  报表2返回
 * @author wyc
 *
 */
public class ReportB2Vo extends ReportBVo {
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
