package com.zifisense.jll.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zifisense.jll.common.response.CommonResponse;
import com.zifisense.jll.dto.CaptchaDTO;
import com.zifisense.jll.service.CaptchaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 验证码信息管理接口
 * Created by DW on 2017/6/28.
 */
@Api(description = "验证码信息管理接口")
@RestController
@RequestMapping("/captchas")
public class CaptchaController {
    private Logger logger = LoggerFactory.getLogger(CaptchaController.class);
    @Autowired
    private CaptchaService captchaService;

    /**
     * 新增验证码
     * @param captchaDTO captchaDTO
     * @return 请求响应
     */
    @ApiOperation(value = "新增验证码", notes = "新增验证码")
    @RequestMapping(method = RequestMethod.POST)
    public CommonResponse addSysUser(@RequestBody CaptchaDTO captchaDTO) {
        return CommonResponse.success(captchaService.add(captchaDTO));
    }

    @ApiOperation(value = "校验验证码", notes = "校验验证码")
    @RequestMapping(method = RequestMethod.PUT)
    public CommonResponse validation(@RequestBody CaptchaDTO captchaDTO,Device device){
        return CommonResponse.success(captchaService.validation(captchaDTO,device));
    }



}
