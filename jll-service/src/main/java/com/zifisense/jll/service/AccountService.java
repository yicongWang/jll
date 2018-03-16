package com.zifisense.jll.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zifisense.jll.model.Account;
import com.zifisense.jll.qo.AccountAddQo;
import com.zifisense.jll.qo.AccountPageQo;
import com.zifisense.jll.qo.AccountQo;
import com.zifisense.jll.qo.ForgotPwdQo;
import com.zifisense.jll.qo.UpdatePwdQo;
import com.zifisense.jll.vo.AccountDetailVo;
import com.zifisense.jll.vo.AccountShortVo;

/**
 * 用户信息Service
 * Created by DW on 2017/6/27.
 */
public interface AccountService {


    /**
     * 单表修改
     * @param account
     * @return
     */
    int update(Account account);



    /**
     * 根据id获取用户
     * @param id    id
     * @return  用户对象
     */
    Account getAccount(Long id);


    /**
     * 根据条件查询用户
     * @param account    account
     * @return  用户对象集合
     */
    List<Account> findAccounts(Account account);


    /**
     * 验证信息的唯一性
     *
     * @param validateType  验证类型：1:账号；2：邮箱；3：手机；4：用户名
     * @param validateValue 对应类型的值
     * @param currentUserId 当前用户id
     */
    void checkUniqueValidate(Integer validateType, String validateValue, Long currentUserId);

    /**
     * 修改密码
     * @param updatePwd  updatePwd
     * @return
     */
    int updatePwd(UpdatePwdQo updatePwd);

    /**
     * 校验密码
     * @param account
     * @param password
     * @return
     */
    boolean checkPwd(String account, String password);
    
    /**
     * 忘记密码
     * @param forgotPwd updatePwd
     * @return
     */
    int forgotPwd(ForgotPwdQo forgotPwd);

    /**
     * 重置密码
     * @param adminAccount 超级管理员账号
     * @param adminPwd 超级管理员密码
     * @param account 重置账号
     * @return
     */
    int resetPwd(String adminAccount,String adminPwd,String account);

    /**
     * 根据账号获取账号信息
     * @param account
     * @return
     */
    Account getAccountInfoByAccount(String account);

	/**
	 * 分页获取用户信息列表
	 * @param accountPageQo
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	PageInfo<AccountDetailVo> findAccountInfoPage(AccountPageQo accountPageQo) throws Exception;


	/**
	 * 获取用户列表
	 * @param accountQo
	 * @return
	 */
	List<AccountDetailVo> findAccountInfoList(AccountQo accountQo);


	/**
	 * 获取用户简称列表
	 * @param identity （用户等级）
	 * @return
	 */
	List<AccountShortVo> getShortAcountList(String identity,String keys);


	/**
	 * 根据账号ID更新手机号码
	 * @param accountId
	 * @param mobilePhone
	 * @return
	 */
	int updateMobilePhone(Long accountId, String mobilePhone);


	/**
	 * 新增账号信息
	 * @param accountAddQo
	 */
	void addAccount(AccountAddQo accountAddQo,Long createrId);

	/**
	 * 更新账号信息
	 * @param accountAddQo
	 */
	void updateAccount(AccountAddQo accountAddQo,Long modifyId);
	
	/**
	 * 批量删除账号信息
	 * @param ids
	 * @param modifyId
	 */
	void removeAccount(String ids,Long modifyId);
	
	/**
	 * 批量导入账号信息
	 * @param accountDetailVoList
	 * @param createrId
	 */
	void batchUploadAccount(List<AccountDetailVo> accountDetailVoList,Long createrId);
	
	/**
	 *获取可关联项目的账号
	 *过滤已经拥有三个项目的账号
	 * @return
	 */
	List<AccountShortVo> getProjectAcountAble();
}
