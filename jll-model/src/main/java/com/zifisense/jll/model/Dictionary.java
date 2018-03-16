package com.zifisense.jll.model;

import java.io.Serializable;
import java.util.Date;

public class Dictionary implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dictionary.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dictionary.group_code
     *
     * @mbggenerated
     */
    private String groupCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dictionary.group_name
     *
     * @mbggenerated
     */
    private String groupName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dictionary.dict_code
     *
     * @mbggenerated
     */
    private String dictCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dictionary.dict_name
     *
     * @mbggenerated
     */
    private String dictName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dictionary.sort_number
     *
     * @mbggenerated
     */
    private Integer sortNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dictionary.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dictionary.deleted
     *
     * @mbggenerated
     */
    private Boolean deleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dictionary.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dictionary.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dictionary.modify_user
     *
     * @mbggenerated
     */
    private String modifyUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dictionary.modify_time
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_dictionary
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dictionary.id
     *
     * @return the value of sys_dictionary.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dictionary.id
     *
     * @param id the value for sys_dictionary.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dictionary.group_code
     *
     * @return the value of sys_dictionary.group_code
     *
     * @mbggenerated
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dictionary.group_code
     *
     * @param groupCode the value for sys_dictionary.group_code
     *
     * @mbggenerated
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode == null ? null : groupCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dictionary.group_name
     *
     * @return the value of sys_dictionary.group_name
     *
     * @mbggenerated
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dictionary.group_name
     *
     * @param groupName the value for sys_dictionary.group_name
     *
     * @mbggenerated
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dictionary.dict_code
     *
     * @return the value of sys_dictionary.dict_code
     *
     * @mbggenerated
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dictionary.dict_code
     *
     * @param dictCode the value for sys_dictionary.dict_code
     *
     * @mbggenerated
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dictionary.dict_name
     *
     * @return the value of sys_dictionary.dict_name
     *
     * @mbggenerated
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dictionary.dict_name
     *
     * @param dictName the value for sys_dictionary.dict_name
     *
     * @mbggenerated
     */
    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dictionary.sort_number
     *
     * @return the value of sys_dictionary.sort_number
     *
     * @mbggenerated
     */
    public Integer getSortNumber() {
        return sortNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dictionary.sort_number
     *
     * @param sortNumber the value for sys_dictionary.sort_number
     *
     * @mbggenerated
     */
    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dictionary.remark
     *
     * @return the value of sys_dictionary.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dictionary.remark
     *
     * @param remark the value for sys_dictionary.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dictionary.deleted
     *
     * @return the value of sys_dictionary.deleted
     *
     * @mbggenerated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dictionary.deleted
     *
     * @param deleted the value for sys_dictionary.deleted
     *
     * @mbggenerated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dictionary.create_user
     *
     * @return the value of sys_dictionary.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dictionary.create_user
     *
     * @param createUser the value for sys_dictionary.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dictionary.create_time
     *
     * @return the value of sys_dictionary.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dictionary.create_time
     *
     * @param createTime the value for sys_dictionary.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dictionary.modify_user
     *
     * @return the value of sys_dictionary.modify_user
     *
     * @mbggenerated
     */
    public String getModifyUser() {
        return modifyUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dictionary.modify_user
     *
     * @param modifyUser the value for sys_dictionary.modify_user
     *
     * @mbggenerated
     */
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dictionary.modify_time
     *
     * @return the value of sys_dictionary.modify_time
     *
     * @mbggenerated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dictionary.modify_time
     *
     * @param modifyTime the value for sys_dictionary.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dictionary
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupCode=").append(groupCode);
        sb.append(", groupName=").append(groupName);
        sb.append(", dictCode=").append(dictCode);
        sb.append(", dictName=").append(dictName);
        sb.append(", sortNumber=").append(sortNumber);
        sb.append(", remark=").append(remark);
        sb.append(", deleted=").append(deleted);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUser=").append(modifyUser);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}