package com.cts.tm.service;

import java.util.List;

import com.cts.tm.vo.ProjectVO;

public interface IProjectService {
	
	public String createProject(ProjectVO projectVo)throws Exception;

	public List<ProjectVO> getAllProjects() throws Exception;

	public String updateProject(ProjectVO projectVo) throws Exception;

	public String deleteProject(int projectId) throws Exception;

	public List<ProjectVO> getAllProjectsForTask(String searchUserKey) throws Exception;
}
