   package com.zifisense.jll.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zifisense.jll.common.constants.InfoState;
import com.zifisense.jll.common.exception.DataNotExistsException;
import com.zifisense.jll.common.response.CommonResponse;
import com.zifisense.jll.common.response.PageResponse;
import com.zifisense.jll.common.response.ResponseCode;
import com.zifisense.jll.controller.common.AccessRequired;
import com.zifisense.jll.controller.common.CurrentRedisUserData;
import com.zifisense.jll.dto.AccountRoleDTO;
import com.zifisense.jll.dto.RedisUserData;
import com.zifisense.jll.model.Account;
import com.zifisense.jll.model.LoginLog;
import com.zifisense.jll.qo.AccountAddQo;
import com.zifisense.jll.qo.AccountPageQo;
import com.zifisense.jll.qo.AccountQo;
import com.zifisense.jll.qo.AccountResetPwQo;
import com.zifisense.jll.qo.AccountUpdateMobileQo;
import com.zifisense.jll.qo.LoginQo;
import com.zifisense.jll.qo.UpdatePwdQo;
import com.zifisense.jll.service.AccountRoleService;
import com.zifisense.jll.service.AccountService;
import com.zifisense.jll.service.ContactAdminService;
import com.zifisense.jll.service.LoginLogService;
import com.zifisense.jll.service.MenuService;
import com.zifisense.jll.service.RoleService;
import com.zifisense.jll.service.common.RoleEnum;
import com.zifisense.jll.service.common.log.OperationLogType;
import com.zifisense.jll.service.common.log.OperationnLogAnnotation;
import com.zifisense.jll.util.DateUtil;
import com.zifisense.jll.util.ExportUtil;
import com.zifisense.jll.util.JwtHelper;
import com.zifisense.jll.util.Md5Utils;
import com.zifisense.jll.util.NetworkUtil;
import com.zifisense.jll.util.RedisUtil;
import com.zifisense.jll.util.ServiceUtil;
import com.zifisense.jll.util.UserSettings;
import com.zifisense.jll.util.ValidatorUtil;
import com.zifisense.jll.vo.AccountDetailVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 账户信息管理接口
 * Created by DW on 2017/6/27.
 */
@Api(description = "账户信息管理接口")
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);


    @Autowired
    private AccountService accountService;
    @Autowired
    private  RoleService roleService;
    @Autowired
    private AccountRoleService accountRoleService;
    @Autowired
    private MenuService muneService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private UserSettings userSettings;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private  ContactAdminService contactAdminService;

    /**
     * 用户登录
     *
     * @param accountDTO 用户对象
     * @return 请求响应
     */
    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResponse userLogin(@RequestBody LoginQo loginDTO,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    Device device) {
        //默认用户名或密码错误，
        CommonResponse commonResponse = new CommonResponse(ResponseCode.SOURCE_NOT_EXIST_ERROR, "用户名或密码错误！");
        try {
            Map<String, String> errorMap = new HashMap<String, String>();
            if (ValidatorUtil.validParameterAlert(loginDTO.getAccount(), "account", true, 1, 36, 4, errorMap) > 0) {
                logger.info("Parameter [account] check failed.");
            }
            if (ValidatorUtil.validParameterAlert(loginDTO.getPassword(), "password", true, 1, 20, 4, errorMap) > 0) {
                logger.info("Parameter [password] check failed.");
            }
            if (!errorMap.isEmpty()) {
                return new CommonResponse(ResponseCode.PARAMETER_ERROR, "参数校验未通过", errorMap);
            }
            Account account = new Account();
            account.setAccount(loginDTO.getAccount());
            List<Account> list = accountService.findAccounts(account);
            if(CollectionUtils.isNotEmpty(list)){
            	account = list.get(0);
            	if(null != account ){
            		  if (account.getState().equals(InfoState.BLOCKED)) {
                          commonResponse.setMsg("该用户已锁定！");
                      } else if (account.getState().equals(InfoState.NORMAL)) {
                          String inputPwd = Md5Utils.encryptPassword(account.getAccount(), loginDTO.getPassword(), account.getSalt());
                          if (account.getPassword().equals(inputPwd)) {
                              Date currentDate = DateUtil.now();
                              //修改登录次数和 当前登录时间 、 上一次登录时间
                              //设置上一次登录时间位 查询出来的登录时间
                              if(account.getLoginTime() != null) {
                                  account.setLastLoginTime(account.getLoginTime());
                              }
                              account.setLoginNumber(account.getLoginNumber()== null?0:account.getLoginNumber()+1);
                              account.setLoginTime(currentDate);
                              accountService.update(account);
                              //使用Redis 保存用户token 菜单，和权限信息
                              String accessToken = ServiceUtil.CreateAccessToken(account.getAccount(), String.valueOf(System.currentTimeMillis()));
                              RedisUserData redisUserData = new RedisUserData();
                              redisUserData.setAccessToken(accessToken);
                              BeanUtils.copyProperties(account, redisUserData);
                              response.setHeader("access_token", accessToken);
                              System.out.println(accessToken);
                              //账号角色
                              AccountRoleDTO accountRoleDTO = accountRoleService.queryAccountRoleByAccountId(account.getId());
                              redisUserData.setRoleName(accountRoleDTO.getRoleName());
                              redisUserData.setRoleIdentity(accountRoleDTO.getIdentity());
                              
                              //根据当前用户信息返回所拥有的菜单集合
                              redisUserData.setMenuList(muneService.queryMuneByRoleId(accountRoleDTO.getRoleId()));
                              
                              Long invalid = Long.parseLong(userSettings.getSessionInvalid()) * 60;
                              redisUserData.setLoginTime(DateUtil.dateToStr(currentDate,DateUtil.DATETIME_DEFAULT_FORMAT));
                              //设置请求ip地址和访问终端类型
                              try{
                                  redisUserData.setRequestIP(NetworkUtil.getIpAddress(request));
                                  redisUserData.setTerminalType(jwtHelper.generateAudience(device));
                              }catch (Exception e){
                                  e.printStackTrace();
                              }
                              //将 redisUserData对象 保存到redis中
                              boolean isWrite = redisUtil.set(accessToken, redisUserData, invalid);
                              if (isWrite && this.addLoginLog(redisUserData,currentDate)) {
                                  commonResponse.setMsg("account login success");
                                  commonResponse.setCode(ResponseCode.SUCCESS);
                                  redisUserData.setRequestIP(null);
                                  redisUserData.setTerminalType(null);
                                  commonResponse.setData(redisUserData);
                              } else {
                                  commonResponse.setMsg("account login failed, userData is write failed");
                                  commonResponse.setCode(ResponseCode.SERVER_ERROR);
                              }
                          }
                      } else {
                          commonResponse.setMsg("用户状态错误！");
                      }
            	}
            	
            }
            commonResponse.setCode(ResponseCode.SUCCESS);
            logger.info(commonResponse.getMsg());
        } catch (Exception e) {
            logger.error("account login failed", e);
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("service error");
        }
        return commonResponse;
    }

    /**
     * 用户退出登录
     *
     * @param redisUserData
     * @return
     */
    @ApiOperation(value = "用户退出登录")
    @AccessRequired
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public CommonResponse logout(@CurrentRedisUserData RedisUserData redisUserData,@RequestHeader String access_token) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            //移除accessToken
            redisUtil.delKey(redisUserData.getAccessToken());
            commonResponse.setCode(ResponseCode.SUCCESS);
            commonResponse.setMsg("account [" + redisUserData.getAccount() + "] logout success");
            logger.info(commonResponse.getMsg());
        } catch (Exception e) {
            logger.error("account [" + redisUserData.getAccount() + "] logout failed", e);
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("service error");
        }
        return commonResponse;
    }
    
    /**
     * 修改密码功能
     * @param updatePwd 封装的updatePwd对象
     * @return 请求响应
     */
    @AccessRequired
    @RequestMapping(value = "/updatePwd", method = RequestMethod.PUT)
    @ApiOperation(value = "修改密码功能",notes = "修改密码功能")
    @OperationnLogAnnotation(description = "修改密码功能", moduleName = "账户信息管理",operationLogType = OperationLogType.UPDATE)
    public CommonResponse updatePwd(@ApiParam @RequestBody UpdatePwdQo updatePwd ,@RequestHeader String access_token) {
        return CommonResponse.success(accountService.updatePwd(updatePwd));
    }
    /**
     * 忘记密码功能
     *
     * @param forgotPwd forgotPwd对象
     * @return 请求响应
     */
   /* @ApiOperation(value = "忘记密码功能")
    @RequestMapping(value = "/forgotPwd", method = RequestMethod.PUT)
    public CommonResponse forgotPwd(@ApiParam @RequestBody ForgotPwd forgotPwd ,@RequestHeader String access_token) {
        return CommonResponse.success(accountService.forgotPwd(forgotPwd));
    }*/

    /**
     * 重置密码功能
     * @param adminPwd
     * @param account(多个账号用逗号隔开)
     * @param access_token
     * @return
     */
    @AccessRequired
    @ApiOperation(value = "重置密码功能")
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "重置密码功能", moduleName = "账户信息管理",operationLogType = OperationLogType.UPDATE)
    public CommonResponse resetPwd(@RequestBody AccountResetPwQo accountResetPwQo,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) {
    	if(StringUtils.equals(redisUserData.getRoleIdentity(), RoleEnum.ADMIN.getCode())){
    		return CommonResponse.success(accountService.resetPwd(redisUserData.getAccount(), accountResetPwQo.getAdminPwd(),accountResetPwQo.getAccount()));
    	}else{
    		 return new CommonResponse(ResponseCode.ACCESS_FAILED, "非法操作,只有超级管理员才能操作");
    	}
    }

    @AccessRequired
    @ApiOperation(value = "修改手机号码")
    @RequestMapping(value = "/updateMobilePhone", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "修改手机号码", moduleName = "账户信息管理",operationLogType = OperationLogType.UPDATE)
    public CommonResponse updateMobilePhone(@RequestBody AccountUpdateMobileQo  accountUpdateMobileQo,@RequestHeader String access_token) {
    	return CommonResponse.success(accountService.updateMobilePhone(accountUpdateMobileQo.getAccountId(),accountUpdateMobileQo.getMobilePhone()));
    }
    
    @ApiOperation(value = "获取用户资料")
    @RequestMapping(value = "/findAccountInfo/{accountId}", method = RequestMethod.GET)
    @AccessRequired
    @OperationnLogAnnotation(description = "获取用户资料", moduleName = "账户信息管理",operationLogType = OperationLogType.QUERY)
    public CommonResponse findAccountInfo(@PathVariable Long accountId,@CurrentRedisUserData RedisUserData redisUserData,@RequestHeader String access_token) {
    	AccountQo  accountQo = new AccountQo();
    	accountQo.setAccountId(accountId);
    	List<AccountDetailVo> list = accountService.findAccountInfoList(accountQo);
    	if(CollectionUtils.isNotEmpty(list)){
    		return CommonResponse.success(list.get(0));
    	}else{
    		throw new DataNotExistsException("用户不存在");
    	}
    }

    /**
     * 添加登录日志
     * @param redisUserData redisUserData
     * @param loginTime  登录时间
     * @return  true表示成功，false表示失败
     */
    private boolean addLoginLog(RedisUserData redisUserData,Date loginTime){
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginUser(redisUserData.getId());
        loginLog.setIp(redisUserData.getRequestIP());
        loginLog.setTerminalType(redisUserData.getTerminalType());
        loginLog.setLoginTime(loginTime);
        loginLog.setRemark("登录成功！");
        return loginLogService.add(loginLog) > 0 ? true : false;
    }

    @AccessRequired
    @ApiOperation(value = "分页获取用户列表")
    @RequestMapping(value = "/findAccountInfoPage", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "分页获取用户列表", moduleName = "账户信息管理",operationLogType = OperationLogType.QUERY)
    public PageResponse findAccountInfoPage(AccountPageQo accountPageQo,  @RequestHeader String access_token) throws Exception{
        PageResponse<AccountDetailVo> response = new PageResponse(ResponseCode.SUCCESS, "OK");
        response.setData(accountService.findAccountInfoPage(accountPageQo));
    	return response;
    }
    
    @AccessRequired
    @ApiOperation(value = "获取用户等级列表")
    @RequestMapping(value = "/findAccountRoleList", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "获取用户等级列表", moduleName = "账户信息管理",operationLogType = OperationLogType.QUERY)
    public CommonResponse findAccountRoleList(@RequestHeader String access_token) throws Exception{
    	return CommonResponse.success(roleService.findRoleListByInCode(Arrays.asList(RoleEnum.LEVEL_TWO.getCode(),RoleEnum.LEVEL_THREE.getCode())));
    }
    
    @AccessRequired
    @ApiOperation(value = "添加账号")
    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "添加账号", moduleName = "账户信息管理",operationLogType = OperationLogType.ADD)
    public CommonResponse addAccount(@RequestBody AccountAddQo accountAddQo ,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) {
    	accountService.addAccount(accountAddQo,redisUserData.getId());
        return CommonResponse.success();
    }
    
    @AccessRequired
    @ApiOperation(value = "更新账号")
    @RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "更新账号", moduleName = "账户信息管理",operationLogType = OperationLogType.UPDATE)
    public CommonResponse updateAccount(@RequestBody AccountAddQo accountAddQo ,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) {
    	accountService.updateAccount(accountAddQo,redisUserData.getId());
        return CommonResponse.success();
    }
    
    @AccessRequired
    @ApiOperation(value = "删除账号")
    @RequestMapping(value = "/removeAccount", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "删除账号", moduleName = "账户信息管理",operationLogType = OperationLogType.DELTE)
    public CommonResponse removeAccount(@RequestBody Map ids ,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) {
    	String idsMap = MapUtils.getString(ids, "ids");
    	if(StringUtils.isBlank(idsMap)){
    		new DataNotExistsException("参数为空");
    	}
    	accountService.removeAccount(idsMap,redisUserData.getId());
        return CommonResponse.success();
    }
    
    @AccessRequired
    @ApiOperation(value = "导出")
    @RequestMapping(value = "/exportAccountInfoList", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "导出下载账号信息列表", moduleName = "账户信息管理",operationLogType = OperationLogType.DOWNLOAD)
    public void exportAccountInfoList(AccountQo accountQo ,HttpServletResponse response,@RequestHeader String access_token) throws IOException {
    	List<AccountDetailVo>  list = accountService.findAccountInfoList(accountQo);
    	ExportUtil.exportExcel(list,"账号信息","账号信息",AccountDetailVo.class,"账号信息.xls",response);
    }
    
    @AccessRequired
    @ApiOperation(value = "下载模板")
    @RequestMapping(value = "/download/template", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "下载账号信息模板", moduleName = "账户信息管理",operationLogType = OperationLogType.DOWNLOAD)
    public CommonResponse template(HttpServletResponse response) throws IOException {
    	  CommonResponse commonResponse = new CommonResponse();
          ExportUtil.exportExcel(Arrays.asList(),"账号信息模板 ","账号信息",AccountDetailVo.class,"账号信息模板.xls",response);
    	return commonResponse;
    }
  
    @AccessRequired
    @ApiOperation(value = "导入")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "导入账号信息", moduleName = "账户信息管理",operationLogType = OperationLogType.UPLOAD)
    public CommonResponse upload(@RequestParam("file") MultipartFile file,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) throws Exception {
	    	  if (file.isEmpty()) {
	    		  CommonResponse commonResponse = new CommonResponse();
	    		  commonResponse.setCode(ResponseCode.IMPORT_FILE_TYPE_ERROR);
				  commonResponse.setMsg("文件格式有误");
	    		  return commonResponse;
	          }
	    	  List<AccountDetailVo> accountDetailVoList = ExportUtil.importExcel(file,1,1,AccountDetailVo.class);
	    	  if(CollectionUtils.isNotEmpty(accountDetailVoList)){
	    		  accountService.batchUploadAccount(accountDetailVoList, redisUserData.getId());
	    	  }else{
	    		  CommonResponse commonResponse = new CommonResponse();
	    		  commonResponse.setCode(ResponseCode.IMPORT_FILE_TYPE_ERROR);
				  commonResponse.setMsg("文件为空");
	    	  }
	    	  return CommonResponse.success();
    }
    
    @AccessRequired
    @ApiOperation(value = "获取账户简称列表")
    @RequestMapping(value = "/getShortAcountList", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "获取账户简称列表", moduleName = "账户信息管理",operationLogType = OperationLogType.QUERY)
    public CommonResponse getShortAcountList(String identity,String keys,@RequestHeader String access_token) {
        return CommonResponse.success(accountService.getShortAcountList(identity, keys));
    }
    
    
    @AccessRequired
    @ApiOperation(value = "联系管理员")
    @RequestMapping(value = "/contactAdministrator", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "联系管理员", moduleName = "联系管理员",operationLogType = OperationLogType.QUERY)
    public CommonResponse contactAdministrator(@RequestBody Map contactContent ,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) {
    	String content = MapUtils.getString(contactContent, "content");
    	String title = MapUtils.getString(contactContent, "title");
    	if(StringUtils.isBlank(content) || StringUtils.isBlank(title)){
    		new DataNotExistsException("参数为空");
    	}
    	contactAdminService.save(title, content, redisUserData.getId());
        return CommonResponse.success();
    }
    
}
