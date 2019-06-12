package com.cts.tm.vo;

import java.util.Date;

import com.cts.tm.util.Utility;

public class TaskVO {
	public Integer taskId;
	public String task;
	public Integer priority;
	public Integer parentTaskId;
	public String parentTask;
	public Date startDate;
	public Date endDate;
	public String status;
	public Integer projectId;
	public String project;
	public Integer userId;
	public boolean parentTaskBoolean;
	
	public ValidateVO isNull() {
		
		ValidateVO vo = new ValidateVO();
		StringBuilder msg = new StringBuilder();
		boolean flag = true;
		
		
		if(!Utility.isValidString(task, 1, 500)) {
			msg.append("Length of a Task should be among 1 - 100.<br>");
			flag = false;
		}
		
		if(!parentTaskBoolean) {
			
			if(projectId == null || projectId == 0) {
				msg.append("Project can not be blank.<br>");
				flag = false;
			}
			
			if(parentTaskId==null || parentTaskId == 0) {
				msg.append("Parent task can not be blank.<br>");
				flag = false;
			}
			if(startDate == null) {
				msg.append("Start date can not be blank.<br>");
				flag = false;
			}
			if(endDate == null) {
				msg.append("End date can not be blank.<br>");
				flag = false;
			}
			if(startDate != null && endDate != null && startDate.after(endDate)) {
				msg.append("Start date can not be after End date.<br>");
				flag = false;
			}
			if(userId==null || userId == 0) {
				msg.append("User can not be blank.<br>");
				flag = false;
			}
		}
		vo.setValid(flag);
		vo.setMsg(msg.toString());
		return vo;
	}
	
	public TaskVO() {}
	
	public TaskVO(int taskId, String task) {
		this.taskId = taskId;
		this.task = task;		
	}
	
	
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Integer getRange() {
		return priority;
	}
	public void setRange(Integer range) {
		this.priority = range;
	}
	public String getParentTask() {
		return parentTask;
	}
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getManagerId() {
		return userId;
	}
	public void setManagerId(Integer managerId) {
		this.userId = managerId;
	}
	public Integer getParentTaskId() {
		return parentTaskId;
	}
	public void setParentTaskId(Integer parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public boolean isParentTaskBoolean() {
		return parentTaskBoolean;
	}
	public void setParentTaskBoolean(boolean parentTaskBoolean) {
		this.parentTaskBoolean = parentTaskBoolean;
	}
}