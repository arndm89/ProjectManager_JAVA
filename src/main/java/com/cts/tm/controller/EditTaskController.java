package com.cts.tm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.tm.service.ITaskService;
import com.cts.tm.vo.CustomPropertyVO;
import com.cts.tm.vo.GenericResponseVO;
import com.cts.tm.vo.TaskVO;
import com.cts.tm.vo.ValidateVO;

@CrossOrigin("*")
@RestController
public class EditTaskController {
	@Autowired private CustomPropertyVO property;
	
	@Autowired private ITaskService iTaskService;

	@RequestMapping(value="/updateTask", method=RequestMethod.PUT)
	public GenericResponseVO editTask(@RequestBody TaskVO taskVo) {
		String s = "";
		GenericResponseVO responseVo = new GenericResponseVO();
		try {
			
			if(taskVo.getTaskId() != null && taskVo.getTaskId() != 0) {
				ValidateVO validateVO = taskVo.isNull();
				
				if(validateVO.isValid()) {
					s = iTaskService.updateTask(taskVo);
					
					if(s.equalsIgnoreCase(property.success)) {
						responseVo.setStatus(property.success);
						responseVo.setMsg(property.taskUpdateSuccess);
					}else {
						responseVo.setStatus(property.failure);
						responseVo.setMsg(property.taskUpdateFail);
					}
				}else {
					responseVo.setStatus(property.failure);
					responseVo.setMsg(property.taskUpdateFail);
					responseVo.setErrorMsg(validateVO.getMsg());
				}
			}else {
				responseVo.setStatus(property.failure);
				responseVo.setMsg(property.taskUpdateFail);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			responseVo.setStatus(property.error);
			responseVo.setMsg(property.taskUpdateFail);
			responseVo.setErrorMsg(e.getLocalizedMessage());
		}
		return responseVo;
	}
	
}
