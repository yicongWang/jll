package com.zifisense.jll.dao;

import com.zifisense.jll.model.PushModel;
import com.zifisense.jll.model.PushModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PushModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_push_model
     *
     * @mbggenerated
     */
    int countByExample(PushModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_push_model
     *
     * @mbggenerated
     */
    int deleteByExample(PushModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_push_model
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_push_model
     *
     * @mbggenerated
     */
    int insert(PushModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_push_model
     *
     * @mbggenerated
     */
    int insertSelective(PushModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_push_model
     *
     * @mbggenerated
     */
    List<PushModel> selectByExample(PushModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_push_model
     *
     * @mbggenerated
     */
    PushModel selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_push_model
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") PushModel record, @Param("example") PushModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_push_model
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") PushModel record, @Param("example") PushModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_push_model
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PushModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_push_model
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PushModel record);
}