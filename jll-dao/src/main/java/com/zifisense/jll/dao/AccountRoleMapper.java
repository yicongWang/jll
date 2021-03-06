package com.zifisense.jll.dao;

import com.zifisense.jll.model.AccountRole;
import com.zifisense.jll.model.AccountRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_role
     *
     * @mbggenerated
     */
    int countByExample(AccountRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_role
     *
     * @mbggenerated
     */
    int deleteByExample(AccountRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_role
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_role
     *
     * @mbggenerated
     */
    int insert(AccountRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_role
     *
     * @mbggenerated
     */
    int insertSelective(AccountRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_role
     *
     * @mbggenerated
     */
    List<AccountRole> selectByExample(AccountRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_role
     *
     * @mbggenerated
     */
    AccountRole selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_role
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") AccountRole record, @Param("example") AccountRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_role
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") AccountRole record, @Param("example") AccountRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AccountRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AccountRole record);
}