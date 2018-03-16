package com.zifisense.jll.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zifisense.jll.common.constants.InfoState;
import com.zifisense.jll.common.exception.DataAlreadyExistsException;
import com.zifisense.jll.common.exception.DataNotExistsException;
import com.zifisense.jll.dao.BusinessGroupMapperExt;
import com.zifisense.jll.model.BusinessGroup;
import com.zifisense.jll.model.BusinessGroupExample;
import com.zifisense.jll.qo.BusinessGroupAddQo;
import com.zifisense.jll.qo.BusinessGroupPageQo;
import com.zifisense.jll.qo.BusinessGroupQo;
import com.zifisense.jll.service.BusinessGroupAccountService;
import com.zifisense.jll.service.BusinessGroupProjectService;
import com.zifisense.jll.service.BusinessGroupService;
import com.zifisense.jll.util.ValidatorUtil;
import com.zifisense.jll.vo.BusinessGroupNameVo;
import com.zifisense.jll.vo.BusinessGroupVo;
import com.zifisense.jll.vo.ShortVo;

@Service
public class BusinessGroupServiceImpl implements BusinessGroupService {
	 private Logger logger = LoggerFactory.getLogger(BusinessGroupServiceImpl.class);
	
    @Autowired
    private BusinessGroupMapperExt businessGroupMapperExt;
    
    @Autowired
    private  BusinessGroupProjectService businessGroupProjectService;
    
    @Autowired
    private  BusinessGroupAccountService businessGroupAccountService;
	
	@Override
	public PageInfo<BusinessGroupVo> findBusinessGroupInfoListPage(BusinessGroupPageQo businessGroupPageQo)
			throws Exception {
		    PageHelper.startPage(businessGroupPageQo.getPageNum(), businessGroupPageQo.getPageSize());
	    	BusinessGroupQo businessGroupQo = new BusinessGroupQo();
	        BeanUtils.copyProperties(businessGroupQo, businessGroupPageQo);
	        List<BusinessGroupVo> list = getBusinessGroupListByQuery(businessGroupQo);
	        return new PageInfo<>(list);
	}

	@Override
	public List<BusinessGroupVo> getBusinessGroupListByQuery(BusinessGroupQo businessGroupQo) {
		return businessGroupMapperExt.findBusinessGroupInfoList(businessGroupQo);
	}

	@Override
	public List<ShortVo> getShortBussiessGroupList(String keys) {
		return businessGroupMapperExt.getShortBussiessGroupList(keys);
	}

	@Override
	public List<BusinessGroup> getAllBussiessGroupList() {
		BusinessGroupExample businessGroupExample = new BusinessGroupExample();
		businessGroupExample.createCriteria().andStateEqualTo(InfoState.NORMAL);
        return businessGroupMapperExt.selectByExample(businessGroupExample);
	}

	@Transactional
	@Override
	public int saveBusinessGroup(BusinessGroupAddQo businessGroupAddQo,Long createrId) {
		/**
		 * 校验业务组名称
		 */
		validateParameter(businessGroupAddQo,false);
		return save(businessGroupAddQo.getGroupName(), createrId);
	}
	/**
	 * 保存业务组信息
	 * @param groupName
	 * @param createrId
	 * @return
	 */
	@Transactional
	private int save(String groupName,Long createrId){
		BusinessGroup businessGroup = new BusinessGroup();
		businessGroup.setCreateTime(new Date());
		businessGroup.setCreateUser(createrId);
		businessGroup.setGroupNameCn(StringUtils.trim(groupName));
		businessGroup.setNameModel(0);
		businessGroup.setState(InfoState.NORMAL);
		return businessGroupMapperExt.insert(businessGroup);
	}

	/**
	 * 校验新增业务组参数
	 * @param businessGroupAddQo
	 */
	private void  validateParameter(BusinessGroupAddQo businessGroupAddQo,boolean isUpdate) {
		Map<String, String> errorMap = new HashMap<>();
		if(!ValidatorUtil.validParameterAlert2(businessGroupAddQo.getGroupName(), "groupName", true, 1, 32, 4, errorMap)) {
            logger.info("Parameter [groupName] check failed.");
        }
		ValidatorUtil.validateParameter(errorMap);
		
		if(isUpdate){
			if(null == businessGroupAddQo.getId()){
				 throw new DataNotExistsException("id 不存在");
			}else{
				BusinessGroup businessGroup = businessGroupMapperExt.selectByPrimaryKey(businessGroupAddQo.getId());
				if( null == businessGroup){
					 throw new DataNotExistsException("该业务组 不存在");
				}
			}
		}else{
			//主要判断业务组名称是否存在
			BusinessGroup businessGroup = getBusinessGroupByName(StringUtils.trim(businessGroupAddQo.getGroupName()));
			if( null != businessGroup){
				 throw new DataAlreadyExistsException("该业务组名称已经存在");
			}
			
		}
		
	}

	/**
	 * 根据名称获取业务组信息
	 * @param groupName
	 * @return
	 */
	private BusinessGroup getBusinessGroupByName(String groupName) {
		BusinessGroupExample example = new BusinessGroupExample();
		example.createCriteria().andGroupNameCnEqualTo(groupName).andStateEqualTo(InfoState.NORMAL);
		List<BusinessGroup> list = businessGroupMapperExt.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	@Transactional
	@Override
	public int updateBusinessGroup(BusinessGroupAddQo businessGroupAddQo,Long modifyId) {
		validateParameter(businessGroupAddQo,true);
		BusinessGroup businessGroup = new BusinessGroup();
		businessGroup.setModifyTime(new Date());
		businessGroup.setModifyUser(modifyId);
		businessGroup.setId(businessGroupAddQo.getId());
		businessGroup.setGroupNameCn(businessGroupAddQo.getGroupName());
		return businessGroupMapperExt.updateByPrimaryKeySelective(businessGroup);
	}

	@Transactional
	@Override
	public void removeBussiessGroup(String ids, Long modifyId) {
		Arrays.asList(ids.split(",")).forEach(id->{
			if(StringUtils.isNoneBlank(id)){
				// 校验业务组是否存在(关联项目或者用户)。如果没有则移除，存在不能移除
				validateBusinessGroupIdRelation(Long.valueOf(id));
				BusinessGroup record = new BusinessGroup();
				record.setId(Long.valueOf(id));
				record.setState(InfoState.DELETED);
				record.setModifyUser(modifyId);
				record.setModifyTime(new Date());
				businessGroupMapperExt.updateByPrimaryKeySelective(record);
			}
		});
	}
	
	/**
	 * 校验业务组是否存在(关联项目或者用户)。如果没有则移除，存在不能移除
	 * @param id
	 */
	private void validateBusinessGroupIdRelation(Long businessGroupId) {
		if(CollectionUtils.isNotEmpty(businessGroupProjectService.findBusinessGroupProject(null, businessGroupId))){
			throw new DataAlreadyExistsException("业务组存在下辖项目,不能被移除");
		}
		
		if(CollectionUtils.isNotEmpty(businessGroupAccountService.findBusinessGroupAccount(null, businessGroupId))){
			throw new DataAlreadyExistsException("业务组存在关联用户,不能被移除");
		}
	}

	@Override
	public void batchUpload(List<BusinessGroupNameVo> businessGroupVoList, Long createrId) {
		//批量校验分组名称
		validateBatchGroupName(businessGroupVoList);
		 //批量创建业务组
		batchSaveBusinessGroup(businessGroupVoList, createrId);
	}

	private void validateBatchGroupName(List<BusinessGroupNameVo> businessGroupVoList) {
		List<BusinessGroup>  list = getAllBussiessGroupList();
		if(CollectionUtils.isNotEmpty(list)){
			List<String> groupNameList = new ArrayList<>();
			for(BusinessGroupNameVo businessGroupNameVo :businessGroupVoList){
				if(null != businessGroupNameVo && StringUtils.isNoneBlank(businessGroupNameVo.getGroupName())){
					if(groupNameList.contains(StringUtils.trim(businessGroupNameVo.getGroupName()))){
						throw new DataAlreadyExistsException("业务组名称【"+businessGroupNameVo.getGroupName()+"】 出现重复");
					}else{
						groupNameList.add(StringUtils.trim(businessGroupNameVo.getGroupName()));
					}
					
					for(BusinessGroup businessGroup : list){
						if(StringUtils.equals(StringUtils.trim(businessGroup.getGroupNameCn()),StringUtils.trim(businessGroupNameVo.getGroupName()))){
							throw new DataAlreadyExistsException("业务组名称【"+businessGroupNameVo.getGroupName()+"】已被创建");
						}
					}
				}
			}
			
			if(CollectionUtils.isEmpty(groupNameList)){
				throw new DataNotExistsException("无导入数据，请检查Excel格式是否正确");
			}
		}
	}

	/**
	 * 批量保存业务组
	 * @param businessGroupVoList
	 * @param createrId
	 */
	@Transactional
	private void batchSaveBusinessGroup(List<BusinessGroupNameVo> businessGroupVoList, Long createrId){
		for(BusinessGroupNameVo businessGroupNameVo :businessGroupVoList){
			if(null != businessGroupNameVo && StringUtils.isNoneBlank(businessGroupNameVo.getGroupName())){
				save(businessGroupNameVo.getGroupName(), createrId);
			}
		}
	}
	
}
