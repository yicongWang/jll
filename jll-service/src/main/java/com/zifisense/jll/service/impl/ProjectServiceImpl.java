package com.zifisense.jll.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zifisense.jll.common.constants.InfoState;
import com.zifisense.jll.dao.ProjectMapperExt;
import com.zifisense.jll.model.Account;
import com.zifisense.jll.model.BusinessGroup;
import com.zifisense.jll.model.Project;
import com.zifisense.jll.model.ProjectExample;
import com.zifisense.jll.qo.ProjectAddQo;
import com.zifisense.jll.qo.ProjectPageQo;
import com.zifisense.jll.qo.ProjectQo;
import com.zifisense.jll.service.AccountService;
import com.zifisense.jll.service.BusinessGroupProjectService;
import com.zifisense.jll.service.BusinessGroupService;
import com.zifisense.jll.service.ProjectAccountService;
import com.zifisense.jll.service.ProjectService;
import com.zifisense.jll.service.ProjectSysSourceService;
import com.zifisense.jll.util.DateUtil;
import com.zifisense.jll.util.RedisUtil;
import com.zifisense.jll.util.ValidatorUtil;
import com.zifisense.jll.vo.ProjectSysSourceVo;
import com.zifisense.jll.vo.ProjectVo;
import com.zifisense.jll.vo.ShortVo;

@Service
public class ProjectServiceImpl implements ProjectService {
	private Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
	private ProjectMapperExt projectMapperExt;
	
	@Autowired
	private ProjectSysSourceService projectSysSourceService;
	
	@Autowired
	private BusinessGroupProjectService businessGroupProjectService;
	
	@Autowired
	private ProjectAccountService projectAccountService;
	
	@Autowired
	private BusinessGroupService businessGroupService;
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private RedisUtil redisUtil;
	@Override
	public PageInfo<ProjectVo> findProjectInfoListPage(ProjectPageQo projectPageQo) throws Exception {
		    PageHelper.startPage(projectPageQo.getPageNum(), projectPageQo.getPageSize());
		    ProjectQo projectQo = new ProjectQo();
	        BeanUtils.copyProperties(projectPageQo,projectQo);
	        List<ProjectVo> list = getProjectListByQuery(projectQo);
	        return new PageInfo<>(list);
	}

	@Override
	public List<ProjectVo> getProjectListByQuery(ProjectQo projectQo) {
		return projectMapperExt.findProjectInfoList(projectQo);
	}

	@Override
	public List<ShortVo> getShortProjectList(String keys) {
		return projectMapperExt.getProjectList( keys);
	}

	@Override
	public List<Project> getAllProjectList() {
		  	ProjectExample projectExample = new ProjectExample();
	        ProjectExample.Criteria criteria = projectExample.createCriteria();
	        criteria.andStateEqualTo(InfoState.NORMAL);
	        return projectMapperExt.selectByExample(projectExample);
	}

	@Override
	public List<ProjectSysSourceVo> getProjectSysCodeList(Long projectId) {
		return projectSysSourceService.getProjectSysCodeList(projectId);
	}

	@Override
	public void saveProjectSysCode(String sysIdJoinCodes,Long projectId ) {
		if(StringUtils.isNoneBlank(sysIdJoinCodes)){
			String[] sysIdJoinCodeAttr = null;
			for(String sysIdJoinCode : StringUtils.split(sysIdJoinCodes, ",")){
				sysIdJoinCodeAttr = null;
				if(StringUtils.isNoneBlank(sysIdJoinCode)){
					sysIdJoinCodeAttr = StringUtils.split(sysIdJoinCode, ":");
					projectSysSourceService.saveOrUpdate(projectId, Long.valueOf(sysIdJoinCodeAttr[0]), sysIdJoinCodeAttr[1]);
				}
			}
		}
	}
	
	@Transactional
	@Override
	public int saveProject(ProjectAddQo projectaAddQo,Long createrId) throws IllegalAccessException, InvocationTargetException {
		 ValidatorUtil.validateParameter(checkFormatValidate(projectaAddQo,false,createrId));
		 Project project = new Project();
 		 BeanUtils.copyProperties(projectaAddQo,project);
		 project.setNameCn(projectaAddQo.getProjectNameCn());
		 project.setNameEn(projectaAddQo.getProjectNameEn());
		 if(StringUtils.isNoneBlank(projectaAddQo.getCatchTime())){
			 project.setCatchTime(DateUtil.strToDate(projectaAddQo.getCatchTime(),"yyyy-MM-dd"));
		 }
		 project.setState(InfoState.NORMAL);
		 project.setCreateTime(new Date());
		 project.setCreateUser(createrId);
		 int record = this.save(project);
		 
		 if(StringUtils.isNoneBlank(projectaAddQo.getBusinessGroupIds())){
			//关联业务组
			 businessGroupProjectService.addBusinessGroupProject(project.getId(), projectaAddQo.getBusinessGroupIds());
		 }
		 
		 if(StringUtils.isNoneBlank(projectaAddQo.getAccountIds())){
			//关联用户
			 projectAccountService.addProjectAccount(project.getId(), projectaAddQo.getAccountIds());
		 }
		 
		 if(StringUtils.isNoneBlank(projectaAddQo.getSysIdJoinCodes())){
			//关联系统对接码
			 projectSysSourceService.saveOrUpdateBatch(project.getId(), projectaAddQo.getSysIdJoinCodes());
		}
		return record;
	}

	/**
	 * 保存
	 * @param project
	 * @return
	 */
	private int save(Project project) {
		return projectMapperExt.insert(project);
	}

	@Transactional
	@Override
	public int updateProject(ProjectAddQo projectaAddQo,Long modifyId) throws IllegalAccessException, InvocationTargetException {
		 ValidatorUtil.validateParameter(checkFormatValidate(projectaAddQo,true,modifyId));
		 Project project = new Project();
		 BeanUtils.copyProperties(projectaAddQo, project);
		 project.setNameCn(projectaAddQo.getProjectNameCn());
		 project.setNameEn(projectaAddQo.getProjectNameEn());
		 if(StringUtils.isNoneBlank(projectaAddQo.getCatchTime())){
			 project.setCatchTime(DateUtil.strToDate(projectaAddQo.getCatchTime(),"yyyy-MM-dd"));
		 }
		 project.setModifyTime(new Date());
		 project.setModifyUser(modifyId);
		 int record =  update(project);
		
		 if(StringUtils.isNoneBlank(projectaAddQo.getBusinessGroupIds())){
				//关联业务组
				 businessGroupProjectService.addBusinessGroupProject(project.getId(), projectaAddQo.getBusinessGroupIds());
		 }
		 
		 if(StringUtils.isNoneBlank(projectaAddQo.getAccountIds())){
			//关联用户
			 projectAccountService.addProjectAccount(project.getId(), projectaAddQo.getAccountIds());
		 }
		 
		 if(StringUtils.isNoneBlank(projectaAddQo.getSysIdJoinCodes())){
			//关联系统对接码
			 projectSysSourceService.saveOrUpdateBatch(project.getId(), projectaAddQo.getSysIdJoinCodes());
		}
		return record;
		
	}
	
	/**
	 * 更新
	 * @param project
	 * @return
	 */
	private int update(Project project) {
		return projectMapperExt.updateByPrimaryKeySelective(project);
	}

	/**
	 * 校验参数
	 * @param projectaAddQo
	 * @param isUpdate
	 * @param currentUserId
	 * @return
	 */
	private Map<String, String> checkFormatValidate(ProjectAddQo projectaAddQo,boolean isUpdate,Long currentUserId) {
		 Map<String, String> errorMap = new HashMap<>();
		  projectaAddQo.setNameModel(projectaAddQo.getNameModel() == null ? 0 : projectaAddQo.getNameModel());
		   if( 1 == projectaAddQo.getNameModel()) {//英文
			   if((isUpdate && StringUtils.isNoneBlank(projectaAddQo.getProjectNameEn())) || !isUpdate){
					if(!ValidatorUtil.validParameterAlert2(projectaAddQo.getProjectNameEn(), "projectNameEn", true, 1, 32, 4, errorMap)) {
			            logger.info("Parameter [userNameEn] check failed.");
			        }
				}
	        }else{
	        	  if((isUpdate && StringUtils.isNoneBlank(projectaAddQo.getProjectNameCn())) || !isUpdate){
	  		    	if(!ValidatorUtil.validParameterAlert2(projectaAddQo.getProjectNameCn(), "projectNameCn", true, 1, 32, 4, errorMap)) {
	  		            logger.info("Parameter [projectNameCn] check failed.");
	  		        }
	  		    }
	        }
		   
		   if(!isUpdate && null == projectaAddQo.getFloorArea()){
			   errorMap.put("floorArea", "建筑面积为空");
		   }
		   
		   if(StringUtils.isNoneBlank(projectaAddQo.getProjectNameEn())){
			  validateNameUnique(1,projectaAddQo.getProjectNameEn(),errorMap);
		   }
		   
		   if(StringUtils.isNoneBlank(projectaAddQo.getProjectNameCn())){
			  validateNameUnique(0,projectaAddQo.getProjectNameCn(),errorMap);
		   }
		   
		   if(isUpdate){//更新
			   if(null == projectaAddQo.getId()){
				   errorMap.put("id", "参数不能为空");
				}else{
					Project project = projectMapperExt.selectByPrimaryKey(projectaAddQo.getId());
					if(null == project){
						 errorMap.put("id", "不存在该项目");
					}
				}
		   }
		   
		   //校验用户最多关联三个项目
		   if(StringUtils.isNoneBlank(projectaAddQo.getAccountIds())){
				for( String accountId : projectaAddQo.getAccountIds().split(",")){
					if(StringUtils.isNoneBlank(accountId)){
						 int count = projectAccountService.findProjectCountByAccountId(Long.valueOf(accountId));
						 if(count > 2){
							 errorMap.put("accountIds", "用户下辖项目超出限制个数");
						 }
					}
				}
			}
		 return errorMap;
	}

	/**
	 * 校验项目名称唯一性
	 * @param nameModel
	 * @param projectName
	 * @param errorMap
	 */
	private void validateNameUnique(int nameModel, String projectName, Map<String, String> errorMap) {
		ProjectExample projectExample = new ProjectExample();
        ProjectExample.Criteria criteria = projectExample.createCriteria();
	    if( 1 == nameModel) {
		    criteria.andNameEnEqualTo(projectName);
	    }else{
		    criteria.andNameCnEqualTo(projectName);
	    	
	    }
	    int count = projectMapperExt.countByExample(projectExample);
	    if(count > 0){
	    	 errorMap.put("projectName", "项目名称【"+projectName+"】已存在，不能重复");
	    	 logger.info("Parameter [projectName] check failed.");
	    }
	}

	@Override
	public void removeProject(String ids, Long modifyId) {
		Arrays.asList(ids.split(",")).forEach(id->{
			if(StringUtils.isNoneBlank(id)){
				// 校验业务组是否存在(关联项目或者用户)。如果没有则移除，存在不能移除
				Project record = new Project();
				record.setId(Long.valueOf(id));
				record.setState(InfoState.DELETED);
				record.setModifyUser(modifyId);
				record.setModifyTime(new Date());
				projectMapperExt.updateByPrimaryKeySelective(record);
			}
		});
		
	}

	@Override
	public void batchUpload(List<ProjectVo> projectVoList, Long createrId) {
		//批量基础信息校验
		batchCheckFormatValidate(projectVoList);
		
		//批量创建项目
		batchSaveProject(projectVoList, createrId);
		
	}
	
	/**
	 * 批量保存项目信息
	 * @param projectVoList
	 * @param createrId
	 */
	private void batchSaveProject(List<ProjectVo> projectVoList, Long createrId) {
		
		for(ProjectVo projectVo :projectVoList){
			if(null != projectVo){
				 Project project = new Project();
		 		 BeanUtils.copyProperties(projectVo,project);
				 project.setNameCn(projectVo.getProjectNameCn());
				 project.setNameEn(projectVo.getProjectNameEn());
				 project.setState(InfoState.NORMAL);
				 project.setCreateTime(new Date());
				 project.setCreateUser(createrId);
				 projectMapperExt.insertSelective(project);
				 if(StringUtils.isNoneBlank(projectVo.getBusinessGroupIds())){
					//关联业务组
					 businessGroupProjectService.addBusinessGroupProject(project.getId(), projectVo.getBusinessGroupIds());
				 }
				 
				 if(StringUtils.isNoneBlank(projectVo.getAccountIds())){
					//关联用户
					 projectAccountService.addProjectAccount(project.getId(), projectVo.getAccountIds());
				 }
			}
		}
	}
	
	
	/**
	 * 校验业务组名称
	 * @param businessGroupList
	 * @param accountAddQo
	 * @param businessGroupNames
	 */
	private void validateBusinessGroupName(List<BusinessGroup> businessGroupList,ProjectVo projectVo,String businessGroupNames,Map<String, String> errorMap) {
		List<String> businessGroupNameList = Arrays.asList(businessGroupNames.split("\\|"));
		Long id = null;
			for(String businessGroupName : businessGroupNameList){
				id = null;
				for(BusinessGroup businessGroup: businessGroupList){
					if(StringUtils.equals(businessGroup.getGroupNameCn(), businessGroupName)){
						id = businessGroup.getId();
					} 
				}
				
				if(null == id){
					 errorMap.put("业务组", "该业务组【"+businessGroupName + "】 不存在");
					 return ;
					// throw new DataNotExistsException("温馨提示：该业务组【"+businessGroupName + "】 不存在");
				}
				projectVo.setBusinessGroupIds(StringUtils.isBlank(projectVo.getBusinessGroupIds())?id.toString() :projectVo.getBusinessGroupIds()+","+id);
			}
	}
	
	/**
	 * 校验用户名称
	 * @param accountList
	 * @param projectVo
	 * @param accountNames
	 */
	private void validateAccountName(List<Account> accountList,ProjectVo projectVo,String accountNames, Map<Long,Integer> accountProjectCount,Map<String, String> errorMap) {
		List<String> accountNameList = Arrays.asList(accountNames.split("\\|"));
		Long id = null;
			for(String accountName : accountNameList){
				id = null;
				for(Account account: accountList){
					if(StringUtils.equals(account.getUserNameCn(), accountName) || StringUtils.equals(account.getUserNameEn(), accountName) ){
						
						id = account.getId();
					} 
				}
				
				if(null == id){
					 errorMap.put("用户", "该用户【"+accountName + "】不存在");
					 return ;
					 //throw new DataNotExistsException("温馨提示：该用户【"+accountName + "】不存在");
				}
				 int count = 0;
				
				 if(accountProjectCount.containsKey(id)){
					count = accountProjectCount.get(id);
					
				 }else{
					 count = projectAccountService.findProjectCountByAccountId(Long.valueOf(id));
				 }
				
				 count +=  1;
				
				 if(count > 3){
					 errorMap.put("用户", "该用户【"+accountName + "】下辖项目超出限制个数");
					 return ;
					// throw new DataNotExistsException("温馨提示：该用户【"+accountName + "】下辖项目超出限制个数");
				 }
				
				accountProjectCount.put(id, count);
				projectVo.setAccountIds(StringUtils.isBlank(projectVo.getAccountIds())?id.toString() :projectVo.getAccountIds()+","+id);
			}
	}
	
	/**
	 * 批量校验上传项目信息
	 * @param projectVoList
	 */
	private void batchCheckFormatValidate(List<ProjectVo> projectVoList) {
		  Map<String, String> errorMap = new HashMap<>();
		  List<BusinessGroup> businessGroupList = businessGroupService.getAllBussiessGroupList();
		  List<Account> accountList =  accountService.findAccounts(null);
		  Map<Long,Integer> accountProjectCount = new HashMap<>();
		  
		  for(ProjectVo projectVo : projectVoList){
			  projectVo.setNameModel(projectVo.getNameModel() == null ? 0 : projectVo.getNameModel());
			   if( 1 == projectVo.getNameModel()) {//英文
					if(!ValidatorUtil.validParameterAlert2(projectVo.getProjectNameEn(), "projectNameEn", true, 1, 32, 4, errorMap)) {
			            logger.info("Parameter [userNameEn] check failed.");
			        }
		        }else{
		  		    if(!ValidatorUtil.validParameterAlert2(projectVo.getProjectNameCn(), "projectNameCn", true, 1, 32, 4, errorMap)) {
		  		        logger.info("Parameter [projectNameCn] check failed.");
		  		    }
		        }
			   
			   if(null == projectVo.getFloorArea()){
				   if( 1 == projectVo.getNameModel()) {
					   errorMap.put("floorArea",projectVo.getProjectNameEn() + " 建筑面积为空");
				   }else{
					   errorMap.put("floorArea",projectVo.getProjectNameCn() + " 建筑面积为空");
				   }
				 
			   }
			   
			   if(StringUtils.isNoneBlank(projectVo.getProjectNameEn())){
				  validateNameUnique(1,projectVo.getProjectNameEn(),errorMap);
			   }
			   
			   if(StringUtils.isNoneBlank(projectVo.getProjectNameCn())){
				  validateNameUnique(0,projectVo.getProjectNameCn(),errorMap);
			   }
			   
			  
			   
			   //校验业务组名称
			   if(StringUtils.isNoneBlank(projectVo.getBusinessGroupNames())){
				   validateBusinessGroupName(businessGroupList, projectVo, projectVo.getBusinessGroupNames(),errorMap);
			   }
			  
			   //校验用户名称
			   if(StringUtils.isNoneBlank(projectVo.getAccountNames())){
				   validateAccountName(accountList, projectVo, projectVo.getAccountNames(),accountProjectCount,errorMap);
				}
		  }
		  
		  ValidatorUtil.validateParameter(errorMap);
		  
	}

	@Override
	public BigDecimal sumAllProjectFloorArea() {
		BigDecimal sum = projectMapperExt.sumAllProjectFloorArea();
		if(null != sum){
			sum = sum.divide(BigDecimal.valueOf(10000),2,BigDecimal.ROUND_UP);
		}
		return sum;
	}
}
