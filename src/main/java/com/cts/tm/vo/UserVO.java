package com.cts.tm.vo;

import com.cts.tm.util.Utility;

public class UserVO {
	public Integer userId;
	public String firstName;
	public String lastName;
	public Integer employeeId;
	
	public Integer projectId;
	public String projectName;
	public Integer taskId;
	public String taskName;
	
	public UserVO() {}
	
	public UserVO(Integer userId, String firstName, String lastName, Integer employeeId) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeId = employeeId;
	}
	
	
	public ValidateVO isNull() {
		
		ValidateVO vo = new ValidateVO();
		StringBuilder msg = new StringBuilder();
		boolean flag = true;
		
		if(!Utility.isValidString(this.firstName, 1, 50)) {
			msg.append("Length of First name should be among 1 - 50.<br>");
			flag = false;
		}
		if(!Utility.isValidString(this.lastName, 1, 50)) {
			msg.append("Length of last name should be among 1 - 50.<br>");
			flag = false;
		}
		if(this.employeeId == null || this.employeeId == 0) {
			msg.append("Employee Id can not be blank or zero.<br>");
			flag = false;
		}
		vo.setValid(flag);
		vo.setMsg(msg.toString());
		return vo;
	}
	
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
}