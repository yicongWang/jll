package com.zifisense.jll.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Project implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.name_cn
     *
     * @mbggenerated
     */
    private String nameCn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.name_en
     *
     * @mbggenerated
     */
    private String nameEn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.address
     *
     * @mbggenerated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.floor_area
     *
     * @mbggenerated
     */
    private BigDecimal floorArea;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.floor_hight
     *
     * @mbggenerated
     */
    private BigDecimal floorHight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.state
     *
     * @mbggenerated
     */
    private Integer state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.name_model
     *
     * @mbggenerated
     */
    private Integer nameModel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.bulid_year
     *
     * @mbggenerated
     */
    private String bulidYear;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.city_name
     *
     * @mbggenerated
     */
    private String cityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.catch_time
     *
     * @mbggenerated
     */
    private Date catchTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.modify_user
     *
     * @mbggenerated
     */
    private Long modifyUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.create_user
     *
     * @mbggenerated
     */
    private Long createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.business_type
     *
     * @mbggenerated
     */
    private String businessType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.zip_code
     *
     * @mbggenerated
     */
    private String zipCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.modify_time
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table project
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.id
     *
     * @return the value of project.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.id
     *
     * @param id the value for project.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.name_cn
     *
     * @return the value of project.name_cn
     *
     * @mbggenerated
     */
    public String getNameCn() {
        return nameCn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.name_cn
     *
     * @param nameCn the value for project.name_cn
     *
     * @mbggenerated
     */
    public void setNameCn(String nameCn) {
        this.nameCn = nameCn == null ? null : nameCn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.name_en
     *
     * @return the value of project.name_en
     *
     * @mbggenerated
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.name_en
     *
     * @param nameEn the value for project.name_en
     *
     * @mbggenerated
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.address
     *
     * @return the value of project.address
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.address
     *
     * @param address the value for project.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.floor_area
     *
     * @return the value of project.floor_area
     *
     * @mbggenerated
     */
    public BigDecimal getFloorArea() {
        return floorArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.floor_area
     *
     * @param floorArea the value for project.floor_area
     *
     * @mbggenerated
     */
    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.floor_hight
     *
     * @return the value of project.floor_hight
     *
     * @mbggenerated
     */
    public BigDecimal getFloorHight() {
        return floorHight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.floor_hight
     *
     * @param floorHight the value for project.floor_hight
     *
     * @mbggenerated
     */
    public void setFloorHight(BigDecimal floorHight) {
        this.floorHight = floorHight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.state
     *
     * @return the value of project.state
     *
     * @mbggenerated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.state
     *
     * @param state the value for project.state
     *
     * @mbggenerated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.name_model
     *
     * @return the value of project.name_model
     *
     * @mbggenerated
     */
    public Integer getNameModel() {
        return nameModel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.name_model
     *
     * @param nameModel the value for project.name_model
     *
     * @mbggenerated
     */
    public void setNameModel(Integer nameModel) {
        this.nameModel = nameModel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.bulid_year
     *
     * @return the value of project.bulid_year
     *
     * @mbggenerated
     */
    public String getBulidYear() {
        return bulidYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.bulid_year
     *
     * @param bulidYear the value for project.bulid_year
     *
     * @mbggenerated
     */
    public void setBulidYear(String bulidYear) {
        this.bulidYear = bulidYear == null ? null : bulidYear.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.city_name
     *
     * @return the value of project.city_name
     *
     * @mbggenerated
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.city_name
     *
     * @param cityName the value for project.city_name
     *
     * @mbggenerated
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.catch_time
     *
     * @return the value of project.catch_time
     *
     * @mbggenerated
     */
    public Date getCatchTime() {
        return catchTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.catch_time
     *
     * @param catchTime the value for project.catch_time
     *
     * @mbggenerated
     */
    public void setCatchTime(Date catchTime) {
        this.catchTime = catchTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.modify_user
     *
     * @return the value of project.modify_user
     *
     * @mbggenerated
     */
    public Long getModifyUser() {
        return modifyUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.modify_user
     *
     * @param modifyUser the value for project.modify_user
     *
     * @mbggenerated
     */
    public void setModifyUser(Long modifyUser) {
        this.modifyUser = modifyUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.create_time
     *
     * @return the value of project.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.create_time
     *
     * @param createTime the value for project.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.create_user
     *
     * @return the value of project.create_user
     *
     * @mbggenerated
     */
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.create_user
     *
     * @param createUser the value for project.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.business_type
     *
     * @return the value of project.business_type
     *
     * @mbggenerated
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.business_type
     *
     * @param businessType the value for project.business_type
     *
     * @mbggenerated
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.zip_code
     *
     * @return the value of project.zip_code
     *
     * @mbggenerated
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.zip_code
     *
     * @param zipCode the value for project.zip_code
     *
     * @mbggenerated
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.modify_time
     *
     * @return the value of project.modify_time
     *
     * @mbggenerated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.modify_time
     *
     * @param modifyTime the value for project.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project
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
        sb.append(", nameCn=").append(nameCn);
        sb.append(", nameEn=").append(nameEn);
        sb.append(", address=").append(address);
        sb.append(", floorArea=").append(floorArea);
        sb.append(", floorHight=").append(floorHight);
        sb.append(", state=").append(state);
        sb.append(", nameModel=").append(nameModel);
        sb.append(", bulidYear=").append(bulidYear);
        sb.append(", cityName=").append(cityName);
        sb.append(", catchTime=").append(catchTime);
        sb.append(", modifyUser=").append(modifyUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", businessType=").append(businessType);
        sb.append(", zipCode=").append(zipCode);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}