package com.cts.tm.vo;

import java.util.List;

public class GenericResponseVO {
	String status;
	String msg;
	String errorMsg;
	SearchVO searchVO;
	SearchTaskVO taskVO;
	List<UserVO> userList;
	List<ProjectVO> projectList;
	List<TaskVO> taskList;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public SearchVO getSearchVO() {
		return searchVO;
	}
	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}
	public SearchTaskVO getTaskVO() {
		return taskVO;
	}
	public void setTaskVO(SearchTaskVO taskVO) {
		this.taskVO = taskVO;
	}
	public List<UserVO> getUserList() {
		return userList;
	}
	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}
	public List<ProjectVO> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<ProjectVO> projectList) {
		this.projectList = projectList;
	}
	public List<TaskVO> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<TaskVO> taskList) {
		this.taskList = taskList;
	}
}