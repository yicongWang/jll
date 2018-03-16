package com.zifisense.jll.service;

import com.zifisense.jll.dto.LoginLogDTO;
import com.zifisense.jll.model.LoginLog;
import com.github.pagehelper.PageInfo;

/**
 * 登录日志Service
 * Created by DW on 2017/6/28.
 */
public interface LoginLogService {


    /**
     * 新增
     * @param loginLog
     * @return
     */
    int add(LoginLog loginLog);

    /**
     * 分页查询登录日志
     * @param loginLog
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<LoginLogDTO> findPage(LoginLogDTO loginLog, Integer pageNum, Integer pageSize);

}
