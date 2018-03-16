package com.zifisense.jll.dao;

import com.zifisense.jll.model.AppSource;
import com.zifisense.jll.model.AppSourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppSourceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_source
     *
     * @mbggenerated
     */
    int countByExample(AppSourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_source
     *
     * @mbggenerated
     */
    int deleteByExample(AppSourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_source
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_source
     *
     * @mbggenerated
     */
    int insert(AppSource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_source
     *
     * @mbggenerated
     */
    int insertSelective(AppSource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_source
     *
     * @mbggenerated
     */
    List<AppSource> selectByExample(AppSourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_source
     *
     * @mbggenerated
     */
    AppSource selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_source
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") AppSource record, @Param("example") AppSourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_source
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") AppSource record, @Param("example") AppSourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_source
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AppSource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_source
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AppSource record);
}