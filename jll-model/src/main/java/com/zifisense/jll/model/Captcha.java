package com.zifisense.jll.model;

import java.io.Serializable;
import java.util.Date;

public class Captcha implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_captcha.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_captcha.mobile_phone
     *
     * @mbggenerated
     */
    private String mobilePhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_captcha.captcha
     *
     * @mbggenerated
     */
    private String captcha;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_captcha.type
     *
     * @mbggenerated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_captcha.generate_time
     *
     * @mbggenerated
     */
    private Date generateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_captcha.is_validation
     *
     * @mbggenerated
     */
    private Boolean isValidation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_captcha.validation_time
     *
     * @mbggenerated
     */
    private Date validationTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_captcha
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_captcha.id
     *
     * @return the value of sys_captcha.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_captcha.id
     *
     * @param id the value for sys_captcha.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_captcha.mobile_phone
     *
     * @return the value of sys_captcha.mobile_phone
     *
     * @mbggenerated
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_captcha.mobile_phone
     *
     * @param mobilePhone the value for sys_captcha.mobile_phone
     *
     * @mbggenerated
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_captcha.captcha
     *
     * @return the value of sys_captcha.captcha
     *
     * @mbggenerated
     */
    public String getCaptcha() {
        return captcha;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_captcha.captcha
     *
     * @param captcha the value for sys_captcha.captcha
     *
     * @mbggenerated
     */
    public void setCaptcha(String captcha) {
        this.captcha = captcha == null ? null : captcha.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_captcha.type
     *
     * @return the value of sys_captcha.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_captcha.type
     *
     * @param type the value for sys_captcha.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_captcha.generate_time
     *
     * @return the value of sys_captcha.generate_time
     *
     * @mbggenerated
     */
    public Date getGenerateTime() {
        return generateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_captcha.generate_time
     *
     * @param generateTime the value for sys_captcha.generate_time
     *
     * @mbggenerated
     */
    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_captcha.is_validation
     *
     * @return the value of sys_captcha.is_validation
     *
     * @mbggenerated
     */
    public Boolean getIsValidation() {
        return isValidation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_captcha.is_validation
     *
     * @param isValidation the value for sys_captcha.is_validation
     *
     * @mbggenerated
     */
    public void setIsValidation(Boolean isValidation) {
        this.isValidation = isValidation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_captcha.validation_time
     *
     * @return the value of sys_captcha.validation_time
     *
     * @mbggenerated
     */
    public Date getValidationTime() {
        return validationTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_captcha.validation_time
     *
     * @param validationTime the value for sys_captcha.validation_time
     *
     * @mbggenerated
     */
    public void setValidationTime(Date validationTime) {
        this.validationTime = validationTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_captcha
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
        sb.append(", mobilePhone=").append(mobilePhone);
        sb.append(", captcha=").append(captcha);
        sb.append(", type=").append(type);
        sb.append(", generateTime=").append(generateTime);
        sb.append(", isValidation=").append(isValidation);
        sb.append(", validationTime=").append(validationTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}