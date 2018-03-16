package com.zifisense.jll.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zifisense.jll.common.BasicVo;

/**
 *  WEB首页展示数据
 * @author wyc
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndexVo  extends BasicVo{
	private int projectCount = 0;
	private int currentAlarmCount  = 0;
	private int historyAlarmCount = 0;
	public Integer getProjectCount() {
		return projectCount;
	}
	public void setProjectCount(Integer projectCount) {
		this.projectCount = projectCount;
	}
	public Integer getCurrentAlarmCount() {
		return currentAlarmCount;
	}
	public void setCurrentAlarmCount(Integer currentAlarmCount) {
		this.currentAlarmCount = currentAlarmCount;
	}
	public Integer getHistoryAlarmCount() {
		return historyAlarmCount;
	}
	public void setHistoryAlarmCount(Integer historyAlarmCount) {
		this.historyAlarmCount = historyAlarmCount;
	}
   
}
