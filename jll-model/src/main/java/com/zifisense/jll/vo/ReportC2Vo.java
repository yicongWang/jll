package com.zifisense.jll.vo;

import java.math.BigDecimal;

/**
 *  报表c2返回
 * @author wyc
 *
 */
public class ReportC2Vo extends ReportCVo {
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
