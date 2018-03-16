package com.zifisense.jll.controller;

import com.zifisense.jll.common.response.CommonResponse;
import com.zifisense.jll.controller.common.AccessRequired;
import com.zifisense.jll.controller.common.CurrentRedisUserData;
import com.zifisense.jll.dto.RedisUserData;
import com.zifisense.jll.service.MenuService;
import com.zifisense.jll.service.common.log.OperationLogType;
import com.zifisense.jll.service.common.log.OperationnLogAnnotation;
import com.zifisense.jll.util.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单控制器
 * @author wyc
 *
 */
@AccessRequired
@Api(description = "菜单信息管理接口")
@RestController
@RequestMapping("/menus")
public class MenuController {

    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;
    
    @Autowired
    private RedisUtil redisUtil;
    
    @ApiOperation(value = "菜单集合")
    @RequestMapping(method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "菜单集合", moduleName = "菜单信息",operationLogType = OperationLogType.QUERY)
    public CommonResponse findMenuList(@CurrentRedisUserData RedisUserData redisUserData,@RequestHeader String access_token){
    	if(redisUserData != null){
    		return CommonResponse.success(redisUserData.getMenuList());
    	}else{
    		return CommonResponse.success(null);
    	}
    	
    }

}
