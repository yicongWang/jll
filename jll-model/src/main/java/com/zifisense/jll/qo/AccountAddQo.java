package com.zifisense.jll.qo;


/**
 * 添加用户所需字段
 * @author wyc
 *
 */
public class AccountAddQo {

	/**
     * 姓名
     */
    private Long id;
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
     * 姓名
     */
    private String userNameCn;
    
    /**
     * 姓名
     */
    private String userNameEn;
    
    /**
     * 手机号
     */
    private String mobilePhone;

   
    /**
     * 邮件
     */
    private String email;
    /**
     * 职位信息
     */
    private String userPosition;
    
    /**
     * 角色名称
     */
    private String roleIdentity;
    
   
    /**
     * 所属业务组ID
     */
    private String businessGroupIds;
    
	/**
     * 所属项目Id
     */
    private String projectIds;

	/**
     *名称显示模式
     */
    private Integer nameModel;

    public Integer getNameModel() {
		return nameModel;
	}

	public void setNameModel(Integer nameModel) {
		this.nameModel = nameModel;
	}
    
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

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
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

	public String getRoleIdentity() {
		return roleIdentity;
	}

	public void setRoleIdentity(String roleIdentity) {
		this.roleIdentity = roleIdentity;
	}


	public String getBusinessGroupIds() {
		return businessGroupIds;
	}

	public void setBusinessGroupIds(String businessGroupIds) {
		this.businessGroupIds = businessGroupIds;
	}

	public String getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}
   
}
