package com.zifisense.jll.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zifisense.jll.common.response.CommonResponse;
import com.zifisense.jll.common.response.PageResponse;
import com.zifisense.jll.common.response.ResponseCode;
import com.zifisense.jll.controller.common.AccessRequired;
import com.zifisense.jll.qo.AlarmQo;
import com.zifisense.jll.qo.ReportAPageQo;
import com.zifisense.jll.qo.ReportBPageQo;
import com.zifisense.jll.qo.ReportBQo;
import com.zifisense.jll.qo.ReportCPageQo;
import com.zifisense.jll.qo.ReportCQo;
import com.zifisense.jll.service.AlarmRecordService;
import com.zifisense.jll.service.common.log.OperationLogType;
import com.zifisense.jll.service.common.log.OperationnLogAnnotation;
import com.zifisense.jll.util.DateUtil;
import com.zifisense.jll.vo.ReportAVo;
import com.zifisense.jll.vo.ReportBVo;
import com.zifisense.jll.vo.ReportCVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 报表接口
 * @author wyc
 *
 */
@AccessRequired
@Api(description = "报表接口")
@RestController
@RequestMapping("/report")
public class ReportController {
    private Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private  AlarmRecordService alarmRecordService;
    
    @ApiOperation(value = "单项目告警统计报表A-1")
    @RequestMapping(value = "/countAlarmReportAOne", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "单项目告警统计报表A-1", moduleName = "单项目告警统计报表A-1",operationLogType = OperationLogType.QUERY)
    public CommonResponse countAlarmReportAOne(@RequestHeader String access_token,AlarmQo alarmQo) {
    	 String year = DateUtil.dateToStr(new Date(), "yyyy");
	   	 if(StringUtils.isBlank(alarmQo.getStartYm()) ){
	   		  alarmQo.setStartYm(year+"01");
	   	 }
	   	 if(StringUtils.isBlank(alarmQo.getEndYm())){
	   		 alarmQo.setEndYm(year+"12");
	   	 }
    	return CommonResponse.success(alarmRecordService.countAlarmReportAOne(alarmQo));
    }
    
    @ApiOperation(value = "单项目告警统计报表A-2")
    @RequestMapping(value = "/countAlarmReportATwo", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "单项目告警统计报表A-2", moduleName = "单项目告警统计报表A-2",operationLogType = OperationLogType.QUERY)
    public CommonResponse countAlarmReportATwo(@RequestHeader String access_token,AlarmQo alarmQo) {
    	String year = DateUtil.dateToStr(new Date(), "yyyy");
	   	 if(StringUtils.isBlank(alarmQo.getStartYm()) ){
	   		  alarmQo.setStartYm(year+"01");
	   	 }
	   	 if(StringUtils.isBlank(alarmQo.getEndYm())){
	   		 alarmQo.setEndYm(year+"12");
	   	 }
    	List<ReportAVo> reportList = alarmRecordService.countAlarmReportATwo(alarmQo);
    	alarmQo.setStartYm(Integer.valueOf(alarmQo.getStartYm())-100+"");
 		alarmQo.setEndYm(Integer.valueOf(alarmQo.getEndYm())-100+"");
    	BigDecimal baseLine =  alarmRecordService.getAlarmReportATwoBaseLine(alarmQo);
    	Map<String,Object> map = new HashMap<>();
    	map.put("dataArray", reportList);
    	map.put("baseLine", baseLine);
        return CommonResponse.success(map);
    }
    
    @ApiOperation(value = "单项目告警统计报表A-3")
    @RequestMapping(value = "/countAlarmReportAThree", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "单项目告警统计报表A-3", moduleName = "单项目告警统计报表A-3",operationLogType = OperationLogType.QUERY)
    public CommonResponse countAlarmReportAThree(@RequestHeader String access_token,AlarmQo alarmQo) {
    	 String year = DateUtil.dateToStr(new Date(), "yyyy");
    	 if(StringUtils.isBlank(alarmQo.getStartYm()) ){
    		  alarmQo.setStartYm(year+"01");
    	 }
    	 if(StringUtils.isBlank(alarmQo.getEndYm())){
    		 alarmQo.setEndYm(year+"12");
    	 }
    	Map<String,Object> map = new HashMap<>();
    	List<ReportAVo> dataList = alarmRecordService.countAlarmReportAOne(alarmQo);
    	alarmQo.setStartYm(Integer.valueOf(alarmQo.getStartYm())-100+"");
 		alarmQo.setEndYm(Integer.valueOf(alarmQo.getEndYm())-100+"");
     	List<ReportAVo> lastDataList = alarmRecordService.countAlarmReportAOne(alarmQo);
    	map.put("dataArray", dataList);
    	map.put("lastDataArray",lastDataList);
        return CommonResponse.success(map);
    }
    
    
    @ApiOperation(value = "多项目告警统计报表B-1")
    @RequestMapping(value = "/countAlarmReportBOne", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "多项目告警统计报表B-1", moduleName = "多项目告警统计报表B-1",operationLogType = OperationLogType.QUERY)
    public CommonResponse countAlarmReportBOne(@RequestHeader String access_token,ReportBQo reportBQo) {
       	Map<String,Object> map = new HashMap<>();
        String year = DateUtil.dateToStr(new Date(), "yyyy");
	   	 if(StringUtils.isBlank(reportBQo.getStartYm()) ){
	   		reportBQo.setStartYm(year+"01");
	   	 }
	   	 if(StringUtils.isBlank(reportBQo.getEndYm())){
	   		reportBQo.setEndYm(year+"12");
	   	 }
    	List<ReportBVo> dataReportList = alarmRecordService.countAlarmReportBOne(reportBQo);
    	map.put("dataArray", dataReportList);
    	reportBQo.setStartYm(Integer.valueOf(reportBQo.getStartYm())-100+"");
    	reportBQo.setEndYm(Integer.valueOf(reportBQo.getEndYm())-100+"");
 		List<ReportBVo> lastDataReportList = alarmRecordService.countAlarmReportBOne(reportBQo);
    	map.put("lastDataArray",lastDataReportList);
    	return CommonResponse.success(map);
    	
    }
    
    @ApiOperation(value = "多项目告警统计报表B-2")
    @RequestMapping(value = "/countAlarmReportBTwo", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "多项目告警统计报表B-2", moduleName = "多项目告警统计报表B-2",operationLogType = OperationLogType.QUERY)
    public CommonResponse countAlarmReportBTwo(@RequestHeader String  access_token,ReportBQo reportBQo) {
    	String year = DateUtil.dateToStr(new Date(), "yyyy");
	   	 if(StringUtils.isBlank(reportBQo.getStartYm()) ){
	   		reportBQo.setStartYm(year+"01");
	   	 }
	   	 if(StringUtils.isBlank(reportBQo.getEndYm())){
	   		reportBQo.setEndYm(year+"12");
	   	 }
    	Map<String,Object> map = new HashMap<>();
    	map.put("dataArray", alarmRecordService.countAlarmReportBTwo(reportBQo));
    	map.put("baseLine",alarmRecordService.getAlarmReportBTwoBaseLine(reportBQo));
        return CommonResponse.success(map);
    }
    
    @ApiOperation(value = "多项目告警统计报表B-3")
    @RequestMapping(value = "/countAlarmReportBThree", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "多项目告警统计报表B-3", moduleName = "多项目告警统计报表B-3",operationLogType = OperationLogType.QUERY)
    public CommonResponse countAlarmReportBThree(@RequestHeader String access_token,ReportBQo reportBQo) {
    	String year = DateUtil.dateToStr(new Date(), "yyyy");
	   	 if(StringUtils.isBlank(reportBQo.getStartYm()) ){
	   		reportBQo.setStartYm(year+"01");
	   	 }
	   	 if(StringUtils.isBlank(reportBQo.getEndYm())){
	   		reportBQo.setEndYm(year+"12");
	   	 }
    	Map<String,Object> map = new HashMap<>();
    	List<ReportAVo> reportList = alarmRecordService.countAlarmReportBThree(reportBQo);
    	map.put("dataArray", reportList);
        return CommonResponse.success(map);
    }
    
    
    @ApiOperation(value = "C组报表C-1")
    @RequestMapping(value = "/countAlarmReportCOne", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "C组报表C-1", moduleName = "C组报表C-1",operationLogType = OperationLogType.QUERY)
    public CommonResponse countAlarmReportCOne(@RequestHeader String access_token,ReportCQo reportCQo) {
    	String year = DateUtil.dateToStr(new Date(), "yyyy");
	   	 if(StringUtils.isBlank(reportCQo.getStartYm()) ){
	   		reportCQo.setStartYm(year+"01");
	   	 }
	   	 if(StringUtils.isBlank(reportCQo.getEndYm())){
	   		reportCQo.setEndYm(year+"12");
	   	 }
    	Map<String,Object> map = new HashMap<>();
    	List<ReportCVo> reportList = alarmRecordService.countAlarmReportCOne(reportCQo);
    	map.put("dataArray", reportList);
        return CommonResponse.success(map);
    }
    
    @ApiOperation(value = "C组报表C-2")
    @RequestMapping(value = "/countAlarmReportCTwo", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "C组报表C-2", moduleName = "C组报表C-2",operationLogType = OperationLogType.QUERY)
    public CommonResponse countAlarmReportCTwo(@RequestHeader String access_token,ReportCQo reportCQo) {
    	String year = DateUtil.dateToStr(new Date(), "yyyy");
	   	 if(StringUtils.isBlank(reportCQo.getStartYm()) ){
	   		reportCQo.setStartYm(year+"01");
	   	 }
	   	 if(StringUtils.isBlank(reportCQo.getEndYm())){
	   		reportCQo.setEndYm(year+"12");
	   	 }
	 	List<ReportCVo> reportList = alarmRecordService.countAlarmReportCTwo(reportCQo);
	 	Map<String,Object> map = new HashMap<>();
	 	map.put("dataArray", reportList);
	 	map.put("baseLine", alarmRecordService.getAlarmReportCTwoBaseLine(reportCQo));
	     return CommonResponse.success(map);
    }
  
    @ApiOperation(value = "C组报表C-3")
    @RequestMapping(value = "/countAlarmReportCThree", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "C组报表C-3", moduleName = "C组报表C-3",operationLogType = OperationLogType.QUERY)
    public CommonResponse countAlarmReportCThree(@RequestHeader String access_token,ReportCQo reportCQo) {
    	String year = DateUtil.dateToStr(new Date(), "yyyy");
	   	 if(StringUtils.isBlank(reportCQo.getStartYm()) ){
	   		reportCQo.setStartYm(year+"01");
	   	 }
	   	 if(StringUtils.isBlank(reportCQo.getEndYm())){
	   		reportCQo.setEndYm(year+"12");
	   	 }
    	Map<String,Object> map = new HashMap<>();
    	List<ReportCVo> reportList = alarmRecordService.countAlarmReportCThree(reportCQo);
    	reportCQo.setStartYm(Integer.valueOf(reportCQo.getStartYm())-100+"");
    	reportCQo.setEndYm(Integer.valueOf(reportCQo.getEndYm())-100+"");
     	List<ReportCVo> lastDataList = alarmRecordService.countAlarmReportCThree(reportCQo);
    	map.put("dataArray", reportList);
    	map.put("lastDataArray",lastDataList);
        return CommonResponse.success(map);
    }
    
    @ApiOperation(value = "查看A组报表数据")
    @RequestMapping(value = "/findAlarmPageByReportA", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "查看A组报表数据", moduleName = "查看A组报表数据",operationLogType = OperationLogType.QUERY)
    public PageResponse findAlarmPageByReportA(@RequestHeader String access_token,ReportAPageQo reportAPageQo) throws Exception {
    	PageResponse response = new PageResponse(ResponseCode.SUCCESS, "OK");
        response.setData(alarmRecordService.findReportAAlarmPage(reportAPageQo));
        return response;
    }
  
    
    @ApiOperation(value = "查看B组报表数据")
    @RequestMapping(value = "/findAlarmPageByReportB", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "查看B组报表数据", moduleName = "查看B组报表数据",operationLogType = OperationLogType.QUERY)
    public PageResponse findAlarmPageByReportB(@RequestHeader String access_token,ReportBPageQo reportBPageQo) throws Exception {
    	PageResponse response = new PageResponse(ResponseCode.SUCCESS, "OK");
        response.setData(alarmRecordService.findAlarmPageByReportB(reportBPageQo));
        return response;
    }
    
    @ApiOperation(value = "查看C组报表数据")
    @RequestMapping(value = "/findAlarmPageByReportC", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "查看C组报表数据", moduleName = "查看C组报表数据",operationLogType = OperationLogType.QUERY)
    public PageResponse findAlarmPageByReportB(@RequestHeader String access_token,ReportCPageQo reportCPageQo) throws Exception {
    	PageResponse response = new PageResponse(ResponseCode.SUCCESS, "OK");
        response.setData(alarmRecordService.findAlarmPageByReportC(reportCPageQo));
        return response;
    }
}
