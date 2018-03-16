package com.zifisense.jll.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zifisense.jll.qo.ProjectQo;
import com.zifisense.jll.vo.ProjectVo;
import com.zifisense.jll.vo.ShortVo;

/**
 * 项目
 * @author wyc
 *
 */
@Mapper
public interface ProjectMapperExt extends ProjectMapper {

    /**
     * 获取项目详细信息列表
     * @return
     */
    List<ProjectVo> findProjectInfoList(ProjectQo projectQo);

    /**
     * 获取业务组简称列表
     * @return
     */
	List<ShortVo> getProjectList(@Param(value="keys") String keys);

	/**
	 *  统计所有项目楼板万平面积
	 * @return
	 */
	BigDecimal sumAllProjectFloorArea();

}
