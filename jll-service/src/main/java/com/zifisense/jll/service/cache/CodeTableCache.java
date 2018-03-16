package com.zifisense.jll.service.cache;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zifisense.jll.model.CodeTable;
import com.zifisense.jll.service.CodeTableService;
import com.zifisense.jll.service.common.RedisKeyConstant;
import com.zifisense.jll.util.RedisUtil;

@Service
public class CodeTableCache {
	 @Autowired
	 private CodeTableService codeTableService;
	 @Autowired
	 private RedisUtil redisUtil;
	 
	 /**
	  * 将所有码表值缓存到redis中
	  */
	public void initCodeCache(){
		//获取可用角色
		List<CodeTable> codeTableList = codeTableService.getAllCodeTableList();
		if(CollectionUtils.isNotEmpty(codeTableList)){
			codeTableList.forEach(codeTable -> {
				redisUtil.set(redisUtil.generatorKey(RedisKeyConstant.CODE_TABLE_TYPE_KEY,codeTable.getCodeType(),codeTable.getKey()), codeTable);
			});
		}
	}
	
	/**
	 * 通过类型与键值获取名称
	 * @param type
	 * @param value
	 * @return
	 */
	public String getNameByTypeKey(String type,String key){
		CodeTable codeTable = (CodeTable) redisUtil.get(redisUtil.generatorKey(RedisKeyConstant.CODE_TABLE_TYPE_KEY,type,key));
		if(codeTable != null){
			return codeTable.getName();
		}else{
			return "";
		}
			
	}
}
