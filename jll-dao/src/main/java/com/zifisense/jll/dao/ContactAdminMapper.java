package com.zifisense.jll.dao;

import com.zifisense.jll.model.ContactAdmin;
import com.zifisense.jll.model.ContactAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContactAdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact_admin
     *
     * @mbggenerated
     */
    int countByExample(ContactAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact_admin
     *
     * @mbggenerated
     */
    int deleteByExample(ContactAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact_admin
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact_admin
     *
     * @mbggenerated
     */
    int insert(ContactAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact_admin
     *
     * @mbggenerated
     */
    int insertSelective(ContactAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact_admin
     *
     * @mbggenerated
     */
    List<ContactAdmin> selectByExample(ContactAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact_admin
     *
     * @mbggenerated
     */
    ContactAdmin selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact_admin
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ContactAdmin record, @Param("example") ContactAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact_admin
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ContactAdmin record, @Param("example") ContactAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact_admin
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ContactAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact_admin
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ContactAdmin record);
}