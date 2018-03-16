package com.zifisense.jll.model;

import java.io.Serializable;

public class BusinessGroupAccount implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_group_account.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_group_account.account_id
     *
     * @mbggenerated
     */
    private Long accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_group_account.business_group_id
     *
     * @mbggenerated
     */
    private Long businessGroupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table business_group_account
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_group_account.id
     *
     * @return the value of business_group_account.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_group_account.id
     *
     * @param id the value for business_group_account.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_group_account.account_id
     *
     * @return the value of business_group_account.account_id
     *
     * @mbggenerated
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_group_account.account_id
     *
     * @param accountId the value for business_group_account.account_id
     *
     * @mbggenerated
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_group_account.business_group_id
     *
     * @return the value of business_group_account.business_group_id
     *
     * @mbggenerated
     */
    public Long getBusinessGroupId() {
        return businessGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_group_account.business_group_id
     *
     * @param businessGroupId the value for business_group_account.business_group_id
     *
     * @mbggenerated
     */
    public void setBusinessGroupId(Long businessGroupId) {
        this.businessGroupId = businessGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business_group_account
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
        sb.append(", accountId=").append(accountId);
        sb.append(", businessGroupId=").append(businessGroupId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}