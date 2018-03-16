package com.zifisense.jll.dao;

import com.zifisense.jll.model.AccountPasswordRecord;
import com.zifisense.jll.model.AccountPasswordRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountPasswordRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_password_record
     *
     * @mbggenerated
     */
    int countByExample(AccountPasswordRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_password_record
     *
     * @mbggenerated
     */
    int deleteByExample(AccountPasswordRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_password_record
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_password_record
     *
     * @mbggenerated
     */
    int insert(AccountPasswordRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_password_record
     *
     * @mbggenerated
     */
    int insertSelective(AccountPasswordRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_password_record
     *
     * @mbggenerated
     */
    List<AccountPasswordRecord> selectByExample(AccountPasswordRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_password_record
     *
     * @mbggenerated
     */
    AccountPasswordRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_password_record
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") AccountPasswordRecord record, @Param("example") AccountPasswordRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_password_record
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") AccountPasswordRecord record, @Param("example") AccountPasswordRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_password_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AccountPasswordRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_password_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AccountPasswordRecord record);
}