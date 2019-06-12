package com.cts.tm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="user_table")
public class User implements Serializable{
	
	private static final long serialVersionUID = -3134858329891452324L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	public Long userId;
	
	@Column(name="first_name")
	public String firstName;
	
	@Column(name="last_name")
	public String lastName;
	
	@Column(name="employee_id")
	public int employeeId;
	
	@Column(name="project_id")
	public Integer projectId;
	
	@Column(name="task_id")
	public int taskId;

	
	
}