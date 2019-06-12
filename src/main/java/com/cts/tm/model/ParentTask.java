package com.cts.tm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="parent_task_table")
public class ParentTask {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="parent_id")
	public long parentId;
		
	@Column(name="parent_task")
	public String parentTask;

	public ParentTask() {}
	
	public ParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
}