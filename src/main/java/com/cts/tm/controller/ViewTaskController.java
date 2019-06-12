package com.cts.tm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.tm.service.ITaskService;
import com.cts.tm.vo.CustomPropertyVO;
import com.cts.tm.vo.GenericResponseVO;
import com.cts.tm.vo.SearchTaskVO;
import com.cts.tm.vo.SearchVO;

@CrossOrigin("*")
@RestController
public class ViewTaskController {
	@Autowired private CustomPropertyVO property;
	
	@Autowired private ITaskService iTaskService;

	@RequestMapping(value="/searchTask", method=RequestMethod.GET, produces="application/json")
	public GenericResponseVO searchTask(@RequestParam("projectId") Integer projectId) {	//	 this si for testing purpose
		
		GenericResponseVO resVO = new GenericResponseVO();
		
		try {
			List<SearchTaskVO> list = iTaskService.searchTask(projectId);
			
			if(list != null) {
				resVO.setStatus(property.success);
				resVO.setSearchVO(new SearchVO(list.size(), list));
			}else {
				resVO.setStatus(property.notFound);
				resVO.setMsg(property.noResultFound);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resVO;
	}
	@RequestMapping(value="/endTask/{taskId}", method=RequestMethod.DELETE)
	public GenericResponseVO endTask(@PathVariable("taskId") Integer taskId) {
		GenericResponseVO resVo = new GenericResponseVO();
		try {
			
			String status = iTaskService.endTask(taskId);
			if(status.equalsIgnoreCase(property.success)) {
				resVo.setStatus(property.success);
				resVo.setMsg(property.taskUpdateSuccess);
			}else {
				resVo.setStatus(property.notFound);
				resVo.setMsg(property.taskNotFound);
			}
		}catch(Exception e) {
			resVo.setStatus(property.failure);
			resVo.setMsg(property.taskDeletionFailed);
			e.printStackTrace();
		}
		return resVo;
	}
	
	
}
