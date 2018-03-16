package com.zifisense.jll.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zifisense.jll.common.exception.DataNotExistsException;
import com.zifisense.jll.common.response.CommonResponse;
import com.zifisense.jll.common.response.PageResponse;
import com.zifisense.jll.common.response.ResponseCode;
import com.zifisense.jll.controller.common.AccessRequired;
import com.zifisense.jll.controller.common.CurrentRedisUserData;
import com.zifisense.jll.dto.RedisUserData;
import com.zifisense.jll.qo.ProjectAddQo;
import com.zifisense.jll.qo.ProjectPageQo;
import com.zifisense.jll.qo.ProjectQo;
import com.zifisense.jll.service.AccountService;
import com.zifisense.jll.service.ProjectService;
import com.zifisense.jll.service.common.log.OperationLogType;
import com.zifisense.jll.service.common.log.OperationnLogAnnotation;
import com.zifisense.jll.util.DateUtil;
import com.zifisense.jll.util.ExportUtil;
import com.zifisense.jll.vo.ProjectVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 项目控制器
 * @author wyc
 *
 */
@AccessRequired
@Api(description = "项目管理接口")
@RestController
@RequestMapping("/project")
public class ProjectController {

    private Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;
    @Autowired
    private AccountService accountService;
    @AccessRequired
    @ApiOperation(value = "分页获取项目列表")
    @RequestMapping(value = "/findProjectInfoPage", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "分页获取项目列表", moduleName = "分页获取项目列表",operationLogType = OperationLogType.QUERY)
    public PageResponse findProjectInfoPage(ProjectPageQo projectPageQo,  @RequestHeader String access_token) throws Exception{
    	 PageResponse response = new PageResponse(ResponseCode.SUCCESS, "OK");
         response.setData(projectService.findProjectInfoListPage(projectPageQo));
     	return response;
    	
    }
    
    @ApiOperation(value = "添加项目")
    @RequestMapping(value = "/addProject", method = RequestMethod.POST )
    @OperationnLogAnnotation(description = "添加项目", moduleName = "添加项目",operationLogType = OperationLogType.ADD)
    public CommonResponse addProject(@RequestBody ProjectAddQo projectaAddQo ,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) throws IllegalAccessException, InvocationTargetException {
    	 return CommonResponse.success(projectService.saveProject(projectaAddQo,redisUserData.getId()));
    }
    
    @ApiOperation(value = "修改项目")
    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "修改项目", moduleName = "修改项目",operationLogType = OperationLogType.UPDATE)
    public CommonResponse updateProject(@RequestBody ProjectAddQo projectaAddQo ,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) throws IllegalAccessException, InvocationTargetException{
        return CommonResponse.success(projectService.updateProject(projectaAddQo,redisUserData.getId()));
        
    }
    
    @ApiOperation(value = "删除项目")
    @RequestMapping(value = "/removeProject", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "删除项目", moduleName = "删除项目",operationLogType = OperationLogType.DELTE)
    public CommonResponse removeProject(@RequestBody Map ids ,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) {
    	String idsStr = MapUtils.getString(ids, "ids");
    	if(StringUtils.isBlank(idsStr)){
    		new DataNotExistsException("参数为空");
    	}
    	projectService.removeProject(idsStr, redisUserData.getId());
        return CommonResponse.success();
    }

    @ApiOperation(value = "获取项目简称列表")
    @RequestMapping(value = "/getShortProjectList", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "获取项目简称列表", moduleName = "获取项目简称列表",operationLogType = OperationLogType.QUERY)
    public CommonResponse getShortProjectList(String keys,@RequestHeader String access_token) {
        return CommonResponse.success(projectService.getShortProjectList(keys));
    }
    
    @ApiOperation(value = "根据项目ID获取项目信息")
    @RequestMapping(value = "/getProjectById/{projectId}", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "根据项目ID获取项目信息", moduleName = "根据项目ID获取项目信息",operationLogType = OperationLogType.QUERY)
    public CommonResponse getProjectById(@PathVariable Long projectId ,@RequestHeader String access_token) {
    	ProjectQo  projectQo = new ProjectQo();
    	projectQo.setProjectId(projectId);
        return CommonResponse.success(projectService.getProjectListByQuery(projectQo));
    }
    
    @ApiOperation(value = "导出")
    @RequestMapping(value = "/exportProjectInfoList", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "导出项目信息", moduleName = "项目管理",operationLogType = OperationLogType.QUERY)
    public void exportProjectInfoList(ProjectQo projectQo ,HttpServletResponse response) throws IOException {
    	List<ProjectVo>  list = projectService.getProjectListByQuery(projectQo);
    	ExportUtil.exportExcel(list,"项目信息","项目信息",ProjectVo.class,DateUtil.dateToStr(new Date(), DateUtil.DATE_DEFAULT_FORMAT)+"项目信息.xls",response);
    }
    
    @ApiOperation(value = "下载模板")
    @RequestMapping(value = "/download/template", method = RequestMethod.GET)
    public CommonResponse template(HttpServletResponse response) throws IOException {
    	  CommonResponse commonResponse = new CommonResponse();
          ExportUtil.exportExcel(Arrays.asList(),"项目信息模板 ","项目信息",ProjectVo.class,"项目信息模板.xls",response);
    	return commonResponse;
    }
    
    @ApiOperation(value = "导入")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @OperationnLogAnnotation(description = "导入项目信息", moduleName = "项目管理",operationLogType = OperationLogType.UPLOAD)
    public CommonResponse upload(@RequestParam("file") MultipartFile file,@RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) throws Exception {
    	  if (file.isEmpty()) {
    		  CommonResponse commonResponse = new CommonResponse();
    		  commonResponse.setCode(ResponseCode.IMPORT_FILE_TYPE_ERROR);
			  commonResponse.setMsg("文件格式有误");
    		  return commonResponse;
          }
    	  List<ProjectVo> projectVoList = ExportUtil.importExcel(file,1,1,ProjectVo.class);
    	  if(CollectionUtils.isNotEmpty(projectVoList)){
    		  projectService.batchUpload(projectVoList, redisUserData.getId());
    	  }else{
    		  CommonResponse commonResponse = new CommonResponse();
    		  commonResponse.setCode(ResponseCode.IMPORT_FILE_TYPE_ERROR);
			  commonResponse.setMsg("文件为空");
    	  }
    	  return CommonResponse.success();
    }
    
    @ApiOperation(value = "获取项目对接系统列表")
    @RequestMapping(value = "/getProjectSysCodeList/{projectId}", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "获取项目对接系统列表", moduleName = "项目管理接口",operationLogType = OperationLogType.QUERY)
    public CommonResponse getProjectSysCodeList(@PathVariable Long projectId ,@RequestHeader String access_token) {
        return CommonResponse.success(projectService.getProjectSysCodeList(projectId));
    }
    
    @ApiOperation(value = "获取可关联项目的账号")
    @RequestMapping(value = "/getProjectAcountAble", method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "获取可关联项目的账号过滤已经拥有三个项目的账号", moduleName = "项目管理接口",operationLogType = OperationLogType.QUERY)
    public CommonResponse getProjectAcountAble(@RequestHeader String access_token) {
        return CommonResponse.success(accountService.getProjectAcountAble());
    }
    
}
