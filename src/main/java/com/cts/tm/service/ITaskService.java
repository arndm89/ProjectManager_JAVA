package com.cts.tm.service;

import java.util.List;

import com.cts.tm.vo.SearchTaskVO;
import com.cts.tm.vo.TaskVO;

public interface ITaskService {
	
	public String createTask(TaskVO taskVo)throws Exception;
	
	public List<SearchTaskVO> searchTask(Integer projectId) throws Exception;
	
	public String endTask(int taskId)throws Exception;

	//public TaskVO getTaskById(Integer taskId) throws Exception;

	public String updateTask(TaskVO taskVo) throws Exception;

	public List<TaskVO> getAllTaskForTaskModal(String searchKey) throws Exception;
}
