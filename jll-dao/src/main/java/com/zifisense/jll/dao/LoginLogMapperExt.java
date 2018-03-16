package com.zifisense.jll.dao;

import com.zifisense.jll.dto.LoginLogDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 登录日志Mapper
 * Created by DW on 2017/6/28.
 */
@Mapper
public interface LoginLogMapperExt extends LoginLogMapper{


    /**
     * 查询登录日志集合
     * @param loginLog
     * @return
     */
    List<LoginLogDTO> findDTO(LoginLogDTO loginLog);
}
