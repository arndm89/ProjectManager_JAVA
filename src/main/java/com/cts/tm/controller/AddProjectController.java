package com.cts.tm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.tm.service.IProjectService;
import com.cts.tm.vo.CustomPropertyVO;
import com.cts.tm.vo.GenericResponseVO;
import com.cts.tm.vo.ProjectVO;
import com.cts.tm.vo.ValidateVO;

@CrossOrigin("*")
@RestController
public class AddProjectController {
	@Autowired private CustomPropertyVO property;
	
	@Autowired private IProjectService iProjectService;

	@RequestMapping(value="/createProject", method=RequestMethod.POST)
	public GenericResponseVO createProject(@RequestBody ProjectVO projectVO) {
		String s = "";
		GenericResponseVO responseVo = new GenericResponseVO();
		try {
			ValidateVO validateVO = projectVO.isNull();
			if(validateVO.isValid()) {
				s = iProjectService.createProject(projectVO);
				if(s.equalsIgnoreCase(property.success)) {
					responseVo.setStatus(property.success);
					responseVo.setMsg(property.taskCreationSuccessfull);
				}else {
					responseVo.setStatus(property.failure);
					responseVo.setMsg(property.taskCreationFail);
				}
			}else {
 				responseVo.setStatus(property.failure);
				responseVo.setMsg(property.taskCreationFail);
				responseVo.setErrorMsg(validateVO.getMsg());
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			responseVo.setStatus(property.error);
			responseVo.setMsg(property.taskCreationFail);
			responseVo.setErrorMsg(e.getLocalizedMessage());
		}
		return responseVo;
	}
	
	@RequestMapping(value="/getAllProjects", method=RequestMethod.GET)
	public GenericResponseVO getAllProjects() {
		GenericResponseVO vo = new GenericResponseVO();
		List<ProjectVO> projList = null;
		try {
			projList = iProjectService.getAllProjects();
			if(projList != null && projList.size() > 0) {
				vo.setStatus(property.success);
			}else {
				vo.setStatus(property.notFound);
				vo.setMsg(property.noResultFound);
			}
			vo.setProjectList(projList);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStatus(property.error);
			vo.setErrorMsg(property.unableToFetchRecords);
		}
		return vo;
	}
	
	@RequestMapping(value="/updateProject", method=RequestMethod.POST)
	public GenericResponseVO updateProject(@RequestBody ProjectVO projectVo) {
		GenericResponseVO vo = new GenericResponseVO();
		try {
			String status = iProjectService.updateProject(projectVo);
			if(status.equalsIgnoreCase(property.success)) {
				vo.setStatus(property.success);
				vo.setMsg(property.updatedSuccessfully);
			}else if(status.equalsIgnoreCase(property.notFound)){
				vo.setStatus(property.notFound);
				vo.setMsg(property.noResultFound);
			}else {
				vo.setStatus(property.failure);
				vo.setErrorMsg(property.failedOperationMsg);
			}
			
		} catch (Exception e) {
			vo.setStatus(property.error);
			vo.setErrorMsg(property.failedOperationMsg);
			e.printStackTrace();
		}
		return vo;
	}
	
	
	@RequestMapping(value="/deleteProject/{projectId}", method=RequestMethod.DELETE)
	public GenericResponseVO deteleTask(@PathVariable("projectId") Integer projectId) {
		GenericResponseVO resVo = new GenericResponseVO();
		try {
			
			String status = iProjectService.deleteProject(projectId);
			if(status.equalsIgnoreCase(property.success)) {
				resVo.setStatus(property.success);
				resVo.setMsg(property.deletedSuccessfully);
			}else {
				resVo.setStatus(property.notFound);
				resVo.setMsg(property.noResultFound);
			}
		}catch(Exception e) {
			resVo.setStatus(property.failure);
			resVo.setMsg(property.failedOperationMsg);
			e.printStackTrace();
		}
		return resVo;
	}
	
	
	@RequestMapping(value="/getAllProjectsForTask", method=RequestMethod.GET)
	public GenericResponseVO getAllProjectsForTask(@RequestParam("searchUserKey") String searchUserKey) {
		GenericResponseVO vo = new GenericResponseVO();
		List<ProjectVO> projList = null;
		try {
			projList = iProjectService.getAllProjectsForTask(searchUserKey);
			if(projList != null && projList.size() > 0) {
				vo.setStatus(property.success);
			}else {
				vo.setStatus(property.notFound);
				vo.setMsg(property.noResultFound);
			}
			vo.setProjectList(projList);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStatus(property.error);
			vo.setErrorMsg(property.unableToFetchRecords);
		}
		return vo;
	}
	
}
