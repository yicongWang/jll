package com.zifisense.jll.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zifisense.jll.model.Project;
import com.zifisense.jll.qo.ProjectAddQo;
import com.zifisense.jll.qo.ProjectPageQo;
import com.zifisense.jll.qo.ProjectQo;
import com.zifisense.jll.vo.ProjectSysSourceVo;
import com.zifisense.jll.vo.ProjectVo;
import com.zifisense.jll.vo.ShortVo;

/**
 * 项目
 * @author ywc
 *
 */
public interface ProjectService {

	/**
	 * 分页获取项目详细信息
	 * @param ProjectPageQo
	 * @return
	 * @throws Exception
	 */
	PageInfo<ProjectVo> findProjectInfoListPage(ProjectPageQo projectPageQo) throws Exception;

	/**
	 * 获取项目详细列表
	 * @param ProjectQo
	 * @return
	 */
	List<ProjectVo> getProjectListByQuery(ProjectQo projectQo);

	/**
	 * 获取项目简称列表
	 * @return
	 */
	List<ShortVo> getShortProjectList(String keys);
	
	/**
	 * 获取所有项目信息列表
	 * @return
	 */
	List<Project> getAllProjectList();
	
	/**
	 * 获取项目系统对接码列表
	 * @param projectId
	 * @return
	 */
	List<ProjectSysSourceVo> getProjectSysCodeList(Long projectId);

	/**
	 * 保存项目系统对接码
	 * @param projectSysSourceQo
	 */
	void saveProjectSysCode(String sysIdJoinCodes, Long projectId);

	/**
	 * 保存项目
	 * @param projectaAddQo
	 * @param modifyId
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	int updateProject(ProjectAddQo projectaAddQo, Long modifyId) throws IllegalAccessException, InvocationTargetException;

	/**
	 * 更新项目
	 * @param projectaAddQo
	 * @param createrId
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	int saveProject(ProjectAddQo projectaAddQo, Long createrId) throws IllegalAccessException, InvocationTargetException;
	
	
	/**
	 * 批量删除项目信息
	 * @param ids
	 * @param modifyId
	 */
	void removeProject(String ids,Long modifyId);

	/**
	 *  批量导入项目信息
	 * @param projectVoList
	 * @param id
	 */
	void batchUpload(List<ProjectVo> projectVoList, Long createrId);
	/**
	 * 统计所有项目楼板面积
	 * @return
	 */
	BigDecimal sumAllProjectFloorArea();
}
