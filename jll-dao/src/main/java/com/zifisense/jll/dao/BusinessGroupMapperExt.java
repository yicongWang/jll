package com.zifisense.jll.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zifisense.jll.vo.ShortVo;
import com.zifisense.jll.qo.BusinessGroupQo;
import com.zifisense.jll.vo.BusinessGroupVo;

/**
 * 业务组
 * @author wyc
 *
 */
@Mapper
public interface BusinessGroupMapperExt extends BusinessGroupMapper{

    /**
     * 获取业务组详细信息列表
     * @return
     */
    List<BusinessGroupVo> findBusinessGroupInfoList(BusinessGroupQo businessGroupQo);

    /**
     * 获取业务组简称列表
     * @return
     */
	List<ShortVo> getShortBussiessGroupList(@Param(value= "keys") String keys);

}
