package com.zifisense.jll.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zifisense.jll.common.exception.DataNotExistsException;
import com.zifisense.jll.common.response.CommonResponse;
import com.zifisense.jll.common.response.PageResponse;
import com.zifisense.jll.common.response.ResponseCode;
import com.zifisense.jll.controller.common.AccessRequired;
import com.zifisense.jll.controller.common.CurrentRedisUserData;
import com.zifisense.jll.dto.RedisUserData;
import com.zifisense.jll.qo.BusinessGroupAddQo;
import com.zifisense.jll.qo.BusinessGroupPageQo;
import com.zifisense.jll.qo.BusinessGroupQo;
import com.zifisense.jll.service.BusinessGroupService;
import com.zifisense.jll.service.common.log.OperationLogType;
import com.zifisense.jll.service.common.log.OperationnLogAnnotation;
import com.zifisense.jll.util.DateUtil;
import com.zifisense.jll.util.ExportUtil;
import com.zifisense.jll.vo.BusinessGroupNameVo;
import com.zifisense.jll.vo.BusinessGroupVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 业务组控制器
 * @author wyc
 *
 */
@AccessRequired
@Api(description = "业务组接口")
@RestController
@RequestMapping("/bussiessGroup")
public class BussiessGroupController {

    private Logger logger = LoggerFactory.getLogger(BussiessGroupController.class);

    @Autowired
    private BusinessGroupService businessGroupService;
    
    
    @ApiOperation(value = "分页获取业务组列表")
    @RequestMapping(value = "/findBussiessGroupInfoPage", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "分页获取业务组列表", moduleName = "分页获取业务组列表",operationLogType = OperationLogType.QUERY)
    public PageResponse findBussiessGroupInfoPage(BusinessGroupPageQo businessGroupPageQo,@RequestHeader String access_token) throws Exception{
    	 PageResponse response = new PageResponse(ResponseCode.SUCCESS, "OK");
         response.setData(businessGroupService.findBusinessGroupInfoListPage(businessGroupPageQo));
     	return response;
    	
    }
    
    @ApiOperation(value = "添加业务组")
    @RequestMapping(value = "/addBussiessGroup", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "添加业务组", moduleName = "添加业务组",operationLogType = OperationLogType.ADD)
    public CommonResponse addBussiessGroup(@RequestBody BusinessGroupAddQo businessGroupaAddQo ,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) {
        return CommonResponse.success(businessGroupService.saveBusinessGroup(businessGroupaAddQo, redisUserData.getId()));
    }
    
    @ApiOperation(value = "修改业务组")
    @RequestMapping(value = "/updateBussiessGroup", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "修改业务组", moduleName = "修改业务组",operationLogType = OperationLogType.UPDATE)
    public CommonResponse updateBussiessGroup(@RequestBody BusinessGroupAddQo businessGroupaAddQo ,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) {
    	 return CommonResponse.success(businessGroupService.updateBusinessGroup(businessGroupaAddQo, redisUserData.getId()));
    }
    
    @ApiOperation(value = "删除业务组")
    @RequestMapping(value = "/removeBussiessGroup", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "删除业务组", moduleName = "删除业务组",operationLogType = OperationLogType.DELTE)
    public CommonResponse removeBussiessGroup(@RequestBody Map ids ,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) {
    	String idsStr = MapUtils.getString(ids, "ids");
    	if(StringUtils.isBlank(idsStr)){
    		new DataNotExistsException("参数为空");
    	}
    	businessGroupService.removeBussiessGroup(idsStr, redisUserData.getId());
        return CommonResponse.success();
    }

    @ApiOperation(value = "获取业务组简称列表")
    @RequestMapping(value = "/getShortBussiessGroupList", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "获取业务组简称列表", moduleName = "获取业务组简称列表",operationLogType = OperationLogType.QUERY)
    public CommonResponse getShortBussiessGroupList(String keys,@RequestHeader String access_token) {
        return CommonResponse.success(businessGroupService.getShortBussiessGroupList(keys));
    }
    
    @ApiOperation(value = "导出")
    @RequestMapping(value = "/exportBussiessGroupInfoList", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "业务组列表导出", moduleName = "业务组列表导出",operationLogType = OperationLogType.EXPROT)
    public void exportBussiessGroupInfoList(BusinessGroupQo businessGroupQo,HttpServletResponse response) throws IOException {
     	List<BusinessGroupVo>  list = businessGroupService.getBusinessGroupListByQuery(businessGroupQo);
    	ExportUtil.exportExcel(list,"业务组信息","业务组信息",BusinessGroupVo.class,DateUtil.dateToStr(new Date(), DateUtil.DATE_DEFAULT_FORMAT)+"业务组信息.xls",response);
    }
    
    @ApiOperation(value = "下载模板")
    @RequestMapping(value = "/download/template", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "业务组列表下载模板", moduleName = "业务组列表下载模板",operationLogType = OperationLogType.DOWNLOAD)
    public CommonResponse template(HttpServletResponse response) throws IOException {
    	 CommonResponse commonResponse = new CommonResponse();
    	 ExportUtil.exportExcel(Arrays.asList(), null,  "业务组信息", BusinessGroupNameVo.class, "业务组信息模板.xls", true, response);   
    	 return commonResponse;
    }
    
    @ApiOperation(value = "导入")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "业务组列表导入", moduleName = "业务组列表导入",operationLogType = OperationLogType.UPLOAD)
    public CommonResponse upload(@RequestParam("file") MultipartFile file,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) throws Exception {
    	  if (file.isEmpty()) {
    		  CommonResponse commonResponse = new CommonResponse();
    		  commonResponse.setCode(ResponseCode.IMPORT_FILE_TYPE_ERROR);
			  commonResponse.setMsg("文件格式有误");
    		  return commonResponse;
          }
    	  List<BusinessGroupNameVo> businessGroupVoList = ExportUtil.importExcel(file,0,1,BusinessGroupNameVo.class);
    	  if(CollectionUtils.isNotEmpty(businessGroupVoList)){
    		  businessGroupService.batchUpload(businessGroupVoList, redisUserData.getId());
    	  }else{
    		  CommonResponse commonResponse = new CommonResponse();
    		  commonResponse.setCode(ResponseCode.IMPORT_FILE_TYPE_ERROR);
			  commonResponse.setMsg("文件为空");
    	  }
    	  return CommonResponse.success();
    }
    
    @ApiOperation(value = "根据业务组ID获取业务组信息")
    @RequestMapping(value = "/getBussiessGroupById/{bussiessGroupId}", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "根据业务组ID获取业务组信息", moduleName = "根据业务组ID获取业务组信息",operationLogType = OperationLogType.QUERY)
    public CommonResponse getBussiessGroupById(@PathVariable Long bussiessGroupId ,@RequestHeader String access_token) {
    	BusinessGroupQo  businessGroupQo = new BusinessGroupQo();
    	businessGroupQo.setBussiessGroupId(bussiessGroupId);
        return CommonResponse.success(businessGroupService.getBusinessGroupListByQuery(businessGroupQo));
    }
}
