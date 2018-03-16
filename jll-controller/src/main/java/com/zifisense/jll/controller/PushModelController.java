package com.zifisense.jll.controller;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zifisense.jll.common.exception.DataNotExistsException;
import com.zifisense.jll.common.response.CommonResponse;
import com.zifisense.jll.controller.common.AccessRequired;
import com.zifisense.jll.controller.common.CurrentRedisUserData;
import com.zifisense.jll.dto.RedisUserData;
import com.zifisense.jll.service.PushModelService;
import com.zifisense.jll.service.common.log.OperationLogType;
import com.zifisense.jll.service.common.log.OperationnLogAnnotation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 警告推送控制器
 * @author wyc
 *
 */
@AccessRequired
@Api(description = "警告推送模式管理接口")
@RestController
@RequestMapping("/push")
public class PushModelController {
    private Logger logger = LoggerFactory.getLogger(PushModelController.class);
    @Autowired
    private PushModelService pushModelService;
    
    @ApiOperation(value = "获取警告模式信息")
    @RequestMapping(value = "/findPushModel", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "获取警告模式信息", moduleName = "获取警告模式信息",operationLogType = OperationLogType.QUERY)
    public CommonResponse findPushModel(@RequestHeader String access_token){
    		return CommonResponse.success(pushModelService.queryPushModelList());
    }

    
    @ApiOperation(value = "设置警告模式")
    @RequestMapping(value = "/setPushModel", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "设置警告模式", moduleName = "设置警告模式",operationLogType = OperationLogType.UPDATE)
    public CommonResponse setPushModel(@RequestBody Map map,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData){
	    	Long id = MapUtils.getLong(map, "id");
	    	Integer state = MapUtils.getInteger(map, "state");
	    	if(null == id || null == state){
	    		new DataNotExistsException("参数为空");
	    	}
	    	pushModelService.updatePushModel(id,state,redisUserData.getId());
    		//先更新在查询
    		return CommonResponse.success(pushModelService.queryPushModelList());
    }
    
    
    
}
