package com.zifisense.jll.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zifisense.jll.common.response.CommonResponse;
import com.zifisense.jll.controller.common.AccessRequired;
import com.zifisense.jll.service.AppSourceService;
import com.zifisense.jll.service.common.log.OperationLogType;
import com.zifisense.jll.service.common.log.OperationnLogAnnotation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 对接系统控制器
 * @author wyc
 *
 */
@AccessRequired
@Api(description = "对接系统管理接口")
@RestController
@RequestMapping("/sys")
public class SysAppSourceController {

    private Logger logger = LoggerFactory.getLogger(SysAppSourceController.class);

    @Autowired private AppSourceService  appSourceService;
    
    @ApiOperation(value = "获取对接系统列表")
    @RequestMapping(value = "/getSysSourceList", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "获取对接系统列表", moduleName = "获取对接系统列表",operationLogType = OperationLogType.QUERY)
    public CommonResponse getSysSourceList(@RequestHeader String access_token) {
        return CommonResponse.success(appSourceService.getALLAppSourceInfoList());
    }

    
 /*   @ApiOperation(value = "修改项目系统对接码")
    @RequestMapping(value = "/updateProjectSysSourceCode", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "修改项目系统对接码", moduleName = "修改项目系统对接码",operationLogType = OperationLogType.UPDATE)
    public CommonResponse updateProjectSysSourceCode(@RequestHeader String access_token,Long projectId,String oldCode,String newCode) {
        return CommonResponse.success();
    }*/
}
