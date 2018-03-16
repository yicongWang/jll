package com.zifisense.jll.service.impl;

import com.zifisense.jll.dao.OperationLogMapperExt;
import com.zifisense.jll.dto.OperationLogDTO;
import com.zifisense.jll.model.OperationLog;
import com.zifisense.jll.model.OperationLogExample;
import com.zifisense.jll.service.OperationLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 操作日志ServiceImpl
 * Created by DW on 2017/6/27.
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {
    @Autowired
    private OperationLogMapperExt operationLogMapperExt;

    @Override
    public int add(OperationLog operationLog) {
        return operationLogMapperExt.insertSelective(operationLog);
    }

    @Override
    public List<OperationLog> find(OperationLog operationLog) {
        OperationLogExample operationLogExample = new OperationLogExample();
        OperationLogExample.Criteria criteria = operationLogExample.createCriteria();
        if(operationLog!=null){
            if(!StringUtils.isEmpty(operationLog.getOperationType())){
                criteria.andOperationTypeEqualTo(operationLog.getOperationType());
            }

            if(!StringUtils.isEmpty(operationLog.getOperationDesc())){
                criteria.andOperationDescLike("%"+operationLog.getOperationDesc()+"%");
            }
            if (!StringUtils.isEmpty(operationLog.getTerminalType())) {
                criteria.andTerminalTypeEqualTo(operationLog.getTerminalType());
            }

        }
        return operationLogMapperExt.selectByExample(operationLogExample);
    }

    @Override
    public PageInfo<OperationLogDTO> findPage(OperationLogDTO operationLogDTO, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(operationLogMapperExt.find(operationLogDTO));
    }
}
