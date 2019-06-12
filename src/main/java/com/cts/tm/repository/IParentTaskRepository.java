package com.cts.tm.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.tm.model.ParentTask;
@Repository
public interface IParentTaskRepository extends CrudRepository<ParentTask, Long>{

	ParentTask findByParentTask(String parentTask);
	
	@Query(value="SELECT * FROM parent_task_table;",nativeQuery = true)
	Set<ParentTask> findAllTaskForTaskModal();
	
	ParentTask findByParentId(Long parentId) throws Exception;
}
