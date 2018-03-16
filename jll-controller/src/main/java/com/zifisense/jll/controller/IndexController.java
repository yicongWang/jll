package com.zifisense.jll.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zifisense.jll.common.response.CommonResponse;
import com.zifisense.jll.controller.common.AccessRequired;
import com.zifisense.jll.service.PushModelService;
import com.zifisense.jll.service.common.log.OperationLogType;
import com.zifisense.jll.service.common.log.OperationnLogAnnotation;
import com.zifisense.jll.vo.IndexVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 警告推送控制器
 * @author wyc
 *
 */
@AccessRequired
@Api(description = "首页管理接口")
@RestController
@RequestMapping("/index")
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);
    
    @ApiOperation(value = "获取统计数据")
    @RequestMapping(value = "/findDataCount", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "获取统计数据", moduleName = "获取统计数据",operationLogType = OperationLogType.QUERY)
    public CommonResponse findDataCount(@RequestHeader String access_token){
    	    IndexVo data = new IndexVo();
    	    data.setCurrentAlarmCount(10);
    	    data.setHistoryAlarmCount(100);
    	    data.setProjectCount(6);
    		return CommonResponse.success(data);
    }
    
}
