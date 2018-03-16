package com.zifisense.jll.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zifisense.jll.vo.AccountShortVo;

/**
 * 业务组账号关联
 * @author wyc
 *
 */
@Mapper
public interface BusinessGroupAccountMapperExt extends BusinessGroupAccountMapper {
		/**
		 * 获取项目归宿业务组的负责人
		 * @param projectId
		 * @return
		 */
	  List<AccountShortVo> getBusinessGroupAcountList(@Param(value="projectId") Long projectId);
}
