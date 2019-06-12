package com.cts.tm.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.tm.model.Task;
@Repository
public interface ITaskRepository extends CrudRepository<Task, Long>{

	/*@Query(value="SELECT * FROM task_table WHERE"
			+ " UPPER(task) = IFNULL(:task,UPPER(task))"
			+ " AND (parent_id_fk IN (SELECT parent_id FROM parent_task_table WHERE"
			+ "	UPPER(parent_task) = IFNULL(UPPER(:parentTask), UPPER(parent_task))))"
			+ " AND ((priority >= :priorityStart OR :priorityStart = 0) "
			+ "	AND (priority <= :priorityTo OR :priorityTo = 0 ))"
			+ " AND (start_date >= :startDate OR :startDate IS NULL)"
			+ " AND (end_date <= :endDate OR :endDate IS NULL)", nativeQuery=true)			
	public List<Task> searchTask(@Param("task") String task, @Param("parentTask") String parentTask,
			@Param("priorityStart") Integer priorityStart, @Param("priorityTo") Integer priorityTo,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);*/
	
	 //@Transactional
	 //public Long deleteByTaskId(Long taskId)throws Exception;
	@Transactional
	@Modifying
	@Query(value="UPDATE task_table "
			+ "SET status=:status WHERE task_id=:taskId", nativeQuery=true)
	public Integer endTaskByTaskId(@Param("taskId") Long taskId, @Param("status") String status)throws Exception;
	 
	 public Task findByTaskId(Long taskId) throws Exception;
	 
	 
	 
	@Query(value="SELECT pt.parent_id, pt.parent_task, t.task_id,t.task, " + 
			"t.priority, t.start_date, t.end_date, t.project_id, p.project, " + 
			"u.user_id, u.first_name, u.last_name " + 
			"FROM " + 
			"parent_task_table pt, task_table t, project p, user_table u " + 
			"WHERE " + 
			"pt.parent_id = t.parent_id " + 
			"AND t.project_id = p.project_id " + 
			"AND u.project_id = t.project_id " + 
			"AND t.project_id = :projectId", nativeQuery=true)
	 public List findByProjectId(@Param("projectId") int projectId) throws Exception;
	 
	 
	 /*@Query(value="SELECT * " + 
				"FROM task_table t, user_table u " + 
				"WHERE t.task_id <> u.task_id;",nativeQuery = true)
		Set<Task> findAllTaskForTaskModal();*/
}