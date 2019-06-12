package com.cts.tm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.tm.service.ITaskService;
import com.cts.tm.vo.CustomPropertyVO;
import com.cts.tm.vo.GenericResponseVO;
import com.cts.tm.vo.TaskVO;
import com.cts.tm.vo.ValidateVO;

@CrossOrigin("*")
@RestController
public class AddTaskController {
	@Autowired private CustomPropertyVO property;
	
	@Autowired private ITaskService iTaskService;
	
	@RequestMapping(value="/createTask", method=RequestMethod.POST)
	public GenericResponseVO createTask(@RequestBody TaskVO taskVo) {
		String s = "";
		GenericResponseVO responseVo = new GenericResponseVO();
		try {
			ValidateVO validateVO = taskVo.isNull();
			if(validateVO.isValid()) {
				s = iTaskService.createTask(taskVo);
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
	
	
	@RequestMapping(value="/getAllTaskForTaskModal", method=RequestMethod.GET)
	public GenericResponseVO getAllTaskForTaskModal(@RequestParam("searchKey") String searchKey) {
		GenericResponseVO vo = new GenericResponseVO();
		List<TaskVO> taskList = null;
		try {
			taskList = iTaskService.getAllTaskForTaskModal(searchKey);
			if(taskList != null && taskList.size() > 0) {
				vo.setStatus(property.success);
			}else {
				vo.setStatus(property.notFound);
				vo.setMsg(property.noResultFound);
			}
			vo.setTaskList(taskList);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStatus(property.error);
			vo.setErrorMsg(property.unableToFetchRecords);
		}
		return vo;
	}
	
}
