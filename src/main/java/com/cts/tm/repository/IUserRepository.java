package com.cts.tm.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.tm.model.User;
@Repository
public interface IUserRepository extends CrudRepository<User, Long>{

	public User findByEmployeeId(Integer employeeId) throws Exception;
	 
	@Transactional
	public Long deleteByEmployeeId(int employeeId) throws Exception;
	
	@Query(value="from User where upper(firstName) like %:searchTxt% or upper(lastName) like %:searchTxt%")
	public List<User> findByUserName(@Param("searchTxt") String searchTxt)throws Exception;
	
	@Modifying
	@Query(value="update User "
			+ "set projectId = :projectId "
			+ "WHERE employeeId = :employeeId")
	public int updateProjectIdByEmployeeId(@Param("projectId") Integer projectId, @Param("employeeId") Integer employeeId);
	
	public User findByProjectId(Integer projectId) throws Exception;

	public User findByUserId(Long userId) throws Exception;
	
	public User findByTaskId(int taskId) throws Exception;
	
	
	//@Modifying
	//@Transactional
	//@Query(value="UPDATE User")
	//public int updateUserByTaskIdAndTaskId(@Param("userId") long userId, @Param("taskId") int taskId) throws Exception; 
}