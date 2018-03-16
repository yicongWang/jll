package com.zifisense.jll.dao;

import com.zifisense.jll.model.CodeTable;
import com.zifisense.jll.model.CodeTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeTableMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_table
     *
     * @mbggenerated
     */
    int countByExample(CodeTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_table
     *
     * @mbggenerated
     */
    int deleteByExample(CodeTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_table
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_table
     *
     * @mbggenerated
     */
    int insert(CodeTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_table
     *
     * @mbggenerated
     */
    int insertSelective(CodeTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_table
     *
     * @mbggenerated
     */
    List<CodeTable> selectByExample(CodeTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_table
     *
     * @mbggenerated
     */
    CodeTable selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_table
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CodeTable record, @Param("example") CodeTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_table
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CodeTable record, @Param("example") CodeTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_table
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CodeTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_table
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CodeTable record);
}