package com.cts.tm.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.tm.model.Project;
@Repository
public interface IProjectRepository extends CrudRepository<Project, Long>{

	Project findByProject(String project);

	Project findByProjectId(Long projectId);

	Long deleteByProjectId(long projectId);	
	
	
	@Query(value="SELECT * " + 
			"FROM project p, user_table u " + 
			"WHERE p.project_id <> u.project_id;",nativeQuery = true)
	Set<Project> findAllProjectsForTask();
}
