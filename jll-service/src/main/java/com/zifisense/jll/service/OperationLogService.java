package com.zifisense.jll.service;

import com.zifisense.jll.dto.OperationLogDTO;
import com.zifisense.jll.model.OperationLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 操作日志Service
 * Created by DW on 2017/6/27.
 */
public interface OperationLogService {

    int add(OperationLog operationLog);

    List<OperationLog> find(OperationLog operationLog);

    PageInfo<OperationLogDTO> findPage(OperationLogDTO operationLog, Integer pageNum, Integer pageSize);


}
