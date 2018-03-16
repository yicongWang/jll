package com.zifisense.jll.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zifisense.jll.model.BusinessGroup;
import com.zifisense.jll.qo.BusinessGroupAddQo;
import com.zifisense.jll.qo.BusinessGroupPageQo;
import com.zifisense.jll.qo.BusinessGroupQo;
import com.zifisense.jll.vo.BusinessGroupNameVo;
import com.zifisense.jll.vo.BusinessGroupVo;
import com.zifisense.jll.vo.ShortVo;

/**
 * 业务组
 * @author ywc
 *
 */
public interface BusinessGroupService {

	/**
	 * 分页获取业务组详细信息
	 * @param businessGroupPageQo
	 * @return
	 * @throws Exception
	 */
	PageInfo<BusinessGroupVo> findBusinessGroupInfoListPage(BusinessGroupPageQo businessGroupPageQo) throws Exception;

	/**
	 * 获取业务组详细列表
	 * @param businessGroupQo
	 * @return
	 */
	List<BusinessGroupVo> getBusinessGroupListByQuery(BusinessGroupQo businessGroupQo);

	/**
	 * 获取业务组简称列表
	 * @return
	 */
	List<ShortVo> getShortBussiessGroupList(String keys);
	
	/**
	 * 获取所有业务组列表
	 * @return
	 */
	List<BusinessGroup> getAllBussiessGroupList();
	
	/**
	 * 添加业务组
	 * @param businessGroupAddQo
	 * @return
	 */
	int saveBusinessGroup(BusinessGroupAddQo businessGroupAddQo,Long createrId);
	
	/**
	 * 更新业务组
	 * @param businessGroupAddQo
	 * @return
	 */
	int updateBusinessGroup(BusinessGroupAddQo businessGroupAddQo,Long modifyId);
	
	/**
	 * 批量删除业务组信息
	 * @param ids
	 * @param modifyId
	 */
	void removeBussiessGroup(String ids,Long modifyId);

	
	/**
	 * 批量导入业务组信息
	 * @param accountDetailVoList
	 * @param createrId
	 */
	void batchUpload(List<BusinessGroupNameVo> businessGroupVoList, Long createrId);
	
}
