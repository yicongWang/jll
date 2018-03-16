package com.zifisense.jll.model;

import java.io.Serializable;
import java.util.Date;

public class ContactAdmin implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contact_admin.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contact_admin.sumbit_account_id
     *
     * @mbggenerated
     */
    private Long sumbitAccountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contact_admin.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contact_admin.state
     *
     * @mbggenerated
     */
    private Integer state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contact_admin.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contact_admin.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table contact_admin
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contact_admin.id
     *
     * @return the value of contact_admin.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contact_admin.id
     *
     * @param id the value for contact_admin.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contact_admin.sumbit_account_id
     *
     * @return the value of contact_admin.sumbit_account_id
     *
     * @mbggenerated
     */
    public Long getSumbitAccountId() {
        return sumbitAccountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contact_admin.sumbit_account_id
     *
     * @param sumbitAccountId the value for contact_admin.sumbit_account_id
     *
     * @mbggenerated
     */
    public void setSumbitAccountId(Long sumbitAccountId) {
        this.sumbitAccountId = sumbitAccountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contact_admin.content
     *
     * @return the value of contact_admin.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contact_admin.content
     *
     * @param content the value for contact_admin.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contact_admin.state
     *
     * @return the value of contact_admin.state
     *
     * @mbggenerated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contact_admin.state
     *
     * @param state the value for contact_admin.state
     *
     * @mbggenerated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contact_admin.title
     *
     * @return the value of contact_admin.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contact_admin.title
     *
     * @param title the value for contact_admin.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contact_admin.create_time
     *
     * @return the value of contact_admin.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contact_admin.create_time
     *
     * @param createTime the value for contact_admin.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact_admin
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
        sb.append(", sumbitAccountId=").append(sumbitAccountId);
        sb.append(", content=").append(content);
        sb.append(", state=").append(state);
        sb.append(", title=").append(title);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}