package com.zifisense.jll.dto;

import com.zifisense.jll.common.jsonserializer.YMDHMSDateSerializer;
import com.zifisense.jll.model.LoginLog;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * 登录日志扩展DTO
 * Created by DW on 2017/6/28.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginLogDTO extends LoginLog {


    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;

    @JsonSerialize(using = YMDHMSDateSerializer.class)
    @Override
    public Date getLoginTime() {
        return super.getLoginTime();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
