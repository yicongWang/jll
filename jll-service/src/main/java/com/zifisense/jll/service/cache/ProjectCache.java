package com.zifisense.jll.service.cache;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zifisense.jll.model.Project;
import com.zifisense.jll.service.ProjectService;
import com.zifisense.jll.service.common.RedisKeyConstant;
import com.zifisense.jll.util.RedisUtil;

@Service
public class ProjectCache {
	 @Autowired
	 private ProjectService projectService;
	 @Autowired
	 private RedisUtil redisUtil;
	 
	 /**
	  * 将所有项目加载如redis中
	  */
	public void initAllProjectCache(){
		//获取可用角色
		List<Project> projectList = projectService.getAllProjectList();
		if(CollectionUtils.isNotEmpty(projectList)){
			projectList.forEach(project -> {
					redisUtil.set(redisUtil.generatorKey(RedisKeyConstant.PROJECT_KEY,project.getId()), project);
			});
		}
	}
}
