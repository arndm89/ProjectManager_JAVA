package com.cts.tm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.tm.model.Project;
import com.cts.tm.model.Task;
import com.cts.tm.model.User;
import com.cts.tm.repository.IProjectRepository;
import com.cts.tm.repository.ITaskRepository;
import com.cts.tm.repository.IUserRepository;
import com.cts.tm.service.IProjectService;
import com.cts.tm.vo.CustomPropertyVO;
import com.cts.tm.vo.ProjectVO;
@Service
public class ProjectServiceImpl implements IProjectService{
	
	@Autowired CustomPropertyVO property;
	@Autowired IProjectRepository iProjectDao;
	@Autowired ITaskRepository iTaskDao;
	@Autowired IUserRepository iUserDao;
	
	
	@Override
	@Transactional
	public String createProject(ProjectVO projectVo) throws Exception {
		String status = "failed"; 
		
		Project project = iProjectDao.findByProject(projectVo.getProject());
		
		if(project == null) {			
			project = new Project();			
			project.project = projectVo.getProject();
			project.priority = projectVo.getPriority();					
			project.startDate = projectVo.getStartDate();
			project.endDate = projectVo.getEndDate();			
			project = iProjectDao.save(project);
			
			if(project == null) {
				status = "failed";				
			}else {
				int l = iUserDao.updateProjectIdByEmployeeId(project.projectId.intValue(), projectVo.getManagerId());
				status = l > 0 ? "success" : "failed";
			}
		}
		
		
		return status;
	}
	
	@Override
	@Transactional
	public String updateProject(ProjectVO projectVo) throws Exception {
		
		String status = "failed";
		Project project = iProjectDao.findByProjectId(projectVo.getProjectId().longValue());
		
		if(project != null) {
						
			project.project = projectVo.getProject();
			project.priority = projectVo.getPriority();					
			project.startDate = projectVo.getStartDate();
			project.endDate = projectVo.getEndDate();
			
			User manager = iUserDao.findByProjectId(projectVo.getProjectId());
			
			if(manager != null) {
				// update existing user with manager id null	-- if manager is updated for the project			
				manager.projectId = null;
				status = iUserDao.save(manager) != null ? "success" : "failure";
			}
			if(!status.equalsIgnoreCase("failure")) {
				// update new user with manager id
				User newManager = iUserDao.findByEmployeeId(projectVo.getManagerId());
				newManager.projectId = projectVo.getProjectId();
				status = iUserDao.save(newManager) != null? "success" : "failure";
			}else {
				return status;
			}
			
			status = iProjectDao.save(project) != null ? "success":"failure";		
		}else {
			status=property.notFound;
		}
		return status;
	}
	
	@Override
	public List<ProjectVO> getAllProjects() throws Exception {
		
		List<ProjectVO> list = null;
		List<Project> project = (List<Project>) iProjectDao.findAll();
		
		if(project != null && project.size() > 0) {
			
			list = new ArrayList<>(project.size());
			for (Project p : project) {
				
				User manager = iUserDao.findByProjectId(p.projectId.intValue());				
				
				
				
				ProjectVO vo = new ProjectVO();
				
				vo.setProjectId(p.projectId.intValue());
				vo.setProject(p.project);
				vo.setPriority(p.priority);
				vo.setStartDate(p.startDate);
				vo.setEndDate(p.endDate);
				if(manager != null) {
					vo.setManagerId(manager.employeeId);
					vo.setManagerName(manager.firstName + " " + manager.lastName);
				}
				list.add(vo);
			}
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public String deleteProject(int projectId) throws Exception {
		
		//Task t = iTaskDao.findByProjectId(projectId);
		
		List tList = iTaskDao.findByProjectId(projectId);
		
		if(tList!=null) {
			Task t = (Task) tList.get(0);
			iTaskDao.delete(t);
		}		
		
		User u = iUserDao.findByProjectId(projectId);
		u.projectId = null;
		u.taskId = 0;
		iUserDao.save(u);		
		
		Long rows = iProjectDao.deleteByProjectId((long) projectId);
		return rows > 0 ? "SUCCESS" : "NOT_FOUND";
	}

	@Override
	public List<ProjectVO> getAllProjectsForTask(String searchUserKey) throws Exception {
		
		List<ProjectVO> list = null;
		Set<Project> projects = iProjectDao.findAllProjectsForTask();
		
		if(searchUserKey!=null && searchUserKey.length() > 0)
			projects = projects.stream()
							.filter(i->i.project.toUpperCase()
							.contains(searchUserKey.toUpperCase()))
							.collect(Collectors.toSet());
									
		if(projects != null && projects.size() > 0) {
			
			list = new ArrayList<>(projects.size());
			for (Project p : projects) {
				
				ProjectVO vo = new ProjectVO(p.projectId.intValue(), p.project);
						
				list.add(vo);
			}
		}
		return list;
	}

}
