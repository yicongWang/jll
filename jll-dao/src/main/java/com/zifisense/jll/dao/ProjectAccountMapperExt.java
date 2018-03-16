package com.zifisense.jll.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zifisense.jll.vo.AccountShortVo;

/**
 * 项目账号关联
 * @author wyc
 *
 */
@Mapper
public interface ProjectAccountMapperExt extends ProjectAccountMapper{

   int countProjectByAccountId(@Param(value="accountId") Long accountId);
   
   /**
    * 获取负责该项目的用户
    * @param projectId
    * @return
    */    
   List<AccountShortVo> getProjectAcountList(@Param(value="projectId") Long projectId);
}
