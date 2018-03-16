package com.zifisense.jll.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zifisense.jll.qo.AlarmQo;
import com.zifisense.jll.qo.ReportAListQo;
import com.zifisense.jll.qo.ReportBListQo;
import com.zifisense.jll.qo.ReportBQo;
import com.zifisense.jll.qo.ReportCListQo;
import com.zifisense.jll.qo.ReportCQo;
import com.zifisense.jll.vo.AlarmVo;
import com.zifisense.jll.vo.ReportA2Vo;
import com.zifisense.jll.vo.ReportAVo;
import com.zifisense.jll.vo.ReportB2Vo;
import com.zifisense.jll.vo.ReportBVo;
import com.zifisense.jll.vo.ReportC2Vo;
import com.zifisense.jll.vo.ReportCVo;

/**
 * 告警记录
 * @author wyc
 *
 */
@Mapper
public interface AlarmRecordMapperExt  extends AlarmRecordMapper{
	
	/**
	 * 获取告警记录列表
	 * @param alarmQo
	 * @return
	 */
	List<AlarmVo> getAlarmListByQuery(AlarmQo alarmQo);
	//List<AlarmVo> getAlarmListByQuery(Map<String,Object> map);
	
	/**
	 * 单项目告警统计报表A-1
	 * @param alarmQo
	 * @return
	 */
	List<ReportAVo> countAlarmReportAOne(AlarmQo alarmQo);
	
	/**
	 * 单项目告警统计报表A-2
	 * @param alarmQo
	 * @return
	 */
	List<ReportA2Vo> countAlarmReportATwo(AlarmQo alarmQo);
	
	/**
	 * 报表B-1
	 * @param reportBQo
	 * @return
	 */
	List<ReportBVo> countAlarmReportBOne(ReportBQo reportBQo);
	/**
	 * 报表B-2
	 * @param alarmQo
	 * @return
	 */
	List<ReportB2Vo> countAlarmReportBTwo(ReportBQo reportBQo);

	/**
	 * 统计报表B-3
	 * @param alarmQo
	 * @return
	 */
	List<ReportAVo> countAlarmReportBThree(ReportBQo reportBQo);

	/**
	 * 统计报表C-1
	 * @param reportBQo
	 * @return
	 */
	List<ReportCVo> countAlarmReportCOne(ReportCQo reportCQo);

	
	/**
	 * 统计报表C-2
	 * @param reportBQo
	 * @return
	 */
	List<ReportC2Vo> countAlarmReportCTwo(ReportCQo reportCQo);

	/**
	 * 统计报表C-3
	 * @param reportBQo
	 * @return
	 */
	List<ReportCVo> countAlarmReportCThree(ReportCQo reportCQo);

	/**
	 * 查看A组报表数据
	 * @param reportAListQo
	 * @return
	 */
	List<AlarmVo> findAlarmListByReportA(ReportAListQo reportAListQo);

	/**
	 * 查看B组报表数据
	 * @param reportAListQo
	 * @return
	 */
	List<AlarmVo> findAlarmListByReportB(ReportBListQo reportBListQo);
	
	/**
	 * 查看C组报表数据
	 * @param reportAListQo
	 * @return
	 */
	List<AlarmVo> findAlarmListByReportC(ReportCListQo reportCListQo);


}
