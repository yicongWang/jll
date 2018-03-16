package com.zifisense.jll.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.zifisense.jll.common.response.CommonResponse;
import com.zifisense.jll.model.AlarmRecord;
import com.zifisense.jll.service.AlarmRecordService;
import com.zifisense.jll.service.ProjectSysSourceService;
import com.zifisense.jll.service.cache.CodeTableCache;
import com.zifisense.jll.service.common.CmptStatusEnum;
import com.zifisense.jll.service.common.CodeTableConstant;
import com.zifisense.jll.service.common.SysTypeEnum;
import com.zifisense.jll.util.DateUtil;
import com.zifisense.jll.vo.ProjectSysSourceInfoVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 设备故障上报接口
 * @author wyc
 *
 */
@Api(description = "设备故障上报接口")
@RestController
@RequestMapping("/data/")
public class DeviceDataController {
    private Logger logger = LoggerFactory.getLogger(DeviceDataController.class);
    @Autowired private AlarmRecordService alarmRecordService;
    @Autowired
  	private ProjectSysSourceService projectSysSourceService;
    @Autowired
  	private CodeTableCache codeTableCache;
    @ApiOperation(value = "上报消防设施部件运行状态")
    @RequestMapping(value = "/deviceRunStatusInfo", method = RequestMethod.POST)
    public CommonResponse deviceRunStatusInfo(@RequestBody Map map) throws IllegalAccessException, InvocationTargetException, ParseException{
    	    logger.debug(map.toString());
    	    if(MapUtils.isNotEmpty(map)){
    	    	  ArrayList<Map> cmptItems = (ArrayList<Map>) MapUtils.getObject(map, "cmptItems");
    	    	if(CollectionUtils.isNotEmpty(cmptItems)){
    	    		List<ProjectSysSourceInfoVo>  list = projectSysSourceService.getProjectSysCodeInfoByCode(MapUtils.getString(map, "sn"));
    	    		if(CollectionUtils.isNotEmpty(list)){
    	    			int dataType = 0;
    	    			String sysTypeName = null;
    	    			String cmptStatus = null;
    	    			int updateRecord = 0;
    	    			for(Map dataMap : cmptItems){
    	    				dataType = 0;//默认告警
    	    			    cmptStatus = "";
    	    			    AlarmRecord record = new AlarmRecord();
    	    			    updateRecord = 0;
    	    				if(StringUtils.equals(MapUtils.getString(dataMap, "cmptStatus"), CmptStatusEnum.NORMAL.getCode())){//该部件当前状态为正常，对之前告警信息进行归档
    	    					updateRecord = alarmRecordService.updateAlarmRecordByCondition(MapUtils.getString(map, "sn"), MapUtils.getString(dataMap, "cmptType"), MapUtils.getString(dataMap, "cmptMac"), null);
        	    				dataType = 1;//常规
        	    				cmptStatus = CmptStatusEnum.NORMAL.getRemark();
        	    				record.setModifyTime(new Date());//默认归档日期
    	    				}else if(StringUtils.equals(MapUtils.getString(dataMap, "cmptStatus"), CmptStatusEnum.ABNORMAL.getCode())){
    	    					cmptStatus = CmptStatusEnum.ABNORMAL.getRemark();
    	    				}else if(StringUtils.equals(MapUtils.getString(dataMap, "cmptStatus"), CmptStatusEnum.FIRE.getCode())){
    	    					cmptStatus = CmptStatusEnum.FIRE.getRemark();
    	    				}else if(StringUtils.equals(MapUtils.getString(dataMap, "cmptStatus"), CmptStatusEnum.ISOLATION.getCode())){
    	    					cmptStatus = CmptStatusEnum.ISOLATION.getRemark();
    	    				}
    	    				
    	    				ProjectSysSourceInfoVo projectSysSourceInfoVo = list.get(0);
							BeanUtils.copyProperties(record, projectSysSourceInfoVo);
							sysTypeName = "";
                			if(StringUtils.equals(MapUtils.getString(dataMap, "sysType"), SysTypeEnum.FIRE.getCode())){
                				sysTypeName = SysTypeEnum.FIRE.getName();
                			}else if(StringUtils.equals(MapUtils.getString(dataMap, "sysType"), SysTypeEnum.WATER.getCode())){
                				sysTypeName = SysTypeEnum.WATER.getName();
                			}
    	    				Date currentDate = DateUtil.unixTimeToDate(MapUtils.getIntValue(dataMap, "time"));
                			record.setCreateTime(new Date());         
                			record.setCmptMac(MapUtils.getString(dataMap, "cmptMac"));
                			//组装告警内容
                			String alarmContent = sysTypeName + codeTableCache.getNameByTypeKey(CodeTableConstant.CMPT_NAME, MapUtils.getString(dataMap, "cmptType"))+cmptStatus;
                    		record.setAlarmContent(alarmContent);
                    		//dataFlag:0：正常  1：告警--->DataType 0：告警 1：正常
                    		record.setDataType(dataType);
                    		record.setDeviceType(MapUtils.getString(dataMap, "cmptType"));
                    		//0:告警中 1:解除警报(归档)常规数据默认为归档yyyy-MM-dd
                    		record.setAlarmState(dataType == 1?1L:0L);
                    		record.setAlarmYear(Integer.valueOf(DateUtil.dateToStr(currentDate, "yyyy")));
                    		record.setAlarmMonth(Integer.valueOf(DateUtil.dateToStr(currentDate, "MM")));
                    		record.setAlarmDay(Integer.valueOf(DateUtil.dateToStr(currentDate, "dd")));
                    		record.setAlarmYm(Integer.valueOf(DateUtil.dateToStr(currentDate, "yyyyMM")));
                    		record.setSysProjectCode(MapUtils.getString(map, "sn"));
                    		record.setAlarmTime(currentDate);
                    		record.setAlarmType(MapUtils.getString(dataMap, "cmptStatus"));
                    		alarmRecordService.save(record);
                    		if(updateRecord > 0 || !StringUtils.equals(MapUtils.getString(dataMap, "cmptStatus"), CmptStatusEnum.NORMAL.getCode())){
                    			//有归档或者告警通知相关人员
                    			alarmRecordService.sendNotifyByProjectId("告警通知",projectSysSourceInfoVo.getAppSourceName() +projectSysSourceInfoVo.getProjectName()+ alarmContent, projectSysSourceInfoVo.getProjectId());
                    		}
    	    			}	
    	    		}
    	    		
    	    	}
    			
    	   }
    		//先更新在查询
    		return CommonResponse.success();
    }
    
    @ApiOperation(value = "上报消防设施部件模拟量值")
    @RequestMapping(value = "/deviceAnalogValue", method = RequestMethod.POST)
    public CommonResponse deviceAnalogValue(@RequestBody Map map){
    	    logger.debug(map.toString());
    		//先更新在查询
    		return CommonResponse.success();
    }
    
    @ApiOperation(value = "上报传输装置运行状态")
    @RequestMapping(value = "/transfersRunStatusInfo", method = RequestMethod.POST)
    public CommonResponse transfersRunStatusInfo(@RequestBody Map map){
    	    logger.debug(map.toString());
    		//先更新在查询
    		return CommonResponse.success();
    }
}
