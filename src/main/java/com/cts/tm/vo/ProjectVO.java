package com.cts.tm.vo;

import java.util.Date;

import com.cts.tm.util.Utility;

public class ProjectVO {
	Integer projectId;
	String project;
	Integer priority;
	Date startDate;
	Date endDate;
	Boolean startEndBoolean;
	
	
	Integer managerId;
	String managerName;
	
public ValidateVO isNull() {
		
		ValidateVO vo = new ValidateVO();
		StringBuilder msg = new StringBuilder();
		boolean flag = true;
		
		if(!Utility.isValidString(this.project, 1, 100)) {
			msg.append("Length of a Task should be among 1 - 500.<br>");
			flag = false;
		}
		if(this.managerId == null || this.managerId ==0) {
			msg.append("Manager field can not be blank.<br>");
			flag = false;
		}
		if(startEndBoolean!=null && startEndBoolean) {
			if(this.startDate == null) {
				msg.append("Start date can not be blank.<br>");
				flag = false;
			}
			if(this.endDate == null) {
				msg.append("End date can not be blank.<br>");
				flag = false;
			}
			
			if(startDate != null && endDate != null && endDate.before(this.startDate)) {
				msg.append("Start date can not be after End date.<br>");
				flag = false;
			}
			
		}
		
		vo.setValid(flag);
		vo.setMsg(msg.toString());
		return vo;
	}

	public ProjectVO() {}
	public ProjectVO(Integer projectId, String project, Integer priority, Date startDate, Date endDate,
		Integer managerId, String managerName) {
	this.projectId = projectId;
	this.project = project;
	this.priority = priority;
	this.startDate = startDate;
	this.endDate = endDate;
	this.startEndBoolean = startDate != null && endDate != null ? true : false;
	this.managerId = managerId;
	this.managerName = managerName;
}
	
	public ProjectVO(Integer projectId, String project) {
		this.projectId = projectId;
		this.project = project;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
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

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Boolean getStartEndBoolean() {
		return startEndBoolean;
	}
	public void setStartEndBoolean(Boolean startEndBoolean) {
		this.startEndBoolean = startEndBoolean;
	}
}