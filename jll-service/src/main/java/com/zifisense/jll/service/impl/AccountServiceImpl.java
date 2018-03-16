package com.zifisense.jll.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zifisense.jll.common.constants.InfoState;
import com.zifisense.jll.common.exception.BusinessException;
import com.zifisense.jll.common.exception.DataAlreadyExistsException;
import com.zifisense.jll.common.exception.DataNotExistsException;
import com.zifisense.jll.common.response.CommonStatus;
import com.zifisense.jll.dao.AccountMapperExt;
import com.zifisense.jll.dto.AccountRoleDTO;
import com.zifisense.jll.model.Account;
import com.zifisense.jll.model.AccountExample;
import com.zifisense.jll.model.AccountPasswordRecord;
import com.zifisense.jll.model.BusinessGroup;
import com.zifisense.jll.model.Project;
import com.zifisense.jll.model.Role;
import com.zifisense.jll.qo.AccountAddQo;
import com.zifisense.jll.qo.AccountPageQo;
import com.zifisense.jll.qo.AccountQo;
import com.zifisense.jll.qo.ForgotPwdQo;
import com.zifisense.jll.qo.UpdatePwdQo;
import com.zifisense.jll.service.AccountPasswordRecordService;
import com.zifisense.jll.service.AccountRoleService;
import com.zifisense.jll.service.AccountService;
import com.zifisense.jll.service.BusinessGroupAccountService;
import com.zifisense.jll.service.BusinessGroupService;
import com.zifisense.jll.service.ProjectAccountService;
import com.zifisense.jll.service.ProjectService;
import com.zifisense.jll.service.RoleService;
import com.zifisense.jll.service.common.RedisKeyConstant;
import com.zifisense.jll.service.common.RoleEnum;
import com.zifisense.jll.util.JwtHelper;
import com.zifisense.jll.util.MailUtill;
import com.zifisense.jll.util.Md5Utils;
import com.zifisense.jll.util.RandCodeUtil;
import com.zifisense.jll.util.RandomStr;
import com.zifisense.jll.util.RedisUtil;
import com.zifisense.jll.util.ValidatorUtil;
import com.zifisense.jll.vo.AccountDetailVo;
import com.zifisense.jll.vo.AccountShortVo;

import io.jsonwebtoken.Claims;

/**
 * 用户信息ServiceImpl
 * Created by DW on 2017/6/27.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    
    @Autowired
    private AccountMapperExt accountMapperExt;
    @Autowired
    private AccountPasswordRecordService accountPasswordRecordService;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
	private MailUtill mailUtill; 
    @Autowired
	private RedisUtil redisUtil;
    @Autowired
	private AccountRoleService accountRoleService;
    @Autowired
   	private ProjectAccountService projectAccountService;
    @Autowired
   	private BusinessGroupAccountService businessGroupAccountService;
    @Autowired
   	private RoleService roleService;
    @Autowired
   	private ProjectService projectService;
    @Autowired
   	private BusinessGroupService businessGroupService;
    
    @Override
    @Transactional
    public int update(Account account) {
        return accountMapperExt.updateByPrimaryKeySelective(account);
    }

    @Override
    public Account getAccount(Long id) {
    	return accountMapperExt.selectByPrimaryKey(id);
    }

    @Override
    public List<Account> findAccounts(Account account) {
        AccountExample accountExample = new AccountExample();
        AccountExample.Criteria criteria = accountExample.createCriteria();
        if(account!=null){

            if(!StringUtils.isBlank(account.getAccount())){
                criteria.andAccountEqualTo(account.getAccount());
            }
            if(!StringUtils.isBlank(account.getUserNameCn())){
                criteria.andUserNameCnEqualTo(account.getUserNameCn());
            }
            
            if(!StringUtils.isBlank(account.getUserNameEn())){
                criteria.andUserNameCnEqualTo(account.getUserNameEn());
            }
            
            if(!StringUtils.isBlank(account.getMobilePhone())){
                criteria.andMobilePhoneEqualTo( account.getMobilePhone());
            }
            if(!StringUtils.isBlank(account.getEmail())){
                criteria.andEmailEqualTo(account.getEmail());
            }

        }
        criteria.andStateNotEqualTo(InfoState.DELETED);
        return accountMapperExt.selectByExample(accountExample);
    }

    @Override
    public void checkUniqueValidate(Integer validateType, String validateValue, Long currentUserId) {
        List<Account> users;
        String msg = null;
        Account conditionSysUser = new Account();
        if (validateType == 1) {//用户账号
            msg = "登录账号";
            conditionSysUser.setAccount(validateValue);
        } else if (validateType == 2) {//邮箱
            msg = "邮箱";
            conditionSysUser.setEmail(validateValue);
        } else if (validateType == 3) {//手机
            msg = "手机";
            conditionSysUser.setMobilePhone(validateValue);
        }
        users = this.findAccounts(conditionSysUser);
        if (users != null && users.size() > 0) {
            for (Account tempSysUser : users) {
                if(currentUserId!=null){
                    if (tempSysUser.getId().longValue() != currentUserId.longValue()) {
                        throw new DataAlreadyExistsException("温馨提示："+msg +" "+validateValue +  " 已存在");
                    }
                }else{
                    throw new DataAlreadyExistsException("温馨提示："+msg +" "+validateValue + " 已存在");
                }

            }
        }

    }

    @Transactional
    @Override
    public int updatePwd(UpdatePwdQo updatePwd) {
        //提交前验证参数的有效性
        ValidatorUtil.validateParameter(this.validParameter(updatePwd));
        Account queryAccount = this.checkAccountIsExists(updatePwd.getAccountId());
        String inputOldPassword = Md5Utils.encryptPassword(queryAccount.getAccount(), updatePwd.getOldPass(), queryAccount.getSalt());
        String inputNewPassword = Md5Utils.encryptPassword(queryAccount.getAccount(), updatePwd.getNewPass(), queryAccount.getSalt());
        if(!inputOldPassword.equals(queryAccount.getPassword())){
            throw new DataNotExistsException("原密码错误");
        }if(StringUtils.equals(inputOldPassword, inputNewPassword)){
            throw new DataAlreadyExistsException("原密码不能与新密码一致");
        }if(!accountPasswordRecordService.validatePw(queryAccount.getId(), inputNewPassword)){
            throw new DataAlreadyExistsException("输入新密码一年内不能重复使用");
        }else{
        	 AccountPasswordRecord  record = new AccountPasswordRecord();
             record.setAccountId(queryAccount.getId());
             record.setCreateTime(new Date());
             record.setPasswordNew(inputNewPassword);
             record.setPasswordOld(inputOldPassword);
             record.setType(1);//用户修改
             accountPasswordRecordService.save(record);
            return this.setNewPassword(queryAccount,inputNewPassword);
        }
    }

    @Transactional
    @Override
    public int forgotPwd(ForgotPwdQo forgotPwd) {
        //提交前验证参数的有效性
        ValidatorUtil.validateParameter(this.validParameter(forgotPwd));
        String token = forgotPwd.getAuthorization();
        //验证token 是否失效
        boolean flag = false; //默认未过期
        try{
            flag = jwtHelper.isTokenExpired(token);
        }catch (NullPointerException e){//如果报空异常，说明未找到
            flag = true;
        }
        if(flag)
            throw new DataNotExistsException("authorization内容有误或已过期！");
        Claims claims = jwtHelper.getClaimsFromToken(token);
        String mobilePhont = (String) claims.get("sub");
        Account queryAccount = this.checkAccountIsExists(mobilePhont);
        return this.setNewPassword(queryAccount, Md5Utils.encryptPassword(queryAccount.getAccount(),forgotPwd.getConfirmPass(), queryAccount.getSalt()));
    }

    @Override
    public int resetPwd(String adminAccount,String adminPwd,String account) {
    	int record = 0;
    	//校验超级管理员账号密码是否正常
    	if(checkPwd(adminAccount, adminPwd)){
    		//校验账号是否存在
    		Account accountBO = getAccountInfoByAccount(account);
    		if(null != accountBO){
    			String pw = RandomStr.randomStr(8);
    			record = this.setNewPassword(accountBO,Md5Utils.encryptPassword(accountBO.getAccount(),pw, accountBO.getSalt()));
    			//发送邮件通知用户
    			if(StringUtils.isNoneBlank(accountBO.getEmail())){
    				mailUtill.sendEmail(accountBO.getEmail(),"重置密码","您的新密码为:"+pw);
    			}
    		}
    	}
    	return record;
    }


    /**
     * 设置用户密码
     * @param account   用户对象
     * @param newPassword   新密码
     * @return
     */
    private int setNewPassword(Account account,String newPassword){
        Account tempAccount = new Account();
        tempAccount.setId(account.getId());
        tempAccount.setPassword(newPassword);
        tempAccount.setLastUpdatePw(new Date());
        return accountMapperExt.updateByPrimaryKeySelective(tempAccount);
    }


    /**
     * 检查账户是否存在
     * @return  存在返回该对象
     */
    private Account checkAccountIsExists(Long accountId){
        Account querySysUser = this.getAccount(accountId);
        if(querySysUser==null || InfoState.DELETED == querySysUser.getState()){
            throw new DataNotExistsException("温馨提示：查询账户信息为空");
        }
        return querySysUser;
    }


    /**
     * 根据手机号查询账户是否存在
     * @param mobilePhone   手机号
     * @return
     */
    private Account checkAccountIsExists(String mobilePhone){
        Account conditionSysUser = new Account();
        conditionSysUser.setMobilePhone(mobilePhone);
        List<Account> users = this.findAccounts(conditionSysUser);
        if(users!=null && users.size()>0 && !users.get(0).getState().equals(InfoState.DELETED)){
            return users.get(0);
        }else {
            throw new DataNotExistsException("温馨提示：查询账户信息为空");
        }
    }

    /**
     * 修改密码
     * @param updatePwd
     * @return
     */
    private  Map<String, String> validParameter(UpdatePwdQo updatePwd) {
        Map<String, String> errorMap = new HashMap<>();
        if (updatePwd.getAccountId() == null || updatePwd.getAccountId() <= 0) {
            errorMap.put("accountId", "为空或小于等于0");
            logger.info("Parameter [accountId] check failed.");
        }
        if(!ValidatorUtil.validParameterAlert2(String.valueOf(updatePwd.getOldPass()), "oldPass", true, 8, 20, 13, errorMap)) {
            logger.info("Parameter [oldPass] check failed.");
        }
        if(!ValidatorUtil.validParameterAlert2(String.valueOf(updatePwd.getNewPass()), "newPass", true, 8, 20, 13, errorMap)) {
            logger.info("Parameter [newPass] check failed.");
        }
        
        return errorMap;
    }

    /**
     * 修改密码
     * @param forgotPwd
     * @return
     */
    private  Map<String, String> validParameter(ForgotPwdQo forgotPwd) {
        Map<String, String> errorMap = new HashMap<>();
        if(!ValidatorUtil.validParameterAlert2(String.valueOf(forgotPwd.getAuthorization()), "authorization", true, 1, 500, 4, errorMap)) {
            logger.info("Parameter [authorization] check failed.");
        }
        if(!ValidatorUtil.validParameterAlert2(String.valueOf(forgotPwd.getNewPass()), "newPass", true, 1, 20, 4, errorMap)) {
            logger.info("Parameter [newPass] check failed.");
        }
        if(!ValidatorUtil.validParameterAlert2(String.valueOf(forgotPwd.getConfirmPass()), "confirmPass", true, 1, 20, 4, errorMap)) {
            logger.info("Parameter [confirmPass] check failed.");
        }
        
        if(!StringUtils.isBlank(forgotPwd.getNewPass()) &&
                !StringUtils.isBlank(forgotPwd.getConfirmPass())
                && !forgotPwd.getNewPass().equals(forgotPwd.getConfirmPass())){
            errorMap.put("confirmPass", "两次密码不一致");
            logger.info("Parameter [confirmPass] check failed.");
        }
        return errorMap;
    }

    @Override
	public PageInfo<AccountDetailVo> findAccountInfoPage(AccountPageQo accountPageQo) throws Exception {
    	 PageHelper.startPage(accountPageQo.getPageNum(), accountPageQo.getPageSize());
    	 AccountQo accountQo = new AccountQo();
         BeanUtils.copyProperties(accountQo, accountPageQo);
         List<AccountDetailVo> list = findAccountInfoList(accountQo);
         return new PageInfo<>(list);
	}

    
    @Override
	public List<AccountDetailVo> findAccountInfoList(AccountQo accountQo) {
    	return accountMapperExt.findAccountInfoList(accountQo);
	}


	@Override
	public List<AccountShortVo> getShortAcountList(String identity,String keys) {
		return accountMapperExt.getShortAcountList(identity,keys);
	}

	@Override
	public boolean checkPwd(String account, String password) {
		Account accountBo = getAccountInfoByAccount(account);
		if(null != accountBo){
			String inputPwd = Md5Utils.encryptPassword(accountBo.getAccount(), password, accountBo.getSalt());
			//密码比对
            if (StringUtils.equals(accountBo.getPassword(), inputPwd)) {
            	return true;
            }else{
            	throw new DataNotExistsException("温馨提示：您输入的密码有误");
            }
		}else{
			throw new DataNotExistsException("温馨提示：您输入的账号不存在");
		}
	}

	@Override
	public Account getAccountInfoByAccount(String account) {
		Account accountBo = new Account();
		accountBo.setAccount(account);
		//根据账号获取账号信息
		List<Account> list =  this.findAccounts(accountBo);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}

	@Transactional
	@Override
	public int updateMobilePhone(Long accountId, String mobilePhone) {
		/**
		 * 手机号码校验
		 */
		if (!ValidatorUtil.isMobile(mobilePhone)) {
			throw new DataNotExistsException("温馨提示：手机号码格式错误");
        }
		Account tempAccount = new Account();
        tempAccount.setId(accountId);
        tempAccount.setMobilePhone(mobilePhone);
        return accountMapperExt.updateByPrimaryKeySelective(tempAccount);
	}
	
	@Transactional
	@Override
	public void addAccount(AccountAddQo accountAddQo,Long createrId) {
		//校验账号信息
	   ValidatorUtil.validateParameter(checkFormatValidate(accountAddQo,false,createrId));
	   //保存账号信息
	   saveAccount(accountAddQo, createrId);
	}
	
	/**
	 * 保存账号信息
	 * @param accountAddQo
	 * @param createrId
	 */
	@Transactional
	private void saveAccount(AccountAddQo accountAddQo,Long createrId){
		   Account account = new Account();
		   account.setUserNameCn(accountAddQo.getUserNameCn());
		   account.setUserNameEn(accountAddQo.getUserNameEn());
		   account.setNameModel( null == accountAddQo.getNameModel()?0:accountAddQo.getNameModel());
		   account.setState(InfoState.NORMAL);;
		   account.setAccount(accountAddQo.getEmail());
		   account.setCreateTime(new Date());
		   account.setCreateUser(createrId);
		   account.setLastUpdatePw(new Date());
		   account.setEmail(accountAddQo.getEmail());
		   account.setMobilePhone(accountAddQo.getMobilePhone());
		   account.setUserPosition(accountAddQo.getUserPosition());
		   String salt = RandCodeUtil.generateSalt();
		   account.setSalt(salt);
		   String pw = RandomStr.randomStr(8);
		   account.setPassword(Md5Utils.encryptPassword(account.getAccount(),pw, salt));
		   //添加用户信息
		   accountMapperExt.insertSelective(account);
		   Role role =  (Role)redisUtil.get(redisUtil.generatorKey(RedisKeyConstant.ROLE_KEY, accountAddQo.getRoleIdentity()));
		   //添加用户角色关联
		   accountRoleService.addAccountRole(account.getId(), role.getId());
		   if(StringUtils.equals(RoleEnum.LEVEL_THREE.getCode(), accountAddQo.getRoleIdentity()) 
				   && StringUtils.isNoneBlank(accountAddQo.getProjectIds())){
			  //添加三级用户项目关联
			   projectAccountService.addAccountProject(account.getId(), accountAddQo.getProjectIds());
		   }else if(StringUtils.equals(RoleEnum.LEVEL_TWO.getCode(), accountAddQo.getRoleIdentity())
				   && StringUtils.isNoneBlank(accountAddQo.getBusinessGroupIds())){
			 //添加二级用户业务关联
			  businessGroupAccountService.addBusinessGroupAccount(account.getId(), accountAddQo.getBusinessGroupIds());
		   }
		   //发送邮件通知用户已开通账户
		   mailUtill.sendEmail(account.getEmail(),"开通账户","您的账户密码为:"+pw);
	} 
	

	/**
	 * 校验账号信息
	 * @param accountAddQo
	 * @param isUpdate
	 * @return
	 */
	private Map<String, String> checkFormatValidate(AccountAddQo accountAddQo,boolean isUpdate,Long currentUserId) {
		    Map<String, String> errorMap = new HashMap<>();
		    
		    if(!isUpdate && StringUtils.isBlank(accountAddQo.getUserNameCn()) && StringUtils.isBlank(accountAddQo.getUserNameEn())){
		    	 errorMap.put("账号名称", "账号名称不能为空");
		    }
		    
		    if(StringUtils.isNoneBlank(accountAddQo.getUserNameCn())){
		    	if(!ValidatorUtil.validParameterAlert2(accountAddQo.getUserNameCn(), "userNameCn", true, 1, 32, 4, errorMap)) {
		            logger.info("Parameter [userNameCn] check failed.");
		        }
		    }
		    
			if(StringUtils.isNoneBlank(accountAddQo.getUserNameEn())){
				if(!ValidatorUtil.validParameterAlert2(accountAddQo.getUserNameEn(), "userNameEn", true, 1, 32, 4, errorMap)) {
		            logger.info("Parameter [userNameEn] check failed.");
		        }
			}
		    
			if((isUpdate && StringUtils.isNoneBlank(accountAddQo.getEmail())) || !isUpdate){
				 	if(!ValidatorUtil.isEmail(accountAddQo.getEmail())) {
			            logger.info("Parameter [email] check failed.");
			            errorMap.put("email", "格式有误");
			        }
				 	
				 	if(!isUpdate){
				 		checkUniqueValidate(2, accountAddQo.getEmail(), currentUserId);
				 	}
				 	
			}
			
			if((isUpdate && StringUtils.isNoneBlank(accountAddQo.getMobilePhone())) || !isUpdate){
				if(!ValidatorUtil.isMobile(accountAddQo.getMobilePhone())) {
		            logger.info("Parameter [mobilePhone] check failed.");
		            errorMap.put("mobilePhone", "格式有误");
		        }
				if(!isUpdate){
			 		checkUniqueValidate(3, accountAddQo.getMobilePhone(), currentUserId);
			 	}
			}
			
			if((isUpdate && StringUtils.isNoneBlank(accountAddQo.getRoleIdentity())) || !isUpdate){
				if(!StringUtils.equals(RoleEnum.LEVEL_ONE.getCode(), accountAddQo.getRoleIdentity())
					&& 	!StringUtils.equals(RoleEnum.LEVEL_TWO.getCode(), accountAddQo.getRoleIdentity())
					&& 	!StringUtils.equals(RoleEnum.LEVEL_THREE.getCode(), accountAddQo.getRoleIdentity())
						) {
		            logger.info("Parameter [roleIdentity] check failed.");
		            errorMap.put("roleIdentity", "参数有误");
		        }
			}
			
			if(isUpdate){
				if(null == accountAddQo.getId()){
					logger.info("Parameter [id] check failed.");
		            errorMap.put("id", "参数为空");
				}else{
					Account account = this.getAccount(accountAddQo.getId());
					if(account==null || InfoState.NORMAL != account.getState()){
					   errorMap.put("id", "记录不存在");
			        }
				}
			}
			
			//关联项目
			if(StringUtils.isNoneBlank(accountAddQo.getProjectIds())){
				int num = 1;
				for( String projectId : accountAddQo.getProjectIds().split(",")){
					if(StringUtils.isNoneBlank(projectId)){
						if(num > 3){
							 errorMap.put("projectIds", "关联的项目最多只能三个");
						}
						num ++;
					}
				}
			}
	        return errorMap;
	    }

	@Transactional
	@Override
	public void updateAccount(AccountAddQo accountAddQo,Long modifyId) {
		  ValidatorUtil.validateParameter(checkFormatValidate(accountAddQo,true,modifyId));
		   Account account = new Account();
		   account.setUserNameCn(accountAddQo.getUserNameCn());
		   account.setUserNameEn(accountAddQo.getUserNameEn());
		   account.setNameModel( null == accountAddQo.getNameModel()?0:accountAddQo.getNameModel());
		   account.setAccount(accountAddQo.getEmail());
		   account.setModifyTime(new Date());
		   account.setModifyUser(modifyId);
		   account.setEmail(accountAddQo.getEmail());
		   account.setMobilePhone(accountAddQo.getMobilePhone());
		   account.setUserPosition(accountAddQo.getUserPosition());
		   account.setId(accountAddQo.getId());
		   accountMapperExt.updateByPrimaryKeySelective(account);
		   if(StringUtils.isNoneBlank(accountAddQo.getRoleIdentity())){
			   Role role =  (Role)redisUtil.get(redisUtil.generatorKey(RedisKeyConstant.ROLE_KEY, accountAddQo.getRoleIdentity()));
			   //更新用户角色关联
			   accountRoleService.updateAccountRole(accountAddQo.getId(), role.getId());
		   }else{
			   //获取该用户等级
			   AccountRoleDTO accountRoleDTO = accountRoleService.queryAccountRoleByAccountId(accountAddQo.getId());
			   accountAddQo.setRoleIdentity(accountRoleDTO.getIdentity());//用户角色
		   }

		   if(StringUtils.equals(RoleEnum.LEVEL_THREE.getCode(), accountAddQo.getRoleIdentity()) 
				   && StringUtils.isNoneBlank(accountAddQo.getProjectIds())){
			  //添加三级用户项目关联
			   projectAccountService.addAccountProject(account.getId(), accountAddQo.getProjectIds());
		   }else if(StringUtils.equals(RoleEnum.LEVEL_TWO.getCode(), accountAddQo.getRoleIdentity())
				   && StringUtils.isNoneBlank(accountAddQo.getBusinessGroupIds())){
			 //添加二级用户业务关联
			  businessGroupAccountService.addBusinessGroupAccount(account.getId(), accountAddQo.getBusinessGroupIds());
		   }
		
	}

	@Transactional
	@Override
	public void removeAccount(String ids, Long modifyId) {
		Arrays.asList(ids.split(",")).forEach(id->{
			if(StringUtils.isNoneBlank(id)){
				Account record = new Account();
				record.setId(Long.valueOf(id));
				record.setState(InfoState.DELETED);
				record.setModifyUser(modifyId);
				record.setModifyTime(new Date());
				accountMapperExt.updateByPrimaryKeySelective(record);
				
				//账号被移除则对应管理的项目，业务也一并移除
				//projectAccountService.removeProjectAccount(Long.valueOf(id), null);
				//businessGroupAccountService.removeBusinessGroupAccount(Long.valueOf(id), null);
			}
		});
		
	}

	@Transactional
	@Override
	public void batchUploadAccount(List<AccountDetailVo> accountDetailVoList, Long createrId){
		 AccountAddQo accountAddQo = null;
		 Map<String,AccountAddQo> accountAddQoMap = new HashMap<>();
		 
		 List<Project> projectList = projectService.getAllProjectList();
		 List<BusinessGroup> businessGroupList = businessGroupService.getAllBussiessGroupList();
		 
		 //数据校验
		 for(AccountDetailVo accountDetailVo : accountDetailVoList){
			 accountAddQo = new AccountAddQo();
			 try {
				BeanUtils.copyProperties(accountAddQo, accountDetailVo);
				accountAddQo.setRoleIdentity(roleService.getRoleCodeByName(accountDetailVo.getRoleName()));
			} catch (Exception e) {
				logger.error("--uploadAccount----copyProperties--error:"+e.getMessage(),e);
				new BusinessException(CommonStatus.SYSTEM_ERROR,"obejct copy error");
			}
			 ValidatorUtil.validateParameter(checkFormatValidate(accountAddQo,false,createrId));
			 if(accountAddQoMap.containsKey(accountAddQo.getEmail())){
				 throw new DataAlreadyExistsException("温馨提示：邮箱"+accountAddQo.getEmail() +  " 存在重复记录");
			 }
			 
			 //校验业务组
			 if(StringUtils.isNoneBlank(accountDetailVo.getBusinessGroupNames()) 
				&& StringUtils.equals(RoleEnum.LEVEL_TWO.getCode(), accountAddQo.getRoleIdentity())
				&& CollectionUtils.isNotEmpty(businessGroupList)) {
				 validateBusinessGroupName(businessGroupList,accountAddQo,accountDetailVo.getBusinessGroupNames());
			 }
			 
			 //校验项目组
			 if(StringUtils.isNoneBlank(accountDetailVo.getProjectNames()) 
				&& StringUtils.equals(RoleEnum.LEVEL_THREE.getCode(), accountAddQo.getRoleIdentity())
				&& CollectionUtils.isNotEmpty(projectList)) {
				 validateProjectName(projectList,accountAddQo,accountDetailVo.getProjectNames());
			 }
			 
			 accountAddQoMap.put(accountAddQo.getEmail(), accountAddQo);
		 }
		//收集要进行新增的用户数据
		 if(MapUtils.isNotEmpty(accountAddQoMap)){
			 for(Entry<String, AccountAddQo> entry : accountAddQoMap.entrySet()){
				 saveAccount(entry.getValue(), createrId);
			 }
		 }
	}

	/**
	 * 校验项目组
	 * @param projectList
	 * @param accountAddQo
	 * @param projectNames
	 */
	private void validateProjectName(List<Project> projectList, AccountAddQo accountAddQo, String projectNames) {
		List<String> projectNameList = Arrays.asList(projectNames.split("\\|"));
		if(projectNameList.size() > 3){
			 throw new DataNotExistsException("温馨提示：存在超过关联的项目个数限制");
		}
		Long id = null;
		for(String projectName : projectNameList){
			id = null;
			for(Project project: projectList){
				if(StringUtils.equals(project.getNameCn(), projectName) || StringUtils.equals(project.getNameEn(), projectName)){
					id = project.getId();
				} 
			}
			
			if(null == id){
				 throw new DataNotExistsException("温馨提示：该项目("+projectName +  ") 系统不存在");
			}
			
			accountAddQo.setProjectIds(StringUtils.isBlank(accountAddQo.getProjectIds())?id.toString() : accountAddQo.getProjectIds()+"," +id);
		}
		accountAddQo.setProjectIds(accountAddQo.getProjectIds());
	}

	/**
	 * 校验业务组
	 * @param businessGroupList
	 * @param accountAddQo
	 * @param businessGroupNames
	 */
	private void validateBusinessGroupName(List<BusinessGroup> businessGroupList, AccountAddQo accountAddQo,
			String businessGroupNames) {
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
				 throw new DataNotExistsException("温馨提示：该业务组("+businessGroupName + ") 系统不存在");
			}
			accountAddQo.setBusinessGroupIds(StringUtils.isBlank(accountAddQo.getBusinessGroupIds())?id.toString() :accountAddQo.getBusinessGroupIds()+","+id);
		}
		
		accountAddQo.setBusinessGroupIds(accountAddQo.getBusinessGroupIds());
	}

	@Override
	public List<AccountShortVo> getProjectAcountAble() {
		return accountMapperExt.getProjectAcountAble();
	}
}
