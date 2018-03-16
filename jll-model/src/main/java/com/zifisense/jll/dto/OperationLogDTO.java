package com.zifisense.jll.dto;

import com.zifisense.jll.common.jsonserializer.YMDHMSDateSerializer;
import com.zifisense.jll.model.OperationLog;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * 操作日志扩展DTO
 * Created by DW on 2017/6/27.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperationLogDTO extends OperationLog {


    @JsonSerialize(using = YMDHMSDateSerializer.class)
    @Override
    public Date getOperationTime() {
        return super.getOperationTime();
    }




}
