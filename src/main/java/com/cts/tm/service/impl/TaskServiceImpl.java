package com.cts.tm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.tm.model.ParentTask;
import com.cts.tm.model.Task;
import com.cts.tm.model.User;
import com.cts.tm.repository.IParentTaskRepository;
import com.cts.tm.repository.ITaskRepository;
import com.cts.tm.repository.IUserRepository;
import com.cts.tm.service.ITaskService;
import com.cts.tm.vo.CustomPropertyVO;
import com.cts.tm.vo.SearchTaskVO;
import com.cts.tm.vo.TaskVO;
@Service
public class TaskServiceImpl implements ITaskService{
	
	@Autowired CustomPropertyVO property;
	@Autowired IParentTaskRepository iParentTaskDao;
	@Autowired ITaskRepository iTaskDao;
	@Autowired IUserRepository iuserDao;
	
	@Override
	@Transactional
	public String createTask(TaskVO taskVo) throws Exception {
		String status = "failed"; 
		
		if(taskVo.parentTaskBoolean) {
			ParentTask pt = iParentTaskDao.findByParentTask(taskVo.task);
			if(pt == null) {
				pt = new ParentTask(taskVo.task);
				status = iParentTaskDao.save(pt) == null ? property.failure : property.success;
			}else {
				status = property.alreadyExist;
			}
		}else {
			// create task with all fields
			ParentTask pt = iParentTaskDao.findByParentId((long) taskVo.parentTaskId);
			if(pt == null) {
				//	parent task doesnt exist
				status = property.failure;
			} else{
				Task t = new Task();
				
				t.parentId = (int) pt.parentId;
				t.projectId = taskVo.projectId;
				t.task = taskVo.task;
				t.startDate = taskVo.startDate;
				t.endDate = taskVo.endDate;
				t.priority = taskVo.priority;
				t.status = property.active;
				
				t = iTaskDao.save(t);
				if(t == null) {
					status = property.failure;
				}else{
					User u = iuserDao.findByUserId((long) taskVo.userId);
					u.taskId = t.taskId.intValue();
					status = iuserDao.save(u) == null ? property.failure : property.success;
				}
				status = status.equalsIgnoreCase(property.failure) ? property.failure : property.success;
			}
		}
		return status;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<SearchTaskVO> searchTask(Integer projectId) throws Exception {
		
		List<SearchTaskVO> tasklist = null;
		List list = iTaskDao.findByProjectId(projectId);
		
		if(list != null) {			
			tasklist = new ArrayList<>(list.size());
			for(int i = 0 ; i < list.size() ; i++) {
				
				Object[] o = (Object[]) list.get(i);
				
				SearchTaskVO vo = new SearchTaskVO();
				vo.setParentTaskId((Integer) o[0]);
				vo.setParentTask(o[1].toString());
				vo.setTaskId((Integer) o[2]); 
				vo.setTask(o[3].toString());
				vo.setPriority((Integer) o[4]); 
				vo.setStartDate((Date) o[5]);
				vo.setEndDate((Date) o[6]);
				vo.setProjectId((Integer) o[7]);
				vo.setProjectName(o[8].toString());
				vo.setUserId((Integer) o[9]);
				vo.setUserName(o[10].toString() + " " +o[11].toString());
				
				tasklist.add(vo);
			}
		}
		return tasklist;
		}
	@Override
	public String endTask(int taskId) throws Exception {
		Integer rows = iTaskDao.endTaskByTaskId((long) taskId, "COMPLETED");
		return rows > 0 ? "SUCCESS" : "NOT_FOUND";
	}

	/*@Override
	public TaskVO getTaskById(Integer taskId) throws Exception {
		TaskVO task = iTaskDao.findByTaskId(new Long(taskId));
		return task;
	}*/
	@Override
	public String updateTask(TaskVO taskVo) throws Exception {
		
		String status = property.failure; 
		
		Task task = iTaskDao.findByTaskId((long) taskVo.getTaskId());
		if(task == null) {
			return status;
		} else {
			//task.taskId = (long) taskVo.getTaskId(); 
			task.task = taskVo.getTask();
			task.priority = taskVo.getPriority();
			task.startDate = taskVo.getStartDate();
			task.endDate = taskVo.getEndDate();
			// update user by taskId
			
			// de-link prev user from task
			User u = iuserDao.findByTaskId(taskVo.taskId);
			u.taskId = 0;
			iuserDao.save(u);
			
			// link current user from task
			u = iuserDao.findByUserId((long) taskVo.userId);
			u.taskId = taskVo.taskId;
			iuserDao.save(u);
			status = iTaskDao.save(task) == null ? property.failure : property.success;
		}
		
		return status;	
	}

	@Override
	public List<TaskVO> getAllTaskForTaskModal(String searchKey) throws Exception {

		List<TaskVO> list = null;
		Set<ParentTask> tasks = iParentTaskDao.findAllTaskForTaskModal();
		
		if(searchKey != null && searchKey.length() > 0)
			tasks = tasks.stream().filter(i->i.parentTask.toUpperCase()
							.contains(searchKey.toUpperCase()))
							.collect(Collectors.toSet());
									
		if(tasks != null && tasks.size() > 0) {
			
			list = new ArrayList<>(tasks.size());
			for (ParentTask p : tasks) {				
				TaskVO vo = new TaskVO((int) p.parentId, p.parentTask);
				list.add(vo);
			}
		}
		return list;
	
	}
}
