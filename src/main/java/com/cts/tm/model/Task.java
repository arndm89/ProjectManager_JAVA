package com.cts.tm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="task_table")
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="task_id")
	public Long taskId;
	
	@Column(name="parent_id")
	public Integer parentId;
	
	@Column(name="task")
	public String task;	
	
	@Column(name="project_id")
	public Integer projectId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	public Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	public Date endDate;
	
	@Column(name="priority")
	public int priority;
	
	@Column(name="status")
	public String status;
	
	
}