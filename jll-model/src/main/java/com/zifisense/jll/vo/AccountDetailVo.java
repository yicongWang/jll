package com.zifisense.jll.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zifisense.jll.common.BasicVo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * 我的资料
 * @author wyc
 *
 */
@ExcelTarget("AccountDetailVo")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDetailVo extends BasicVo{

    /**
     * 账户id
     */

    private Long id;

    /**
     * 姓名
     */
	@Excel(name = "中文姓名", orderNum = "1", mergeVertical = false, isImportField = "userNameCn",width =25)
    private String userNameCn;
    
    /**
     * 姓名
     */
	@Excel(name = "英文姓名", orderNum = "2", mergeVertical = false, isImportField = "userNameEn",width =25)
    private String userNameEn;
    

    /**
     * 账号
     */
	@Excel(name = "账号", orderNum = "3", mergeVertical = false, isImportField = "account",width =30)
    private String account;

    /**
     * 手机号
     */
	@Excel(name = "手机号", orderNum = "4", mergeVertical = false, isImportField = "mobilePhone",width =25)
    private String mobilePhone;

    /**
     * 头像
     */
    private String headImgPath;

    /**
     * 邮件
     */
    @Excel(name = "邮件", orderNum = "5", mergeVertical = false, isImportField = "email",width =30)
    private String email;
    /**
     * 职位信息
     */
    @Excel(name = "职位信息", orderNum = "6", mergeVertical = false, isImportField = "userPosition",width =25)
    private String userPosition;
    /**
     * 角色
     */
    @Excel(name = "用户等级", orderNum = "7", mergeVertical = false, isImportField = "roleName",width =25)
    private String roleName;

    /**
     * 角色代号
     */
    private String roleIdentity;
    
    /**
     * 所属业务组名称
     */
    @Excel(name = "所属业务组名称", orderNum = "8", mergeVertical = false, isImportField = "businessGroupNames",width =40)
    private String businessGroupNames;
    private String businessGroupNameIds;
    /**
     * 所属项目名称
     */
    @Excel(name = "管理项目名称", orderNum = "8", mergeVertical = false,width =40)
    private String projectNames;
    public String getBusinessGroupNames() {
		return businessGroupNames;
	}

	public void setBusinessGroupNames(String businessGroupNames) {
		this.businessGroupNames = businessGroupNames;
	}

	public String getProjectNames() {
		return projectNames;
	}

	public void setProjectNames(String projectNames) {
		this.projectNames = projectNames;
	}

	private String projectNameIds;
   


    
    public String getUserNameCn() {
		return userNameCn;
	}

	public void setUserNameCn(String userNameCn) {
		this.userNameCn = userNameCn;
	}

	public String getUserNameEn() {
		return userNameEn;
	}

	public void setUserNameEn(String userNameEn) {
		this.userNameEn = userNameEn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleIdentity() {
		return roleIdentity;
	}

	public void setRoleIdentity(String roleIdentity) {
		this.roleIdentity = roleIdentity;
	}
	
   

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHeadImgPath() {
        return headImgPath;
    }

    public void setHeadImgPath(String headImgPath) {
        this.headImgPath = headImgPath;
    }

	public String getBusinessGroupNameIds() {
		return businessGroupNameIds;
	}

	public void setBusinessGroupNameIds(String businessGroupNameIds) {
		this.businessGroupNameIds = businessGroupNameIds;
	}

	public String getProjectNameIds() {
		return projectNameIds;
	}

	public void setProjectNameIds(String projectNameIds) {
		this.projectNameIds = projectNameIds;
	}

}
