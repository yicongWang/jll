package com.zifisense.jll.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zifisense.jll.dao.AlarmRecordMapperExt;
import com.zifisense.jll.model.AlarmRecord;
import com.zifisense.jll.model.AlarmRecordExample;
import com.zifisense.jll.model.AlarmRecordExample.Criteria;
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
import com.zifisense.jll.service.AccountService;
import com.zifisense.jll.service.AlarmRecordService;
import com.zifisense.jll.service.BusinessGroupAccountService;
import com.zifisense.jll.service.ProjectAccountService;
import com.zifisense.jll.service.ProjectService;
import com.zifisense.jll.service.PushModelService;
import com.zifisense.jll.service.common.RoleEnum;
import com.zifisense.jll.util.DateUtil;
import com.zifisense.jll.vo.AccountShortVo;
import com.zifisense.jll.vo.AlarmVo;
import com.zifisense.jll.vo.ReportA2Vo;
import com.zifisense.jll.vo.ReportAVo;
import com.zifisense.jll.vo.ReportB2Vo;
import com.zifisense.jll.vo.ReportBVo;
import com.zifisense.jll.vo.ReportC2Vo;
import com.zifisense.jll.vo.ReportCVo;

/**
 * 告警记录ServiceImpl
 * @author wyc
 *
 */
@Service
public class AlarmRecordServiceImpl implements AlarmRecordService {

    @Autowired
    private AlarmRecordMapperExt alarmRecordMapperExt;
    
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectAccountService  projectAccountService;
    @Autowired
    private BusinessGroupAccountService  businessGroupAccountService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PushModelService pushModelService;
    @Override
    public PageInfo<AlarmVo>  findCurrentAlarmPage(AlarmPageQo alarmPageQo) throws Exception{
        PageHelper.startPage(alarmPageQo.getPageNum(), alarmPageQo.getPageSize());
    	AlarmQo alarmQo = new AlarmQo();
        BeanUtils.copyProperties(alarmQo, alarmPageQo);
        alarmQo.setAlarmState(0);//0:告警中 1:解除警报(归档)
        alarmQo.setDataType(0);//0:报警信息、1：常规信息
        List<AlarmVo> list = getAlarmListByQuery(alarmQo);
        return new PageInfo<>(list);
    }
    
    @Override
    public List<AlarmVo> getAlarmListByQuery(AlarmQo alarmQo) {
    	if(StringUtils.isNotBlank(alarmQo.getAppSourceCodes())){
    		alarmQo.setAppSourceCodeArray(StringUtils.split(alarmQo.getAppSourceCodes(), ","));
    	}
    	return alarmRecordMapperExt.getAlarmListByQuery(alarmQo);
	}

	@Override
	public void updateAlarmState(String alarmIds,Long modifyId) {
		Arrays.asList(alarmIds.split(",")).forEach(id->{
			if(StringUtils.isNoneBlank(id)){
				AlarmRecord record = new AlarmRecord();
				record.setId(Long.valueOf(id));
				record.setAlarmState(1L);
				record.setModifyUser(modifyId);
				record.setModifyTime(new Date());
				alarmRecordMapperExt.updateByPrimaryKeySelective(record);
			}
		});
	}

	@Override
	public PageInfo<AlarmVo> findHistoryAlarmPage(AlarmPageQo alarmPageQo) throws Exception {
		PageHelper.startPage(alarmPageQo.getPageNum(), alarmPageQo.getPageSize());
    	AlarmQo alarmQo = new AlarmQo();
        BeanUtils.copyProperties(alarmQo, alarmPageQo);
        List<AlarmVo> list = getAlarmListByQuery(alarmQo);
        return new PageInfo<>(list);
	}
	
	@Override
	public List<ReportAVo> countAlarmReportAOne(AlarmQo alarmQo){
		List<ReportAVo> list = new ArrayList<>();
		if(StringUtils.isNotBlank(alarmQo.getAppSourceCodes())){
    		alarmQo.setAppSourceCodeArray(StringUtils.split(alarmQo.getAppSourceCodes(), ","));
    	}
		List<String> monthList = DateUtil.getMonthList(alarmQo.getStartYm(), alarmQo.getEndYm());
		List<ReportAVo> listData = alarmRecordMapperExt.countAlarmReportAOne(alarmQo);
		ReportAVo reportAVo = null;
		for(String month : monthList){
			List<ReportAVo> filterList = listData.stream().filter(a -> a.getAlarmYm().equals(month)).collect(Collectors.toList());
			if(CollectionUtils.isNotEmpty(filterList)){
				reportAVo = filterList.get(0);
			}else{
				reportAVo = new ReportAVo();
				reportAVo.setAlarmYm(month);
				reportAVo.setCountNum(BigDecimal.valueOf(0));
			}
			list.add(reportAVo);
		}
		
		return list;
	}

	@Override
	public List<ReportAVo> countAlarmReportATwo(AlarmQo alarmQo) {
		if(StringUtils.isNotBlank(alarmQo.getAppSourceCodes())){
    		alarmQo.setAppSourceCodeArray(StringUtils.split(alarmQo.getAppSourceCodes(), ","));
    	}
		
		Map<String,List<ReportA2Vo>> map = new HashMap<>();
		List<ReportA2Vo> list = alarmRecordMapperExt.countAlarmReportATwo(alarmQo);
		List<ReportAVo> resultList = null;
		if(CollectionUtils.isNotEmpty(list)){
			List<ReportA2Vo> listNew = null;
			resultList = new ArrayList<>();
			ReportAVo reportAVo = null;
			//收集各个月份所有数据列表
			for(ReportA2Vo reportA2Vo :list){
				if(map.containsKey(reportA2Vo.getAlarmYm())){
					map.get(reportA2Vo.getAlarmYm()).add(reportA2Vo);
				}else{
					listNew = new LinkedList<>();
					listNew.add(reportA2Vo);
					map.put(reportA2Vo.getAlarmYm(),listNew);
				}
			}
			//根据各个月份汇总数据
			if(MapUtils.isNotEmpty(map)){
				BigDecimal sumCount = null;
				BigDecimal sumFloorArea = null;
				for(Entry<String,List<ReportA2Vo>> entry : map.entrySet()){
					 sumCount = new BigDecimal(0);
					 sumFloorArea = new BigDecimal(0);
					 reportAVo = new ReportAVo();
					 reportAVo.setAlarmYm(entry.getKey());
					 for(ReportA2Vo reportA2Vo :entry.getValue()){
						 sumFloorArea =  sumFloorArea.add(reportA2Vo.getFloorArea());
						 sumCount = sumCount .add(reportA2Vo.getCountNum());
					 }
					 reportAVo.setCountNum(sumCount.divide(sumFloorArea.divide(BigDecimal.valueOf(10000), 2,BigDecimal.ROUND_UP),2,BigDecimal.ROUND_UP));
					 resultList.add(reportAVo);
				}
			}
			
		}
		resultList.sort((ReportAVo h1, ReportAVo h2) -> h1.getAlarmYm().compareTo(h2.getAlarmYm()));
		List<ReportAVo> result = new ArrayList<>();
		List<String> monthList = DateUtil.getMonthList(alarmQo.getStartYm(), alarmQo.getEndYm());
		ReportAVo reportAVo = null;
		for(String month : monthList){
			List<ReportAVo> filterList = resultList.stream().filter(a -> a.getAlarmYm().equals(month)).collect(Collectors.toList());
			if(CollectionUtils.isNotEmpty(filterList)){
				reportAVo = filterList.get(0);
			}else{
				reportAVo = new ReportAVo();
				reportAVo.setAlarmYm(month);
				reportAVo.setCountNum(BigDecimal.valueOf(0));
			}
			result.add(reportAVo);
		}
		return result;
	}

	@Override
	public BigDecimal getAlarmReportATwoBaseLine(AlarmQo alarmQo) {
		if(StringUtils.isNotBlank(alarmQo.getAppSourceCodes())){
    		alarmQo.setAppSourceCodeArray(StringUtils.split(alarmQo.getAppSourceCodes(), ","));
    	}
	
		List<ReportA2Vo> list = alarmRecordMapperExt.countAlarmReportATwo(alarmQo);
	
		int yearStart = Integer.valueOf(alarmQo.getStartYm().substring(0,4));
		int monthStart = Integer.valueOf(alarmQo.getStartYm().substring(4,6));
		int yearEnd = Integer.valueOf(alarmQo.getEndYm().substring(0,4));
		int monthEnd = Integer.valueOf(alarmQo.getEndYm().substring(4,6));
		//统计月份
		int diffMonth = 0;
		diffMonth = (yearEnd - yearStart)*12 + (monthEnd - monthStart);
		
		//统计告警条数
		BigDecimal sumCount = null;
		if(CollectionUtils.isNotEmpty(list)){
			sumCount = BigDecimal.valueOf(0);
			for(ReportA2Vo reportA2Vo :list){
				 sumCount = sumCount.add(reportA2Vo.getCountNum());
			}
		}
		//统计所有项目楼板面积
		BigDecimal sumFloorArea = projectService.sumAllProjectFloorArea();
		if(sumCount == null  || sumFloorArea == null || diffMonth == 0) {
			return BigDecimal.valueOf(0);
		}
		System.out.println( "diffMonth:"+diffMonth +" sumCount:"+sumCount +" sumFloorArea："+sumFloorArea);
		return sumCount.divide(sumFloorArea,2,BigDecimal.ROUND_UP).divide(BigDecimal.valueOf(diffMonth),2,BigDecimal.ROUND_UP);
	}
	
	
	@Override
	public List<ReportAVo> countAlarmReportAThree(AlarmQo alarmQo) {
		return null;
	}

	@Override
	public List<ReportBVo> countAlarmReportBOne(ReportBQo reportBQo) {
		if(StringUtils.isNotBlank(reportBQo.getAppSourceCodes())){
			reportBQo.setAppSourceCodeArray(StringUtils.split(reportBQo.getAppSourceCodes(), ","));
    	}
		
		if(StringUtils.isNotBlank(reportBQo.getProjectIds())){
			reportBQo.setProjectIdArray(StringUtils.split(reportBQo.getProjectIds(), ","));
    	}
		
		return alarmRecordMapperExt.countAlarmReportBOne(reportBQo);
	}

	@Override
	public List<ReportBVo> countAlarmReportBTwo(ReportBQo reportBQo) {
		List<ReportBVo> list = null;
		if(StringUtils.isNotBlank(reportBQo.getAppSourceCodes())){
			reportBQo.setAppSourceCodeArray(StringUtils.split(reportBQo.getAppSourceCodes(), ","));
    	}
		
		if(StringUtils.isNotBlank(reportBQo.getProjectIds())){
			reportBQo.setProjectIdArray(StringUtils.split(reportBQo.getProjectIds(), ","));
    	}
		//获取报表数据
		List<ReportB2Vo> reportB2VoList = alarmRecordMapperExt.countAlarmReportBTwo(reportBQo);
		if(CollectionUtils.isNotEmpty(reportB2VoList)){
			list = new ArrayList<>();
			ReportBVo reportBVo = null;
			for(ReportB2Vo reportB2Vo : reportB2VoList ){
				reportBVo = new ReportBVo();
				reportBVo.setCountNum(reportB2Vo.getCountNum().divide(reportB2Vo.getFloorArea().divide(BigDecimal.valueOf(10000),2,BigDecimal.ROUND_UP),2,BigDecimal.ROUND_UP));
				reportBVo.setProjectId(reportB2Vo.getProjectId());
				reportBVo.setProjectName(reportB2Vo.getProjectName());
				list.add(reportBVo);
			}
		}
		return list;
	}

	@Override
	public BigDecimal getAlarmReportBTwoBaseLine(ReportBQo reportBQo) {
		//获取报表数据
		reportBQo.setProjectIdArray(null);
		reportBQo.setProjectIds(null);
		reportBQo.setAppSourceCodeArray(null);
		reportBQo.setAppSourceCodes(null);
		List<ReportB2Vo> reportB2VoList = alarmRecordMapperExt.countAlarmReportBTwo(reportBQo);
		
		BigDecimal sumCount = null;
		BigDecimal sumFloorArea = null;
		if(CollectionUtils.isNotEmpty(reportB2VoList)){
			sumCount = new BigDecimal(0);
			sumFloorArea = new BigDecimal(0);
			for(ReportB2Vo reportB2Vo : reportB2VoList){
				sumCount = sumCount.add(reportB2Vo.getCountNum());
				sumFloorArea = sumFloorArea.add(reportB2Vo.getFloorArea());
			}
		}
		
		if(sumCount == null  || sumFloorArea == null) {
			return BigDecimal.valueOf(0);
		}
		
		return sumCount.divide(sumFloorArea.divide(BigDecimal.valueOf(10000),2,BigDecimal.ROUND_UP),2,BigDecimal.ROUND_UP);
	}
	
	
	@Override
	public List<ReportAVo> countAlarmReportBThree(ReportBQo reportBQo) {
		if(StringUtils.isNotBlank(reportBQo.getAppSourceCodes())){
			reportBQo.setAppSourceCodeArray(StringUtils.split(reportBQo.getAppSourceCodes(), ","));
    	}
		
		if(StringUtils.isNotBlank(reportBQo.getProjectIds())){
			reportBQo.setProjectIdArray(StringUtils.split(reportBQo.getProjectIds(), ","));
    	}
		List<ReportAVo> dataList = alarmRecordMapperExt.countAlarmReportBThree(reportBQo);
		
		List<ReportAVo> result = new ArrayList<>();
		List<String> monthList = DateUtil.getMonthList(reportBQo.getStartYm(), reportBQo.getEndYm());
		ReportAVo reportAVo = null;
		for(String month : monthList){
			List<ReportAVo> filterList = dataList.stream().filter(a -> a.getAlarmYm().equals(month)).collect(Collectors.toList());
			if(CollectionUtils.isNotEmpty(filterList)){
				reportAVo = filterList.get(0);
			}else{
				reportAVo = new ReportAVo();
				reportAVo.setAlarmYm(month);
				reportAVo.setCountNum(BigDecimal.valueOf(0));
			}
			result.add(reportAVo);
		}
		return result;
	}

	@Override
	public List<ReportCVo> countAlarmReportCOne(ReportCQo reportCQo) {
		if(StringUtils.isNotBlank(reportCQo.getAppSourceCodes())){
			reportCQo.setAppSourceCodeArray(StringUtils.split(reportCQo.getAppSourceCodes(), ","));
    	}
		
		List<ReportCVo> dataList = alarmRecordMapperExt.countAlarmReportCOne(reportCQo);
		
		List<ReportCVo> result = new ArrayList<>();
		List<String> monthList = DateUtil.getMonthList(reportCQo.getStartYm(), reportCQo.getEndYm());
		ReportCVo reportCVo = null;
		for(String month : monthList){
			List<ReportCVo> filterList = dataList.stream().filter(a -> a.getAlarmYm().equals(month)).collect(Collectors.toList());
			if(CollectionUtils.isNotEmpty(filterList)){
				reportCVo = filterList.get(0);
			}else{
				reportCVo = new ReportCVo();
				reportCVo.setAlarmYm(month);
			}
			result.add(reportCVo);
		}
		return result;
	}

	@Override
	public List<ReportCVo> countAlarmReportCTwo(ReportCQo reportCQo) {
		List<ReportCVo> dataList = null;
		if(StringUtils.isNotBlank(reportCQo.getAppSourceCodes())){
			reportCQo.setAppSourceCodeArray(StringUtils.split(reportCQo.getAppSourceCodes(), ","));
    	}
		List<ReportC2Vo> list = alarmRecordMapperExt.countAlarmReportCTwo(reportCQo);
		if(CollectionUtils.isNotEmpty(list)){
			dataList = new ArrayList<>();
			ReportCVo reportCVo = null;
			for(ReportC2Vo reportC2Vo : list){
				reportCVo = new ReportCVo();
				reportCVo.setCountNum(reportC2Vo.getCountNum().divide(reportC2Vo.getFloorArea().divide(BigDecimal.valueOf(10000),2,BigDecimal.ROUND_UP),2,BigDecimal.ROUND_UP));
				reportCVo.setAlarmYm(reportC2Vo.getAlarmYm());
				reportCVo.setGroupId(reportC2Vo.getGroupId());
				reportCVo.setGroupName(reportC2Vo.getGroupName());
				dataList.add(reportCVo);
			}
			dataList.sort((ReportCVo h1, ReportCVo h2) -> h1.getAlarmYm().compareTo(h2.getAlarmYm()));
		}
		
		List<ReportCVo> resultList = new ArrayList<>();
		List<String> monthList = DateUtil.getMonthList(reportCQo.getStartYm(), reportCQo.getEndYm());
		ReportCVo reportCVo = null;
		for(String month : monthList){
			List<ReportCVo> filterList = dataList.stream().filter(a -> a.getAlarmYm().equals(month)).collect(Collectors.toList());
			if(CollectionUtils.isNotEmpty(filterList)){
				reportCVo = filterList.get(0);
			}else{
				reportCVo = new ReportCVo();
				reportCVo.setAlarmYm(month);
			}
			resultList.add(reportCVo);
		}
		return resultList;
	}
	
	@Override
	public BigDecimal getAlarmReportCTwoBaseLine(ReportCQo reportCQo) {
		int yearStart = Integer.valueOf(reportCQo.getStartYm().substring(0,4));
		int monthStart = Integer.valueOf(reportCQo.getStartYm().substring(4,6));
		int yearEnd = Integer.valueOf(reportCQo.getEndYm().substring(0,4));
		int monthEnd = Integer.valueOf(reportCQo.getEndYm().substring(4,6));
		//统计月份
		int diffMonth = 0;
		diffMonth = (yearEnd - yearStart)*12 + (monthEnd - monthStart);
		//统计告警条数
		BigDecimal sumCount = null;
		//去年同期系统中所有项目符合条件数据总量
		List<ReportCVo> list = alarmRecordMapperExt.countAlarmReportCOne(reportCQo);
		if(CollectionUtils.isNotEmpty(list)){
			sumCount = BigDecimal.valueOf(0);
			for(ReportCVo reportCVo : list){
				 sumCount = sumCount.add(reportCVo.getCountNum());
			}
		}
		
		//统计所有项目楼板面积
		BigDecimal sumFloorArea = projectService.sumAllProjectFloorArea();
		if(sumCount == null  || sumFloorArea == null || diffMonth == 0) {
			return BigDecimal.valueOf(0);
		}
		System.out.println( "diffMonth:"+diffMonth +" sumCount:"+sumCount +" sumFloorArea："+sumFloorArea);
		return sumCount.divide(sumFloorArea,2,BigDecimal.ROUND_UP).divide(BigDecimal.valueOf(diffMonth),2,BigDecimal.ROUND_UP);
	}
	

	@Override
	public List<ReportCVo> countAlarmReportCThree(ReportCQo reportCQo) {
		if(StringUtils.isNotBlank(reportCQo.getAppSourceCodes())){
			reportCQo.setAppSourceCodeArray(StringUtils.split(reportCQo.getAppSourceCodes(), ","));
    	}
		return alarmRecordMapperExt.countAlarmReportCThree(reportCQo);
	}

	@Override
	public List<AlarmVo> findReportAAlarmListByQuery(ReportAListQo reportAListQo) {
		if(StringUtils.isNotBlank(reportAListQo.getAppSourceCodes())){
			reportAListQo.setAppSourceCodeArray(StringUtils.split(reportAListQo.getAppSourceCodes(), ","));
    	}
		return alarmRecordMapperExt.findAlarmListByReportA(reportAListQo);
	}

	@Override
	public PageInfo<AlarmVo> findReportAAlarmPage(ReportAPageQo reportAPageQo) throws Exception {
		 PageHelper.startPage(reportAPageQo.getPageNum(), reportAPageQo.getPageSize());
		    ReportAListQo reportAListQo = new ReportAListQo();
	        BeanUtils.copyProperties(reportAListQo, reportAPageQo);
	        List<AlarmVo> list = findReportAAlarmListByQuery(reportAListQo);
	        return new PageInfo<>(list);
	}

	@Override
	public PageInfo<AlarmVo> findAlarmPageByReportB(ReportBPageQo reportBPageQo) throws Exception {
		   PageHelper.startPage(reportBPageQo.getPageNum(), reportBPageQo.getPageSize());
		   ReportBPageQo reportBListQo = new ReportBPageQo();
	        BeanUtils.copyProperties(reportBListQo, reportBPageQo);
	        List<AlarmVo> list = findAlarmListByReportB(reportBListQo);
	        return new PageInfo<>(list);
	}
	
	@Override
	public List<AlarmVo> findAlarmListByReportB(ReportBListQo reportBListQo) {
		if(StringUtils.isNotBlank(reportBListQo.getAppSourceCodes())){
			reportBListQo.setAppSourceCodeArray(StringUtils.split(reportBListQo.getAppSourceCodes(), ","));
    	}
		return alarmRecordMapperExt.findAlarmListByReportB(reportBListQo);
	}

	@Override
	public PageInfo<AlarmVo> findAlarmPageByReportC(ReportCPageQo reportCPageQo) throws Exception {
		 PageHelper.startPage(reportCPageQo.getPageNum(), reportCPageQo.getPageSize());
		 ReportCListQo reportCListQo = new ReportCListQo();
	        BeanUtils.copyProperties(reportCListQo, reportCPageQo);
	        List<AlarmVo> list = findAlarmListByReportC(reportCListQo);
	        return new PageInfo<>(list);
	}

	@Override
	public List<AlarmVo> findAlarmListByReportC(ReportCListQo reportCListQo) {
		if(StringUtils.isNotBlank(reportCListQo.getAppSourceCodes())){
			reportCListQo.setAppSourceCodeArray(StringUtils.split(reportCListQo.getAppSourceCodes(), ","));
    	}
		return alarmRecordMapperExt.findAlarmListByReportC(reportCListQo);
	}

	@Override
	public void save(AlarmRecord alarmRecord) {
		alarmRecordMapperExt.insert(alarmRecord);
	}

	@Override
	public int updateAlarmRecordByCondition(String sysProjectCode, String deviceType, String cmptMac,String deviceId) {
		AlarmRecordExample example = new AlarmRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andSysProjectCodeEqualTo(sysProjectCode);
		criteria.andDeviceTypeEqualTo(deviceType);
		if(StringUtils.isNoneBlank(deviceId)){
			criteria.andDeviceIdEqualTo(deviceId);
		}else{
			criteria.andCmptMacEqualTo(cmptMac);
		}
		criteria.andDataTypeEqualTo(0);//报警中
		criteria.andAlarmStateEqualTo(0L);
		AlarmRecord record = new AlarmRecord();
		record.setAlarmState(1L);//进行归档
		record.setModifyTime(new Date());
		return alarmRecordMapperExt.updateByExampleSelective(record, example);
	}

	@Override
	public void sendNotifyByProjectId(String title,String content,Long projectId) {
		List<AccountShortVo> sendList = new ArrayList<>();
		/***
		 * 获取项目负责人
		 */
		List<AccountShortVo> projectList = projectAccountService.getProjectAcountList(projectId);
		if(CollectionUtils.isNotEmpty(projectList)){
			sendList.addAll(projectList);
		}
		/***
		 * 获取业务组负责人
		 */
		List<AccountShortVo> businessGroupAccountList = businessGroupAccountService.getBusinessGroupAcountList(projectId);
		if(CollectionUtils.isNotEmpty(businessGroupAccountList)){
			sendList.addAll(businessGroupAccountList);
		}
		/**
		 * 获取一级用户
		 */
		List<AccountShortVo> levelOneAccountList = accountService.getShortAcountList(RoleEnum.LEVEL_ONE.getCode(), null);
		if(CollectionUtils.isNotEmpty(levelOneAccountList)){
			sendList.addAll(levelOneAccountList);
		}
		/**
		 *  发送邮件通知用户
		 */
		pushModelService.notifyEmailToAccount(title, content,sendList);
	}
	
}
