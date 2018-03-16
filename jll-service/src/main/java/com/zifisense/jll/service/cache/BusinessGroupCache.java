package com.zifisense.jll.service.cache;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zifisense.jll.model.BusinessGroup;
import com.zifisense.jll.service.BusinessGroupService;
import com.zifisense.jll.service.common.RedisKeyConstant;
import com.zifisense.jll.util.RedisUtil;

@Service
public class BusinessGroupCache {
	 @Autowired
	 private BusinessGroupService businessGroupService;
	 @Autowired
	 private RedisUtil redisUtil;
	 
	 /**
	  * 将所有业务组加载如redis中
	  */
	public void initAllBusinessGroupCache(){
		//获取业务组
		List<BusinessGroup> businessGroupList = businessGroupService.getAllBussiessGroupList();
		//将所有业务组加载如redis中
		if(CollectionUtils.isNotEmpty(businessGroupList)){
			businessGroupList.forEach(businessGroup -> {
				redisUtil.set(redisUtil.generatorKey(RedisKeyConstant.BUSINESSGROUP_KEY, businessGroup.getId()), businessGroup);
			});
		}
	}
}
