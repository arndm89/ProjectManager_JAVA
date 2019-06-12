package com.cts.tm.vo;

import java.util.Date;

import com.cts.tm.util.Utility;

public class SearchTaskVO {
	String task;
	Integer taskId;
	String parentTask;
	Integer parentTaskId;
	int priorityStart;
	int priorityTo;
	Date startDate;
	Date endDate;
	Integer projectId;
	String projectName;
	Integer userId;
	String userName;
	
	int priority;	//	 for search response
	
	public SearchTaskVO() {}
	
	public void setTask(String task) {
		task = Utility.isEmptyStr(task) ? null : task;
		this.task = task;
	}
	
	public String getTask() {
		return task;
	}	
	public String getParentTask() {
		return parentTask;
	}
	public void setParentTask(String parentTask) {
		parentTask = Utility.isEmptyStr(parentTask) ? null : parentTask;
		this.parentTask = parentTask;
	}
	public int getPriorityStart() {
		return priorityStart;
	}
	public void setPriorityStart(int priorityStart) {
		this.priorityStart = priorityStart;
	}
	public void setPriorityStart(String priorityStart) {
		
		this.priorityStart = Utility.isEmptyStr(priorityStart) ? 0: Integer.parseInt(priorityStart);
	}
	public int getPriorityTo() {
		return priorityTo;
	}
	public void setPriorityTo(int priorityTo) {
		this.priorityTo = priorityTo;
	}
	public void setPriorityTo(String priorityTo) {
		this.priorityTo = Utility.isEmptyStr(priorityTo) ? 0 : Integer.parseInt(priorityTo);
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = Utility.isEmptyStr(startDate) ? null : Utility.parseDate(startDate);
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = Utility.isEmptyStr(endDate) ? null : Utility.parseDate(endDate);;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Integer getParentTaskId() {
		return parentTaskId;
	}
	public void setParentTaskId(Integer parentTaskId) {
		this.parentTaskId = parentTaskId;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}