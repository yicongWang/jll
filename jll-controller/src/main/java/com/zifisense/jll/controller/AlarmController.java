 package com.zifisense.jll.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zifisense.jll.common.exception.DataNotExistsException;
import com.zifisense.jll.common.response.CommonResponse;
import com.zifisense.jll.common.response.PageResponse;
import com.zifisense.jll.common.response.ResponseCode;
import com.zifisense.jll.controller.common.AccessRequired;
import com.zifisense.jll.controller.common.CurrentRedisUserData;
import com.zifisense.jll.dto.RedisUserData;
import com.zifisense.jll.qo.AlarmPageQo;
import com.zifisense.jll.qo.AlarmQo;
import com.zifisense.jll.service.AlarmRecordService;
import com.zifisense.jll.service.common.RoleEnum;
import com.zifisense.jll.service.common.log.OperationLogType;
import com.zifisense.jll.service.common.log.OperationnLogAnnotation;
import com.zifisense.jll.util.DateUtil;
import com.zifisense.jll.util.ExportUtil;
import com.zifisense.jll.vo.AlarmVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 警告推送控制器
 * @author wyc
 *
 */
@AccessRequired
@Api(description = "警告接口")
@RestController
@RequestMapping("/alarm")
public class AlarmController {
    private Logger logger = LoggerFactory.getLogger(AlarmController.class);
    @Autowired
    private  AlarmRecordService alarmRecordService;
    
    @ApiOperation(value = "分页获取当前告警列表")
    @RequestMapping(value = "/findCurrentAlarmPage", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "分页获取当前告警列表", moduleName = "分页获取当前告警列表",operationLogType = OperationLogType.QUERY)
    public PageResponse findCurrentAlarmPage(AlarmPageQo alarmPageQo,  @RequestHeader String access_token) throws Exception{
        PageResponse response = new PageResponse(ResponseCode.SUCCESS, "OK");
        response.setData(alarmRecordService.findCurrentAlarmPage(alarmPageQo));
    	return response;
    }
    
    @AccessRequired
    @ApiOperation(value = "告警归档 ")
    @RequestMapping(value = "/updateAlarmState", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "告警归档", moduleName = "告警归档",operationLogType = OperationLogType.UPDATE)
    public CommonResponse updateAlarmState(@RequestBody Map ids ,  @RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) throws Exception{
    	CommonResponse response = new CommonResponse(ResponseCode.SUCCESS, "OK");
    	String idsStr = MapUtils.getString(ids, "ids");
    	if(StringUtils.isBlank(idsStr)){
    		new DataNotExistsException("参数为空");
    	}
    	alarmRecordService.updateAlarmState(idsStr,redisUserData.getId());
    	return response;
    }           
    
    @ApiOperation(value = "导出当前告警列表 ")
    @RequestMapping(value = "/exportCurrentAlarmList", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "导出当前告警列表", moduleName = "导出当前告警列表",operationLogType = OperationLogType.EXPROT)
    public void exportCurrentAlarmList(AlarmQo alarmQo,HttpServletResponse response) throws Exception{
    	List<AlarmVo>  list = alarmRecordService.getAlarmListByQuery(alarmQo);
    	ExportUtil.exportExcel(list,"当前告警","当前告警",AlarmVo.class,DateUtil.dateToStr(new Date(), DateUtil.DATE_DEFAULT_FORMAT)+"当前告警列表.xls",response);
    }
    
    @ApiOperation(value = "分页获取历史告警列表")
    @RequestMapping(value = "/findHistoryAlarmPage", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "分页获取历史告警列表", moduleName = "分页获取历史告警列表",operationLogType = OperationLogType.QUERY)
    public PageResponse findHistoryAlarmPage(AlarmPageQo alarmPageQo,  @RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) throws Exception{
        PageResponse response = new PageResponse(ResponseCode.SUCCESS, "OK");
        //只有超管才有操作常规数据
        if(!StringUtils.equals(RoleEnum.ADMIN.getCode(), redisUserData.getRoleIdentity())){
        	alarmPageQo.setDataType(0);//如果不是超级管理员只能查询报警数据
        }
        alarmPageQo.setAlarmState(1);//只找归档后数据，常规数据默认为已归档
        response.setData(alarmRecordService.findHistoryAlarmPage(alarmPageQo));
    	return response;
    }
    
    @ApiOperation(value = "导出历史告警列表 ")
    @RequestMapping(value = "/exportHistoryAlarmList", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "导出历史告警列表", moduleName = "导出历史告警列表",operationLogType = OperationLogType.EXPROT)
    public void exportHistoryAlarmList(AlarmQo alarmQo,HttpServletResponse response,@CurrentRedisUserData RedisUserData redisUserData) throws Exception{
    	alarmQo.setAlarmState(1);//只找归档后数据，常规数据默认为已归档
    	if(!StringUtils.equals(RoleEnum.ADMIN.getCode(), redisUserData.getRoleIdentity())){
    		  alarmQo.setDataType(0);//如果不是超级管理员只能查询报警数据
        }
      	List<AlarmVo>  list = alarmRecordService.getAlarmListByQuery(alarmQo);
    	ExportUtil.exportExcel(list,"历史告警","历史告警",AlarmVo.class,DateUtil.dateToStr(new Date(), DateUtil.DATE_DEFAULT_FORMAT)+"历史告警列表.xls",response);
    }
}
