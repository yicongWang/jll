package com.zifisense.jll.dao;

import com.zifisense.jll.dto.OperationLogDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作日志Mapper
 * Created by DW on 2017/6/27.
 */
@Mapper
public interface OperationLogMapperExt extends OperationLogMapper{


    /**
     * 根据查询条件查询操作日志对象集合
     * @param operationLogDTO   操作日志对象
     * @return
     */
    List<OperationLogDTO> find(OperationLogDTO operationLogDTO);
}
