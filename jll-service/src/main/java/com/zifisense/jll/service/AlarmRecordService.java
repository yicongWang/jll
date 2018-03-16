package com.zifisense.jll.service;

import java.math.BigDecimal;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zifisense.jll.model.AlarmRecord;
import com.zifisense.jll.qo.AlarmPageQo;
import com.zifisense.jll.qo.AlarmQo;
import com.zifisense.jll.qo.ReportAListQo;
import com.zifisense.jll.qo.ReportAPageQo;
import com.zifisense.jll.qo.ReportBListQo;
import com.zifisense.jll.qo.ReportBPageQo;
import com.zifisense.jll.qo.ReportBQo;
import com.zifisense.jll.qo.ReportCListQo;
import com.zifisense.jll.qo.ReportCPageQo;
import com.zifisense.jll.qo.ReportCQo;
import com.zifisense.jll.vo.AlarmVo;
import com.zifisense.jll.vo.ReportAVo;
import com.zifisense.jll.vo.ReportBVo;
import com.zifisense.jll.vo.ReportCVo;

/**
 * 告警记录业务
 * @author ywc
 *
 */
public interface AlarmRecordService {

	/**
	 * 分页获取当前告警列表
	 * @param alarmQo
	 * @return
	 * @throws Exception 
	 */
	PageInfo<AlarmVo> findCurrentAlarmPage(AlarmPageQo alarmPageQo) throws Exception;

	/**
	 * 根据查询条件获取告警列表
	 * @param appSourceCode
	 * @param projectId
	 * @param startYm
	 * @param endYm
	 * @return
	 */
	List<AlarmVo> getAlarmListByQuery(AlarmQo alarmQo);

	/**
	 * 根据告警ID更新告警状态
	 * @param alarmIds
	 * @return
	 */
	void updateAlarmState(String alarmIds,Long modifyId);

	/**
	 * 分页获取历史告警列表
	 * @param alarmQo
	 * @return
	 * @throws Exception 
	 */
	PageInfo<AlarmVo> findHistoryAlarmPage(AlarmPageQo alarmPageQo) throws Exception;

	/**
	 * 单项目告警统计报表A-1
	 * @param alarmQo
	 * @return
	 */
	List<ReportAVo> countAlarmReportAOne(AlarmQo alarmQo);
	/**
	 * 报表A-2
	 * @param alarmQo
	 * @return
	 */
	List<ReportAVo> countAlarmReportATwo(AlarmQo alarmQo);
	
	/**
	 * 获取报表A-2 基准线
	 * @param alarmQo
	 * @return
	 */
	BigDecimal getAlarmReportATwoBaseLine(AlarmQo alarmQo);
	
	/**
	 * 报表A-3
	 * @param alarmQo
	 * @return
	 */
	List<ReportAVo> countAlarmReportAThree(AlarmQo alarmQo);
	/**
	 * 报表B-1
	 * @param reportBQo
	 * @return
	 */
	List<ReportBVo> countAlarmReportBOne(ReportBQo reportBQo);
	/**
	 * 报表B-2
	 * @param reportBQo
	 * @return
	 */
	List<ReportBVo> countAlarmReportBTwo(ReportBQo reportBQo);
	
	/**
	 * 获取报表B-2 基准线
	 * @param alarmQo
	 * @return
	 */
	BigDecimal getAlarmReportBTwoBaseLine(ReportBQo reportBQo);
	
	/**
	 * 报表B-3
	 * @param reportBQo
	 * @return
	 */
	List<ReportAVo> countAlarmReportBThree(ReportBQo reportBQo);

	/**
	 * 报表C-1
	 * @param reportCQo
	 * @return
	 */
	List<ReportCVo> countAlarmReportCOne(ReportCQo reportCQo);

	/**
	 * 报表C-2
	 * @param reportCQo
	 * @return
	 */
	List<ReportCVo> countAlarmReportCTwo(ReportCQo reportCQo);
	/**
	 * 报表C-2 基准线
	 * @param reportCQo
	 * @return
	 */
	BigDecimal getAlarmReportCTwoBaseLine(ReportCQo reportCQo);
	
	/**
	 * 报表C-3
	 * @param reportCQo
	 * @return
	 */
	List<ReportCVo> countAlarmReportCThree(ReportCQo reportCQo);

	/**
	 * 查看A组报表数据
	 * @param reportAListQo
	 * @return
	 */
	List<AlarmVo> findReportAAlarmListByQuery(ReportAListQo reportAListQo);
	
	
	/**
	 * 分页查看A组报表数据
	 * @param reportAPageQo
	 * @return
	 * @throws Exception 
	 */
	PageInfo<AlarmVo> findReportAAlarmPage(ReportAPageQo reportAPageQo) throws Exception;
	
	/**
	 * 分页查看B组报表数据
	 * @param reportBPageQo
	 * @return
	 * @throws Exception 
	 */
	PageInfo<AlarmVo> findAlarmPageByReportB(ReportBPageQo reportBPageQo) throws Exception;

	/**
	 * 查看B组报表数据
	 * @param reportAListQo
	 * @return
	 */
	List<AlarmVo> findAlarmListByReportB(ReportBListQo reportBListQo);
	
	
	/**
	 * 分页查看C组报表数据
	 * @param reportBPageQo
	 * @return
	 * @throws Exception 
	 */
	PageInfo<AlarmVo> findAlarmPageByReportC(ReportCPageQo reportCPageQo) throws Exception;

	/**
	 * 查看C组报表数据
	 * @param reportCListQo
	 * @return
	 */
	List<AlarmVo> findAlarmListByReportC(ReportCListQo reportCListQo);
	 
	/**
	 * 保存
	 * @param alarmRecord
	 */
	void save(AlarmRecord alarmRecord);
	
	/**
	 * 针对项目系统（部件类型部件地址 OR deviceId）出现故障(还未归档)进行归档
	 */
	int updateAlarmRecordByCondition(String sysProjectCode, String deviceType, String cmptMac, String deviceId);
	/**
	 * 通过项目Id发送邮件通知有关人员
	 */
	void sendNotifyByProjectId(String title, String content, Long projectId);
	
}
